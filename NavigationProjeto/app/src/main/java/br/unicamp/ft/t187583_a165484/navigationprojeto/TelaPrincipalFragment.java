package br.unicamp.ft.t187583_a165484.navigationprojeto;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces.OnBebidaRequest;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces.OnCardapioRequest;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces.OnCategoriaRequest;


/**
 */
public class TelaPrincipalFragment extends android.support.v4.app.Fragment {
    View view;
    private ImageView imgNossosPratos, imgNossasBebidas, imgNossasCategorias;
    private Button btnEntrar, btnCadastrar;
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

        return view;
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
