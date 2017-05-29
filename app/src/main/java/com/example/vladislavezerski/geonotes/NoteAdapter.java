package com.example.vladislavezerski.geonotes;

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteBody;
        ImageView note_image;

        NoteViewHolder(View itemView) {
            super(itemView);
            noteBody = (TextView) itemView.findViewById(R.id.note_body);
            note_image = (ImageView) itemView.findViewById(R.id.note_image);
        }
    }

    private List<Note> items;

    NoteAdapter(List<Note> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = items.get(position);
        holder.noteBody.setText(note.getBody());
        holder.note_image.getBackground().setColorFilter(note.getColor(), PorterDuff.Mode.SRC_ATOP);
    }
}
