package com.archiveUpdater.model;

/**
 * Abstract class of the synopsis entries.
 *
 * Created 01/04/2017
 */
public abstract class Entry implements Comparable<Entry> {

    private String entrySource;
    private String imageSource;
    private String title;
    private boolean didFinalEdit;
    private boolean isUploaded;

    /**
     * Gets the URL of the entry on MAL
     *
     * @return the entry's URL
     */
    public String getEntrySource() {
        return entrySource;
    }

    /**
     * Sets the URL of the entry on MAL
     *
     * @param source  the URL of the entry on MAL
     */
    public void setEntrySource(String source) {
        this.entrySource = source;
    }

    /**
     * Gets the URL of the entry's poster on MAL
     *
     * @return the entry's poster's URL
     */
    public String getImageSource() {
        return imageSource;
    }

    /**
     * Sets the URL of the entry's poster on MAL
     *
     * @param source  the URL of the entry's poster on MAL
     */
    public void setImageSource(String source) {
        this.imageSource = source;
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
     * Sets the title of the entry
     *
     * @param title  the title of the entry
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets whether I completed the entry's final edit
     *
     * @return whether I completed the final edit
     */
    public boolean getFinalEdit() {
        return didFinalEdit;
    }

    /**
     * Sets whether I completed the entry's final edit
     *
     * @param didEdit  whether I completed the entry's final edit
     */
    public void setFinalEdit(boolean didEdit) {
        this.didFinalEdit = didEdit;
    }

    /**
     * Gets whether the synopsis has been uploaded to MAL
     *
     * @return whether the synopsis has been uploaded to MAL
     */
    public boolean getUploaded() {
        return isUploaded;
    }

    /**
     * Sets whether the synopsis has been uploaded to MAL
     *
     * @param uploaded  whether the synopsis has been uploaded to MAL
     */
    public void setUploaded(boolean uploaded) {
        this.isUploaded = uploaded;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entry)) {
            return false;
        }
        Entry that = (Entry) o;
        return this.entrySource.equals(that.entrySource);
    }

    @Override
    public int hashCode() {
        return entrySource.hashCode();
    }

    @Override
    /**
     * Compare based on title.
     */
    public int compareTo(Entry that) {
        return this.title.compareTo(that.title);
    }
}
