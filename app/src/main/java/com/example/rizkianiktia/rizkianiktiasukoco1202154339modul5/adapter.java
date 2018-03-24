package com.example.rizkianiktia.rizkianiktiasukoco1202154339modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.holder> {
    //deklarasi
    private Context context;
    private List<itemtodo> item;
    int id;

    //Constructor
    public adapter(Context con, List<itemtodo> itemnya, int id) {
        this.context = con;
        this.item = itemnya;
        this.id = id;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_todolist, parent, false);
        holder hold = new holder(v);
        return hold;
    }

//mdthod untuk menampung data
    @Override
    public void onBindViewHolder(holder holder, int position) {
        itemtodo satuan = item.get(position);
        holder.name.setText(satuan.getNama());
        holder.deskripsi.setText(satuan.getDeskripsi());
        holder.priority.setText(satuan.getPrioritas());
        holder.cd.setCardBackgroundColor(context.getResources().getColor(this.id));
    }

    //Method untuk mendapatkan addtodo dari adapter
    public itemtodo getItem(int position) {
        return item.get(position);
    }
    @Override
    public int getItemCount() {
        return item.size();
    }

    //Method untuk menghapus addtodo
    public void remove(int i){
        item.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, item.size());
    }

    class holder extends RecyclerView.ViewHolder{
        public TextView name, deskripsi, priority;
        public CardView cd;
        public holder(View itemView) {
            super(itemView);

            //Menginisialisasikan objek
            name = itemView.findViewById(R.id.todoname);
            deskripsi = itemView.findViewById(R.id.description);
            priority = itemView.findViewById(R.id.priority);
            cd = itemView.findViewById(R.id.cardview);


        }
    }
}
