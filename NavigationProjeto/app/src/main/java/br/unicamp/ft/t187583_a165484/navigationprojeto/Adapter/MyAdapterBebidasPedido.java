package br.unicamp.ft.t187583_a165484.navigationprojeto.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Bebida;
import br.unicamp.ft.t187583_a165484.navigationprojeto.R;

public class MyAdapterBebidasPedido extends RecyclerView.Adapter{

    private ArrayList<Bebida> bebidas;
    private MyAdapterBebidasPedido.MyOnItemClickListener mo;
    private MyAdapterBebidasPedido.MyOnItemLongClickListener moLong;
    private MyAdapterBebidasPedido.MyButtonOnItemClickListener moBtn;

    MyAdapterBebidasPedido(ArrayList<Bebida>bebidas){
        this.bebidas = bebidas;
    }

    public void setMyOnItemClickListener(MyAdapterBebidasPedido.MyOnItemClickListener mo){
        this.mo = mo;
    }

    public void setMyOnItemLongClickListener(MyOnItemLongClickListener moLong){
        this.moLong = moLong;
    }
    public void setMyButtonOnItemClickListener(MyAdapterBebidasPedido.MyButtonOnItemClickListener moBtn){
        this.moBtn = moBtn;
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView1;
        private TextView textView4;
        private TextView textQtd;

        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_bebida_pedido);
            textView1 = itemView.findViewById(R.id.txt_bebida_produto);
            textQtd = itemView.findViewById(R.id.txt_bebida_quantidade);
            textView4 = itemView.findViewById(R.id.txt_bebida_preco);
        }

        public void bind(final Bebida bebida){
            imageView.setImageResource(bebida.getFoto());
            textView1.setText(bebida.getNome());
            textQtd.setText(""+bebida.getQuantidade());
            textView4.setText(String.valueOf(bebida.getPreço()));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pedido,viewGroup,false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mo!=null){
                    TextView txt = v.findViewById(R.id.textView1);
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
                    moLong.myOnItemLongClick(position);
                }
                return false;
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
