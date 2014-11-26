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
 * information: "Portions Copyrighted [year] [name of copyright owner]".
 *
 * Copyright 2014 ForgeRock AS. All rights reserved.
 */

package org.forgerock.openam.shared.sts;

/**
 * Defines some constants shared between the openam-sts module and the sts ViewBean/Model in openam-console.
 */
public class SharedSTSConstants {
    /*
    The name of the json field in the json rest-sts publish invocation that references the field which allows the
    marshalling logic in the RestSTSPublishServiceRequestHandler to distinguish between programmatic invocations via
    the client stk classes, which will publish with state generated by calling toJson() on an instance of the RestSTSInstanceConfig
    class, and the RestSecurityTokenServiceViewBean, which will publish with state harvested from the ViewBean property
     sheet, and will thus be in the format of Map<String, Set<String>>.
     */
    public static final String REST_STS_PUBLISH_INVOCATION_CONTEXT = "invocation_context";

    /*
    Used  as the value for the REST_STS_PUBLISH_INVOCATION_CONTEXT key for invocations to the rest sts publish service
    issued by the RestSecurityTokenServiceViewBean.
     */
    public static final String REST_STS_PUBLISH_INVOCATION_CONTEXT_VIEW_BEAN = "invocation_context_view_bean";

    /*
    Used as the key to the JsonValue corresponding to a wrapped Map<String, Set<String>> or the output of
    RestSTSInstanceConfig#toJson(), depending upon the invocation context.
     */
    public static final String REST_STS_PUBLISH_INSTANCE_STATE = "instance_state";

    /*
    This field referenced in RestDeploymentConfig.DEPLOYMENT_REALM. It is the name of the key of the json field referencing
    the realm in which the rest instance is deployed, which also matches the name of the AttributeSchema element defined in restSTS.xml
     */
    public static final String DEPLOYMENT_REALM = "deployment-realm";

    /*
    This field referenced in SAML2Config.TOKEN_LIFETIME. It is the name of the key of the json field referencing
    the token lifetime of issued saml2 assertions, which also matches the name of the AttributeSchema element defined in restSTS.xml
     */
    public static final String SAML2_TOKEN_LIFETIME = "saml2-token-lifetime-seconds";

    /*
    This field referenced in SAML2Config.SIGN_ASSERTION. It is the name of the key of the json field referencing
    whether the issued assertion should be signed, which also matches the name of the AttributeSchema element defined in restSTS.xml
     */
    public static final String SAML2_SIGN_ASSERTION = "saml2-sign-assertion";

    /*
    This field referenced in SAML2Config.ENCRYPT_NAME_ID. It is the name of the key of the json field referencing
    whether the issued assertion should have its NameID encrypted, which also matches the name of the AttributeSchema
    element defined in restSTS.xml
     */
    public static final String SAML2_ENCRYPT_NAME_ID = "saml2-encrypt-nameid";

    /*
    This field referenced in SAML2Config.ENCRYPT_ATTRIBUTES. It is the name of the key of the json field referencing
    whether the issued assertion should have its Attributes encrypted, which also matches the name of the AttributeSchema
    element defined in restSTS.xml
     */
    public static final String SAML2_ENCRYPT_ATTRIBUTES = "saml2-encrypt-attributes";

    /*
    This field referenced in SAML2Config.ENCRYPT_ASSERTION. It is the name of the key of the json field referencing
    whether the issued assertion should be encrypted, which also matches the name of the AttributeSchema
    element defined in restSTS.xml
     */
    public static final String SAML2_ENCRYPT_ASSERTION = "saml2-encrypt-assertion";

    /*
    This field referenced in SAML2Config.ENCRYPTION_ALGORITHM. It is the name of the key of the json field referencing
    the type of encryption algorithm, which also matches the name of the AttributeSchema
    element defined in restSTS.xml.
     */
    public static final String SAML2_ENCRYPTION_ALGORITHM = "saml2-encryption-algorithm";

    /*
   This field referenced in SAML2Config.ENCRYPTION_ALGORITHM_STRENGTH. It is the name of the key of the json field referencing
   the strength of the encryption algorithm, which also matches the name of the AttributeSchema
   element defined in restSTS.xml
    */
    public static final String SAML2_ENCRYPTION_ALGORITHM_STRENGTH = "saml2-encryption-algorithm-strength";

    /*
   This field referenced in SAML2Config.KEYSTORE_FILE_NAME. It is the name of the key of the json field referencing
   the keystore location for keys used to sign and encrypt SAML assertions, which also matches the name of the AttributeSchema
   element defined in restSTS.xml
    */
    public static final String SAML2_KEYSTORE_FILE_NAME = "saml2-keystore-filename";

    /*
   This field referenced in SAML2Config.KEYSTORE_PASSWORD. It is the name of the key of the json field referencing
   the keystore password, which also matches the name of the AttributeSchema
   element defined in restSTS.xml
    */
    public static final String SAML2_KEYSTORE_PASSWORD = "saml2-keystore-password";

    /*
   This field referenced in SAML2Config.SIGNATURE_KEY_ALIAS. It is the name of the key of the json field referencing
   the signature key alias, which also matches the name of the AttributeSchema
   element defined in restSTS.xml
    */
    public static final String SAML2_SIGNATURE_KEY_ALIAS = "saml2-signature-key-alias";
    /*
   This field referenced in SAML2Config.SIGNATURE_KEY_PASSWORD. It is the name of the key of the json field referencing
   the signature key password, which also matches the name of the AttributeSchema
   element defined in restSTS.xml
    */
    public static final String SAML2_SIGNATURE_KEY_PASSWORD = "saml2-signature-key-password";
    /*
   This field referenced in SAML2Config.SP_ENTITY_ID. It is the name of the key of the json field referencing
   the entity id of the SP for whom generated assertions are intended, which also matches the name of the AttributeSchema
   element defined in restSTS.xml
    */
    public static final String SAML2_SP_ENTITY_ID = "saml2-sp-entity-id";
    /*
   This field referenced in SAML2Config.SP_ACS_URL. It is the name of the key of the json field referencing
   the url of the SP's assertion consumer service, which is required when issuing bearer assertions. Also matches the
   name of the AttributeSchema element defined in restSTS.xml
    */
    public static final String SAML2_SP_ACS_URL = "saml2-sp-acs-url";
    /*
   This field referenced in SAML2Config.ENCRYPTION_KEY_ALIAS. It is the name of the key of the json field referencing
   the public key of the SP intented to consume issued assertions, which also matches the name of the AttributeSchema
   element defined in restSTS.xml
    */
    public static final String SAML2_ENCRYPTION_KEY_ALIAS = "saml2-encryption-key-alias";
    /*
    This field referenced in RestDeploymentConfig.URI_ELEMENT. It is the name of the key of the json field referencing
    the realm-relative url element where a published rest instance is to be exposed, which also matches the name of the
    AttributeSchema element defined in restSTS.xml
     */
    public static final String DEPLOYMENT_URL_ELEMENT = "deployment-url-element";

    /**
     * If a rest-sts instance is configured to support a token transformation with an x509 token as an input token type, the
     * instance must be invoked via a two-way TLS exchange (i.e. where the client presents their certificate). If OpenAM
     * is deployed behind a tls-offloading engine, the client certificate won't be set as a HttpServetRequest attribute
     * referenced by the javax.servlet.request.X509Certificate key, but rather the rest sts instance must be configured
     * with the name of the http header where the tls-offloading engine will store the client certificate prior to invoking
     * OpenAM.
     */
    public static final String OFFLOADED_TWO_WAY_TLS_HEADER_KEY = "deployment-offloaded-two-way-tls-header-key";

    /**
     * If a rest-sts instance is configured to support a token transformation with an x509 token as an input token type, the
     * instance must be invoked via a two-way TLS exchange (i.e. where the client presents their certificate). If OpenAM
     * is deployed behind a tls-offloading engine, the client certificate won't be set as a HttpServetRequest attribute
     * referenced by the javax.servlet.request.X509Certificate key, but rather the rest sts instance must be configured
     * with the name of the http header where the tls-offloading engine will store the client certificate prior to invoking
     * OpenAM. The rest-sts instance will undertake the further check to determine if the ip address invoking the rest-sts
     * corresponds to the set of IP-addresses corresponding to the TLS-offload-engine hosts.
     */
    public static final String TLS_OFFLOAD_ENGINE_HOSTS = "deployment-tls-offload-engine-hosts";

    /*
    This field referenced in STSInstanceConfig.ISSUER_NAME. It is the name of the key of the json field referencing
    the token issuer name, which also matches the name of the AttributeSchema element defined in restSTS.xml
     */
    public static final String ISSUER_NAME = "issuer-name";

    /*
    This field referenced in RestSTSInstanceConfig.SUPPORTED_TOKEN_TRANSLATIONS. It is the name of the key of the json field referencing
    the set of token transformations, which also matches the name of the AttributeSchema element defined in restSTS.xml
     */
    public static final String SUPPORTED_TOKEN_TRANSFORMS = "supported-token-transforms";

    public static final String FORWARD_SLASH = "/";

    /*
    The url element at which the rest publish service is exposed. The rest-sts-publish corresponds to the entry in web.xml
    defining the servlet-mapping for the rest-sts-publish servlet.
     */
    public static final String REST_PUBLISH_SERVICE_URL_ELEMENT = "/rest-sts-publish/publish";

    /*
    The url constituent, appended to the REST_PUBLISH_SERVICE_URL_ELEMENT, which will trigger a POST to the rest-sts-publish
    crest service to create a new rest sts instance.
     */
    public static final String REST_PUBLISH_SERVICE_CREATE_ACTION_URL_ELEMENT = "?_action=create";

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";

    /**
     * The name of the CREST header identifying the version of a targeted service.
     */
    public static final String CREST_VERSION_HEADER_KEY = "Accept-API-Version";
}
