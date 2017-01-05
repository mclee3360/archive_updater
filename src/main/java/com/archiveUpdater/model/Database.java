package com.archiveUpdater.model;

import java.util.List;
import java.util.NoSuchElementException;
/**
 * Interface for backing database.
 *
 * Created 01/04/2017
 */
public abstract class Database {

    /**
     * Initializes the database.
     */
    public abstract void initDatabase();

    /**
     * Gets all written entries from the database.
     *
     * @param list  the list to update with all the entries
     */
    public abstract void getAllWrittenEntries(List<Entry> list);

    /**
     * gets all edited entries from the database.
     *
     * @param list  the list to update with all the entries
     */
    public abstract void getAllEditedEntries(List<Entry> list);

    /**
     * Add an entry to the database. If an equivalent entry is
     * already in the database, removes it and adds this entry
     * in its place.
     *
     * @param entry  the entry to be uploaded to the database
     */
    public abstract void addEntry(Entry entry);

    /**
     * Remove an entry from the database.
     *
     * @param entry  the entry to be removed from the database
     * @return the removed entry
     */
    public abstract Entry removeEntry(Entry entry);
}
