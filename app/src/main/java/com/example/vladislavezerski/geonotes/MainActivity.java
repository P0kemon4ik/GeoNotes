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
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 2;
    List<Note> notes = new ArrayList<>();
    private NoteAdapter adapter;

    private List<Note> initializeData(){
        return notes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        adapter = new NoteAdapter(initializeData());
        rv.setAdapter(adapter);

        ImageView addNote = (ImageView) findViewById(R.id.imgAddNote);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotesAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                notes.add(new Note(data.getStringExtra("text")));
                adapter.notifyDataSetChanged();
            }
        }
    }
}
