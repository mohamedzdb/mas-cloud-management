/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discover;

import behaviours.RequestRecvBehaviour;
import behaviours.SearchingVmBehavior;
import dao.RequestDao;
import dao.VMDao;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import model.RequestModel;
import model.VmModel;

/**
 *
 * @author ZT
 */
public class DiscoverAgent extends Agent {
    public String reqId;
    public RequestDao reqDao;
    public VMDao vmDao = VMDao.getInstance();
    @Override
    protected void setup() {
        System.out.println("hello I'm discover agent");
        SequentialBehaviour sequentialBehaviour = new SequentialBehaviour();
        sequentialBehaviour.addSubBehaviour(new RequestRecvBehaviour());
        sequentialBehaviour.addSubBehaviour(new SearchingVmBehavior());
        addBehaviour(sequentialBehaviour);
    }

        
    
}
