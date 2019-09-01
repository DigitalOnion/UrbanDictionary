package com.outerspace.urbandictionary.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.outerspace.urbandictionary.api.TermDefinition;

@Database(entities = {TermDefinition.class}, version = 1, exportSchema = false)
public abstract class TermDefinitionDatabase extends RoomDatabase {
    public static final String TERM_DEFINITION_DB_NAME = "TermDefinitionDatabase";

    public abstract TermDefinitionDAO termDefinitionDAO();
}
