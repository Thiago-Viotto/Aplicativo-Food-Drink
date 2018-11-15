package br.unicamp.ft.t187583_a165484.navigationprojeto.Model;

public class ComidasBebidas {

    private int id;
    private String nome;
    private int foto;
    private String descricao;
    private double preco;
    private int quantidade = 1;

    public ComidasBebidas(int id, String nome, int foto, String descricao, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
