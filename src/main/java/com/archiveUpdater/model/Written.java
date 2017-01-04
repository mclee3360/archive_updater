package com.archiveUpdater.model;

/**
 * Synopsis entries that were written by me.
 *
 * Created 01/04/2017
 */
public class Written extends Entry {

    /**
     * Creates an entry for a written synopsis.
     *
     * @param entrySource  the URL of the entry's MAL page
     * @param imageSource  the URL of the entry's poster
     * @param title  the title of the entry
     * @param didFinalEdit  whether the I completed the final edit
     * @param isUploaded  whether the synopsis has been uploaded to MAL
     */
    public Written(String entrySource, String imageSource, String title,
        boolean didFinalEdit, boolean isUploaded) {
        setEntrySource(entrySource);
        setImageSource(imageSource);
        setTitle(title);
        setFinalEdit(didFinalEdit);
        setUploaded(isUploaded);
    }
}
