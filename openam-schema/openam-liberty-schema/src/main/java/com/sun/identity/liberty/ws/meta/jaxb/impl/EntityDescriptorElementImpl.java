//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-b27-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.11 at 10:33:54 AM PDT 
//


package com.sun.identity.liberty.ws.meta.jaxb.impl;

public class EntityDescriptorElementImpl
    extends com.sun.identity.liberty.ws.meta.jaxb.impl.EntityDescriptorTypeImpl
    implements com.sun.identity.liberty.ws.meta.jaxb.EntityDescriptorElement, com.sun.xml.bind.RIElement, com.sun.xml.bind.JAXBObject, com.sun.identity.federation.jaxb.entityconfig.impl.runtime.UnmarshallableObject, com.sun.identity.federation.jaxb.entityconfig.impl.runtime.XMLSerializable, com.sun.identity.federation.jaxb.entityconfig.impl.runtime.ValidatableObject
{

    public final static java.lang.Class version = (com.sun.identity.liberty.ws.meta.jaxb.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sun.identity.liberty.ws.meta.jaxb.EntityDescriptorElement.class);
    }

    public java.lang.String ____jaxb_ri____getNamespaceURI() {
        return "urn:liberty:metadata:2003-08";
    }

    public java.lang.String ____jaxb_ri____getLocalName() {
        return "EntityDescriptor";
    }

    public com.sun.identity.federation.jaxb.entityconfig.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.UnmarshallingContext context) {
        return new com.sun.identity.liberty.ws.meta.jaxb.impl.EntityDescriptorElementImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("urn:liberty:metadata:2003-08", "EntityDescriptor");
        super.serializeURIs(context);
        context.endNamespaceDecls();
        super.serializeAttributes(context);
        context.endAttributes();
        super.serializeBody(context);
        context.endElement();
    }

    public void serializeAttributes(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public void serializeURIs(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.sun.identity.liberty.ws.meta.jaxb.EntityDescriptorElement.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000"
+"\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv."
+"grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000"
+"\fcontentModelt\u0000 Lcom/sun/msv/grammar/Expression;xr\u0000\u001ecom.sun."
+"msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Lj"
+"ava/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0003xppp\u0000sr\u0000\u001fcom.sun.msv.gra"
+"mmar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.BinaryExp"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1q\u0000~\u0000\u0003L\u0000\u0004exp2q\u0000~\u0000\u0003xq\u0000~\u0000\u0004ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007pps"
+"q\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsr\u0000\u001dcom.sun.m"
+"sv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\bppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0012ppsr\u0000 c"
+"om.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.gr"
+"ammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0003xq\u0000~\u0000\u0004sr\u0000\u0011java.lang.Bool"
+"ean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psq\u0000~\u0000\u0012q\u0000~\u0000\u001apsq\u0000~\u0000\u0000q\u0000~\u0000\u001ap\u0000sq\u0000~\u0000\u0012pps"
+"q\u0000~\u0000\u0016q\u0000~\u0000\u001apsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000"
+"\u0003expq\u0000~\u0000\u0003L\u0000\tnameClassq\u0000~\u0000\u0001xq\u0000~\u0000\u0004q\u0000~\u0000\u001apsr\u00002com.sun.msv.gramma"
+"r.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004sq\u0000~\u0000\u0019\u0001q\u0000~\u0000"
+"\"sr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun."
+"msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Ex"
+"pression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004q\u0000~\u0000#q\u0000~\u0000(sr\u0000#com"
+".sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Lj"
+"ava/lang/String;L\u0000\fnamespaceURIq\u0000~\u0000*xq\u0000~\u0000%t\u0000:com.sun.identit"
+"y.liberty.ws.meta.jaxb.IDPDescriptorElementt\u0000+http://java.su"
+"n.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\u0000q\u0000~\u0000\u001ap\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000s"
+"q\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001apsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(sq\u0000~\u0000)t\u00007com.s"
+"un.identity.liberty.ws.meta.jaxb.IDPDescriptorTypeq\u0000~\u0000-sq\u0000~\u0000"
+"\u0012ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001apsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002"
+"dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0003L\u0000\u0004namet\u0000\u001d"
+"Lcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0004q\u0000~\u0000\u001apsr\u0000\"com.sun.msv.dat"
+"atype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.B"
+"uiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.Conc"
+"reteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeIm"
+"pl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000*L\u0000\btypeNameq\u0000~\u0000*L\u0000\nwhiteSpa"
+"cet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http"
+"://www.w3.org/2001/XMLSchemat\u0000\u0005QNamesr\u00005com.sun.msv.datatype"
+".xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv."
+"datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv"
+".grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004q\u0000~\u0000\u001ap"
+"sr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000*L"
+"\u0000\fnamespaceURIq\u0000~\u0000*xpq\u0000~\u0000Cq\u0000~\u0000Bsq\u0000~\u0000)t\u0000\u0004typet\u0000)http://www.w3"
+".org/2001/XMLSchema-instanceq\u0000~\u0000(sq\u0000~\u0000)t\u0000\rIDPDescriptort\u0000\u001cur"
+"n:liberty:metadata:2003-08q\u0000~\u0000(sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001apsq\u0000~\u0000\u0012q\u0000~"
+"\u0000\u001apsq\u0000~\u0000\u0000q\u0000~\u0000\u001ap\u0000sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001apsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\"q\u0000~\u0000&q\u0000"
+"~\u0000(sq\u0000~\u0000)t\u00009com.sun.identity.liberty.ws.meta.jaxb.SPDescript"
+"orElementq\u0000~\u0000-sq\u0000~\u0000\u0000q\u0000~\u0000\u001ap\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000"
+"~\u0000\u001apsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(sq\u0000~\u0000)t\u00006com.sun.identity.lib"
+"erty.ws.meta.jaxb.SPDescriptorTypeq\u0000~\u0000-sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq"
+"\u0000~\u0000;q\u0000~\u0000Kq\u0000~\u0000(sq\u0000~\u0000)t\u0000\fSPDescriptorq\u0000~\u0000Pq\u0000~\u0000(sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007"
+"ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001apsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(sq"
+"\u0000~\u0000)t\u0000?com.sun.identity.liberty.ws.meta.jaxb.AffiliationDesc"
+"riptorTypeq\u0000~\u0000-sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000;q\u0000~\u0000Kq\u0000~\u0000(sq\u0000~\u0000)t\u0000\u0015A"
+"ffiliationDescriptorq\u0000~\u0000Psq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0000q\u0000~\u0000\u001ap\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000"
+"pp\u0000sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001apsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(sq\u0000~\u0000)t\u00001c"
+"om.sun.identity.liberty.ws.meta.jaxb.ContactTypeq\u0000~\u0000-sq\u0000~\u0000\u0012p"
+"psq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000;q\u0000~\u0000Kq\u0000~\u0000(sq\u0000~\u0000)t\u0000\rContactPersonq\u0000~\u0000Pq\u0000~\u0000("
+"sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0000q\u0000~\u0000\u001ap\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001apsq"
+"\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(sq\u0000~\u0000)t\u00006com.sun.identity.liberty.w"
+"s.meta.jaxb.OrganizationTypeq\u0000~\u0000-sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000;q\u0000"
+"~\u0000Kq\u0000~\u0000(sq\u0000~\u0000)t\u0000\fOrganizationq\u0000~\u0000Pq\u0000~\u0000(sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0012q\u0000~\u0000\u001aps"
+"q\u0000~\u0000\u0000q\u0000~\u0000\u001ap\u0000sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001apsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(s"
+"q\u0000~\u0000)t\u00006com.sun.identity.liberty.ws.meta.jaxb.ExtensionEleme"
+"ntq\u0000~\u0000-sq\u0000~\u0000\u0000q\u0000~\u0000\u001ap\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001apsq\u0000"
+"~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(sq\u0000~\u0000)t\u00003com.sun.identity.liberty.ws"
+".meta.jaxb.ExtensionTypeq\u0000~\u0000-sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000;q\u0000~\u0000Kq"
+"\u0000~\u0000(sq\u0000~\u0000)t\u0000\tExtensionq\u0000~\u0000Pq\u0000~\u0000(sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0012q\u0000~\u0000\u001apsq\u0000~\u0000\u0000q\u0000"
+"~\u0000\u001ap\u0000sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001apsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(sq\u0000~\u0000)t\u0000"
+"?com.sun.identity.liberty.ws.common.jaxb.xmlsig.SignatureEle"
+"mentq\u0000~\u0000-sq\u0000~\u0000\u0000q\u0000~\u0000\u001ap\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001aps"
+"q\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(sq\u0000~\u0000)t\u0000<com.sun.identity.liberty."
+"ws.common.jaxb.xmlsig.SignatureTypeq\u0000~\u0000-sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001ap"
+"q\u0000~\u0000;q\u0000~\u0000Kq\u0000~\u0000(sq\u0000~\u0000)t\u0000\tSignaturet\u0000\"http://www.w3.org/2000/0"
+"9/xmldsig#q\u0000~\u0000(sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001apsq\u0000~\u00008ppsr\u0000%com.sun.msv.d"
+"atatype.xsd.SimpleURType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000=q\u0000~\u0000Bt\u0000\ranySimpleTy"
+"pesr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Preserve\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Eq\u0000~\u0000Hsq\u0000~\u0000Iq\u0000~\u0000\u00baq\u0000~\u0000Bsq\u0000~\u0000)t\u0000\rcacheDurationt"
+"\u0000\u0000q\u0000~\u0000(sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001apsq\u0000~\u00008ppsr\u0000\u001fcom.sun.msv.datatype."
+"xsd.IDType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.NcnameType"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\"com.sun.msv.datatype.xsd.TokenType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
+"xr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlway"
+"sValidxq\u0000~\u0000=q\u0000~\u0000Bt\u0000\u0002IDq\u0000~\u0000F\u0000q\u0000~\u0000Hsq\u0000~\u0000Iq\u0000~\u0000\u00c9q\u0000~\u0000Bsq\u0000~\u0000)t\u0000\u0002id"
+"q\u0000~\u0000\u00c0q\u0000~\u0000(sq\u0000~\u0000\u001fppsq\u0000~\u00008ppsr\u0000\'com.sun.msv.datatype.xsd.MaxLe"
+"ngthFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\tmaxLengthxr\u00009com.sun.msv.datatype.xsd"
+".DataTypeWithValueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv."
+"datatype.xsd.DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012n"
+"eedValueCheckFlagL\u0000\bbaseTypet\u0000)Lcom/sun/msv/datatype/xsd/XSD"
+"atatypeImpl;L\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/datatype/xsd/Conc"
+"reteType;L\u0000\tfacetNameq\u0000~\u0000*xq\u0000~\u0000?q\u0000~\u0000Pt\u0000\fentityIDTypeq\u0000~\u0000F\u0000\u0000s"
+"r\u0000#com.sun.msv.datatype.xsd.AnyURIType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000=q\u0000~\u0000B"
+"t\u0000\u0006anyURIq\u0000~\u0000Fq\u0000~\u0000\u00d7t\u0000\tmaxLength\u0000\u0000\u0004\u0000q\u0000~\u0000Hsq\u0000~\u0000Iq\u0000~\u0000\u00d5q\u0000~\u0000Psq\u0000~"
+"\u0000)t\u0000\nproviderIDq\u0000~\u0000\u00c0sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000\u00b7sq\u0000~\u0000)t\u0000\nvalidU"
+"ntilq\u0000~\u0000\u00c0q\u0000~\u0000(sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001apq\u0000~\u0000;q\u0000~\u0000Kq\u0000~\u0000(sq\u0000~\u0000)t\u0000\u0010En"
+"tityDescriptorq\u0000~\u0000Psr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$Cl"
+"osedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash"
+"\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/"
+"grammar/ExpressionPool;xp\u0000\u0000\u0000?\u0001pq\u0000~\u0000\u001eq\u0000~\u0000\u0014q\u0000~\u0000\u007fq\u0000~\u0000rq\u0000~\u0000\u0013q\u0000~\u0000"
+"\nq\u0000~\u0000\u00c1q\u0000~\u0000\u00a0q\u0000~\u0000\u008cq\u0000~\u0000Qq\u0000~\u0000\u00abq\u0000~\u0000\u00a3q\u0000~\u0000\u0097q\u0000~\u0000\u008fq\u0000~\u0000\u0083q\u0000~\u0000vq\u0000~\u0000\u0011q\u0000~\u0000"
+"iq\u0000~\u0000]q\u0000~\u0000Uq\u0000~\u00001q\u0000~\u0000\u001dq\u0000~\u0000\u0015q\u0000~\u0000\u001bq\u0000~\u0000\u00a1q\u0000~\u0000\u008dq\u0000~\u0000Sq\u0000~\u0000\tq\u0000~\u0000\u00b0q\u0000~\u0000"
+"\u009cq\u0000~\u0000\u0088q\u0000~\u0000{q\u0000~\u0000nq\u0000~\u0000bq\u0000~\u00006q\u0000~\u0000\u000bq\u0000~\u0000\u00e1q\u0000~\u0000\u00ddq\u0000~\u0000\rq\u0000~\u0000\u000eq\u0000~\u0000\u00a9q\u0000~\u0000"
+"\u0095q\u0000~\u0000\u0081q\u0000~\u0000tq\u0000~\u0000gq\u0000~\u0000[q\u0000~\u0000/q\u0000~\u0000Rq\u0000~\u0000\u0018q\u0000~\u0000\u00b5q\u0000~\u0000\fq\u0000~\u0000\u000fq\u0000~\u0000\u00acq\u0000~\u0000"
+"\u00a4q\u0000~\u0000\u0010q\u0000~\u0000\u0098q\u0000~\u0000\u0090q\u0000~\u0000\u0084q\u0000~\u0000wq\u0000~\u0000jq\u0000~\u0000^q\u0000~\u0000Vq\u0000~\u00002x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sun.identity.federation.jaxb.entityconfig.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.UnmarshallingContext context) {
            super(context, "----");
        }

        protected Unmarshaller(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sun.identity.liberty.ws.meta.jaxb.impl.EntityDescriptorElementImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  0 :
                        if (("EntityDescriptor" == ___local)&&("urn:liberty:metadata:2003-08" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 1;
                            return ;
                        }
                        break;
                    case  1 :
                        attIdx = context.getAttribute("", "cacheDuration");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        attIdx = context.getAttribute("", "providerID");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  2 :
                        if (("EntityDescriptor" == ___local)&&("urn:liberty:metadata:2003-08" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  3 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  1 :
                        attIdx = context.getAttribute("", "cacheDuration");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        attIdx = context.getAttribute("", "providerID");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  1 :
                        if (("cacheDuration" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterAttribute((((com.sun.identity.liberty.ws.meta.jaxb.impl.EntityDescriptorTypeImpl)com.sun.identity.liberty.ws.meta.jaxb.impl.EntityDescriptorElementImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname);
                            return ;
                        }
                        if (("id" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterAttribute((((com.sun.identity.liberty.ws.meta.jaxb.impl.EntityDescriptorTypeImpl)com.sun.identity.liberty.ws.meta.jaxb.impl.EntityDescriptorElementImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname);
                            return ;
                        }
                        if (("providerID" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterAttribute((((com.sun.identity.liberty.ws.meta.jaxb.impl.EntityDescriptorTypeImpl)com.sun.identity.liberty.ws.meta.jaxb.impl.EntityDescriptorElementImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  1 :
                        attIdx = context.getAttribute("", "cacheDuration");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        attIdx = context.getAttribute("", "providerID");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  3 :
                            revertToParentFromText(value);
                            return ;
                        case  1 :
                            attIdx = context.getAttribute("", "cacheDuration");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            attIdx = context.getAttribute("", "id");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            attIdx = context.getAttribute("", "providerID");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

    }

}
