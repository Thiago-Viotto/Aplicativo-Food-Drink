package br.unicamp.ft.t187583_a165484.navigationprojeto.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Bebida;
import br.unicamp.ft.t187583_a165484.navigationprojeto.R;

public class MyAdapterBebidas extends RecyclerView.Adapter{

    private ArrayList<Bebida> bebidas;
    private MyAdapterBebidas.MyOnItemClickListener mo;
    private MyAdapterBebidas.MyOnItemLongClickListener moLong;
    private MyAdapterBebidas.MyButtonOnItemClickListener moBtn;

    MyAdapterBebidas(ArrayList<Bebida>bebidas){
        this.bebidas = bebidas;
    }

    public void setMyOnItemClickListener(MyAdapterBebidas.MyOnItemClickListener mo){
        this.mo = mo;
    }
    public void setMyButtonOnItemClickListener(MyAdapterBebidas.MyButtonOnItemClickListener moBtn){
        this.moBtn = moBtn;
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView1;
        private TextView textView3;
        private TextView textView4;
        private Button btnAdd;

        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgBebida);
            textView1 = itemView.findViewById(R.id.txtNomeBebida);
            textView3 = itemView.findViewById(R.id.txtDescricaoBebida);
            textView4 = itemView.findViewById(R.id.txtPrecoBebida);
            btnAdd    = itemView.findViewById(R.id.btnAddBebida);
        }

        public void bind(final Bebida bebida){
            imageView.setImageResource(bebida.getFoto());
            textView1.setText(bebida.getNome());
            textView3.setText(bebida.getDescricao());
            textView4.setText(String.valueOf(bebida.getPreço()));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bebidas,viewGroup,false);
        final TextView txt = v.findViewById(R.id.txtNomeBebida);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mo!=null){

                    mo.myOnItemClick((String) txt.getText().toString());
                }
            }
        });


        return new MyAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((MyAdapterViewHolder)viewHolder).bind(bebidas.get(i));

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (moLong != null) {
                    final int position = i;
                    //bebidas.remove(position);
                    moLong.myOnItemLongClick(position);
                }

                return false;
            }
        });

        ((MyAdapterViewHolder) viewHolder).btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (moBtn != null) {
                    final int position = i;
                    moBtn.myButtonOnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bebidas.size();
    }

    public interface MyOnItemClickListener {
        void myOnItemClick(String nome);
    }

    public interface MyButtonOnItemClickListener {
        void myButtonOnItemClick(int pos);
    }

    public interface MyOnItemLongClickListener {
        void myOnItemLongClick(int pos);
    }
}
