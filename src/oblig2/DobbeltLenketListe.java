package oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;

import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }

        @Override
        public String toString() {
            return verdi.toString();
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen
    private ArrayList<Node <T>> innerList;

    public DobbeltLenketListe() {
        innerList = new ArrayList<Node<T>>();
    }

    public DobbeltLenketListe(T[] a) {
        if(a==null){
            throw new NullPointerException("Tabellen a er null!");
        }
        innerList = new ArrayList<Node<T>>();
        Node<T> node = null;

        for(int i = 0;i<a.length;i++){
            if(a[i] == null){
                continue;
            }
            node = new Node<>(a[i]);
            antall++;
            innerList.add(node);
            if(innerList.size()==1){
                hode = node;
            }
            else if(innerList.size()>1){
                node.forrige=hale;
                hale.neste=node;
            }
            hale=node;
        }
    }

    public Liste<T> subliste(int fra, int til){
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return innerList.size();
    }

    @Override
    public boolean tom() {
        return innerList.size()==0;
    }

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        Node<T> current = hode;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(current!=null){
            sb.append(current.toString()+", ");
            current=current.neste;
        }
        if(sb.length()>2){
            sb.delete(sb.length()-2,sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    public String omvendtString() {
        Node<T> current = hale;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(current!=null){
            sb.append(current.toString()+", ");
            current=current.forrige;
        }
        if(sb.length()>2){
            sb.delete(sb.length()-2,sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


