package gasmas.ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: Notification
* @author ontology bean generator
* @version 2013/03/10, 21:16:57
*/
public class Notification implements AgentAction {

   /**
* Protege name: reason
   */
   private String reason;
   public void setReason(String value) { 
    this.reason=value;
   }
   public String getReason() {
     return this.reason;
   }

   /**
* Protege name: notificationObject
   */
   private Object notificationObject;
   public void setNotificationObject(Object value) { 
    this.notificationObject=value;
   }
   public Object getNotificationObject() {
     return this.notificationObject;
   }

}