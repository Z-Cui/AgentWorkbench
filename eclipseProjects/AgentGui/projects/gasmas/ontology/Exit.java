package gasmas.ontology;

import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: Exit
* @author ontology bean generator
* @version 2013/03/10, 21:16:57
*/
public class Exit extends Innode{ 

   /**
* Protege name: flowMin
   */
   private ValueType flowMin;
   public void setFlowMin(ValueType value) { 
    this.flowMin=value;
   }
   public ValueType getFlowMin() {
     return this.flowMin;
   }

   /**
* Protege name: flowMax
   */
   private ValueType flowMax;
   public void setFlowMax(ValueType value) { 
    this.flowMax=value;
   }
   public ValueType getFlowMax() {
     return this.flowMax;
   }

}