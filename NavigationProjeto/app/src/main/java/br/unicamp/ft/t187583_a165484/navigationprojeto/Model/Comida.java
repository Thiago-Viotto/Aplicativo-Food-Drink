package br.unicamp.ft.t187583_a165484.navigationprojeto.Model;

public class Comida {
    private int id;
    private String nome;
    private int foto;
    private String descricao;
    private double preco;
    private int quantidade = 1;

    public Comida(){

    }

    Comida(int id,String nome,int foto,String descricao){
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.descricao = descricao;
    }

    Comida(int id,String nome,int foto,String descricao, double preco){
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.descricao = descricao;
        this.preco = preco;
    }

    Comida(int id,String nome,String descricao){
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.descricao = descricao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString(){
        return  "Produto: "+nome + "\n"+"Descrição: "+descricao +"\n"+"Preço: "+ preco * quantidade + "\n" + "Quantidade: "+quantidade+"\n";
    }
}
