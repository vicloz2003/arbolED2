/*<
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebafinal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author sin usuario
 */

public class BinarioArbol<K extends Comparable<K>,V> {
    protected NodoBinario<K,V> raiz;

    public BinarioArbol() {
        raiz = null;
    }

    // Método para insertar un nuevo nodo en el árbol binario
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if(claveAInsertar==null){
            throw new IllegalArgumentException("Clave no puede ser nula");
        }
        if (valorAInsertar==null){
            throw new IllegalArgumentException("Valor no puede ser nula");
        }
        if (this.esArbolVacio()){
            this.raiz=new NodoBinario<>(claveAInsertar,valorAInsertar);
            return ;
        }
        NodoBinario<K,V>nodoActual=this.raiz;
        NodoBinario<K,V> nodoAnterior=(NodoBinario<K,V>)NodoBinario.nodoVacio();
        
        while(!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual=nodoActual.getClave();
            nodoAnterior=nodoActual;
            if(claveAInsertar.compareTo(claveActual)<0){
                nodoActual = nodoActual.getHijoIzquierdo();  
            }else if (claveAInsertar.compareTo(claveActual)>0){
                nodoActual= nodoActual.getHijoDerecho();
            }else{
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }
        //si llego hasta este punt, quiere decir que no existe en el arbol
        //la clave, entonces debo crear un nodo, con la clave y valor a insertar
        // y el nodoAnterior es el padre de ese nuevo nodo
        NodoBinario<K,V> nuevoNodo=new NodoBinario<>(claveAInsertar,valorAInsertar);
        K claveDelPadre=nodoAnterior.getClave();
        //nodoAnterior=nodoActual;
        if(claveAInsertar.compareTo(claveDelPadre)<0){
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }else{
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    // Método para imprimir el árbol en orden
    private void inorderTraversal(NodoBinario raiz) {
        if (raiz != null) {
            inorderTraversal(raiz.getHijoIzquierdo());
            System.out.print(raiz.getClave() + " ");
            inorderTraversal(raiz.getHijoDerecho());
        }
    }

    public void printTree() {
        inorderTraversal(raiz);
        System.out.println();
    }
    public NodoBinario getRoot(){
        return this.raiz;
    }
     protected static void printTreeWithArrows(NodoBinario raiz, String prefix, boolean isLeft) {
        if (raiz != null) {
            System.out.println(prefix + (isLeft ? "|-- " : "|-- ") + raiz.getClave());
            printTreeWithArrows(raiz.getHijoIzquierdo(), prefix + (isLeft ? "|   " : "    "), true);
            printTreeWithArrows(raiz.getHijoDerecho(), prefix + (isLeft ? "|   " : "    "), false);
        }
    }
     public V buscar(K claveABuscar) {
        if(claveABuscar==null){
            throw new IllegalArgumentException("Clave no puede ser nula");
            
        }
        if (this.esArbolVacio()){
            return null;
        }
        NodoBinario<K,V> nodoActual = this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual=nodoActual.getClave();
            if(claveABuscar.compareTo(claveActual)==0){
                return nodoActual.getValor();
            }else if (claveABuscar.compareTo(claveActual)<0){
                nodoActual=nodoActual.getHijoIzquierdo();
            }else{
                nodoActual=nodoActual.getHijoDerecho();
            }
        }
        //si llega a este punto quiere decir que no se encuentra la clave a buscar
        //en el arbol
        return null;
    }
     public List<K> recorridoEnInOrden() {
        List<K> recorrido =new ArrayList<>();
        //para una implementacion recursiva se necesita
        //un metodo amigo que haga el grueso del trabajo
        recorridoEnInOrden(this.raiz,recorrido);
        return recorrido;
    }
     private void recorridoEnInOrden(NodoBinario<K, V> nodoActual, List<K> recorrido) {
         if(NodoBinario.esNodoVacio(nodoActual)){
             return ;
         }
         recorridoEnInOrden(nodoActual.getHijoIzquierdo(),recorrido);
         recorrido.add(nodoActual.getClave());
         recorridoEnInOrden(nodoActual.getHijoDerecho(),recorrido);
         
     }
         public List<K> recorridoEnPreOrden() {
    List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodo = new Stack<>();
        pilaDeNodo.push(this.raiz);
        while (!pilaDeNodo.isEmpty()) {
            NodoBinario<K,V>nodoActual= pilaDeNodo.pop();
            recorrido.add(nodoActual.getClave());
            if (!nodoActual.esVacioHijoIzquierdo()){
                pilaDeNodo.push(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho()){
                pilaDeNodo.push(nodoActual.getHijoDerecho());
            } 
        }
        return recorrido;   
    }
     public List<K> recorridoEnPostOrdenRec() {
        List<K> recorrido =new ArrayList<>();
        //para una implementacion recursiva se necesita
        //un metodo amigo que haga el grueso del trabajo
        recorridoEnPostOrdenRec(this.raiz,recorrido);
        return recorrido;
    }
     private void recorridoEnPostOrdenRec(NodoBinario<K, V> nodoActual, List<K> recorrido) {
         if(NodoBinario.esNodoVacio(nodoActual)){
             return ;
         }
         recorridoEnPostOrdenRec(nodoActual.getHijoIzquierdo(),recorrido);
         recorridoEnPostOrdenRec(nodoActual.getHijoDerecho(),recorrido);

         recorrido.add(nodoActual.getClave());
         
     }
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()){
            
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        do {
            NodoBinario<K,V>nodoActual= colaDeNodos.poll();
            recorrido.add(nodoActual.getClave());
            if (!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho()){
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            } 
        }while (!colaDeNodos.isEmpty());
        }
        return recorrido;
    }
    public boolean contiene(K clave) {
        return this.buscar(clave)!=null;
    }
     public void inicializar(BinarioArbol arbol){
        arbol.insertar(221043977,"");
        arbol.insertar(215680210,"");
        arbol.insertar(230091091,"");
        arbol.insertar(989171214,"");
        arbol.insertar(202108411,"");
        arbol.insertar(222309901,"");
        arbol.insertar(216979481,"");
        
        
        arbol.insertar(219194111,"");
        arbol.insertar(239522081,"");
        arbol.insertar(810834011,"");
        arbol.insertar(129421232,"");
     }
}