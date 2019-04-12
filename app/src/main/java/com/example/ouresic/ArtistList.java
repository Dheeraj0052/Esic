package com.example.ouresic;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArtistList extends ArrayAdapter<Artist> {
    private Activity context;
    private List<Artist> artistList;
    public ArtistList(Activity context,List<Artist> artistList){
        super(context,R.layout.list_layout,artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View ListViewItem=inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewName=(TextView)ListViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre=(TextView)ListViewItem.findViewById(R.id.textViewGenre);
        TextView textViewid=(TextView)ListViewItem.findViewById(R.id.textViewid);
        Artist artist=artistList.get(position);
        textViewName.setText(artist.getArtistName());
        textViewGenre.setText(artist.getArtistGenre());
        textViewid.setText(artist.getArtistId());
        return ListViewItem;
    }
}
