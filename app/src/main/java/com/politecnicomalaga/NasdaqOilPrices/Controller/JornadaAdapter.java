package com.politecnicomalaga.NasdaqOilPrices.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.politecnicomalaga.NasdaqOilPrices.Model.Partido;
import com.politecnicomalaga.NasdaqOilPrices.R;

import java.util.LinkedList;
import java.util.List;

public class JornadaAdapter extends RecyclerView.Adapter<JornadaViewHolder> {

    private final LinkedList<Partido> mList;
    private LayoutInflater mInflater;

    public JornadaAdapter(Context context, List<Partido> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = (LinkedList<Partido>) list;
    }


    @NonNull
    @Override
    public JornadaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.day_list_item,
                parent, false);
        return new JornadaViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull JornadaViewHolder holder, int position) {
        holder.setEquipo1(this.mList.get(position).getEquipo1());
        holder.setEquipo2(this.mList.get(position).getEquipo2());
        holder.setResultado(this.mList.get(position).getResultado());
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

}
