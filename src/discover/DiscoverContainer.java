/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discover;

import cloudmas.App;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.ContainerController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZT
 */
public class DiscoverContainer {
    ContainerController container;
    public DiscoverContainer(){
         jade.core.Runtime run = jade.core.Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        container = run.createAgentContainer(p);
           try {
          
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public ContainerController getDiscoverContainer(){return container;}
}
