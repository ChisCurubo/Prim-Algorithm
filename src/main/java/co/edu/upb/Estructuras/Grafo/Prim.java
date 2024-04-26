package co.edu.upb.Estructuras.Grafo;

import co.edu.upb.Estructuras.ListaEnlazadaDoble.Inferface.NodeInterface;
import co.edu.upb.Estructuras.ListaEnlazadaDoble.LinkedList;
import java.util.Iterator;

public class Prim {
    private LinkedList<Arista> colaConexiones;
    private LinkedList<NodoGrafo> nodesTree;

    public Prim(){
        colaConexiones = new LinkedList<>();
        nodesTree = new LinkedList<>();
    }

    public NodoGrafo prim(NodoGrafo nodoInicial){
        Arista aristaActual;
        NodoGrafo treeHead = cloneNode(nodoInicial);
        NodoGrafo auxTree;
        Iterator<NodeInterface<Arista>> iterator = nodoInicial.getConexiones().iterator();
        while (iterator.hasNext())
            addCola(colaConexiones, iterator.next().getObject());

        while (!colaConexiones.isEmpty()){
            //Tomar arista con menos peso
            aristaActual = popCola();
            auxTree = cloneNode(aristaActual.getRoot());

            auxTree.addConexionSimple(cloneNode(aristaActual.getDestino()), aristaActual.getPeso());
            iterator = aristaActual.getDestino().getConexiones().iterator();
            //Ingresar todas las aristas en la cola
            while (iterator.hasNext()){
                addCola(colaConexiones, iterator.next().getObject());
            }
            aristaActual.getRoot().setVisitado(true);
            aristaActual.getDestino().setVisitado(true);
            actualizarCola();
        }
        return treeHead;
    }

    private void actualizarCola(){
        // Eliminar aristas que conecten a nodos ya visitados para evitar ciclos
        Iterator<NodeInterface<Arista>> iterator = colaConexiones.iterator();
        while (iterator.hasNext()){
            Arista temp = iterator.next().getObject();
            if (temp.getDestino().isVisitado())
                colaConexiones.remove(temp);
        }
    }

    private void addCola(LinkedList<Arista> conexiones, Arista aristaNueva){
        if (aristaNueva.getDestino().isVisitado()){
            return;
        }
        /* ELIMINAR ARISTAS QUE APUNTEN AL MISMO NODO
           Revisar si ya hay una arista con ese destino y dejar la que tenga menos peso
         */
        Iterator<NodeInterface<Arista>> iterator = conexiones.iterator();
        Arista aristaTemp;
        while (iterator.hasNext()){
            aristaTemp = iterator.next().getObject();
            if (aristaTemp.getDestino() == aristaNueva.getDestino()){
                if (aristaTemp.getPeso() > aristaNueva.getPeso()){
                    conexiones.remove(aristaTemp);
                    conexiones.add(aristaNueva);
                }
                return;
            }
        }
        conexiones.add(aristaNueva);
    }

    private Arista popCola(){
        Iterator<NodeInterface<Arista>> iterator = colaConexiones.iterator();
        Arista aristaMenor = iterator.next().getObject();
        Arista aristaTemp;
        while (iterator.hasNext()){
            aristaTemp = iterator.next().getObject();
            if (aristaTemp.getPeso() < aristaMenor.getPeso()){
                aristaMenor = aristaTemp;
            }
        }
        colaConexiones.remove(aristaMenor);
        return aristaMenor;
    }

    private NodoGrafo cloneNode(NodoGrafo nodo){
        Iterator<NodeInterface<NodoGrafo>> iterator = nodesTree.iterator();
        while (iterator.hasNext()){
            NodoGrafo temp = iterator.next().getObject();
            if (temp.getNombre().equals(nodo.getNombre())){
                return temp;
            }
        }
        nodesTree.add(new NodoGrafo(nodo.getNombre()));
        return nodesTree.getFromEnd();
    }

}