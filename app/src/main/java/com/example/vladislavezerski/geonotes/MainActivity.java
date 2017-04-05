package com.example.vladislavezerski.geonotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Note> initializeData(){
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("Notes 1. It's good notes", R.drawable.note3));
        notes.add(new Note("Notes 2. It's no good notes", R.drawable.note3));
        notes.add(new Note("Notes 2. It's no good notes", R.drawable.note3));
        notes.add(new Note("Notes 2. It's no good notes", R.drawable.note3));
        notes.add(new Note("Notes 2. It's no good notes", R.drawable.note3));
        return notes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rv.setAdapter(new NoteAdapter(initializeData()));

        findViewById(R.id.imgAddNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), NotesAdd.class);
                startActivity(intent);
            }
        });
    }
}
