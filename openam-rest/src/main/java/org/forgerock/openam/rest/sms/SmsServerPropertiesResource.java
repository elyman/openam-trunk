/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */

package org.forgerock.openam.rest.sms;

import com.iplanet.services.ldap.DSConfigMgr;
import com.iplanet.sso.SSOException;
import com.iplanet.sso.SSOToken;
import com.sun.identity.setup.SetupConstants;
import com.sun.identity.shared.Constants;
import com.sun.identity.shared.debug.Debug;
import com.sun.identity.sm.SMSException;
import com.sun.identity.sm.ServiceConfig;
import com.sun.identity.sm.ServiceConfigManager;
import com.sun.xml.bind.StringInputStream;
import org.apache.commons.io.IOUtils;
import org.forgerock.json.fluent.JsonPointer;
import org.forgerock.json.fluent.JsonValue;
import org.forgerock.json.resource.ActionRequest;
import org.forgerock.json.resource.BadRequestException;
import org.forgerock.json.resource.NotSupportedException;
import org.forgerock.json.resource.PatchRequest;
import org.forgerock.json.resource.ReadRequest;
import org.forgerock.json.resource.Resource;
import org.forgerock.json.resource.ResultHandler;
import org.forgerock.json.resource.RouterContext;
import org.forgerock.json.resource.ServerContext;
import org.forgerock.json.resource.SingletonResourceProvider;
import org.forgerock.json.resource.UpdateRequest;
import org.forgerock.openam.rest.resource.SSOTokenContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.forgerock.json.fluent.JsonValue.*;

/**
 * A service to allow the modification of server properties
 */
public class SmsServerPropertiesResource implements SingletonResourceProvider {

    public static final String SCHEMA_NAME = "com-sun-identity-servers";
    public static final String AM_CONSOLE_CONFIG_XML = "amConsoleConfig.xml";
    public static final String SERVER_CONFIG = "serverconfig";
    public static final String NON_DEFAULT = "non-default";
    public static final String DIRECTORY_CONFIG_XML = "/com/sun/identity/console/propertyServerConfigXML.xml";
    public static final String DIRECTORY_CONFIGURATION_TAB_NAME = "directoryconfiguration";
    public static final String ADVANCED_TAB_NAME = "advanced";
    private static final String SERVER_DEFAULT_NAME = "server-default";
    public static final String SERVER_TABLE_PROPERTY_PREFIX = "amconfig.serverconfig.xml.server.table.column.";
    private static Map<String, String> syntaxRawToReal = new HashMap<>();
    private static JsonValue defaultSchema;
    private static JsonValue nonDefaultSchema;
    private static JsonValue directoryConfigSchema;
    //this list is to enable us to distinguish which attributes are in the "advanced" tab
    private static List<String> allAttributeNamesInNamedTabs = new ArrayList<>();
    private final Debug logger;
    private DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder dBuilder;

    static {
        syntaxRawToReal.put("true,false", "boolean");
        syntaxRawToReal.put("false,true", "boolean");
        syntaxRawToReal.put("integer", "number");
        syntaxRawToReal.put("on,off", "on,off");
        syntaxRawToReal.put("off,on", "on,off");
        syntaxRawToReal.put("", "string");
    }

    @Inject
    public SmsServerPropertiesResource(@Named("ServerAttributeSyntax") Properties syntaxProperties, @Named
            ("ServerAttributeTitles") Properties titleProperties, @Named("frRest") Debug logger) {
        this.logger = logger;
        defaultSchema = getSchema(syntaxProperties, titleProperties, true);
        nonDefaultSchema = getSchema(syntaxProperties, titleProperties, false);
        directoryConfigSchema = getDirectorySchema(titleProperties, logger);
    }

    private JsonValue getDirectorySchema(Properties titleProperties, Debug logger) {
        try {
            JsonValue directoryConfigSchema = json(object());
            dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            dBuilder = dbFactory.newDocumentBuilder();

            Document document = dBuilder.parse(getClass().getResourceAsStream(DIRECTORY_CONFIG_XML));

            XPath xPath = XPathFactory.newInstance().newXPath();
            final String sectionExpression = "//propertysheet/section/@defaultValue";
            String sectionRawValue = (String) xPath.compile(sectionExpression).evaluate(document, XPathConstants.STRING);
            String sectionTitle = titleProperties.getProperty(sectionRawValue);

            final String baseExpression = "//propertysheet/section/property/label/@defaultValue";
            NodeList attributes = (NodeList) xPath.compile(baseExpression).evaluate(document, XPathConstants.NODESET);

            final String path = "/_schema/properties/directoryConfiguration/" + sectionRawValue;
            directoryConfigSchema.putPermissive(new JsonPointer(path + "/title"), sectionTitle);
            for (int i = 0; i < attributes.getLength(); i++) {
                String attributeRawName = attributes.item(i).getNodeValue();
                String attributePath = path + "/" + attributeRawName;
                directoryConfigSchema.putPermissive(new JsonPointer(attributePath + "/title"), titleProperties.getProperty(attributeRawName));
                directoryConfigSchema.putPermissive(new JsonPointer(attributePath + "/propertyOrder"), i);
                directoryConfigSchema.putPermissive(new JsonPointer(attributePath + "/type"), "string");
            }

            final String serverPath = path + "/servers";
            directoryConfigSchema.putPermissive(new JsonPointer(serverPath + "/title"), titleProperties.get("amconfig.serverconfig.xml.server.table.header"));
            directoryConfigSchema.putPermissive(new JsonPointer(serverPath + "/type"), "array");
            directoryConfigSchema.putPermissive(new JsonPointer(serverPath + "/items/type"), "object");

            List<String> columnNames = new ArrayList<>();
            columnNames.add("name");
            columnNames.add("host");
            columnNames.add("port");
            columnNames.add("type");

            for (String columnName : columnNames) {
                final String attributePath = serverPath + "/items/properties/" + SERVER_TABLE_PROPERTY_PREFIX + columnName;
                directoryConfigSchema.putPermissive(new JsonPointer(attributePath + "/title"),
                        titleProperties.getProperty(SERVER_TABLE_PROPERTY_PREFIX + columnName));
                directoryConfigSchema.putPermissive(new JsonPointer(attributePath + "/type"), "string");
                directoryConfigSchema.putPermissive(new JsonPointer(attributePath + "/propertyOrder"), columnNames.indexOf(columnName));
            }

            return directoryConfigSchema;
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            logger.error("Error creating document builder", e);
        }
        return null;
    }

    private JsonValue getSchema(Properties syntaxProperties, Properties titleProperties, boolean isDefault) {
        JsonValue template = json(object());
        for (String tabName : getTabNames()) {
            try {
                Document propertySheet = getPropertySheet(tabName);
                Map<String, Set<String>> options = getOptions(propertySheet, tabName);
                List<String> sectionNames = getSectionNames(propertySheet);
                Set<String> optionalAttributes = getOptionalAttributes(propertySheet, tabName);

                int sectionOrder = 0;
                for (String sectionName : sectionNames) {

                    final String sectionPath = "/properties/" + tabName + "/" + sectionName;
                    template.putPermissive(new JsonPointer(sectionPath + "/title"), titleProperties.getProperty(sectionName));

                    template.putPermissive(new JsonPointer(sectionPath + "/propertyOrder"), sectionOrder++);

                    int attributeOrder = 0;
                    for (String attributeName : getAttributeNamesForSection(sectionName, propertySheet)) {
                        final String title = titleProperties.getProperty(attributeName);
                        String property = syntaxProperties.getProperty(attributeName);
                        if (property == null) {
                            property = "";
                        }
                        final String type = syntaxRawToReal.get(property);
                        final Set<String> attributeOptions = getAttributeOptions(options, attributeName, type);
                        final boolean isOptional;

                        if (isDefault) {
                            isOptional = optionalAttributes.contains(attributeName);
                        } else {
                            isOptional = true;
                        }

                        final String path = sectionPath + "/" + attributeName;

                        if (attributeOptions != null && !attributeOptions.isEmpty()) {
                            template.putPermissive(new JsonPointer(path + "/type/enum"), attributeOptions);
                        } else {
                            template.putPermissive(new JsonPointer(path + "/type"), type);
                        }

                        template.putPermissive(new JsonPointer(path + "/title"), title);
                        template.putPermissive(new JsonPointer(path + "/propertyOrder"), attributeOrder++);
                        template.putPermissive(new JsonPointer(path + "/required"), !isOptional);
                        template.putPermissive(new JsonPointer(path + "/pattern"), ".+");

                        allAttributeNamesInNamedTabs.add(attributeName);
                    }
                }
            } catch (ParserConfigurationException | IOException | XPathExpressionException | SAXException e) {
                logger.error("Error reading property sheet for tab " + tabName, e);
            }
        }

        return template;
    }

    private Set<String> getAttributeOptions(Map<String, Set<String>> options, String attributeName, String syntax) {
        Set<String> attributeOptions;
        if (syntax != null && syntax.equals("on,off")) {
            final HashSet<String> onOffOptions = new HashSet<>();
            onOffOptions.add("on");
            onOffOptions.add("off");
            attributeOptions = onOffOptions;
        } else {
            attributeOptions = options.get(attributeName);
        }

        return attributeOptions;
    }

    private Set<String> getOptionalAttributes(Document propertySheet, String tabName) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        Set<String> optionalValues = new HashSet<>();

        try {
            String expression = "//propertysheet/section/property[@required='false']/cc/@name";
            NodeList optionalValuesList = (NodeList) xPath.compile(expression).evaluate(propertySheet,
                    XPathConstants.NODESET);

            for (int i = 0; i < optionalValuesList.getLength(); i++) {
                optionalValues.add(optionalValuesList.item(i).getNodeValue());
            }

        } catch (XPathExpressionException e) {
            logger.error("Error reading property sheet for tab " + tabName, e);
        }

        return optionalValues;
    }

    private String getConvertedName(String defaultValueName) {
        return "csc".concat(defaultValueName.replace('.', '-'));
    }

    private Set<String> getTabNames() {
        Set<String> tabNames = new HashSet<>();
        try {
            String result = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(AM_CONSOLE_CONFIG_XML));
            Matcher matcher = Pattern.compile(".*ServerEdit(.*)ViewBean.*").matcher(result);
            while (matcher.find()) {
                tabNames.add(matcher.group(1).toLowerCase());
            }
        } catch (IOException e) {
            logger.error("Error getting tab names", e);
        }
        return tabNames;
    }

    @Override
    public void actionInstance(ServerContext serverContext, ActionRequest actionRequest, ResultHandler<JsonValue> resultHandler) {
        if (actionRequest.getAction().equals("schema")) {
            Map<String, String> uriVariables = getUriTemplateVariables(serverContext);

            final String serverName = uriVariables.get("serverName").toLowerCase();
            if (serverName == null) {
                resultHandler.handleError(new BadRequestException("Server name not specified."));
            }

            try {
                ServiceConfigManager scm = getServiceConfigManager(serverContext);
                ServiceConfig serverConfigs = getServerConfigs(scm);
                if (!serverConfigs.getSubConfigNames().contains(serverName)) {
                    resultHandler.handleError(new BadRequestException("Unknown server: " + serverName));
                }
            } catch (SSOException | SMSException e) {
                logger.error("Error getting server config", e);
            }

            final String tabName = getTabName(uriVariables);
            if (tabName == null) {
                resultHandler.handleError(new BadRequestException("Tab name not specified."));
                return;
            }

            JsonValue schema;
            final JsonPointer tabPointer = new JsonPointer("_schema/properties/" + tabName);
            if (DIRECTORY_CONFIGURATION_TAB_NAME.equalsIgnoreCase(tabName)) {
                schema = directoryConfigSchema;
            } else if (serverName.equals(SERVER_DEFAULT_NAME)) {
                schema = defaultSchema.get(tabPointer);
            } else {
                schema = nonDefaultSchema.get(tabPointer);
            }

            if (schema == null) {
                resultHandler.handleError(new BadRequestException("Unknown tab: " + tabName));
                return;
            }

            resultHandler.handleResult(schema);
        } else {
            resultHandler.handleError(new NotSupportedException("Action not supported: " + actionRequest.getAction()));
        }
    }

    private Properties getAttributes(ServiceConfig serverConfig) throws IOException, SMSException, SSOException {
        Set<String> rawValues = (Set<String>) serverConfig.getAttributes().get(SERVER_CONFIG);

        StringBuilder stringBuilder = new StringBuilder();
        for (String value : rawValues) {
            stringBuilder.append(value);
            stringBuilder.append("\n");
        }

        Properties properties = new Properties();
        properties.load(new StringReader(stringBuilder.toString()));
        return properties;
    }

    private List<String> getDefaultValueNames(String tabName) throws ParserConfigurationException, SAXException,
            IOException,
            XPathExpressionException {
        Document propertySheet = getPropertySheet(tabName);
        return getValues("/propertysheet/section/property/cc/@name", propertySheet);
    }

    List<String> getAttributeNamesForSection(String sectionName, Document propertySheet) throws
            ParserConfigurationException,
            SAXException,
            IOException, XPathExpressionException {
        return getValues("/propertysheet/section[@defaultValue='" + sectionName + "']/property/cc/@name", propertySheet);
    }

    private List<String> getValues(String expression, Document propertySheet) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        List<String> defaultValueNames = new ArrayList<>();
        NodeList defaultValues = (NodeList) xPath.compile(expression).evaluate(propertySheet, XPathConstants.NODESET);
        for (int i = 0; i < defaultValues.getLength(); i++) {
            String nodeValue = defaultValues.item(i).getNodeValue().replace('-', '.');
            if (nodeValue.substring(0, 3).equals("csc")) {
                nodeValue = nodeValue.substring(3, nodeValue.length());
                nodeValue = "amconfig.".concat(nodeValue);
            }
            defaultValueNames.add(nodeValue);
        }
        return defaultValueNames;
    }

    private List<String> getSectionNames(Document propertySheet) throws ParserConfigurationException, SAXException,
            IOException,
            XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//propertysheet/section/@defaultValue";
        List<String> sectionNames = new ArrayList<>();
        NodeList defaultValues = (NodeList) xPath.compile(expression).evaluate(propertySheet, XPathConstants.NODESET);
        for (int i = 0; i < defaultValues.getLength(); i++) {
            final String nodeValue = defaultValues.item(i).getNodeValue();
            sectionNames.add(nodeValue);
        }

        return sectionNames;
    }

    private Map<String, Set<String>> getOptions(Document propertySheet, String tabName) {
        Map<String, Set<String>> radioOptions = new HashMap<>();
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();

            List<String> attributeNamesForTab = getDefaultValueNames(tabName);
            for (String defaultValueName : attributeNamesForTab) {
                String convertedName = getConvertedName(defaultValueName);
                String expression = "//propertysheet/section/property/cc[@name='" + convertedName + "']/option/@value";
                NodeList optionsList = (NodeList) xPath.compile(expression).evaluate(propertySheet, XPathConstants.NODESET);
                Set<String> options = new HashSet<>();
                for (int i = 0; i < optionsList.getLength(); i++) {
                    options.add(optionsList.item(i).getNodeValue());
                }

                if (!options.isEmpty()) {
                    radioOptions.put(defaultValueName, options);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            logger.error("Error reading property sheet", e);
        }
        return radioOptions;
    }

    private Document getPropertySheet(String tabName) throws ParserConfigurationException, SAXException, IOException {
        InputStream resourceStream = getInputStream(tabName);

        if (resourceStream == null) {
            //try with an uppercase first letter
            resourceStream = getInputStream(tabName.substring(0, 1).toUpperCase() + tabName.substring(1));
        }

        if (resourceStream == null) {
            //try all in caps
            resourceStream = getInputStream(tabName.toUpperCase());
        }

        if (resourceStream == null) {
            throw new IOException("Unable to locate propertySheet for " + tabName);
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(resourceStream);
    }

    private InputStream getInputStream(String tabName) {
        final String propertyFileName = "propertyServerEdit" + tabName + ".xml";
        return this.getClass().getClassLoader().getResourceAsStream
                ("com/sun/identity/console/" + propertyFileName);
    }

    protected ServiceConfigManager getServiceConfigManager(ServerContext context) throws SSOException, SMSException {
        SSOToken ssoToken = context.asContext(SSOTokenContext.class).getCallerSSOToken();
        return new ServiceConfigManager(ssoToken, "iPlanetAMPlatformService", "1.0");
    }

    protected ServiceConfig getServerConfigs(ServiceConfigManager scm)
            throws SMSException, SSOException {
        ServiceConfig config = scm.getGlobalConfig(null);
        return config.getSubConfig(SCHEMA_NAME);
    }

    @Override
    public void patchInstance(ServerContext serverContext, PatchRequest patchRequest, ResultHandler<Resource> resultHandler) {
        resultHandler.handleError(new NotSupportedException());
    }

    @Override
    public void readInstance(ServerContext serverContext, ReadRequest readRequest, ResultHandler<Resource> resultHandler) {
        Map<String, String> uriVariables = getUriTemplateVariables(serverContext);

        final String tabName = getTabName(uriVariables);

        if (tabName == null) {
            resultHandler.handleError(new BadRequestException("Tab name not specified."));
            return;
        }

        final String serverName = getServerName(uriVariables);
        if (serverName == null) {
            resultHandler.handleError(new BadRequestException("Server name not specified."));
            return;
        }

        try {
            ServiceConfigManager scm = getServiceConfigManager(serverContext);
            ServiceConfig serverConfigs = getServerConfigs(scm);

            Properties defaultAttributes = getAttributes(serverConfigs.getSubConfig(SERVER_DEFAULT_NAME));
            final ServiceConfig serverConfig = serverConfigs.getSubConfig(serverName);

            if (serverConfig == null) {
                resultHandler.handleError(new BadRequestException("Unknown Server " + serverName));
                return;
            }

            Properties serverSpecificAttributes = getAttributes(serverConfig);
            Map<String, String> defaultSection = new HashMap<>();

            JsonValue result = json(object(
                    field("default", defaultSection)));

            List<String> attributeNamesForTab;
            if (tabName.equalsIgnoreCase(DIRECTORY_CONFIGURATION_TAB_NAME)) {
                InputStream resourceStream = new StringInputStream(getServerConfigXml(serverConfig));

                Document serverXml = dBuilder.parse(resourceStream);

                XPath xPath = XPathFactory.newInstance().newXPath();

                final String baseExpression = "//iPlanetDataAccessLayer/ServerGroup[@name='sms']/";
                String minConnections = (String) xPath.compile(baseExpression + "@" + DSConfigMgr.MIN_CONN_POOL).evaluate(serverXml, XPathConstants.STRING);
                String maxConnections = (String) xPath.compile(baseExpression + "@" + DSConfigMgr.MAX_CONN_POOL).evaluate(serverXml, XPathConstants.STRING);
                String dirDN = (String) xPath.compile(baseExpression + "User/DirDN").evaluate(serverXml, XPathConstants.STRING);
                String directoryPassword = (String) xPath.compile(baseExpression + "User/DirPassword").evaluate(serverXml, XPathConstants.STRING);

                result.put("minConnections", minConnections);
                result.put("maxConnections", maxConnections);
                result.put("dirDN", dirDN);
                result.put("directoryPassword", directoryPassword);

                NodeList serverNames = (NodeList) xPath.compile(baseExpression + "Server/@name").evaluate
                        (serverXml, XPathConstants.NODESET);

                for (int i = 0; i < serverNames.getLength(); i++) {
                    final String directoryServerName = serverNames.item(i).getNodeValue();
                    final String serverExpression = baseExpression + "Server[@name='" + directoryServerName + "']";
                    String hostExpression = serverExpression + "/@host";
                    String portExpression = serverExpression + "/@port";
                    String typeExpression = serverExpression + "/@type";

                    NodeList serverAttributes = (NodeList) xPath.compile(hostExpression + "|" + portExpression + "|" +
                            typeExpression).evaluate(serverXml, XPathConstants.NODESET);

                    for (int a = 0; a < serverAttributes.getLength(); a++) {
                        final Node serverAttribute = serverAttributes.item(a);
                        result.addPermissive(new JsonPointer("servers/" + directoryServerName + "/" + serverAttribute
                                .getNodeName()), serverAttribute.getNodeValue());
                    }
                }
            } else {
                if (tabName.equalsIgnoreCase(ADVANCED_TAB_NAME)) {
                    attributeNamesForTab = getAdvancedTabAttributeNames(serverConfig);
                } else {
                    attributeNamesForTab = getDefaultValueNames(tabName);
                }

                for (String attributeName : attributeNamesForTab) {
                    final String defaultAttribute = (String) defaultAttributes.get(attributeName);
                    if (defaultAttribute != null) {
                        defaultSection.put(attributeName, (String) defaultAttributes.get(attributeName));
                    }

                    final String serverSpecificAttribute = (String) serverSpecificAttributes.get(attributeName);
                    if (serverSpecificAttribute != null) {
                        result.add(attributeName, serverSpecificAttribute);
                    }
                }
            }

            resultHandler.handleResult(new Resource(serverName + "/properties/" + tabName, String.valueOf(result
                    .hashCode()), result));
            return;
        } catch (SMSException | SSOException | ParserConfigurationException | SAXException | IOException
                | XPathExpressionException e) {
            logger.error("Error reading property sheet for tab " + tabName, e);
        }

        resultHandler.handleError(new BadRequestException("Error reading properties file for " + tabName));
    }

    private String getServerConfigXml(ServiceConfig serverConfig) {
        return (String) ((Set) serverConfig.getAttributes().get("serverconfigxml"))
                .iterator().next();
    }

    private List<String> getAdvancedTabAttributeNames(ServiceConfig serverConfig) {
        List<String> attributeNamesForTab;
        Set<String> allAttributeNames = (Set<String>) serverConfig.getAttributes().get(SERVER_CONFIG);

        attributeNamesForTab = new ArrayList<>();
        for (String attributeRawValue : allAttributeNames) {
            String attributeName = attributeRawValue.split("=")[0];
            if (!allAttributeNamesInNamedTabs.contains(attributeName)) {
                attributeNamesForTab.add(attributeName);
            }
        }

        attributeNamesForTab.remove(Constants.AM_SERVER_PROTOCOL);
        attributeNamesForTab.remove(Constants.AM_SERVER_HOST);
        attributeNamesForTab.remove(Constants.AM_SERVER_PORT);
        attributeNamesForTab.remove(Constants.AM_SERVICES_DEPLOYMENT_DESCRIPTOR);
        attributeNamesForTab.remove(Constants.SERVER_MODE);
        attributeNamesForTab.remove(SetupConstants.AMC_OVERRIDE_PROPERTY);
        return attributeNamesForTab;
    }

    private Map<String, String> getUriTemplateVariables(ServerContext serverContext) {
        return serverContext.asContext(RouterContext.class).getUriTemplateVariables();
    }

    private String getTabName(Map<String, String> uriVariables) {
        return uriVariables.get("tab").toLowerCase();
    }

    private String getServerName(Map<String, String> uriVariables) {
        return uriVariables.get("serverName");
    }

    @Override
    public void updateInstance(ServerContext serverContext, UpdateRequest updateRequest, ResultHandler<Resource> resultHandler) {
        Map<String, String> uriVariables = getUriTemplateVariables(serverContext);

        final String tabName = getTabName(uriVariables);
        if (tabName == null) {
            resultHandler.handleError(new BadRequestException("Tab name not specified."));
        }

        final String serverName = getServerName(uriVariables);
        if (serverName == null) {
            resultHandler.handleError(new BadRequestException("Server name not specified."));
        }

        try {
            ServiceConfigManager scm = getServiceConfigManager(serverContext);
            ServiceConfig serverConfigs = getServerConfigs(scm);
            final ServiceConfig serverConfig = serverConfigs.getSubConfig(serverName);

            final JsonValue jsonValue = updateRequest.toJsonValue();
            final Map newAttributeValues = (Map) ((Map) jsonValue.getObject()).get("content");
            Set<String> attributesToBeAlteredNames = newAttributeValues.keySet();

            final Map allAttributes = serverConfig.getAttributes();
            Set<String> currentAttributes = (Set) allAttributes.get(SERVER_CONFIG);
            Set<String> newAttributes = new HashSet<>();

            for (String attribute : currentAttributes) {
                String attributeName = attribute.split("=")[0];

                if (attributesToBeAlteredNames.contains(attributeName)) {
                    newAttributes.add(attributeName + "=" + newAttributeValues.get(attributeName));
                } else {
                    newAttributes.add(attribute);
                }
            }

            allAttributes.put(SERVER_CONFIG, newAttributes);
            serverConfig.setAttributes(allAttributes);

            resultHandler.handleResult(new Resource(tabName, String.valueOf(jsonValue.hashCode()),
                    jsonValue.get("content")));
            return;
        } catch (SSOException e) {
            logger.error("Error getting SSOToken", e);
        } catch (SMSException e) {
            logger.error("Error getting service config manager", e);
        }

        resultHandler.handleError(new BadRequestException("Error updating values for " + tabName));
    }

}
