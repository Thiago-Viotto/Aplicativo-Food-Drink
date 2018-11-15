package br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Comida;

public interface OnCardapioRequest {
    void request();
    void requestList(Comida c, int pos);
}
