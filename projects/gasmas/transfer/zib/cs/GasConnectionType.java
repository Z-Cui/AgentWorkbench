//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.01 at 12:38:15 AM MESZ 
//


package gasmas.transfer.zib.cs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 *  connectionType defines any (abstract) way to connect two nodes in the
 *         network with active or passive network elements. 
 * 
 * <p>Java class for connectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="connectionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}connectionType">
 *       &lt;sequence>
 *         &lt;element name="flowMin" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}flowType"/>
 *         &lt;element name="flowMax" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}flowType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "connectionType", namespace = "http://mathematik.tu-darmstadt.de/opt/gas/XMLSchema", propOrder = {
    "flowMin",
    "flowMax"
})
@XmlSeeAlso({
    ControlValveType.class,
    ShortPipeType.class,
    ResistorType.class,
    PipeType.class,
    ValveType.class,
    CompressorStationType.class
})
public class GasConnectionType
    extends ConnectionType
{

    @XmlElement(required = true)
    protected FlowType flowMin;
    @XmlElement(required = true)
    protected FlowType flowMax;

    /**
     * Gets the value of the flowMin property.
     * 
     * @return
     *     possible object is
     *     {@link FlowType }
     *     
     */
    public FlowType getFlowMin() {
        return flowMin;
    }

    /**
     * Sets the value of the flowMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlowType }
     *     
     */
    public void setFlowMin(FlowType value) {
        this.flowMin = value;
    }

    /**
     * Gets the value of the flowMax property.
     * 
     * @return
     *     possible object is
     *     {@link FlowType }
     *     
     */
    public FlowType getFlowMax() {
        return flowMax;
    }

    /**
     * Sets the value of the flowMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlowType }
     *     
     */
    public void setFlowMax(FlowType value) {
        this.flowMax = value;
    }

}