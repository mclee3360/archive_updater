package com.archiveUpdater.controller;

import javafx.fxml.FXML;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;

import com.archiveUpdater.model.Entry;
/**
 * Controller for the screen to view all entries
 *
 * Create 01/04/2017
 */
public class ViewController extends Controller {

    @FXML private ListView<Entry> written;
    @FXML private Button editWritten;
    @FXML private Button showcaseWritten;

    @FXML private ListView<Entry> edited;
    @FXML private Button editEdited;
    @FXML private Button showcaseEdited;

    @FXML private ListView<Entry> writtenShowcase;
    @FXML private ListView<Entry> editedShowcase;
    @FXML private Button removeWrittenShowcase;
    @FXML private Button removeEditedShowcase;

    private List<Entry> writtenList;
    private List<Entry> editedList;
    private List<Entry> writtenShowList;
    private List<Entry> editedShowList;

    /**
     * True when written showcase is selected, false when edited is.
     */
    private boolean showcaseSelected;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    /**
     * Loads the lists from the database.
     */
    public void loadLists() {
        writtenList = getApplication().getDatabase().getAllWrittenEntries();
        editedList = getApplication().getDatabase().getAllEditedEntries();
        writtenShowList = getApplication().getDatabase().getWrittenShowcase();
        editedShowList = getApplication().getDatabase().getEditedShowcase();

        Comparator<Entry> sorter = new Sorter();
        writtenList.sort(sorter);
        editedList.sort(sorter);
        writtenShowList.sort(sorter);
        editedShowList.sort(sorter);
    }

    /**
     * Loads the lists into the listviews.
     */
    public void updateLists() {
        ObservableList<Entry> entries;

        written.getItems().clear();
        entries = FXCollections.observableArrayList(writtenList);
        written.setItems(entries);

        edited.getItems().clear();
        entries = FXCollections.observableArrayList(editedList);
        edited.setItems(entries);

        writtenShowcase.getItems().clear();
        entries = FXCollections.observableArrayList(writtenShowList);
        writtenShowcase.setItems(entries);

        editedShowcase.getItems().clear();
        entries = FXCollections.observableArrayList(editedShowList);
        editedShowcase.setItems(entries);
    }

    /**
     * Takes to editing screen using selected written entry.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void editWritten(ActionEvent e) {
        try {
            Entry selected = written.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new NullPointerException("Please select an entry to remove");
            }
            getApplication().loadEdit(selected);
        } catch (NullPointerException error) {
            showAlert(error.getMessage());
        }
    }

    /**
     * Adds currently selected written entry to written showcase.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void addWrittenShowcase(ActionEvent e) {
        if (getApplication().getDatabase().getWrittenShowcase().size() >= 4) {
            showAlert("Cannot have more than four entries in showcase. Please "
                + "remove one before adding.");
        } else {
            try {
                Entry selected = written.getSelectionModel().getSelectedItem();
                getApplication().getDatabase().addShowcase(selected);
                showAlert("Entry was successfully added to written showcase");
                getApplication().loadView();
            } catch (NullPointerException error) {
                showAlert("Please select an entry to add");
            }
        }
    }

    /**
     * Takes to editing screen using selected edited entry.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void editEdited(ActionEvent e) {
        try {
            Entry selected = edited.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new NullPointerException("Please select an entry to remove");
            }
            getApplication().loadEdit(selected);
        } catch (NullPointerException error) {
            showAlert(error.getMessage());
        }
    }

    /**
     * Adds currently selected edited entry to edited showcase.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void addEditedShowcase(ActionEvent e) {
        if (getApplication().getDatabase().getEditedShowcase().size() >= 4) {
            showAlert("Cannot have more than four entries in showcase. Please "
                + "remove one before adding.");
        } else {
            try {
                Entry selected = edited.getSelectionModel().getSelectedItem();
                getApplication().getDatabase().addShowcase(selected);
                showAlert("Entry was successfully added to written showcase");
                getApplication().loadView();
            } catch (NullPointerException error) {
                showAlert("Please select an entry to add");
            }
        }
    }

    /**
     * Removes selected written showcase.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void removeWrittenShowcase(ActionEvent e) {
        try {
            Entry selected = writtenShowcase.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new NullPointerException("Please select an entry to remove");
            }
            Optional<ButtonType> selection = askConfirmation();
            if (selection.get() == ButtonType.OK) {
                getApplication().getDatabase().removeShowcase(selected);
                getApplication().loadView();
            }
        } catch (NullPointerException error) {
            showAlert(error.getMessage());
        }
    }

    /**
     * Removes selected edited showcase.
     *
     * @param e  the event that triggers the action
     */
    @FXML
    public void removeEditedShowcase(ActionEvent e) {
        try {
            Entry selected = editedShowcase.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new NullPointerException("Please select an entry to remove");
            }
            Optional<ButtonType> selection = askConfirmation();
            if (selection.get() == ButtonType.OK) {
                getApplication().getDatabase().removeShowcase(selected);
                getApplication().loadView();
            }
        } catch (NullPointerException error) {
            showAlert(error.getMessage());
        }
    }

    /**
     * Returns to the starting screen.
     *
     * @param e  the event that triggers the action
     */
    public void back(ActionEvent e) {
        getApplication().loadInit();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private Optional<ButtonType> askConfirmation() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove this showcase?");
        return alert.showAndWait();
    }

    private class Sorter implements Comparator<Entry> {
        @Override
        public int compare(Entry e1, Entry e2) {
            return e1.compareTo(e2);
        }
    }
}
