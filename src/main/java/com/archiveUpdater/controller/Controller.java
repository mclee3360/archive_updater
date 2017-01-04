package com.archiveUpdater.controller;

import javafx.fxml.Initializable;
/**
 * Abstrac class for controllers.
 *
 * Created 01/04/2017
 */
public abstract class Controller implements Initializable {

    private MainApplication application;

    /**
     * Sets the main application.
     *
     * @param app  the main application
     */
    public void setApplication(MainApplication app) {
        this.application = app;
    }

    /**
     * Gets the main application.
     *
     * @return the main application
     */
    public MainApplication getApplication() {
        return this.application;
    }
}
