package com.archiveUpdater.controller;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.concurrent.TimeUnit;

import com.archiveUpdater.model.Database;
import com.archiveUpdater.model.FireDatabase;
import com.archiveUpdater.model.Entry;
import com.archiveUpdater.model.Written;
import com.archiveUpdater.model.Edited;

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
    @FXML private ComboBox typeBox;
    @FXML private CheckBox uploadedBox;
    @FXML private CheckBox finalEditBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    /**
     * Disables Final Edit checkbox if entry is an edit.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void checkType(ActionEvent e) {
        if (typeBox.getValue().equals("Edited")) {
            finalEditBox.setSelected(false);
            finalEditBox.setDisable(true);
        } else {
            finalEditBox.setDisable(false);
        }
    }

    @FXML
    public void add(ActionEvent e) {
        if (validate()) {
            Entry entry;
            if (typeBox.getValue().equals("Written")) {
                entry = new Written(sourceInput.getText(),
                    posterInput.getText(), titleInput.getText(),
                    finalEditBox.isSelected(), uploadedBox.isSelected());
            } else {
                entry = new Edited(sourceInput.getText(),
                    posterInput.getText(), titleInput.getText(),
                    uploadedBox.isSelected());
            }
            getApplication().getDatabase().addEntry(entry);
            Optional<ButtonType> selection = showAlert("Entry was successfully "
                + "added to the database. Add another?");
            if (selection.get() == ButtonType.OK) {
                getApplication().loadAdd();
            } else {
                getApplication().loadInit();
            }
        }
    }

    // @FXML
    // public void test(ActionEvent event) {
    //     List<Entry> list = new ArrayList<Entry>();
    //     list = getApplication().getDatabase().getAllWrittenEntries(list);
    //     try {
    //         TimeUnit.MILLISECONDS.sleep(500);
    //     } catch (InterruptedException e) { }
    //     System.out.println(list);
    //     for (Entry e: list) {
    //         System.out.println(e.getTitle());
    //     }
    //     System.out.println("Nothing to Print");
    // }

    /**
     * Returns application to starting screen.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void back(ActionEvent e) {
        getApplication().loadInit();
    }

    private boolean validate() {
        if (titleInput.getText() == null || titleInput.getText().equals("")) {
            showAlert("Must input a title");
            return false;
        }
        if (sourceInput.getText() == null || sourceInput.getText().equals("")) {
            showAlert("Must input a source URL");
            return false;
        }
        if (posterInput.getText() == null || posterInput.getText().equals("")) {
            showAlert("Must input a poster URL");
            return false;
        }
        if (typeBox.getValue() == null) {
            showAlert("Must select a entry type");
            return false;
        }
        return true;
    }

    private Optional<ButtonType> showAlert(String msg) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        return alert.showAndWait();
    }
}
