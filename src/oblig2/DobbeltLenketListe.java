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
        if(indeks>=antall){
            throw new IndexOutOfBoundsException("indeks er høyere ann antall noder!");
        }
        Node<T> current = null;
        if(antall == 1 && indeks == 0) return hode;
        if(indeks < antall/2){
            // Begynn på hodet, .neste gjennom
            for(int i=-1;i<indeks;i++){
                if(i == -1) {
                    current = hode;

                }else{
                    current=current.neste;
                }
            }
        }else{
            // Begynn på hale, .forrige gjennom
            for(int i=antall;i>indeks;i--){
                if(i == antall){
                    current = hale;
                }else{
                    if(current.forrige!=null){
                        current=current.forrige;
                    }
                }
            }
        }
        return current;
    }

    public Liste<T> subliste(int fra, int til){
        if (fra > til) {
            throw new IllegalArgumentException ("fra kan ikke være større enn til.");
        } else if (fra < 0 || til < 0 || fra > antall || til > antall) {
            throw new IndexOutOfBoundsException("Ugyldig(e) indeks(er)");
        }
        DobbeltLenketListe<T> returnList = new DobbeltLenketListe<T>();
        Node<T> node = null;
        for(int i=fra; i<til; i++) {
            node = finnNode(i);
            returnList.leggInn((T) node);
        }
        return returnList;
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
            antall++;
            endringer++;
            innerList.add(node);
        }
        else if(innerList.size()>0){
            while(current.neste!=null){
                current=current.neste;
            }
            node = new Node<>(Objects.requireNonNull(verdi));
            antall++;
            endringer++;
            innerList.add(node);
            node.forrige=current;
            current.neste=node;
        }
        hale = node;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Node<T> node = new Node<>(Objects.requireNonNull(verdi));
        if(verdi == null){
            throw new NullPointerException("verdi kan ikke være null");
        }
        if(indeks>=antall || indeks<0){
            throw new IndexOutOfBoundsException("indeks er ugyldig");
        }
        if(antall==0){
            leggInn(verdi);
        }else {

            Node<T> forrigeNode = finnNode(indeks - 1);
            Node<T> nesteNode = finnNode(indeks + 1);

            node.neste = nesteNode;
            node.forrige = forrigeNode;
            if(indeks!=0){
                forrigeNode.neste = node;
                nesteNode.forrige = node;
            }
        }
        antall++;
        endringer++;
        innerList.add(node);
    }

    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi) == -1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> hentetNode= finnNode(indeks);
        return hentetNode.verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if(verdi==null){
            return -1;
        }
        for(int i = 0;i<antall;i++){
            if(hent(i).equals(verdi)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        if(indeks > antall){
            throw new IndexOutOfBoundsException("indeks er høyere enn antall!");
        }
        if(nyverdi==null){
            throw new NullPointerException("nyverdi er null!");
        }
        T gammelVerdi = hent(indeks);
        finnNode(indeks).verdi = nyverdi;
        endringer++;
        return gammelVerdi;
    }

    private void fjernNode(Node<T> node){
        if(antall == 0) throw new NullPointerException();
        if(antall == 1) {
            hode = null;
            hale = null;
        }else if(node.forrige == null){//hode
            node.neste.forrige = null;
            hode= node.neste;
        }
        else if(node.neste == null){//hale
            node.forrige.neste = null;
            hale = node.forrige;
        }else{
            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }

        antall--;
        innerList.remove(innerList.size()-1);
        endringer++;
    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null){
            return false;
        }
        Node<T> forrigeNode = null;
        Node<T> node = hode;

        while(node != null) {
            if (node.verdi.equals(verdi)) {
                fjernNode(node);
                return true;
            }

            node = node.neste;

        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        if(indeks>=antall || indeks<0){
            throw new IndexOutOfBoundsException("indeks er ugyldig");
        }
        Node<T>slettNode = finnNode(indeks);
        T verdi= slettNode.verdi;
        fjernNode(slettNode);
        return verdi;
    }

    @Override
    public void nullstill() {
        this.hode = null;
        this.hale = null;
        antall = 0;
        innerList.clear();
        endringer++;
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
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks,false);
        return new DobbeltLenketListeIterator(indeks);
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
            denne = hode;     // p starter på den første i listen

            int teller = 0;
            while(teller<indeks){
                teller++;
                if(denne.neste!=null){
                    denne=denne.neste;
                }
            }

            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            if(iteratorendringer!=endringer){
                throw new ConcurrentModificationException("iteratorendringer er ikke lik endringer");
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            fjernOK = true;
            T verdi = denne.verdi;
            denne = denne.neste;
            return verdi;
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


