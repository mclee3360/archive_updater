package com.archiveUpdater.model;

/**
 * Mock Entry class to pull data straight from database.
 *
 * Created 01/04/2017
 */
public class MockEntry {

    private boolean finalEdit;
    private String poster;
    private String source;
    private String title;
    private boolean uploaded;

    /**
     * Blank constructor for database purposes.
     */
    public MockEntry() { };

    /**
     * Creates mock entry based on real entry.
     *
     * @param entry  the entry the mock is based off of
     */
    public MockEntry(Entry entry) {
        this.finalEdit = entry.getFinalEdit();
        this.poster = entry.getImageSource();
        this.source = entry.getEntrySource();
        this.title = entry.getTitle();
        this.uploaded = entry.getUploaded();
    }

    /**
     * Gets the URL of the entry on MAL
     *
     * @return the entry's URL
     */
    public String getSource() {
        return source;
    }

    /**
     * Gets the URL of the entry's poster on MAL
     *
     * @return the entry's poster's URL
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Gets the title of the entry
     *
     * @return the entry's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets whether I completed the entry's final edit
     *
     * @return whether I completed the final edit
     */
    public boolean getFinalEdit() {
        return finalEdit;
    }

    /**
     * Gets whether the synopsis has been uploaded to MAL
     *
     * @return whether the synopsis has been uploaded to MAL
     */
    public boolean getUploaded() {
        return uploaded;
    }
}
