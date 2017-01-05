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
     * @return a list of all written entries in the database
     */
    public abstract List<Entry> getAllWrittenEntries();

    /**
     * Gets all edited entries from the database.
     *
     * @return a list of all edited entries in the database
     */
    public abstract List<Entry> getAllEditedEntries();

    /**
     * Gets the entries from the written showcase.
     *
     * @return list of entries in written showcase
     */
    public abstract List<Entry> getWrittenShowcase();

    /**
     * Gets the entries from the edited showcase.
     *
     * @return list of entries in the edited showcase
     */
    public abstract List<Entry> getEditedShowcase();

    /**
     * Add an entry to the database. If an equivalent entry is
     * already in the database, removes it and adds this entry
     * in its place.
     *
     * @param entry  the entry to be uploaded to the database
     */
    public abstract void addEntry(Entry entry);

    /**
     * Add an entry to the showcase database. If an equivalent entry is
     * already in the database, removes it and adds this entry
     * in its place.
     *
     * @param entry  the entry to be uploaded to the database
     */
    public abstract void addShowcase(Entry entry);

    /**
     * Remove an entry from the database.
     *
     * @param entry  the entry to be removed from the database
     * @return the removed entry
     */
    public abstract Entry removeEntry(Entry entry);

    /**
     * Remove an entry from the showcase.
     *
     * @param entry  the entry to be removed from the showcase
     * @return the removed entry
     */
    public abstract Entry removeShowcase(Entry entry);
}
