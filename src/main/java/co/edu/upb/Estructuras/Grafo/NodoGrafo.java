package co.edu.upb.Estructuras.Grafo;


import co.edu.upb.Estructuras.ListaEnlazadaDoble.Inferface.NodeInterface;
import co.edu.upb.Estructuras.ListaEnlazadaDoble.LinkedList;
import co.edu.upb.Estructuras.ListaEnlazadaDoble.DoubleListNode;
import java.io.Serializable;
import java.util.Iterator;

public class NodoGrafo implements Serializable {

    private boolean visitado;
    private String nombre;

    private LinkedList<Arista> conexiones;

    public NodoGrafo(String nombre) {
        this.nombre = nombre;
        this.conexiones = new LinkedList<>();
        this.visitado = false;
    }

    public LinkedList<Arista> getConexiones() {
        return conexiones;
    }
    public String getNombre() {
        return nombre;
    }

    public void addConexion(NodoGrafo conexion, int peso) {
        conexiones.add(new Arista(this, conexion, peso));
        // Agregar la conexión en la dirección opuesta
        conexion.conexiones.add(new Arista(conexion, this, peso));
    }
    public void addConexionSimple(NodoGrafo conexion, int peso) {
        conexiones.add(new Arista(this, conexion, peso));
    }

    public int getNumeroConexiones() {
        return conexiones.size();
    }
    public void setConexiones(LinkedList<Arista> conexiones){
        this.conexiones = conexiones;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    public boolean isVisitado() {
        return visitado;
    }

    @Override
    public String toString(){
        return nombre;
    }
}