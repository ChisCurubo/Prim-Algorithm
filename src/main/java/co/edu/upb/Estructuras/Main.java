package co.edu.upb.Estructuras;

import co.edu.upb.Estructuras.Grafo.Grafo;


public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        //grafo.imprimirGrafo();

        grafo.prim("C");
        System.out.println("--- Grafo ---");
        grafo.imprimirGrafo();


        //C  K  A  I  H  B  E  G  F  J  D
        /*
        (C-K)
        (K-A)
        (A-I)
        (I-H)
        (K-B)
        (B-E)
        (E-G)
        (K-J)
        (G-F)
        (C-D)
         */
    }

}
