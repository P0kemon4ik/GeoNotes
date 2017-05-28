package com.example.vladislavezerski.geonotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.*;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CODE = 2;
    List<Note> notes = new ArrayList<>();
    private NoteAdapter adapter;

    private String title = "Вы действительно хотите выйти?";
    private String button1String = "Да";
    private String button2String = "Нет";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private List<Note> initializeData() {
        return notes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("people");

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        adapter = new NoteAdapter(initializeData());
        rv.setAdapter(adapter);

        final ImageView addNote = (ImageView) findViewById(R.id.imgAddNote);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotesAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object data = ((HashMap) dataSnapshot.getValue()).get("li0m5d2RgIcMmnaW8mBjrjUTfAD3");
                Set<String> keys = ((HashMap) data).keySet();

                for (String key : keys) {
                    HashMap o = (HashMap) ((HashMap) data).get(key);
                    notes.add(new Note(key, (String) o.get("body"), (String) o.get("backColor"), (Double) o.get("lat"), (Double) o.get("lng"), (String) o.get("imageData")));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("addValueEventListener", "Failed to read value.", error.toException());
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

    @Override
    public void onBackPressed() {
        MyDialog();
    }

    public void MyDialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setTitle(title);
        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Продолжайте работу", Toast.LENGTH_SHORT).show();
            }
        });
        ad.show();
    }
}
