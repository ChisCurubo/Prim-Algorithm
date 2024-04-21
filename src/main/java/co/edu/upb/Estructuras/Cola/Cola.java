package co.edu.upb.Estructuras.Cola;


import co.edu.upb.Estructuras.ListaEnlazadaDoble.LinkedList;

import java.io.Serializable;

/**
 * @author JuanDavidCarvajal
 * @param <T>
 */
public class Cola<T extends Serializable> implements ColaInterface<T>, Serializable{
    LinkedList<T> contenedor = new LinkedList<>();

    @Override
    public boolean clear(){
        return contenedor.clear();
    }

    @Override
    public T peek(){
        return contenedor.get();
    }

    @Override
    public T extraer() {
        return contenedor.pop();
    }

    @Override
    public boolean insertar(T object) {
        return contenedor.add(object);
    }

    @Override
    public boolean isEmpty(){
        return contenedor.isEmpty();
    }

    @Override
    public int size(){
        return contenedor.size();
    }

    @Override
    public boolean search(T object){
        return contenedor.contains(object);
    }

    @Override
    public boolean sort() {
        return false;
    }

    @Override
    public boolean reverse() {
        return false;
    }

    public void imprimir(){
        contenedor.imprimir2();
    }

    @Override
    public String toString() {
        if (isEmpty()){
            return "";
        }
        return contenedor.toString();
    }
}
