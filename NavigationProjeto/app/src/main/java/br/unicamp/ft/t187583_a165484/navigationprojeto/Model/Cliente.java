package br.unicamp.ft.t187583_a165484.navigationprojeto.Model;

public class Cliente {
    private String id;
    private String nome;
    private String tipoPessoa;
    private String CPF;
    private String data;
    private String endereco;
    private String cidade;
    private String telefone;
    private String estado;
    private String pais;
    private String email;
    private String senha;


    Cliente(){

    }

    public Cliente(String id, String nome, String tipoPessoa, String CPF, String data, String endereco, String cidade, String telefone, String estado, String pais, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
        this.CPF = CPF;
        this.data = data;
        this.endereco = endereco;
        this.cidade = cidade;
        this.telefone = telefone;
        this.estado = estado;
        this.pais = pais;
        this.email = email;
        this.senha = senha;
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

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
