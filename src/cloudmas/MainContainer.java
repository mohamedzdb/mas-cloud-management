/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudmas;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.ContainerController;

/**
 *
 * @author ZT
 */
public class MainContainer {
     ContainerController container;
    public MainContainer(){
        // Créer une instance de la MV
		Runtime rt = Runtime.instance();		
		// Fixer quelques propriétés
		Properties p = new ExtendedProperties();
		// le GUI Jade
		p.setProperty("gui", "true");
		ProfileImpl profile = new ProfileImpl(p);
		// créer le main container
		container = rt.createMainContainer(profile);
    }
    public ContainerController getMainContainer(){return container;}
}
