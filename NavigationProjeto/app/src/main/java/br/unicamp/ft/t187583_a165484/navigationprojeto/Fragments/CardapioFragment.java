package br.unicamp.ft.t187583_a165484.navigationprojeto.Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Comidas;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces.OnCardapioRequest;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Adapter.MyFirstAdapter;
import br.unicamp.ft.t187583_a165484.navigationprojeto.R;


public class CardapioFragment extends android.support.v4.app.Fragment {
    View view;
    private RecyclerView mrecyclerView;
    private MyFirstAdapter mAdapter;
    private OnCardapioRequest onCardapioRequest;
    PedidoFragmento p = new PedidoFragmento();


    public CardapioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null){
            view = inflater.inflate(R.layout.fragment_cardapio, container, false);
        }

        mrecyclerView = view.findViewById(R.id.recycler_view);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MyFirstAdapter(new ArrayList(Arrays.asList(Comidas.comidas)));
        mrecyclerView.setAdapter(mAdapter);

        MyFirstAdapter.MyOnItemClickListener listener = new MyFirstAdapter.MyOnItemClickListener() {
            @Override
            public void myOnItemClick(String nome) {
                Toast.makeText(getContext(), nome, Toast.LENGTH_SHORT).show();
            }
        };


        /*MyFirstAdapter.MyOnItemLongClickListener listenerLong = new MyFirstAdapter.MyOnItemLongClickListener() {
            @Override
            public void myOnItemLongClick(int pos) {
                comidas.add(Comidas.comidas[pos]);
                bundle.putSerializable("key", comidas);
                android.support.v4.app.Fragment frag = new PedidoFragmento();
                ((PedidoFragmento)frag).setArguments(bundle);
          /*      Pedido p = new Pedido();
                p.getComidas().add(Comidas.comidas[pos]);
                p.setNumeroPedido(25);
                bundle.putSerializable("key", p);
                android.support.v4.app.Fragment frag = new PedidoFragmento();
                if(frag!=null){
                    frag.setArguments(bundle);
                    FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.commit();

                Toast.makeText(getContext(),comidas.get(pos).getNome() , Toast.LENGTH_SHORT).show();
            }
        };*/


        mAdapter.setMyButtonOnItemClickListener(new MyFirstAdapter.MyButtonOnItemClickListener() {
            @Override
            public void myButtonOnItemClick(int pos) {
                if (onCardapioRequest != null) {
                    onCardapioRequest.requestList(Comidas.comidas[pos], pos);
                }
            }
        });

       /* mAdapter.setMyOnItemLongClickListener(new MyFirstAdapter.MyOnItemLongClickListener() {
            @Override
            public void myOnItemLongClick(int pos) {
                if (onCardapioRequest != null) {
                    onCardapioRequest.requestList(Comidas.comidas[pos],  pos);
                }
            }
        });*/

        mAdapter.setMyOnItemClickListener(listener);



        // Inflate the layout for this fragment
        return view;
    }


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setOnCardapioRequest(OnCardapioRequest onCardapioRequest) {
        this.onCardapioRequest = onCardapioRequest;
    }

}
