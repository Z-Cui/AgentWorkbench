package gasmas.ontology;

import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: Innode
* @author ontology bean generator
* @version 2013/03/10, 21:16:57
*/
public class Innode extends GridComponent{ 

   /**
* Protege name: height
   */
   private ValueType height;
   public void setHeight(ValueType value) { 
    this.height=value;
   }
   public ValueType getHeight() {
     return this.height;
   }

   /**
* Protege name: pressureMax
   */
   private ValueType pressureMax;
   public void setPressureMax(ValueType value) { 
    this.pressureMax=value;
   }
   public ValueType getPressureMax() {
     return this.pressureMax;
   }

   /**
* Protege name: pressureMin
   */
   private ValueType pressureMin;
   public void setPressureMin(ValueType value) { 
    this.pressureMin=value;
   }
   public ValueType getPressureMin() {
     return this.pressureMin;
   }

   /**
* Protege name: geoCoordinate
   */
   private GeoCoordinate geoCoordinate;
   public void setGeoCoordinate(GeoCoordinate value) { 
    this.geoCoordinate=value;
   }
   public GeoCoordinate getGeoCoordinate() {
     return this.geoCoordinate;
   }

}