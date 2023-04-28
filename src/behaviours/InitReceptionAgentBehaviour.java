/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;
import model.RequestModel;
import reception.ReceptionAgent;

/**
 *
 * @author ZT
 */
public class InitReceptionAgentBehaviour extends OneShotBehaviour{

    @Override
    public void action() {
        ReceptionAgent recep = (ReceptionAgent) myAgent ;
        String id = recep.reqDao.generateId();
        recep.reqId = id;
        RequestModel req = new RequestModel(id,2698, 2000,1028,100,1, "XEN");
        try {
            
            recep.reqDao.addReq(req);
        } catch (TransformerException ex) {
            Logger.getLogger(ReceptionAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
                msg.addReceiver(new AID("discover",AID.ISLOCALNAME));
                msg.setContent(req.getVmidstr());
                myAgent.send(msg);
    }
    
}
