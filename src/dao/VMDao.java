/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import cloudmangment.URLS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.xml.transform.TransformerException;
import model.RequestModel;
import model.VmModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ZT
 */
public class VMDao {
    private String eleName = "vm";
    static private VMDao instance;
    DomDao domDao;
    private VMDao(){
       this.domDao = new DomDao(URLS.VM_URL_DATA,URLS.VM_URL_DTD);
        this.domDao.createDom();
    }
    static public VMDao getInstance(){
        if(instance == null){
            instance = new VMDao();
        }
        return instance;
    }
    public ArrayList getAllVms(){
        ArrayList<VmModel> vms = new ArrayList() ;
        NodeList eles= domDao.getAllElements(eleName);
        if(eles.getLength() == 0){
            return null;
        }
        for(int i=0;i<eles.getLength();i++){
            vms.add(nodeToObj(eles.item(i)));
        }
     return vms;
    }
    public VmModel nodeToObj(Node n){
        NodeList eles = n.getChildNodes();
        String id = ((Element)n).getAttribute("id");
        ArrayList<Node> elements = domDao.getAllElements(n);
        int mips = Integer.parseInt(elements.get(0).getTextContent());
        long size = Integer.parseInt(elements.get(1).getTextContent());
        Integer ram = Integer.parseInt(elements.get(2).getTextContent());
        long bw = Integer.parseInt(elements.get(3).getTextContent());
        int cpunum = Integer.parseInt(elements.get(4).getTextContent());
        String vmm = elements.get(5).getTextContent();
        String status = elements.get(6).getTextContent();
       return new VmModel(id, mips, size, ram, bw, cpunum, vmm,status);
   }
    public Node ObjToNode(VmModel vm) throws TransformerException{
       Node mips = domDao.createNode("mips",String.valueOf(vm.getMips()),null);
       Node size = domDao.createNode("size",String.valueOf(vm.getSize()),null);
       Node ram = domDao.createNode("ram",String.valueOf(vm.getRam()),null);
       Node bw = domDao.createNode("bw",String.valueOf(vm.getBw()),null);
       Node cpunum = domDao.createNode("cpunum",String.valueOf(vm.getPesNumber()),null);
       Node vmm = domDao.createNode("vmm",vm.getVmm(),null);
       Node status = domDao.createNode("vmm",vm.getStatus(),null);
       HashMap<String,String> mp = new HashMap();
       mp.put("id",vm.getVmidstr());
       Node productEle = domDao.createNode(eleName,null,mp);
       productEle.appendChild(mips);
       productEle.appendChild(size);
       productEle.appendChild(ram);
       productEle.appendChild(bw);
       productEle.appendChild(cpunum);
       productEle.appendChild(vmm);
       productEle.appendChild(status);
       return productEle;
   }
    
    public void updateVm(VmModel vm,String val) throws TransformerException{
       domDao.update(val,ObjToNode(vm));
   }
     public void addVm(VmModel vm) throws TransformerException{
       domDao.addNode(ObjToNode(vm));
    }
      
   public String generateId(){
       Boolean find = false;
       Random rand = new Random();
       while(!find){   
        int randomInt = rand.nextInt(250); 
        if(domDao.getAllIds(eleName).indexOf(randomInt)<0 && domDao.getAllIdsInteger(eleName) != null ){
            find = true;
            return "vm"+randomInt;
        }else if(domDao.getAllIdsInteger(eleName) == null){
            find = true;
            return "vm"+randomInt;
        }
       }
       return "";
   }  
    public ArrayList<Integer> getVmsID(){
        
       return domDao.getAllIdsInteger(eleName);
   }
    public VmModel getVm(String id){
       return this.nodeToObj(domDao.removeNode(id));
   }
}
