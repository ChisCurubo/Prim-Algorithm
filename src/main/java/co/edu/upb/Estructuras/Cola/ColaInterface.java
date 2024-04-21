package co.edu.upb.Estructuras.Cola;

import java.io.Serializable;

public interface ColaInterface<T extends Serializable> {
    public boolean clear();

    public boolean isEmpty();

    public T peek();

    public T extraer();

    public boolean insertar(T object);

    public int size();

    public boolean search(T object);

    public boolean sort();

    public boolean reverse();

    public String toString();
}
