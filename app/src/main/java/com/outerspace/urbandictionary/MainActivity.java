package com.outerspace.urbandictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.outerspace.urbandictionary.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity {

    private static MainPresenter presenter = new MainPresenter();

    private TextView result;
    private EditText termEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result_text);
        termEntry = findViewById(R.id.term_entry);
    }

    public void onClickQueryTerm(View view) {
        presenter.fetchDefinitions(termEntry.getText().toString(), result::setText);
    }

}
