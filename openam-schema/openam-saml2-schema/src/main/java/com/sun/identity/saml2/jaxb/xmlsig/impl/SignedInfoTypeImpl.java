//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-b27-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.11 at 10:34:07 AM PDT 
//


package com.sun.identity.saml2.jaxb.xmlsig.impl;

public class SignedInfoTypeImpl implements com.sun.identity.saml2.jaxb.xmlsig.SignedInfoType, com.sun.xml.bind.JAXBObject, com.sun.identity.saml2.jaxb.assertion.impl.runtime.UnmarshallableObject, com.sun.identity.saml2.jaxb.assertion.impl.runtime.XMLSerializable, com.sun.xml.bind.marshaller.IdentifiableObject, com.sun.identity.saml2.jaxb.assertion.impl.runtime.ValidatableObject
{

    protected com.sun.identity.saml2.jaxb.xmlsig.SignatureMethodType _SignatureMethod;
    protected com.sun.xml.bind.util.ListImpl _Reference;
    protected java.lang.String _Id;
    protected com.sun.identity.saml2.jaxb.xmlsig.CanonicalizationMethodType _CanonicalizationMethod;
    public final static java.lang.Class version = (com.sun.identity.saml2.jaxb.xmlsig.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sun.identity.saml2.jaxb.xmlsig.SignedInfoType.class);
    }

    public com.sun.identity.saml2.jaxb.xmlsig.SignatureMethodType getSignatureMethod() {
        return _SignatureMethod;
    }

    public void setSignatureMethod(com.sun.identity.saml2.jaxb.xmlsig.SignatureMethodType value) {
        _SignatureMethod = value;
    }

    protected com.sun.xml.bind.util.ListImpl _getReference() {
        if (_Reference == null) {
            _Reference = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        }
        return _Reference;
    }

    public java.util.List getReference() {
        return _getReference();
    }

    public java.lang.String getId() {
        return _Id;
    }

    public void setId(java.lang.String value) {
        _Id = value;
    }

    public com.sun.identity.saml2.jaxb.xmlsig.CanonicalizationMethodType getCanonicalizationMethod() {
        return _CanonicalizationMethod;
    }

    public void setCanonicalizationMethod(com.sun.identity.saml2.jaxb.xmlsig.CanonicalizationMethodType value) {
        _CanonicalizationMethod = value;
    }

    public com.sun.identity.saml2.jaxb.assertion.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sun.identity.saml2.jaxb.assertion.impl.runtime.UnmarshallingContext context) {
        return new com.sun.identity.saml2.jaxb.xmlsig.impl.SignedInfoTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sun.identity.saml2.jaxb.assertion.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = ((_Reference == null)? 0 :_Reference.size());
        if (_CanonicalizationMethod instanceof javax.xml.bind.Element) {
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _CanonicalizationMethod), "CanonicalizationMethod");
        } else {
            context.startElement("http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethod");
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _CanonicalizationMethod), "CanonicalizationMethod");
            context.endNamespaceDecls();
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _CanonicalizationMethod), "CanonicalizationMethod");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _CanonicalizationMethod), "CanonicalizationMethod");
            context.endElement();
        }
        if (_SignatureMethod instanceof javax.xml.bind.Element) {
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _SignatureMethod), "SignatureMethod");
        } else {
            context.startElement("http://www.w3.org/2000/09/xmldsig#", "SignatureMethod");
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _SignatureMethod), "SignatureMethod");
            context.endNamespaceDecls();
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _SignatureMethod), "SignatureMethod");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _SignatureMethod), "SignatureMethod");
            context.endElement();
        }
        while (idx2 != len2) {
            if (_Reference.get(idx2) instanceof javax.xml.bind.Element) {
                context.childAsBody(((com.sun.xml.bind.JAXBObject) _Reference.get(idx2 ++)), "Reference");
            } else {
                context.startElement("http://www.w3.org/2000/09/xmldsig#", "Reference");
                int idx_4 = idx2;
                context.childAsURIs(((com.sun.xml.bind.JAXBObject) _Reference.get(idx_4 ++)), "Reference");
                context.endNamespaceDecls();
                int idx_5 = idx2;
                context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _Reference.get(idx_5 ++)), "Reference");
                context.endAttributes();
                context.childAsBody(((com.sun.xml.bind.JAXBObject) _Reference.get(idx2 ++)), "Reference");
                context.endElement();
            }
        }
    }

    public void serializeAttributes(com.sun.identity.saml2.jaxb.assertion.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = ((_Reference == null)? 0 :_Reference.size());
        if (_Id!= null) {
            context.startAttribute("", "Id");
            try {
                context.text(context.onID(this, ((java.lang.String) _Id)), "Id");
            } catch (java.lang.Exception e) {
                com.sun.identity.saml2.jaxb.assertion.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        if (_CanonicalizationMethod instanceof javax.xml.bind.Element) {
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _CanonicalizationMethod), "CanonicalizationMethod");
        }
        if (_SignatureMethod instanceof javax.xml.bind.Element) {
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _SignatureMethod), "SignatureMethod");
        }
        while (idx2 != len2) {
            if (_Reference.get(idx2) instanceof javax.xml.bind.Element) {
                context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _Reference.get(idx2 ++)), "Reference");
            } else {
                idx2 += 1;
            }
        }
    }

    public void serializeURIs(com.sun.identity.saml2.jaxb.assertion.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = ((_Reference == null)? 0 :_Reference.size());
        if (_CanonicalizationMethod instanceof javax.xml.bind.Element) {
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _CanonicalizationMethod), "CanonicalizationMethod");
        }
        if (_SignatureMethod instanceof javax.xml.bind.Element) {
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _SignatureMethod), "SignatureMethod");
        }
        while (idx2 != len2) {
            if (_Reference.get(idx2) instanceof javax.xml.bind.Element) {
                context.childAsURIs(((com.sun.xml.bind.JAXBObject) _Reference.get(idx2 ++)), "Reference");
            } else {
                idx2 += 1;
            }
        }
    }

    public java.lang.String ____jaxb____getId() {
        return ((java.lang.String) _Id);
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.sun.identity.saml2.jaxb.xmlsig.SignedInfoType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\u001dcom.sun.msv.grammar."
+"ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000\'com.sun.msv.grammar.trex.Ele"
+"mentPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/Na"
+"meClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aigno"
+"reUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000sq\u0000~\u0000\bpps"
+"r\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.ms"
+"v.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003sr\u0000\u0011java.lang."
+"Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000 com.sun.msv.grammar.Attrib"
+"uteExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u000bxq\u0000~\u0000\u0003q\u0000~\u0000\u0013psr\u0000"
+"2com.sun.msv.grammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\u0012\u0001q\u0000~\u0000\u0017sr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com"
+".sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~"
+"\u0000\u0003q\u0000~\u0000\u0018q\u0000~\u0000\u001dsr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq\u0000~\u0000\u001fxq\u0000~\u0000"
+"\u001at\u0000@com.sun.identity.saml2.jaxb.xmlsig.CanonicalizationMetho"
+"dElementt\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\n"
+"pp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\npp\u0000sq\u0000~\u0000\bppsq\u0000~\u0000\u000fq\u0000~\u0000\u0013psq\u0000~\u0000\u0014q\u0000~\u0000\u0013pq\u0000~\u0000\u0017q\u0000~"
+"\u0000\u001bq\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u0000=com.sun.identity.saml2.jaxb.xmlsig.Canonical"
+"izationMethodTypeq\u0000~\u0000\"sq\u0000~\u0000\bppsq\u0000~\u0000\u0014q\u0000~\u0000\u0013psr\u0000\u001bcom.sun.msv.gr"
+"ammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Dataty"
+"pe;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~"
+"\u0000\u0003ppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com"
+".sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.su"
+"n.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.da"
+"tatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000\u001fL\u0000\bt"
+"ypeNameq\u0000~\u0000\u001fL\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteS"
+"paceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0005QNames"
+"r\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expression$NullSetExpression"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u0013psr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f"
+"\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001fL\u0000\fnamespaceURIq\u0000~\u0000\u001fxpq\u0000~\u00008q\u0000~\u00007sq\u0000~\u0000\u001e"
+"t\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-instanceq\u0000~\u0000\u001dsq\u0000~"
+"\u0000\u001et\u0000\u0016CanonicalizationMethodt\u0000\"http://www.w3.org/2000/09/xmld"
+"sig#sq\u0000~\u0000\bppsq\u0000~\u0000\npp\u0000sq\u0000~\u0000\bppsq\u0000~\u0000\u000fq\u0000~\u0000\u0013psq\u0000~\u0000\u0014q\u0000~\u0000\u0013pq\u0000~\u0000\u0017q\u0000"
+"~\u0000\u001bq\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u00009com.sun.identity.saml2.jaxb.xmlsig.Signatur"
+"eMethodElementq\u0000~\u0000\"sq\u0000~\u0000\npp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\npp\u0000sq\u0000~\u0000\bppsq\u0000~\u0000\u000fq"
+"\u0000~\u0000\u0013psq\u0000~\u0000\u0014q\u0000~\u0000\u0013pq\u0000~\u0000\u0017q\u0000~\u0000\u001bq\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u00006com.sun.identity.sa"
+"ml2.jaxb.xmlsig.SignatureMethodTypeq\u0000~\u0000\"sq\u0000~\u0000\bppsq\u0000~\u0000\u0014q\u0000~\u0000\u0013p"
+"q\u0000~\u00000q\u0000~\u0000@q\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u0000\u000fSignatureMethodq\u0000~\u0000Esq\u0000~\u0000\u000fppsq\u0000~\u0000\bpp"
+"sq\u0000~\u0000\npp\u0000sq\u0000~\u0000\bppsq\u0000~\u0000\u000fq\u0000~\u0000\u0013psq\u0000~\u0000\u0014q\u0000~\u0000\u0013pq\u0000~\u0000\u0017q\u0000~\u0000\u001bq\u0000~\u0000\u001dsq\u0000~"
+"\u0000\u001et\u00003com.sun.identity.saml2.jaxb.xmlsig.ReferenceElementq\u0000~\u0000"
+"\"sq\u0000~\u0000\npp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\npp\u0000sq\u0000~\u0000\bppsq\u0000~\u0000\u000fq\u0000~\u0000\u0013psq\u0000~\u0000\u0014q\u0000~\u0000\u0013pq"
+"\u0000~\u0000\u0017q\u0000~\u0000\u001bq\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u00000com.sun.identity.saml2.jaxb.xmlsig.Re"
+"ferenceTypeq\u0000~\u0000\"sq\u0000~\u0000\bppsq\u0000~\u0000\u0014q\u0000~\u0000\u0013pq\u0000~\u00000q\u0000~\u0000@q\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u0000\t"
+"Referenceq\u0000~\u0000Esq\u0000~\u0000\bppsq\u0000~\u0000\u0014q\u0000~\u0000\u0013psq\u0000~\u0000-ppsr\u0000\u001fcom.sun.msv.da"
+"tatype.xsd.IDType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.Ncn"
+"ameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\"com.sun.msv.datatype.xsd.TokenType\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\r"
+"isAlwaysValidxq\u0000~\u00002q\u0000~\u00007t\u0000\u0002IDq\u0000~\u0000;\u0000q\u0000~\u0000=sq\u0000~\u0000>q\u0000~\u0000uq\u0000~\u00007sq\u0000~"
+"\u0000\u001et\u0000\u0002Idt\u0000\u0000q\u0000~\u0000\u001dsr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$Closed"
+"Hash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N"
+"\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/gram"
+"mar/ExpressionPool;xp\u0000\u0000\u0000\u001a\u0001pq\u0000~\u0000\u000eq\u0000~\u0000&q\u0000~\u0000Hq\u0000~\u0000Pq\u0000~\u0000\\q\u0000~\u0000dq\u0000~"
+"\u0000Yq\u0000~\u0000\u0006q\u0000~\u0000\u0005q\u0000~\u0000$q\u0000~\u0000Nq\u0000~\u0000bq\u0000~\u0000\u0011q\u0000~\u0000\'q\u0000~\u0000Iq\u0000~\u0000Qq\u0000~\u0000]q\u0000~\u0000eq\u0000~"
+"\u0000mq\u0000~\u0000+q\u0000~\u0000Uq\u0000~\u0000iq\u0000~\u0000\tq\u0000~\u0000Fq\u0000~\u0000Zq\u0000~\u0000\u0007x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sun.identity.saml2.jaxb.assertion.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sun.identity.saml2.jaxb.assertion.impl.runtime.UnmarshallingContext context) {
            super(context, "-------------");
        }

        protected Unmarshaller(com.sun.identity.saml2.jaxb.assertion.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sun.identity.saml2.jaxb.xmlsig.impl.SignedInfoTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        if (("Reference" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            _getReference().add(((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceElementImpl) spawnChildFromEnterElement((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceElementImpl.class), 10, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("Reference" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 11;
                            return ;
                        }
                        break;
                    case  4 :
                        attIdx = context.getAttribute("", "Algorithm");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                    case  11 :
                        attIdx = context.getAttribute("", "Id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        attIdx = context.getAttribute("", "Type");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        attIdx = context.getAttribute("", "URI");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        if (("Transforms" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            _getReference().add(((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl) spawnChildFromEnterElement((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl.class), 12, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("Transforms" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            _getReference().add(((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl) spawnChildFromEnterElement((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl.class), 12, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("DigestMethod" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            _getReference().add(((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl) spawnChildFromEnterElement((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl.class), 12, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("DigestMethod" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            _getReference().add(((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl) spawnChildFromEnterElement((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl.class), 12, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        break;
                    case  3 :
                        if (("CanonicalizationMethod" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            _CanonicalizationMethod = ((com.sun.identity.saml2.jaxb.xmlsig.impl.CanonicalizationMethodElementImpl) spawnChildFromEnterElement((com.sun.identity.saml2.jaxb.xmlsig.impl.CanonicalizationMethodElementImpl.class), 6, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("CanonicalizationMethod" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "Id");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText1(v);
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  10 :
                        if (("Reference" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            _getReference().add(((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceElementImpl) spawnChildFromEnterElement((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceElementImpl.class), 10, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("Reference" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 11;
                            return ;
                        }
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  6 :
                        if (("SignatureMethod" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            _SignatureMethod = ((com.sun.identity.saml2.jaxb.xmlsig.impl.SignatureMethodElementImpl) spawnChildFromEnterElement((com.sun.identity.saml2.jaxb.xmlsig.impl.SignatureMethodElementImpl.class), 9, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("SignatureMethod" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        break;
                    case  7 :
                        attIdx = context.getAttribute("", "Algorithm");
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

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Id = context.addToIdTable(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  5 :
                        if (("CanonicalizationMethod" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  4 :
                        attIdx = context.getAttribute("", "Algorithm");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  11 :
                        attIdx = context.getAttribute("", "Id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        attIdx = context.getAttribute("", "Type");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        attIdx = context.getAttribute("", "URI");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  12 :
                        if (("Reference" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.popAttributes();
                            state = 10;
                            return ;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "Id");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText1(v);
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  8 :
                        if (("SignatureMethod" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  10 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  7 :
                        attIdx = context.getAttribute("", "Algorithm");
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
                    case  4 :
                        if (("Algorithm" == ___local)&&("" == ___uri)) {
                            _CanonicalizationMethod = ((com.sun.identity.saml2.jaxb.xmlsig.impl.CanonicalizationMethodTypeImpl) spawnChildFromEnterAttribute((com.sun.identity.saml2.jaxb.xmlsig.impl.CanonicalizationMethodTypeImpl.class), 5, ___uri, ___local, ___qname));
                            return ;
                        }
                        break;
                    case  11 :
                        if (("Id" == ___local)&&("" == ___uri)) {
                            _getReference().add(((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl) spawnChildFromEnterAttribute((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl.class), 12, ___uri, ___local, ___qname)));
                            return ;
                        }
                        if (("Type" == ___local)&&("" == ___uri)) {
                            _getReference().add(((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl) spawnChildFromEnterAttribute((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl.class), 12, ___uri, ___local, ___qname)));
                            return ;
                        }
                        if (("URI" == ___local)&&("" == ___uri)) {
                            _getReference().add(((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl) spawnChildFromEnterAttribute((com.sun.identity.saml2.jaxb.xmlsig.impl.ReferenceTypeImpl.class), 12, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
                    case  0 :
                        if (("Id" == ___local)&&("" == ___uri)) {
                            state = 1;
                            return ;
                        }
                        state = 3;
                        continue outer;
                    case  10 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  7 :
                        if (("Algorithm" == ___local)&&("" == ___uri)) {
                            _SignatureMethod = ((com.sun.identity.saml2.jaxb.xmlsig.impl.SignatureMethodTypeImpl) spawnChildFromEnterAttribute((com.sun.identity.saml2.jaxb.xmlsig.impl.SignatureMethodTypeImpl.class), 8, ___uri, ___local, ___qname));
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
                    case  4 :
                        attIdx = context.getAttribute("", "Algorithm");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  11 :
                        attIdx = context.getAttribute("", "Id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        attIdx = context.getAttribute("", "Type");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        attIdx = context.getAttribute("", "URI");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "Id");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText1(v);
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  2 :
                        if (("Id" == ___local)&&("" == ___uri)) {
                            state = 3;
                            return ;
                        }
                        break;
                    case  10 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  7 :
                        attIdx = context.getAttribute("", "Algorithm");
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
                        case  4 :
                            attIdx = context.getAttribute("", "Algorithm");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  11 :
                            attIdx = context.getAttribute("", "Id");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            attIdx = context.getAttribute("", "Type");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            attIdx = context.getAttribute("", "URI");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  0 :
                            attIdx = context.getAttribute("", "Id");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 3;
                                eatText1(v);
                                continue outer;
                            }
                            state = 3;
                            continue outer;
                        case  10 :
                            revertToParentFromText(value);
                            return ;
                        case  1 :
                            state = 2;
                            eatText1(value);
                            return ;
                        case  7 :
                            attIdx = context.getAttribute("", "Algorithm");
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
