
public class Arc {

    private Noeud source;
    private Noeud cible;


    public Arc(Noeud x,Noeud y) {
        this.source=x;
        this.cible=y;
    }

    public String toString() {
        return "Source : "+source.getId()+"\nCible : "+cible.getId();
    }

    public Noeud getSource() {
        return this.source;
    }

    public Noeud getCible() {
        return this.cible;
    }



}
