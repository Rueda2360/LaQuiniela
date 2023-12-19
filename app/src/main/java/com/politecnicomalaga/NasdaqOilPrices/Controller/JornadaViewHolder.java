package com.politecnicomalaga.NasdaqOilPrices.Controller;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.politecnicomalaga.NasdaqOilPrices.R;

public class JornadaViewHolder extends RecyclerView.ViewHolder {

    //ESTADO

    //TODO: declare attributes to hold two textviews, day and money
    final JornadaAdapter mAdapter;
    private TextView tvEquipo1;
    private TextView tvEquipo2;
    private TextView tvResultado;

    // ...


    //COMPORTAMIENTO
    public JornadaViewHolder(View itemView, JornadaAdapter adapter) {
        super(itemView);

        tvEquipo1 = itemView.findViewById(R.id.tvEquipo1);
        tvEquipo2 = itemView.findViewById(R.id.tvEquipo2);
        tvResultado = itemView.findViewById(R.id.tvResultado);
        this.mAdapter = adapter;
    }

    public void setEquipo1(String data) {
        tvEquipo1.setText(data);
    }

    public void setEquipo2(String data) {
        tvEquipo2.setText(data);
    }

    public void setResultado(String data) {
        tvResultado.setText(data);
    }

}
