package contmas.ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: RejectLoadOffer
* @author ontology bean generator
* @version 2009/10/20, 22:25:25
*/
public class RejectLoadOffer implements AgentAction {

   /**
* Protege name: load_offer
   */
   private TransportOrderChain load_offer;
   public void setLoad_offer(TransportOrderChain value) { 
    this.load_offer=value;
   }
   public TransportOrderChain getLoad_offer() {
     return this.load_offer;
   }

}
