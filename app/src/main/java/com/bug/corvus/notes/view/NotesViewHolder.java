package com.bug.corvus.notes.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bug.corvus.notes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.note)
    TextView note;
    @BindView(R.id.dot)
    TextView dot;
    @BindView(R.id.timestamp)
    TextView timestamp;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
