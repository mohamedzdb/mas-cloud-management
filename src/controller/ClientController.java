/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author ZT
 */
public class ClientController implements Initializable {

    
    @FXML
    private Text title;
    
    @FXML
    private Pane vmForm;

    @FXML
    private TextField vmbwfield;

    @FXML
    private TextField vmcpufield;

    @FXML
    private TextField vmmipsfield;

    @FXML
    private TextField vmramfield;

    @FXML
    private TextField vmsizefield;

    @FXML
    private TextField vmvmmfield;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vmForm.setVisible(false);
    }
    @FXML
    void addvm(ActionEvent event) {

    }

    @FXML
    void newVm(ActionEvent event) {
        vmForm.setVisible(true);
        title.setVisible(false);
    }

    @FXML
    void vmStat(ActionEvent event) {

    }
}
