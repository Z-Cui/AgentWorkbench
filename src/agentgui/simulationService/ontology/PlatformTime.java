package agentgui.simulationService.ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: PlatformTime
* @author ontology bean generator
* @version 2012/07/24, 12:09:17
*/
public class PlatformTime implements Concept {

   /**
* Protege name: TimeStampAsString
   */
   private String timeStampAsString;
   public void setTimeStampAsString(String value) { 
    this.timeStampAsString=value;
   }
   public String getTimeStampAsString() {
     return this.timeStampAsString;
   }

}
