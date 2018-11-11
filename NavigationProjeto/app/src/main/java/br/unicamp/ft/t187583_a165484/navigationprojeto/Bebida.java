package br.unicamp.ft.t187583_a165484.navigationprojeto;

public class Bebida {
    private String id;
    private String nome;
    private int foto;
    private String descricao;
    private double preço;
    private int quantidade = 1;

    Bebida(String id,String nome,int foto,String descricao,double preço){
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.descricao = descricao;
        this.preço = preço;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreço() {
        return preço;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString(){
        return  "Produto: "+nome + "\n"+"Descrição: "+descricao +"\n"+"Preço: "+ preço * quantidade + "\n" + "Quantidade: "+quantidade+"\n";
    }
}
