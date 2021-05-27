package com.example.system_cars.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.system_cars.R;

import java.util.List;

public class Adapter extends BaseAdapter {

    Context context;
    List<ContentValues> cvList;

    public Adapter(Context pContext, List<ContentValues> pListaCV) {
        this.context = pContext;
        this.cvList = pListaCV;
    }

    @Override
    public int getCount() {
        return this.cvList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.cvList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.datatable_carros, null);
        TextView colId = (TextView) view.findViewById(R.id.colId);
        TextView colMarca = (TextView) view.findViewById(R.id.colMarca);
        TextView colModelo = (TextView) view.findViewById(R.id.colModelo);
        TextView colAnoFab = (TextView) view.findViewById(R.id.colAnoFab);

        ContentValues cv = cvList.get(i);

        colId.setText(String.valueOf(cv.getAsInteger("id")));
        colMarca.setText(String.valueOf(cv.getAsString("marca")));
        colModelo.setText(String.valueOf(cv.getAsString("modelo")));
        colAnoFab.setText(String.valueOf(cv.getAsInteger("anoFab")));

        return view;
    }
}
