package br.com.androidpro.eventoapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ItemVideoAdapter extends ArrayAdapter<ItemVideo> {


    public ItemVideoAdapter(Context context, List<ItemVideo> Lista ) {
        super(context, 0, Lista);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View ItemView = convertView;
        if (ItemView == null){
            ItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_video,parent,false);
        }
        ItemVideo itemVideo = getItem(position);

        TextView titulo =  (TextView) ItemView.findViewById(R.id.titulo);
        titulo.setText(itemVideo.getTitulo());

        TextView data =  (TextView) ItemView.findViewById(R.id.data);
        data.setText(itemVideo.getData());


        return ItemView;
    }
}
