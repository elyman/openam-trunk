//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-b27-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.11 at 10:33:54 AM PDT 
//


package com.sun.identity.liberty.ws.common.jaxb.ps.impl;

public class RequestAuthnContextTypeImpl implements com.sun.identity.liberty.ws.common.jaxb.ps.RequestAuthnContextType, com.sun.xml.bind.JAXBObject, com.sun.identity.federation.jaxb.entityconfig.impl.runtime.UnmarshallableObject, com.sun.identity.federation.jaxb.entityconfig.impl.runtime.XMLSerializable, com.sun.identity.federation.jaxb.entityconfig.impl.runtime.ValidatableObject
{

    protected com.sun.xml.bind.util.ListImpl _AuthnContextStatementRef;
    protected com.sun.xml.bind.util.ListImpl _AuthnContextClassRef;
    protected java.lang.String _AuthnContextComparison;
    public final static java.lang.Class version = (com.sun.identity.liberty.ws.common.jaxb.ps.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sun.identity.liberty.ws.common.jaxb.ps.RequestAuthnContextType.class);
    }

    protected com.sun.xml.bind.util.ListImpl _getAuthnContextStatementRef() {
        if (_AuthnContextStatementRef == null) {
            _AuthnContextStatementRef = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        }
        return _AuthnContextStatementRef;
    }

    public java.util.List getAuthnContextStatementRef() {
        return _getAuthnContextStatementRef();
    }

    protected com.sun.xml.bind.util.ListImpl _getAuthnContextClassRef() {
        if (_AuthnContextClassRef == null) {
            _AuthnContextClassRef = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        }
        return _AuthnContextClassRef;
    }

    public java.util.List getAuthnContextClassRef() {
        return _getAuthnContextClassRef();
    }

    public java.lang.String getAuthnContextComparison() {
        return _AuthnContextComparison;
    }

    public void setAuthnContextComparison(java.lang.String value) {
        _AuthnContextComparison = value;
    }

    public com.sun.identity.federation.jaxb.entityconfig.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.UnmarshallingContext context) {
        return new com.sun.identity.liberty.ws.common.jaxb.ps.impl.RequestAuthnContextTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx1 = 0;
        final int len1 = ((_AuthnContextStatementRef == null)? 0 :_AuthnContextStatementRef.size());
        int idx2 = 0;
        final int len2 = ((_AuthnContextClassRef == null)? 0 :_AuthnContextClassRef.size());
        if ((((_AuthnContextClassRef == null)? 0 :_AuthnContextClassRef.size())>= 1)&&(((_AuthnContextStatementRef == null)? 0 :_AuthnContextStatementRef.size()) == 0)) {
            while (idx2 != len2) {
                context.startElement("urn:liberty:iff:2003-08", "AuthnContextClassRef");
                int idx_0 = idx2;
                try {
                    idx_0 += 1;
                } catch (java.lang.Exception e) {
                    com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
                context.endNamespaceDecls();
                int idx_1 = idx2;
                try {
                    idx_1 += 1;
                } catch (java.lang.Exception e) {
                    com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
                context.endAttributes();
                try {
                    context.text(((java.lang.String) _AuthnContextClassRef.get(idx2 ++)), "AuthnContextClassRef");
                } catch (java.lang.Exception e) {
                    com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
                context.endElement();
            }
        } else {
            if ((((_AuthnContextClassRef == null)? 0 :_AuthnContextClassRef.size()) == 0)&&(((_AuthnContextStatementRef == null)? 0 :_AuthnContextStatementRef.size())>= 1)) {
                while (idx1 != len1) {
                    context.startElement("urn:liberty:iff:2003-08", "AuthnContextStatementRef");
                    int idx_2 = idx1;
                    try {
                        idx_2 += 1;
                    } catch (java.lang.Exception e) {
                        com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
                    }
                    context.endNamespaceDecls();
                    int idx_3 = idx1;
                    try {
                        idx_3 += 1;
                    } catch (java.lang.Exception e) {
                        com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
                    }
                    context.endAttributes();
                    try {
                        context.text(((java.lang.String) _AuthnContextStatementRef.get(idx1 ++)), "AuthnContextStatementRef");
                    } catch (java.lang.Exception e) {
                        com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
                    }
                    context.endElement();
                }
            }
        }
        if (_AuthnContextComparison!= null) {
            context.startElement("urn:liberty:iff:2003-08", "AuthnContextComparison");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _AuthnContextComparison), "AuthnContextComparison");
            } catch (java.lang.Exception e) {
                com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
    }

    public void serializeAttributes(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx1 = 0;
        final int len1 = ((_AuthnContextStatementRef == null)? 0 :_AuthnContextStatementRef.size());
        int idx2 = 0;
        final int len2 = ((_AuthnContextClassRef == null)? 0 :_AuthnContextClassRef.size());
        if ((((_AuthnContextClassRef == null)? 0 :_AuthnContextClassRef.size())>= 1)&&(((_AuthnContextStatementRef == null)? 0 :_AuthnContextStatementRef.size()) == 0)) {
            while (idx2 != len2) {
                try {
                    idx2 += 1;
                } catch (java.lang.Exception e) {
                    com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
            }
        } else {
            if ((((_AuthnContextClassRef == null)? 0 :_AuthnContextClassRef.size()) == 0)&&(((_AuthnContextStatementRef == null)? 0 :_AuthnContextStatementRef.size())>= 1)) {
                while (idx1 != len1) {
                    try {
                        idx1 += 1;
                    } catch (java.lang.Exception e) {
                        com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
                    }
                }
            }
        }
    }

    public void serializeURIs(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx1 = 0;
        final int len1 = ((_AuthnContextStatementRef == null)? 0 :_AuthnContextStatementRef.size());
        int idx2 = 0;
        final int len2 = ((_AuthnContextClassRef == null)? 0 :_AuthnContextClassRef.size());
        if ((((_AuthnContextClassRef == null)? 0 :_AuthnContextClassRef.size())>= 1)&&(((_AuthnContextStatementRef == null)? 0 :_AuthnContextStatementRef.size()) == 0)) {
            while (idx2 != len2) {
                try {
                    idx2 += 1;
                } catch (java.lang.Exception e) {
                    com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
            }
        } else {
            if ((((_AuthnContextClassRef == null)? 0 :_AuthnContextClassRef.size()) == 0)&&(((_AuthnContextStatementRef == null)? 0 :_AuthnContextStatementRef.size())>= 1)) {
                while (idx1 != len1) {
                    try {
                        idx1 += 1;
                    } catch (java.lang.Exception e) {
                        com.sun.identity.federation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
                    }
                }
            }
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.sun.identity.liberty.ws.common.jaxb.ps.RequestAuthnContextType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000x"
+"r\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003p"
+"psr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tna"
+"meClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.gra"
+"mmar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fco"
+"ntentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000sq\u0000~\u0000\u0000ppsr\u0000\u001bcom.sun.msv.grammar.Data"
+"Exp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exce"
+"ptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003sr\u0000\u0011java"
+".lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000#com.sun.msv.datatype"
+".xsd.AnyURIType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.Built"
+"inAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.Concrete"
+"Type\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000"
+"\u001aL\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcess"
+"or;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006anyURIsr\u00005com.sun"
+".msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000"
+",com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr"
+"\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u0015psr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlo"
+"calNameq\u0000~\u0000\u001aL\u0000\fnamespaceURIq\u0000~\u0000\u001axpq\u0000~\u0000\u001eq\u0000~\u0000\u001dsq\u0000~\u0000\u0006ppsr\u0000 com."
+"sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameCla"
+"ssq\u0000~\u0000\fxq\u0000~\u0000\u0003q\u0000~\u0000\u0015psq\u0000~\u0000\u0010q\u0000~\u0000\u0015psr\u0000\"com.sun.msv.datatype.xsd."
+"QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0017q\u0000~\u0000\u001dt\u0000\u0005QNameq\u0000~\u0000!q\u0000~\u0000#sq\u0000~\u0000$q\u0000~\u0000,"
+"q\u0000~\u0000\u001dsr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlo"
+"calNameq\u0000~\u0000\u001aL\u0000\fnamespaceURIq\u0000~\u0000\u001axr\u0000\u001dcom.sun.msv.grammar.Name"
+"Class\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema"
+"-instancesr\u00000com.sun.msv.grammar.Expression$EpsilonExpressio"
+"n\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\u0014\u0001q\u0000~\u00004sq\u0000~\u0000.t\u0000\u0014AuthnContextClassReft"
+"\u0000\u0017urn:liberty:iff:2003-08sq\u0000~\u0000\bppsq\u0000~\u0000\u000bpp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0013sq\u0000~\u0000"
+"\u0006ppsq\u0000~\u0000\'q\u0000~\u0000\u0015pq\u0000~\u0000)q\u0000~\u00000q\u0000~\u00004sq\u0000~\u0000.t\u0000\u0018AuthnContextStatement"
+"Refq\u0000~\u00008sq\u0000~\u0000\u0006ppsq\u0000~\u0000\u000bq\u0000~\u0000\u0015p\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0010ppsr\u0000)com.sun.msv"
+".datatype.xsd.EnumerationFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0006valuest\u0000\u000fLjava/u"
+"til/Set;xr\u00009com.sun.msv.datatype.xsd.DataTypeWithValueConstr"
+"aintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.DataTypeWit"
+"hFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFlagL\u0000\bbase"
+"Typet\u0000)Lcom/sun/msv/datatype/xsd/XSDatatypeImpl;L\u0000\fconcreteT"
+"ypet\u0000\'Lcom/sun/msv/datatype/xsd/ConcreteType;L\u0000\tfacetNameq\u0000~"
+"\u0000\u001axq\u0000~\u0000\u0019q\u0000~\u00008t\u0000\u001aAuthnContextComparisonTypesr\u00005com.sun.msv.da"
+"tatype.xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000 \u0000\u0000sr"
+"\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysV"
+"alidxq\u0000~\u0000\u0017q\u0000~\u0000\u001dt\u0000\u0006stringq\u0000~\u0000M\u0001q\u0000~\u0000Ot\u0000\u000benumerationsr\u0000\u0011java.ut"
+"il.HashSet\u00baD\u0085\u0095\u0096\u00b8\u00b74\u0003\u0000\u0000xpw\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0003t\u0000\u0007minimumt\u0000\u0006bettert\u0000\u0005e"
+"xactxq\u0000~\u0000#sq\u0000~\u0000$q\u0000~\u0000Kq\u0000~\u00008sq\u0000~\u0000\u0006ppsq\u0000~\u0000\'q\u0000~\u0000\u0015pq\u0000~\u0000)q\u0000~\u00000q\u0000~\u0000"
+"4sq\u0000~\u0000.t\u0000\u0016AuthnContextComparisonq\u0000~\u00008q\u0000~\u00004sr\u0000\"com.sun.msv.gr"
+"ammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/gr"
+"ammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.Ex"
+"pressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000"
+"\u0006parentt\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\u000b\u0001pq\u0000~\u0000\u0007q"
+"\u0000~\u0000\u000fq\u0000~\u0000;q\u0000~\u0000@q\u0000~\u0000\u0005q\u0000~\u0000\nq\u0000~\u00009q\u0000~\u0000Bq\u0000~\u0000&q\u0000~\u0000<q\u0000~\u0000Xx"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sun.identity.federation.jaxb.entityconfig.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.UnmarshallingContext context) {
            super(context, "---------");
        }

        protected Unmarshaller(com.sun.identity.federation.jaxb.entityconfig.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sun.identity.liberty.ws.common.jaxb.ps.impl.RequestAuthnContextTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("AuthnContextClassRef" == ___local)&&("urn:liberty:iff:2003-08" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        if (("AuthnContextStatementRef" == ___local)&&("urn:liberty:iff:2003-08" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                    case  3 :
                        if (("AuthnContextClassRef" == ___local)&&("urn:liberty:iff:2003-08" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        if (("AuthnContextStatementRef" == ___local)&&("urn:liberty:iff:2003-08" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        if (("AuthnContextComparison" == ___local)&&("urn:liberty:iff:2003-08" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        state = 6;
                        continue outer;
                    case  6 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
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
                    case  5 :
                        if (("AuthnContextComparison" == ___local)&&("urn:liberty:iff:2003-08" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  8 :
                        if (("AuthnContextClassRef" == ___local)&&("urn:liberty:iff:2003-08" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  2 :
                        if (("AuthnContextStatementRef" == ___local)&&("urn:liberty:iff:2003-08" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  6 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
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
                        state = 6;
                        continue outer;
                    case  6 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
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
                        state = 6;
                        continue outer;
                    case  6 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
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
                            state = 5;
                            eatText1(value);
                            return ;
                        case  7 :
                            state = 8;
                            eatText2(value);
                            return ;
                        case  3 :
                            state = 6;
                            continue outer;
                        case  1 :
                            state = 2;
                            eatText3(value);
                            return ;
                        case  6 :
                            revertToParentFromText(value);
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _AuthnContextComparison = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _getAuthnContextClassRef().add(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _getAuthnContextStatementRef().add(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
