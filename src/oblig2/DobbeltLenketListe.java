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

    private Node<T> finnNode(int indeks){
        Node<T> current = null;
        if(indeks < antall/2){
            current = hode;
            // Begynn på hodet, .neste gjennom
            for(int i=0;i<indeks;i++){
                current=current.neste;
            }
        }else{
            current = hale;
            // Begynn på hale, .forrige gjennom
            for(int i=antall-1;i>indeks;i--){
                current=current.forrige;
            }
        }
        return current;
    }

    public Liste<T> subliste(int fra, int til){
        //Lag LL som itererer fra int fra til int til.
        return null;
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
        Node<T> node = null;
        Node<T> current = hode;
        if(innerList.size()==0){
            node = new Node<>(Objects.requireNonNull(verdi));
            hode=node;
            innerList.add(node);
        }
        else if(innerList.size()>0){
            while(current.neste!=null){
                current=current.neste;
            }
            node = new Node<>(Objects.requireNonNull(verdi));
            innerList.add(node);
            node.forrige=current;
            current.neste=node;
        }
        hale = node;
        return true;
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
        indeksKontroll(indeks, false);
        T hentetNode= (T) finnNode(indeks).verdi;
        return hentetNode;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        if(nyverdi==null){
            throw new NullPointerException("nyverdi er null!");
        }
        T gammelVerdi = hent(indeks);
        finnNode(indeks).verdi = nyverdi;
        endringer++;
        return gammelVerdi;
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


