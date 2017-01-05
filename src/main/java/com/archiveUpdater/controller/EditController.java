package com.archiveUpdater.controller;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.archiveUpdater.model.Database;
import com.archiveUpdater.model.FireDatabase;
import com.archiveUpdater.model.MockEntry;
import com.archiveUpdater.model.Entry;
import com.archiveUpdater.model.Written;
import com.archiveUpdater.model.Edited;

/**
 * Controller for the entry editing screen.
 *
 * Create 01/05/2017
 */
public class EditController extends Controller {

    @FXML private TextField titleInput;
    @FXML private TextField sourceInput;
    @FXML private TextField posterInput;
    @FXML private Button addButton;
    @FXML private Button backButton;
    @FXML private Button removeButton;
    @FXML private CheckBox uploadedBox;
    @FXML private CheckBox finalEditBox;

    private Entry currentEntry;
    private boolean isShowcase = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    /**
     * Loads the entry-to-be-edited's information into the fields.
     *
     * @param entry  the entry to be edited
     */
    public void loadEntry(Entry entry) {
        this.currentEntry = entry;
        titleInput.setText(entry.getTitle());
        sourceInput.setText(entry.getEntrySource());
        posterInput.setText(entry.getImageSource());
        if (entry.getUploaded()) {
            uploadedBox.setSelected(true);
        }
        if (entry instanceof Edited) {
            finalEditBox.setDisable(true);
        } else {
            if (entry.getFinalEdit()) {
                finalEditBox.setSelected(true);
            }
        }
    }

    /**
     * Updates the entry in the database and local lists.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void update(ActionEvent e) {
        if (validate()) {
            getApplication().getDatabase().removeEntry(currentEntry);
            if (getApplication().getDatabase().getWrittenShowcase().contains(
                currentEntry) || getApplication().getDatabase()
                .getEditedShowcase().contains(currentEntry)) {
                getApplication().getDatabase().removeShowcase(currentEntry);
                isShowcase = true;
            }
            MockEntry old = new MockEntry(currentEntry);
            currentEntry.setTitle(titleInput.getText());
            currentEntry.setEntrySource(sourceInput.getText());
            currentEntry.setImageSource(posterInput.getText());
            currentEntry.setUploaded(uploadedBox.isSelected());
            currentEntry.setFinalEdit(finalEditBox.isSelected());
            getApplication().getDatabase().addEntry(currentEntry);
            if (isShowcase) {
                getApplication().getDatabase().addShowcase(currentEntry);
            }
            showAlert("Entry successfully updated!");
            getApplication().loadView();

        }
    }

    /**
     * Removes entry from database and local lists.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void remove(ActionEvent e) {
        Optional<ButtonType> selection = showAlert("Are you sure you want to de"
            + "lete this entry?");
        if (selection.get() == ButtonType.OK) {
            getApplication().getDatabase().removeEntry(currentEntry);
            if (getApplication().getDatabase().getWrittenShowcase().contains(
                currentEntry) || getApplication().getDatabase()
                .getEditedShowcase().contains(currentEntry)) {
                getApplication().getDatabase().removeShowcase(currentEntry);
            }
        }
        showAlert("Entry successfully removed");
        getApplication().loadView();
    }

    /**
     * Returns application to view screen.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void back(ActionEvent e) {
        getApplication().loadView();
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
        try {
            String[] url = sourceInput.getText().split("/");
            String id = url[3] + "-" + url[4];
        } catch (IndexOutOfBoundsException e) {
            showAlert("Must input valid MAL Source URL");
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
