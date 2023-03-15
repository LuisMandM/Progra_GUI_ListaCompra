package com.ikasgela;

public class Item {

    private String producto;
    private int unidades = 0;

    public Item(String producto, int unidades) {
        this.producto = producto;
        this.unidades = unidades;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        if (unidades == 0) return producto;
        else return String.format("%-3s(%-1d)", producto, unidades);

    }
}
