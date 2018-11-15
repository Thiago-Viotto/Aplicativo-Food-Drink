package br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Bebida;

public interface OnBebidaRequest {
    void onRequest();
    void requestList(Bebida b,int pos);
}
