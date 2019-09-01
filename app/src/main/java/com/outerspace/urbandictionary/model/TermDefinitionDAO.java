package com.outerspace.urbandictionary.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.outerspace.urbandictionary.api.TermDefinition;

import java.util.List;

@Dao
public interface TermDefinitionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTermDefinition(TermDefinition termDefinition);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTermDefinitions(List<TermDefinition> termDefinitions);

    @Delete
    void deleteTermDefinition(TermDefinition termDefinition);

    @Query("DELETE FROM TermDefinition WHERE word = :targetWord")
    void deleteTermsWhere(String targetWord);

    @Query("SELECT * FROM TermDefinition WHERE word LIKE :searchWord")
    public List<TermDefinition> findTermDefinitions(String searchWord);
}
