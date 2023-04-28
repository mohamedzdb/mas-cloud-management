package dao;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.xml.parsers.*;

import org.w3c.dom.*;

import org.xml.sax.SAXException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class DomDao {

    private Document doc;
    private String url;
    private String dtdUrl;
    public DomDao(String url,String dtdUrl){
      this.url = url;
      this.dtdUrl = dtdUrl;
   }
   
    public void createDom(){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.doc = builder.parse(new File(this.url));
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public Node createNode(String tagName,String val,HashMap<String, String> atts) throws TransformerException{
        Node n = this.doc.createElement(tagName);
        if(val != null){
            Node nData = this.doc.createTextNode(val);
            n.appendChild(nData);
        }
        if(atts != null){
            for (String i : atts.keySet()) {
                 ((Element) n).setAttribute(i, atts.get(i));
            }
           
        }
       return n;       
    }
    public void update(String val,Node updateNode) throws DOMException{
        Node n = getNodeById(val);
        doc.getDocumentElement().replaceChild(updateNode,n);
        try {
            transform();
        } catch (Exception ex) {
            Logger.getLogger(DomDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addNode(Node n){
        Node father = getRoot();
        father.appendChild(n);
         try {
            transform();
        } catch (Exception ex) {
            Logger.getLogger(DomDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public Node removeNode(String val){
         Element n = getNodeById(val);
        try {
            transform();
        } catch (Exception ex) {
            Logger.getLogger(DomDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public Document getDocument(){
        return this.doc;
    }
    public Element getNodeById(String val){
        showTree("--",this.doc,val);
        return this.doc.getElementById(val);
    }
    public NodeList getAllElements(String val){
        return doc.getElementsByTagName(val);
    }
    public Node getRoot(){
        return  doc.getDocumentElement();
    }
    Node node;
    public  void showTree (String whiteSpace,Node n,String id){
        
        switch(n.getNodeType()){
            case org.w3c.dom.Node.ELEMENT_NODE : 
                if(getAtt(n,id)){
                    node = n;
                  //  System.out.println("hello"+n.getFirstChild().getLocalName());
                }
                break;
        }
        
            NodeList fils = n.getChildNodes();
            for(int i=0;i<fils.getLength();i++){
                showTree(whiteSpace + "- ",fils.item(i),id);  
            }
            
        
    }
    public  boolean getAtt(Node n,String id){
        if(n.hasAttributes()){
           
           NamedNodeMap att = n.getAttributes();
           for(int i=0;i<att.getLength();i++){
              // System.out.println(att.item(i).getNodeValue());
              if(att.item(i).getNodeValue().equals(id));
              return true;
           }
        }        
        return false;     
    }
    public void transform() throws TransformerException, FileNotFoundException, IOException{
        try{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,this.dtdUrl);
        DOMSource domSource = new DOMSource(this.doc);
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        transformer.transform(domSource, sr);
        File file = new File(this.url);
        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.write(sw.toString().getBytes());
        dos.close();
        fos.close();
        createDom();
        }catch (Exception ex) {
                Logger.getLogger(DomDao.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public ArrayList getAllElements(Node n){
        ArrayList<Node> elements = new ArrayList();
            NodeList fils = n.getChildNodes();
            for(int i=0;i<fils.getLength();i++){
                switch(fils.item(i).getNodeType()){
            case org.w3c.dom.Node.ELEMENT_NODE : 
                elements.add(fils.item(i));
                break;
                 } 
            }
           
      return elements;
    }
    public ArrayList<String> getAllIds(String val){
        ArrayList<String> ids = new ArrayList();
         NodeList eles= getAllElements(val);
         for(int i=0;i<eles.getLength();i++){
            ids.add(((Element)eles.item(i)).getAttribute("id"));
        }
         
        return ids;
    }
    public ArrayList<Integer> getAllIdsInteger(String val){
        ArrayList<Integer> idsInt = new ArrayList();
        ArrayList<String> ids = getAllIds(val);
         for(int i=0;i<ids.size();i++){
            idsInt.add(Integer.parseInt(ids.get(i).substring(2)));
        }
         
        return idsInt;
    }
    public NodeList searchNodes(String racine,String subNode,String testNode,String searchVal){
        
        NodeList nodeList = null;
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "/"+racine+"/"+subNode+"[contains(./"+testNode+",'"+searchVal+"')]";
            nodeList = (NodeList) xPath.compile(expression).evaluate(this.doc, XPathConstants.NODESET);
 
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DomDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         return nodeList; 
        
    }
    public Node searchNodesById(String id){
        showTree("--",this.doc,id);
        System.out.println(node);
        Node nodeList = null;
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "/reqs/req[@id='"+id+"']";
            System.out.println(expression);
            nodeList = (Node) xPath.compile(expression).evaluate(this.doc, XPathConstants.NODE);
            System.out.println(nodeList);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DomDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         return nodeList; 
        
    }
}
