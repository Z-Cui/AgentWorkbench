//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.01 at 12:38:15 AM MESZ 
//


package gasmas.transfer.zib.cs;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class GasConnection
    extends JAXBElement<GasConnectionType>
{

    protected final static QName NAME = new QName("http://mathematik.tu-darmstadt.de/opt/gas/XMLSchema", "connection");

    public GasConnection(GasConnectionType value) {
        super(NAME, ((Class) GasConnectionType.class), null, value);
    }

    public GasConnection() {
        super(NAME, ((Class) GasConnectionType.class), null, null);
    }

}