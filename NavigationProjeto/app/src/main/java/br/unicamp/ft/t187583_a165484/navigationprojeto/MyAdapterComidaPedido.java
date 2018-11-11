package br.unicamp.ft.t187583_a165484.navigationprojeto;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterComidaPedido extends RecyclerView.Adapter{

    private ArrayList<Comida> comida;
    private MyAdapterComidaPedido.MyOnItemClickListener mo;
    private MyAdapterComidaPedido.MyOnItemLongClickListener moLong;
    private MyAdapterComidaPedido.MyButtonOnItemClickListener moBtn;

    MyAdapterComidaPedido(ArrayList<Comida>comidas){
        this.comida = comidas;
    }

    public void setMyOnItemClickListener(MyAdapterComidaPedido.MyOnItemClickListener mo){
        this.mo = mo;
    }
    public void setMyButtonOnItemClickListener(MyAdapterComidaPedido.MyButtonOnItemClickListener moBtn){
        this.moBtn = moBtn;
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView1;
        private TextView textView4;
        private TextView textQtd;

        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_comida_pedido);
            textView1 = itemView.findViewById(R.id.txt_comida_produto);
            textQtd = itemView.findViewById(R.id.txt_comida_quantidade);
            textView4 = itemView.findViewById(R.id.txt_comida_preco);
        }

        public void bind(final Comida comida){
            imageView.setImageResource(comida.getFoto());
            textView1.setText(comida.getNome());
            textQtd.setText(""+comida.getQuantidade());
            textView4.setText(String.valueOf(comida.getPreco()));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pedido_comida,viewGroup,false);

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
        MyAdapterViewHolder myAdapterViewHolder = ( (MyAdapterViewHolder)viewHolder);
        System.out.println("asdfasdf "+i);
        myAdapterViewHolder.bind(  comida.get(i));

    }

    @Override
    public int getItemCount() {
        System.out.println("SIZE " + comida.size());
        return comida.size();
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
