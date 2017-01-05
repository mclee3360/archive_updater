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

    /**
     * Creates and initializes an instance of the database.
     */
    public FireDatabase() {
        initDatabase();
    }

    @Override
    public void initDatabase() {
        database = new Firebase("https://rewrite-archive-f7a8b.firebaseio.com/");
    }

    @Override
    public void getAllWrittenEntries(List<Entry> list) {
        database.child("written").addListenerForSingleValueEvent(
            new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    MockEntry mock = snap.getValue(MockEntry.class);
                    Entry entry = new Written(mock);
                    list.add(entry);
                }
                list.sort((Entry entry1, Entry entry2) -> {
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
    public void getAllEditedEntries(List<Entry> list) {
        database.child("edited").addListenerForSingleValueEvent(
            new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    MockEntry mock = snap.getValue(MockEntry.class);
                    Entry entry = new Edited(mock);
                    list.add(entry);
                }
                list.sort((Entry entry1, Entry entry2) -> {
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
    }

    @Override
    public Entry removeEntry(Entry entry) {
        return null;
    }

}
