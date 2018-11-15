package br.unicamp.ft.t187583_a165484.navigationprojeto.Fragments;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Adapter.MyAdapterComidaPedido;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Pedido;
import br.unicamp.ft.t187583_a165484.navigationprojeto.R;

public class ListaPedidoFragmento extends android.support.v4.app.Fragment {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView total;
        ListView mrecyclerViewBebida;
        ListView mrecyclerViewComida;

        public PedidoViewHolder(View v) {
            super(v);
            total = (TextView) v.findViewById(R.id.txt_lista_total);
            mrecyclerViewBebida = v.findViewById(R.id.recycle_list_Bebida);
            mrecyclerViewComida = v.findViewById(R.id.recycle_list_comida);

        }
    }

    // Firebase instance variables

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<Pedido, PedidoViewHolder>
            mFirebaseAdapter;

    public ListaPedidoFragmento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lview = inflater.inflate(R.layout.fragment_lista_pedido_fragmento, container, false);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        SnapshotParser<Pedido> parser = new SnapshotParser<Pedido>() {
            @Override
            public Pedido parseSnapshot(DataSnapshot dataSnapshot) {
                Pedido pedido = dataSnapshot.getValue(Pedido.class);
                return pedido;
            }
        };

        DatabaseReference messagesRef = mFirebaseDatabaseReference.child("pedidos");
        FirebaseRecyclerOptions<Pedido> options =
                new FirebaseRecyclerOptions.Builder<Pedido>()
                        .setQuery(messagesRef, parser)
                        .build();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Pedido, PedidoViewHolder>(options) {
            @Override
            public PedidoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new PedidoViewHolder(inflater.inflate(R.layout.item_message, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(final PedidoViewHolder viewHolder,
                                            int position,
                                            Pedido pedido) {

                DecimalFormat df = new DecimalFormat("###,##0.00");

                   if (pedido.getIdCliente().equals(mFirebaseUser.getUid())) {
                    viewHolder.total.setText("Total: "+df.format(pedido.getTotal()));

                    MyAdapterComidaPedido mAdapterComida;
                    //mAdapterComida = new MyAdapterComidaPedido();

                    ArrayAdapter adpComida = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, new ArrayList(Arrays.asList(pedido.getComidas())));
                    viewHolder.mrecyclerViewComida.setAdapter(adpComida);

                    ArrayAdapter adpBebida = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, new ArrayList(Arrays.asList(pedido.getBebidas())));
                    viewHolder.mrecyclerViewBebida.setAdapter(adpBebida);

                }
            }
        };
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecycler = ((RecyclerView) lview.findViewById(R.id.lista_pedido));
        mRecycler.setLayoutManager(llm);
        mRecycler.setAdapter(mFirebaseAdapter);

        return lview;
    }

    @Override
    public void onPause() {
        mFirebaseAdapter.stopListening();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAdapter.startListening();
    }
}