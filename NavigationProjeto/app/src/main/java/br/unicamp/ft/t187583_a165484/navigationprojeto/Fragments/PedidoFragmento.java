package br.unicamp.ft.t187583_a165484.navigationprojeto.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Bebida;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Comida;
import br.unicamp.ft.t187583_a165484.navigationprojeto.MainActivity;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Adapter.MyAdapterBebidasPedido;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Adapter.MyAdapterComidaPedido;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Pedido;
import br.unicamp.ft.t187583_a165484.navigationprojeto.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PedidoFragmento extends android.support.v4.app.Fragment {


    View view;
    TextView txtTotal;
    ArrayList<Comida> comidas = new ArrayList<Comida>();
    ArrayList<Bebida> bebidas = new ArrayList<Bebida>();
    private Button btnFinalizarPedido;
    private ImageView mImageView;
    private RecyclerView mrecyclerViewBebida;
    private RecyclerView mrecyclerViewComida;
    private MyAdapterBebidasPedido mAdapterBebida;
    private MyAdapterComidaPedido mAdapterComida;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public PedidoFragmento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_pedido_fragmento, container, false);
        }

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        //Toast.makeText(getContext(),""+account.getEmail(),Toast.LENGTH_SHORT).show();
     //   if (account == null) {
     //       startActivity(new Intent(getContext(), SignInActivity.class));
     //   } else {
            mrecyclerViewBebida = view.findViewById(R.id.recycle_list_bebida);
            mrecyclerViewBebida.setHasFixedSize(true);
            btnFinalizarPedido = view.findViewById(R.id.btn_finalizar);
            mrecyclerViewBebida.setLayoutManager(new LinearLayoutManager(getContext()));
            mAdapterBebida = new MyAdapterBebidasPedido(bebidas);
            mrecyclerViewBebida.setAdapter(mAdapterBebida);

            mrecyclerViewComida = view.findViewById(R.id.recycle_list_comida);
            mrecyclerViewComida.setHasFixedSize(true);
            mrecyclerViewComida.setLayoutManager(new LinearLayoutManager(getContext()));
            mAdapterComida = new MyAdapterComidaPedido(comidas);
            mrecyclerViewComida.setAdapter(mAdapterComida);

            txtTotal = view.findViewById(R.id.txtTotal);

            //DecimalFormat df = new DecimalFormat("0.##");
            double preco = precoBebida(bebidas) + precoCardapio(comidas);


            txtTotal.setText(preco + "");
            //txtTotal.setText("Total: "+(df.format(precoBebida(bebidas)])));

            btnFinalizarPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Pedido pedido = new Pedido(bebidas, comidas, mFirebaseUser.getUid() , Double.parseDouble(txtTotal.getText().toString()));
                        mFirebaseDatabaseReference.child("pedidos").push().setValue(pedido);
                        Toast.makeText(getContext(), "Pedido adicionado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro no adicionar pedido " + e.getMessage());
                    }
                }
            });
     //   }

        mAdapterComida.setMyOnItemLongClickListener(new MyAdapterComidaPedido.MyOnItemLongClickListener() {
            @Override
            public void myOnItemLongClick(int pos) {
                Toast.makeText(getContext(), "Item removido" , Toast.LENGTH_SHORT).show();
                comidas.remove(pos);
                double preco = precoBebida(bebidas) + precoCardapio(comidas);
                txtTotal.setText(preco + "");
                mAdapterComida.notifyDataSetChanged();
               // notify();
            }
        });


        mAdapterBebida.setMyOnItemLongClickListener(new MyAdapterBebidasPedido.MyOnItemLongClickListener() {
            @Override
            public void myOnItemLongClick(int pos) {
                Toast.makeText(getContext(), "Item removido"  , Toast.LENGTH_SHORT).show();
                bebidas.remove(pos);
                double preco = precoBebida(bebidas) + precoCardapio(comidas);
                txtTotal.setText(preco + "");
                mAdapterBebida.notifyDataSetChanged();
                // notify();
            }
        });


        return view;
    }


    public void setArgumentsPedido(Pedido p) {
        comidas = p.getComidas();
    }

    public double precoCardapio(List<Comida> comidas) {
        double total = 0;
        for (Comida c : comidas) {
            total += c.getPreco() * c.getQuantidade();
        }
        return total;
    }

    public void setArgumentsPedidoBebidas(Pedido p) {
        bebidas = p.getBebidas();
    }

    public double precoBebida(List<Bebida> bebidas) {
        double total = 0;
        for (Bebida b : bebidas) {
            total += b.getPreço() * b.getQuantidade();
        }
        return total;
    }

}
