package com.archiveUpdater.controller;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controller for the entry addition screen.
 *
 * Create 01/04/2017
 */
public class AddController extends Controller {

    @FXML private TextField titleInput;
    @FXML private TextField sourceInput;
    @FXML private TextField posterInput;
    @FXML private Button addButton;
    @FXML private Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    /**
     * Returns application to starting screen.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void back(ActionEvent e) {
        getApplication().loadInit();
    }
}
