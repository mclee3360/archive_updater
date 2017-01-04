package com.archiveUpdater.model;

import java.util.List;
/**
 * Interface for backing database.
 *
 * Created 01/04/2017
 */
public interface Database {

    /**
     * Gets an entry from the database.
     *
     * @return an Entry from the database
     */
    public Entry getEntry() { }

    /**
     * Gets all entries from the database.
     *
     * @return a list of all entries in the database
     */
    public List<Entry> getAllEntries() { }

    /**
     * Gets all written entries from the database.
     *
     * @return a list of all written entries in the database
     */
    public List<Written> getAllWrittenEntries() { }

    /**
     * gets all edited entries from the database.
     *
     * @return a list of all edited entries in the database
     */
    public List<Edited> getAllEditedEntries() { }

    /**
     * Add an entry to the database. If an equivalent entry is
     * already in the database, removes it and adds this entry
     * in its place.
     *
     * @param entry  the entry to be uploaded to the database
     */
    public void addEntry(Entry entry) { }

    /**
     * Remove an entry from the database.
     *
     * @param entry  the entry to be removed from the database
     * @throws NoSuchElementException if entry does not exist in the database
     * @return the removed entry
     */
    public Entry removeEntry(Entry entry) throws NoSuchElementException { }
}
