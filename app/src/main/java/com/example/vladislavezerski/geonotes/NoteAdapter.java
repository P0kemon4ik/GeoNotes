package com.example.vladislavezerski.geonotes;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteBody;
        ImageView noteImage;

       NoteViewHolder(View itemView) {
            super(itemView);
            noteBody = (TextView)itemView.findViewById(R.id.note_body);
            noteImage = (ImageView)itemView.findViewById(R.id.note_image);
        }
    }

    private List<Note> items;
    NoteAdapter(List<Note> items){
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.noteBody.setText(items.get(position).body);
        holder.noteImage.setImageResource(items.get(position).imgId);
    }
}
