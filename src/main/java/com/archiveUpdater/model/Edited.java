package com.archiveUpdater.model;

/**
 * Synopsis entries that were edited by me.
 *
 * Created 01/04/2017
 */
public class Edited extends Entry {

    /**
     * Creates an entry for a edited synopsis. Final edit is always
     * false since I wasn't the original writer.
     *
     * @param entrySource  the URL of the entry's MAL page
     * @param imageSource  the URL of the entry's poster
     * @param title  the title of the entry
     * @param isUploaded  whether the synopsis has been uploaded to MAL
     */
    public Edited(String entrySource, String imageSource, String title,
        boolean isUploaded) {
        setEntrySource(entrySource);
        setImageSource(imageSource);
        setTitle(title);
        setFinalEdit(false);
        setUploaded(isUploaded);
    }

    /**
     * Creates an entry for an edited synopsis using a mock entry.
     *
     * @param mock  the mock entry to create an entry from
     */
    public Edited(MockEntry mock) {
        setEntrySource(mock.getSource());
        setImageSource(mock.getPoster());
        setTitle(mock.getTitle());
        setFinalEdit(false);
        setUploaded(mock.getUploaded());
    }
}
