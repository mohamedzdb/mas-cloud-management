/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours;

import discover.DiscoverAgent;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import reception.ReceptionAgent;

/**
 *
 * @author ZT
 */
public class VmRecvBehaviour extends Behaviour {
    boolean isFinish;
    @Override
    public void action() {
        ReceptionAgent recep = (ReceptionAgent) myAgent ;
        MessageTemplate mt = MessageTemplate.MatchSender(new AID("discover",AID.ISLOCALNAME));
                ACLMessage msg = myAgent.receive(mt);
                if(msg != null){
                    
                    recep.vmId = msg.getContent(); 
                    System.out.println("VM id:"+recep.vmId+" .....");
                    isFinish = true;
                }else{
                    block();
                }
    }

    @Override
    public boolean done() {
        return isFinish;
    }
    
}
