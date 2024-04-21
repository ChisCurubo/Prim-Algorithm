package co.edu.upb.Estructuras.Cola;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author JuanDavidCarvajal
 * @param <T>
 */
public class ColaPrioridad<T extends Serializable> implements Serializable {
    Cola<T>[] array;
    int contador;

    public ColaPrioridad(int size){
        array = new Cola[size];
        for (int i=0; i<size; i++){
            array[i] = new Cola<>();
        }
        contador = 0;
    }

    public boolean add(T object, int prioridad){
        if ((prioridad >= 0) && (prioridad<array.length)){
            contador++;
            return array[prioridad].insertar(object);
        }
        return false;
    }

    public T extraer(){
        try {
            for (int i = array.length - 1; i >= 0 ; i--){
                if (!array[i].isEmpty()){
                    return array[i].extraer();
                }
            }
        }
        catch (Exception e){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(),e);
        }
        return null;
    }

    public boolean isEmptyLastPriority(){
        return array[array.length - 1].isEmpty();
    }

    @Override
    public String toString(){
        String text = "";

        for (int i = array.length-1; i >= 0; i--){
            text += array[i].toString();
        }

        return text;
    }

    public boolean isEmpty() {
        for (int i=0; i<array.length; i++){
            if (!array[i].isEmpty()){
                return false;
            }
        }
        return true;
    }
}
