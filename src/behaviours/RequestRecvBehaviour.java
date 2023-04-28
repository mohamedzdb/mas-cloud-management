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

/**
 *
 * @author ZT
 */
public class RequestRecvBehaviour extends Behaviour{
    boolean isRecv;
    @Override
    public void action() {
        DiscoverAgent discover = (DiscoverAgent) myAgent;
        MessageTemplate mt = MessageTemplate.MatchSender(new AID("recption",AID.ISLOCALNAME));
                ACLMessage msg = myAgent.receive(mt);
                if(msg != null){
                    
                    discover.reqId = msg.getContent(); 
                    System.out.println("Request id:"+discover.reqId+" .....");
                    isRecv = true;
                }else{
                    block();
                }
    }

    @Override
    public boolean done() {
        return isRecv;
    }
    
}
