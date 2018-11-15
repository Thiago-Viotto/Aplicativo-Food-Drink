package br.unicamp.ft.t187583_a165484.navigationprojeto.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Bebidas;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces.OnBebidaRequest;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Adapter.MyAdapterBebidas;
import br.unicamp.ft.t187583_a165484.navigationprojeto.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BebidasFragment extends android.support.v4.app.Fragment {
    View view;
    private RecyclerView mrecyclerView;
    private MyAdapterBebidas mAdapter;
    private MyAdapterBebidas.MyOnItemClickListener mo;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnBebidaRequest onBebidaRequest;
    PedidoFragmento p = new PedidoFragmento();

    public BebidasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_bebidas, container, false);
        }

        mrecyclerView = view.findViewById(R.id.recycler_viewBebidas);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MyAdapterBebidas(new ArrayList(Arrays.asList(Bebidas.bebidas)));
        mrecyclerView.setAdapter(mAdapter);


        MyAdapterBebidas.MyOnItemClickListener listener = new MyAdapterBebidas.MyOnItemClickListener() {
            @Override
            public void myOnItemClick(String nome) {
                Toast.makeText(getContext(), nome, Toast.LENGTH_SHORT).show();
            }
        };

        mAdapter.setMyButtonOnItemClickListener(new MyAdapterBebidas.MyButtonOnItemClickListener() {
            @Override
            public void myButtonOnItemClick(int pos) {
                if (onBebidaRequest != null) {
                    onBebidaRequest.requestList(Bebidas.bebidas[pos], pos);
                }
            }
        });

        mAdapter.setMyOnItemClickListener(listener);


        // Inflate the layout for this fragment
        return view;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setOnBebidaRequest(OnBebidaRequest onBebidaRequest) {
        this.onBebidaRequest = onBebidaRequest;
    }

}
