package br.unicamp.ft.t187583_a165484.navigationprojeto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MyFirstAdapter extends RecyclerView.Adapter{

    private ArrayList<Comida> comidas;
    private MyOnItemClickListener mo;
    private MyOnItemLongClickListener moLong;
    private MyButtonOnItemClickListener moBtn;

    MyFirstAdapter(ArrayList<Comida>comidas){
        this.comidas = comidas;
    }

    public void setMyOnItemClickListener(MyOnItemClickListener mo){
        this.mo = mo;
    }
    public void setMyButtonOnItemClickListener(MyButtonOnItemClickListener moBtn){
        this.moBtn = moBtn;
    }
    public void setMyOnItemLongClickListener(MyOnItemLongClickListener mo){
        this.moLong = mo;
    }

    public class MyFirstViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private Button btnAdd;

        public MyFirstViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            btnAdd    = itemView.findViewById(R.id.btnAddComida);
        }

        public void bind(final Comida comida){
            imageView.setImageResource(comida.getFoto());
            textView1.setText(comida.getNome());
            textView2.setText("Preço: "+String.valueOf(comida.getPreco()));
            textView3.setText(comida.getDescricao());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler,viewGroup,false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mo!=null){
                    TextView txt = v.findViewById(R.id.textView1);
                    mo.myOnItemClick((String) txt.getText().toString());
                }
            }
        });


        return new MyFirstViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((MyFirstViewHolder)viewHolder).bind(comidas.get(i));

      viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (moLong != null) {
                    final int position = i;
                    //comidas.remove(position);
                    moLong.myOnItemLongClick(position);
                }

                return false;
            }
        });

      ((MyFirstViewHolder) viewHolder).btnAdd.setOnClickListener(new View.OnClickListener(){

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
        return comidas.size();
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
