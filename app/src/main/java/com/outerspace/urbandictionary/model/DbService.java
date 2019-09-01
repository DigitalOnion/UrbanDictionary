package com.outerspace.urbandictionary.model;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.room.Room;

import com.outerspace.urbandictionary.R;
import com.outerspace.urbandictionary.UrbanDictionaryApp;
import com.outerspace.urbandictionary.api.TermDefinition;
import java.util.List;

import static com.outerspace.urbandictionary.model.TermDefinitionDatabase.TERM_DEFINITION_DB_NAME;

public class DbService {
    private static TermDefinitionDatabase database = Room.databaseBuilder(
        UrbanDictionaryApp.getInstance().getApplicationContext(),
        TermDefinitionDatabase.class,
        TERM_DEFINITION_DB_NAME)
        .build();

    private static final DbService instance = new DbService();

    public static DbService getInstance() { return instance; }

    private DbService() { }

    public void queryTerm(String word, DbServiceCallback callback) {
        new Thread(() -> {
            List<TermDefinition> definitions = database.termDefinitionDAO().findTermDefinitions(word);

            new Handler(Looper.getMainLooper()).post(() -> {
                callback.onQueryTermReady(definitions);
            });
        }).start();
    }

    public void persist(List<TermDefinition> termDefinitions) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                database.termDefinitionDAO().insertTermDefinitions(termDefinitions);
                new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(UrbanDictionaryApp.getInstance().getApplicationContext(),
                            R.string.keep_in_db,
                            Toast.LENGTH_SHORT).show();

                        });
            }
        }).start();
    }
}


