package co.edu.upb.Estructuras.Grafo;

import java.io.Serializable;

public class Arista implements Serializable {
    private NodoGrafo destino;
    private NodoGrafo root;
    private int peso;

    public Arista(NodoGrafo root, NodoGrafo destino, int peso) {
        this.root = root;
        this.destino = destino;
        this.peso = peso;
    }

    public int getPeso() {
        return this.peso;
    }
    public NodoGrafo getRoot(){
        return root;
    }

    public NodoGrafo getDestino() {
        return destino;
    }

    @Override
    public String toString(){
        //return "(" + root + "-" + destino + ")";
        return "  (" + peso + ") " + root + "-" + destino;
    }
}