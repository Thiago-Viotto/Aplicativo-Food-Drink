package br.unicamp.ft.t187583_a165484.navigationprojeto.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Conexao;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces.OnBebidaRequest;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces.OnCardapioRequest;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces.OnCategoriaRequest;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Activity.LoginActivity;
import br.unicamp.ft.t187583_a165484.navigationprojeto.R;


/**
 */
public class TelaPrincipalFragment extends android.support.v4.app.Fragment {
    View view;
    private FirebaseAuth firebaseAuth;
    private ImageView imgNossosPratos, imgNossasBebidas, imgNossasCategorias;
    private Button btnEntrar, btnCadastrar, btnDeslogar;
    private OnCardapioRequest onCardapioRequest;
    private OnBebidaRequest onBebidaRequest;
    private OnCategoriaRequest onCategoriaRequest;


    public TelaPrincipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_tela_principal, container, false);
        }

        btnDeslogar = view.findViewById(R.id.btnSair);

        btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth = Conexao.getFirebaseAuth();
    }

    public void setOnCardapioRequest(OnCardapioRequest onCardapioRequest) {
        this.onCardapioRequest = onCardapioRequest;
    }

    public void setOnBebidaRequest(OnBebidaRequest onBebidaRequest) {
        this.onBebidaRequest = onBebidaRequest;
    }

    public void setOnCategoriaRequest(OnCategoriaRequest onCategoriaRequest) {
        this.onCategoriaRequest = onCategoriaRequest;
    }

}
