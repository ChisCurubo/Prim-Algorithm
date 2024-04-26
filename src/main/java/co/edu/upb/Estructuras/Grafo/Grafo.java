package co.edu.upb.Estructuras.Grafo;

import co.edu.upb.Estructuras.Cola.Cola;
import co.edu.upb.Estructuras.ListaEnlazadaDoble.LinkedList;
import co.edu.upb.Estructuras.ListaEnlazadaDoble.Inferface.NodeInterface;
import java.util.Iterator;
import java.util.Random;

public class Grafo {
    private LinkedList<NodoGrafo> grafo;

    public Grafo() {
        this.grafo = new LinkedList<>();
        cargarGrafo();
    }
    public void cargarGrafo(){
        NodoGrafo A = new NodoGrafo("A");
        NodoGrafo B = new NodoGrafo("B");
        NodoGrafo C = new NodoGrafo("C");
        NodoGrafo D = new NodoGrafo("D");
        NodoGrafo E = new NodoGrafo("E");
        NodoGrafo F = new NodoGrafo("F");
        NodoGrafo G = new NodoGrafo("G");
        NodoGrafo H = new NodoGrafo("H");
        NodoGrafo I = new NodoGrafo("I");
        NodoGrafo J = new NodoGrafo("J");
        NodoGrafo K = new NodoGrafo("K");

        C.addConexion(K, 5);
        C.addConexion(B, 10);
        C.addConexion(D, 9);

        K.addConexion(J, 8);
        K.addConexion(A, 3);
        K.addConexion(B, 7);

        A.addConexion(J, 12);
        A.addConexion(I, 6);
        A.addConexion(H, 10);
        A.addConexion(G, 9);
        A.addConexion(B, 8);

        J.addConexion(I, 10);
        I.addConexion(H, 3);
        H.addConexion(G, 7);
        B.addConexion(E, 2);

        G.addConexion(E, 6);
        G.addConexion(F, 8);

        E.addConexion(F, 10);

        D.addConexion(E, 13);
        D.addConexion(F, 12);

        this.grafo.add(A);
        this.grafo.add(B);
        this.grafo.add(C);
        this.grafo.add(D);
        this.grafo.add(E);
        this.grafo.add(F);
        this.grafo.add(G);
        this.grafo.add(H);
        this.grafo.add(I);
        this.grafo.add(J);
        this.grafo.add(K);
    }

    private int nodosVisitados;
    public void rutaDFS(String nombreNodoInicial) {
        NodoGrafo nodoInicial = nodeOf(nombreNodoInicial);

        if (nodoInicial != null){
            if (nodoInicial.getNumeroConexiones() > 0){
                this.nodosVisitados = 0;
                DFS(nodoInicial);
                System.out.println("\nNodos visitados: " + nodosVisitados);
                resetearEstadoDeVisita();
            }
            else System.out.println("No es un nodo valido para iniciar.");
        }
        else System.out.println("Nodo no encontrado");

    }
    public void rutaDFS(NodoGrafo nodoInicial) {
        //Prioridad a la derecha
        if (nodoInicial != null){
            if (nodoInicial.getNumeroConexiones() > 0){
                this.nodosVisitados = 0;
                DFS(nodoInicial);
                System.out.println("\nNodos visitados: " + nodosVisitados);
                resetearEstadoDeVisita();
            }
            else System.out.println("No es un nodo valido para iniciar.");
        }
        else System.out.println("Nodo no encontrado");

    }
    private void DFS(NodoGrafo nodo){
        if (!nodo.isVisitado()){
            System.out.print(nodo.getNombre() + "  ");
            nodo.setVisitado(true);
            nodosVisitados++;
            LinkedList<Arista> conexiones = nodo.getConexiones();
            Iterator<NodeInterface<Arista>> iterador = conexiones.iterator();
            while (iterador.hasNext()){
                //Iterar todas las conexiones del nodo
                DFS(iterador.next().getObject().getDestino());
            }
        }
    }

    public void rutaBFS(String nombreNodoInicial){
        NodoGrafo nodoInicial = nodeOf(nombreNodoInicial);
        if (nodoInicial != null){
            Cola<NodoGrafo> cola = new Cola<>();
            cola.insertar(nodoInicial);
            nodoInicial.setVisitado(true);
            this.nodosVisitados = 0;
            BFS(cola);
            System.out.println("\nNodos visitados: " + nodosVisitados);
            resetearEstadoDeVisita();
        }
        else System.out.println("Nodo no encontrado.");
    }
    private void BFS(Cola<NodoGrafo> cola){
        NodoGrafo nodoIterador = cola.extraer();
        nodosVisitados++;
        Iterator<NodeInterface<Arista>> iterador = nodoIterador.getConexiones().iterator();
        while (iterador.hasNext()){
            NodoGrafo nodo = iterador.next().getObject().getDestino();
            if (!nodo.isVisitado()){
                nodo.setVisitado(true);
                cola.insertar(nodo);
            }
        }
        if (!cola.isEmpty()){
            System.out.print(nodoIterador.getNombre() + " - ");
            BFS(cola);
        }
        else System.out.print(nodoIterador.getNombre());

    }

    // Método para restablecer el estado de visitado en todos los nodos del grafo
    private void resetearEstadoDeVisita() {
        Iterator<NodeInterface<NodoGrafo>> iterador = grafo.iterator();
        while (iterador.hasNext()) {
            NodoGrafo nodo = iterador.next().getObject();
            nodo.setVisitado(false);
        }
    }

    public void generarGrafoDirigido(int numNodos, double densidad){
        this.grafo.clear();
        int limite = (int) (numNodos*densidad);
        System.out.println("|Grafo|   Nodos:" + numNodos + "  Aristas:" + limite + "  Den:(" + densidad + ")");
        Random rand = new Random();

        NodoGrafo[] nodos = new NodoGrafo[numNodos];

        for (int i=0; i < numNodos; i++){
            NodoGrafo nodo = new NodoGrafo((i + 1) + "");
            this.grafo.add(nodo);
            nodos[i] = nodo;
        }
        int index;
        Iterator<NodeInterface<NodoGrafo>> nodeIterator = this.grafo.iterator();
        while (nodeIterator.hasNext()){
            NodoGrafo nodo = nodeIterator.next().getObject();
            index = rand.nextInt(numNodos);
            while (nodos[index].getNombre().equals(nodo.getNombre()) || checkConex(nodo, nodos[index])){
                index++;
                if (index >= numNodos-1) index = 0;
            }
            nodo.addConexionSimple(nodos[index], 1);
        }

        limite -= numNodos;
        for (int i=0; i<limite; i++){
            NodoGrafo nodo = nodos[rand.nextInt(numNodos)];
            index = rand.nextInt(numNodos);
            while (nodos[index].getNombre().equals(nodo.getNombre()) || checkConex(nodo, nodos[index])){
                index++;
                if (index >= numNodos-1) index = 0;
            }
            nodo.addConexionSimple(nodos[index], rand.nextInt(20));
        }
    }
    private boolean checkConex(NodoGrafo nodoA, NodoGrafo nodoB){
        //Verificar si nodoA esta conectado a nodoB
        Iterator<NodeInterface<Arista>> iterator = nodoA.getConexiones().iterator();
        while (iterator.hasNext()){
            if (iterator.next().getObject().getDestino().equals(nodoB)) return true;
        }
        return false;
    }
    public void generarGrafoNoDirigido(int numNodos, double densidad){
        this.grafo.clear();
        int limite = (int) (numNodos*densidad);
        System.out.println("|Grafo|   Nodos:" + numNodos + "  Aristas:" + limite + "  Den:(" + densidad + ")");
        Random rand = new Random();

        NodoGrafo[] nodos = new NodoGrafo[numNodos];

        for (int i=0; i < numNodos; i++){
            NodoGrafo nodo = new NodoGrafo((i + 1) + "");
            this.grafo.add(nodo);
            nodos[i] = nodo;
        }
        int index;
        Iterator<NodeInterface<NodoGrafo>> nodeIterator = this.grafo.iterator();
        while (nodeIterator.hasNext()){
            NodoGrafo nodo = nodeIterator.next().getObject();
            index = rand.nextInt(numNodos);
            while (nodos[index].getNombre().equals(nodo.getNombre()) || checkConex(nodo, nodos[index])){
                index++;
                if (index >= numNodos-1) index = 0;
            }
            nodo.addConexion(nodos[index], 1);
        }

        limite -= numNodos;
        for (int i=0; i<limite; i++){
            NodoGrafo nodo = nodos[rand.nextInt(numNodos)];
            index = rand.nextInt(numNodos);
            while (nodos[index].getNombre().equals(nodo.getNombre()) || checkConex(nodo, nodos[index])){
                index++;
                if (index >= numNodos-1) index = 0;
            }
            nodo.addConexion(nodos[index], 1);
        }
    }

    public void imprimirGrafo() {
        Iterator<NodeInterface<NodoGrafo>> nodeIterator = this.grafo.iterator();
        while (nodeIterator.hasNext()){
            NodoGrafo nodo = nodeIterator.next().getObject();
            System.out.print(nodo.getNombre() + " ->  [  ");

            Iterator<NodeInterface<Arista>> aristaIterator = nodo.getConexiones().iterator();
            Arista aristaTemp;
            while (aristaIterator.hasNext()){
                aristaTemp = aristaIterator.next().getObject();
                System.out.print(aristaTemp.getDestino().getNombre());
                System.out.print("(" + aristaTemp.getPeso() + ") ");
            }
            System.out.println(" ]");
        }
    }

    public NodoGrafo nodeOf(String nombre){
        NodoGrafo nodo;
        Iterator<NodeInterface<NodoGrafo>> iterador = this.grafo.iterator();
        while (iterador.hasNext()){
            nodo = iterador.next().getObject();
            if (nodo.getNombre().equals(nombre)) return nodo;
        }
        return null;
    }

    public void prim(String nombreNodoInicial){
        if (!grafo.isEmpty()){
            NodoGrafo tree = nodeOf(nombreNodoInicial);
            if (tree != null){
                Prim prim = new Prim();
                tree = prim.prim(nodeOf(nombreNodoInicial));
                rutaDFS(tree);
                resetearEstadoDeVisita();
            }
            else System.out.println("Nodo no encontrado.");
        }
    }

}