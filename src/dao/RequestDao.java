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
public class RequestDao {
    private String eleName = "req";
    static private RequestDao instance;
    DomDao domDao;
    private RequestDao(){
       this.domDao = new DomDao(URLS.REQ_URL_DATA,URLS.REQ_URL_DTD);
        this.domDao.createDom();
    }
    static public RequestDao getInstance(){
        if(instance == null){
            instance = new RequestDao();
        }
        return instance;
    }
    public RequestModel nodeToObj(Node n){
        NodeList eles = n.getChildNodes();
        String id = ((Element)n).getAttribute("id");
        ArrayList<Node> elements = domDao.getAllElements(n);
        int mips = Integer.parseInt(elements.get(0).getTextContent());
        long size = Integer.parseInt(elements.get(1).getTextContent());
        Integer ram = Integer.parseInt(elements.get(2).getTextContent());
        long bw = Integer.parseInt(elements.get(3).getTextContent());
        int cpunum = Integer.parseInt(elements.get(4).getTextContent());
        String vmm = elements.get(5).getTextContent();
       return new RequestModel(id, mips, size, ram, bw, cpunum, vmm);
   }
    public Node ObjToNode(RequestModel req) throws TransformerException{
       Node mips = domDao.createNode("mips",String.valueOf(req.getMips()),null);
       Node size = domDao.createNode("size",String.valueOf(req.getSize()),null);
       Node ram = domDao.createNode("ram",String.valueOf(req.getRam()),null);
       Node bw = domDao.createNode("bw",String.valueOf(req.getBw()),null);
       Node cpunum = domDao.createNode("cpunum",String.valueOf(req.getPesNumber()),null);
       Node vmm = domDao.createNode("vmm",req.getVmm(),null);
       HashMap<String,String> mp = new HashMap();
       mp.put("id",req.getVmidstr());
       Node productEle = domDao.createNode(eleName,null,mp);
       productEle.appendChild(mips);
       productEle.appendChild(size);
       productEle.appendChild(ram);
       productEle.appendChild(bw);
       productEle.appendChild(cpunum);
       productEle.appendChild(vmm);
       return productEle;
   }
     public String generateId(){
       Boolean find = false;
       Random rand = new Random();
       while(!find){   
        int randomInt = rand.nextInt(250); 
        if(domDao.getAllIds(eleName).indexOf(randomInt)<0 && domDao.getAllIdsInteger(eleName) != null ){
            find = true;
            return "rq"+randomInt;
        }else if(domDao.getAllIdsInteger(eleName) == null){
            find = true;
            return "rq"+randomInt;
        }
       }
       return "";
   }
   public RequestModel getRequest(String id){
       return this.nodeToObj(domDao.removeNode(id));
   }
   public void addReq(RequestModel req) throws TransformerException{
       domDao.addNode(ObjToNode(req));
    }
}
