package gasmas.ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
   * A set of parameters describing the current gas flow at a PropagationPoint.
* Protege name: FlowParameters
* @author ontology bean generator
* @version 2013/03/10, 21:16:57
*/
public class FlowParameters implements Concept {

   /**
* Protege name: temperature
   */
   private float temperature;
   public void setTemperature(float value) { 
    this.temperature=value;
   }
   public float getTemperature() {
     return this.temperature;
   }

   /**
* Protege name: flow
   */
   private float flow;
   public void setFlow(float value) { 
    this.flow=value;
   }
   public float getFlow() {
     return this.flow;
   }

   /**
* Protege name: pressure
   */
   private float pressure;
   public void setPressure(float value) { 
    this.pressure=value;
   }
   public float getPressure() {
     return this.pressure;
   }

   /**
* Protege name: reynoldsNumber
   */
   private int reynoldsNumber;
   public void setReynoldsNumber(int value) { 
    this.reynoldsNumber=value;
   }
   public int getReynoldsNumber() {
     return this.reynoldsNumber;
   }

   /**
* Protege name: massFlow
   */
   private float massFlow;
   public void setMassFlow(float value) { 
    this.massFlow=value;
   }
   public float getMassFlow() {
     return this.massFlow;
   }

   /**
* Protege name: fluidVelocity
   */
   private float fluidVelocity;
   public void setFluidVelocity(float value) { 
    this.fluidVelocity=value;
   }
   public float getFluidVelocity() {
     return this.fluidVelocity;
   }

}