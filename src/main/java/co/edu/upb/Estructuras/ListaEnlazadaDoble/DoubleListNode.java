package co.edu.upb.Estructuras.ListaEnlazadaDoble;

import java.io.Serializable;

/**
 * @author JuanDavidCarvajal
 * @param <T>
 */
public class DoubleListNode<T extends Serializable> extends Node<T> implements Serializable{

    private DoubleListNode<T> next;
    private DoubleListNode<T> prev;

    //Constructores
    public DoubleListNode(T object){
        super(object);
    }
    public DoubleListNode(){
        super();
    }

    //Metodos
    public DoubleListNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleListNode<T> next) {
        this.next = next;
    }

    public DoubleListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoubleListNode<T> prev) {
        this.prev = prev;
    }
}
