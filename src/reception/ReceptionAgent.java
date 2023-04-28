/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reception;

import behaviours.CalcStatisticBehaviours;
import behaviours.InitReceptionAgentBehaviour;
import behaviours.VmRecvBehaviour;
import dao.RequestDao;
import dao.VMDao;
import gui.ClientGUI;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;
import model.RequestModel;

/**
 *
 * @author ZT
 */
public class ReceptionAgent extends Agent{
    public RequestDao reqDao = RequestDao.getInstance(); 
    public String vmId;
     public String reqId;
     
    public VMDao vmDao = VMDao.getInstance();
    @Override
    protected void setup() {
        SequentialBehaviour sequentialBehaviour = new SequentialBehaviour();
           sequentialBehaviour.addSubBehaviour(new InitReceptionAgentBehaviour()); 
           sequentialBehaviour.addSubBehaviour(new VmRecvBehaviour()); 
           sequentialBehaviour.addSubBehaviour(new CalcStatisticBehaviours()); 
           addBehaviour(sequentialBehaviour);
    }
    
    
}
