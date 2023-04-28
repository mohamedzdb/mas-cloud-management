/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudmas;

import cloudmangment.URLS;
import discover.DiscoverAgent;
import discover.DiscoverContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import reception.ReceptionAgent;
import reception.ReceptionContainer;
/**
 *
 * @author ZT
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         try {
          
            Parent root = FXMLLoader.load(getClass().getResource(URLS.CLIENT_URL_VIEW));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Welcome");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        try {
          ContainerController main = new MainContainer().getMainContainer();
          ContainerController reception = new ReceptionContainer().getReceptionContainer();
          ContainerController discover = new DiscoverContainer().getDiscoverContainer();
          AgentController receptionAgent = reception.createNewAgent("recption",ReceptionAgent.class.getName(), new Object[]{});
          AgentController discoverAgent = discover.createNewAgent("discover",DiscoverAgent.class.getName(), new Object[]{});
           discoverAgent.start();
          receptionAgent.start();
        
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }  
        //launch(args);
    }
    
}
