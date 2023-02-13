package com.example.devinette;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ScoreAdapter extends ArrayAdapter<Score> {
    public ScoreAdapter(Context context, List<Score>scores){
        super(context,0,scores);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Score score = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.score_ceil,parent,false);
        TextView name = convertView.findViewById(R.id.gamerName);
        TextView mscore = convertView.findViewById(R.id.gamerScore);
        return convertView;
    }
}
