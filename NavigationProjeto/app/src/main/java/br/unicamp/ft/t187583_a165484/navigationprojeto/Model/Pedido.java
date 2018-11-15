package br.unicamp.ft.t187583_a165484.navigationprojeto.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable {

    private ArrayList<Bebida> bebidas;
    private ArrayList<Comida> comidas;
    private String idCliente;
    private double total;

    public ArrayList<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Bebida> bebidas) {
        this.bebidas = bebidas;
    }

    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    public Pedido(ArrayList<Bebida> bebidas, ArrayList<Comida> comidas, String idCliente, double total) {
        this.bebidas = bebidas;
        this.comidas = comidas;
        this.idCliente = idCliente;
        this.total = total;
    }

    public Pedido(){
        comidas = new ArrayList<Comida>();
        bebidas = new ArrayList<Bebida>();
    }

    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
