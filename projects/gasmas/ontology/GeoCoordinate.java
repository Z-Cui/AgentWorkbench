package gasmas.ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
   * A set of parameters describing the physical properties of a PropagationPoint.
* Protege name: GeoCoordinate
* @author ontology bean generator
* @version 2012/06/26, 14:32:24
*/
public class GeoCoordinate implements Concept {

   /**
   * The geographical x coordinate of the propagation point.
* Protege name: geoX
   */
   private float geoX;
   public void setGeoX(float value) { 
    this.geoX=value;
   }
   public float getGeoX() {
     return this.geoX;
   }

   /**
   * The geographical z coordinate of the propagation point.
* Protege name: geoZ
   */
   private float geoZ;
   public void setGeoZ(float value) { 
    this.geoZ=value;
   }
   public float getGeoZ() {
     return this.geoZ;
   }

   /**
   * The geographical y coordinate of the propagation point.
* Protege name: geoY
   */
   private float geoY;
   public void setGeoY(float value) { 
    this.geoY=value;
   }
   public float getGeoY() {
     return this.geoY;
   }

}