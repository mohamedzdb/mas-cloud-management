/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours;

import dao.RequestDao;
import discover.DiscoverAgent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import model.RequestModel;
import model.VmModel;

/**
 *
 * @author ZT
 */
public class SearchingVmBehavior extends OneShotBehaviour {

    @Override
    public void action() {
        DiscoverAgent discover = (DiscoverAgent) myAgent;
                System.out.println("hello I'm discover agent searching a vm .....");
        discover.reqDao = RequestDao.getInstance();
        ArrayList<VmModel> vms = discover.vmDao.getAllVms();
        RequestModel req = discover.reqDao.getRequest(discover.reqId);
        boolean isTrue = false;
        int i =0;
        VmModel vm = null;
        while(!isTrue &&  i < vms.size()){
            vm = vms.get(i);

             if(!vm.getStatus().equalsIgnoreCase("bad")){
                System.out.println("hello I'm discover agent searching a vm    " + vm.getStatus());
                    if(vm.getMips()>= req.getMips() && 
                       vm.getBw() >= req.getBw() &&
                       vm.getPesNumber() >= req.getPesNumber() &&
                       vm.getRam() >= req.getRam() &&
                       vm.getSize() >= req.getSize() &&
                       vm.getVmm().equalsIgnoreCase(req.getVmm())
                            ){
                        isTrue = true;
                }
                }
             i++;
        }
        String msgContent;
        ACLMessage msg ;
        if(isTrue){
            msg = new ACLMessage(ACLMessage.PROPOSE);
            msgContent = vm.getVmidstr();
        }else{
            msg = new ACLMessage(ACLMessage.CANCEL);
            msgContent  = " ";
        }
                msg.addReceiver(new AID("recption",AID.ISLOCALNAME));
                msg.setContent(msgContent);
                myAgent.send(msg);
        
        
    }
    }
    

