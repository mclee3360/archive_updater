package com.archiveUpdater.model;

import com.firebase.client.*;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.database.*;
// import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Firebase database implementation class
 *
 * Create 01/04/2017
 */
public class FireDatabase extends Database {

    private Firebase database;
    private List<Entry> allWritten;
    private List<Entry> allEdited;
    private List<Entry> writtenShowcase;
    private List<Entry> editedShowcase;

    /**
     * Creates and initializes an instance of the database. Also updates list
     * of all entries.
     */
    public FireDatabase() {
        initDatabase();
        allWritten = new ArrayList<Entry>();
        updateWritten();
        allEdited = new ArrayList<Entry>();
        updateEdited();
        writtenShowcase = new ArrayList<Entry>();
        updateWrittenShowcase();
        editedShowcase = new ArrayList<Entry>();
        updateEditedShowcase();
    }

    @Override
    public void initDatabase() {
        database = new Firebase("https://rewrite-archive-f7a8b.firebaseio.com/");
    }

    @Override
    public List<Entry> getAllWrittenEntries() {
        return allWritten;
    }

    @Override
    public List<Entry> getAllEditedEntries() {
        return allEdited;
    }

    @Override
    public List<Entry> getWrittenShowcase() {
        return writtenShowcase;
    }

    @Override
    public List<Entry> getEditedShowcase() {
        return editedShowcase;
    }

    @Override
    public void addEntry(Entry entry) {
        Firebase location = database;
        if (entry instanceof Written) {
            location = database.child("written");
        } else if (entry instanceof Edited) {
            location = database.child("edited");
        }
        String[] url = entry.getEntrySource().split("/");
        String id = url[3] + "-" + url[4];
        location = location.child(id);
        MockEntry mock = new MockEntry(entry);
        location.setValue(mock);
        if (entry instanceof Written) {
            allWritten.add(entry);
        } else if (entry instanceof Edited) {
            allEdited.add(entry);
        }
    }

    @Override
    public void addShowcase(Entry entry) {
        Firebase location = database;
        if (entry instanceof Written) {
            location = database.child("showcase/written");
        } else if (entry instanceof Edited) {
            location = database.child("showcase/edited");
        }
        String[] url = entry.getEntrySource().split("/");
        String id = url[3] + "-" + url[4];
        location = location.child(id);
        MockEntry mock = new MockEntry(entry);
        location.setValue(mock);
        if (entry instanceof Written) {
            writtenShowcase.add(entry);
        } else if (entry instanceof Edited) {
            editedShowcase.add(entry);
        }
    }

    /**
     * Updates the list of all written entries.
     */
    public void updateWritten() {
        database.child("written").addListenerForSingleValueEvent(
            new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    MockEntry mock = snap.getValue(MockEntry.class);
                    Entry entry = new Written(mock);
                    allWritten.add(entry);
                }
                allWritten.sort((Entry entry1, Entry entry2) -> {
                    return entry1.compareTo(entry2);
                });
            }

            @Override
            public void onCancelled(FirebaseError databaseError) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    null, databaseError);
            }
        });
    }

    /**
     * Updates the list of all edited entries.
     */
    public void updateEdited() {
        database.child("edited").addListenerForSingleValueEvent(
            new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    MockEntry mock = snap.getValue(MockEntry.class);
                    Entry entry = new Edited(mock);
                    allEdited.add(entry);
                }
                allEdited.sort((Entry entry1, Entry entry2) -> {
                    return entry1.compareTo(entry2);
                });
            }

            @Override
            public void onCancelled(FirebaseError databaseError) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    null, databaseError);
            }
        });
    }

    /**
     * Updates the written showcase list
     */
    public void updateWrittenShowcase() {
        database.child("showcase/written").addListenerForSingleValueEvent(
            new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    MockEntry mock = snap.getValue(MockEntry.class);
                    Entry entry = new Written(mock);
                    writtenShowcase.add(entry);
                }
                writtenShowcase.sort((Entry entry1, Entry entry2) -> {
                    return entry1.compareTo(entry2);
                });
            }

            @Override
            public void onCancelled(FirebaseError databaseError) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    null, databaseError);
            }
        });
    }

    /**
     * Updates the edited showcase list
     */
    public void updateEditedShowcase() {
        database.child("showcase/edited").addListenerForSingleValueEvent(
            new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    MockEntry mock = snap.getValue(MockEntry.class);
                    Entry entry = new Edited(mock);
                    editedShowcase.add(entry);
                }
                editedShowcase.sort((Entry entry1, Entry entry2) -> {
                    return entry1.compareTo(entry2);
                });
            }

            @Override
            public void onCancelled(FirebaseError databaseError) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    null, databaseError);
            }
        });
    }

    @Override
    public Entry removeEntry(Entry entry) {
        return null;
    }

}
