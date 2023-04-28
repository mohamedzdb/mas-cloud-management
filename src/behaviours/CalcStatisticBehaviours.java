/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours;

import jade.core.behaviours.OneShotBehaviour;
import model.RequestModel;
import model.VmModel;
import reception.ReceptionAgent;
import sun.rmi.runtime.Log;

/**
 *
 * @author ZT
 */
public class CalcStatisticBehaviours extends OneShotBehaviour {

    @Override
    public void action() {
        ReceptionAgent recep = (ReceptionAgent) myAgent ;
        VmModel vm = recep.vmDao.getVm(recep.vmId);
        RequestModel req = recep.reqDao.getRequest(recep.reqId);
        String indent = "    ";
		System.out.println();
		System.out.println("========== OUTPUT ==========");
		System.out.println("VM ID" + indent + "status" + indent
				+ "mips" + indent + "size" + indent + "ram" + indent
				+ "bw" + indent + "pesNumber");


				System.out.println( vm.getVmidstr()  + indent+ indent + vm.getStatus()
						  + indent + req.getMips()*100/vm.getMips() +"%"+
						 indent + indent
						+ req.getSize()*100/vm.getSize() +"%"+ indent
						 + req.getRam()*100/vm.getRam() +"%"
						+ indent
						+ req.getBw()*100/vm.getBw() +"%"
                                                 + indent
						+ req.getPesNumber()*100/vm.getPesNumber() +"%");
		
    }
    
}
