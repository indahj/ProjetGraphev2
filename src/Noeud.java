import java.util.LinkedList;

public class Noeud {
    private int id;
    LinkedList<Arc> succ=new LinkedList<Arc>();
    boolean mark;


    public Noeud(int id) {
        this.id=id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }


    public 	LinkedList<Arc> getSuccesseurs(){
        return this.succ;
    }

    public boolean isMark() {
        return mark;
    }


    public void setMark(boolean mark) {
        this.mark = mark;
    }

    /*---------------définition d'un successeur sur un noeuds----------*/
    public void setSuccesseurs(LinkedList<Arc> succ) {
        this.succ = succ;
    }

    /*---------------le noeud a t'il un successeur?----------*/
    public boolean hasSuccesseur(int j) {
        LinkedList<Integer> cibles = new LinkedList<Integer>();
        boolean trouve=false;

        for(Arc c:succ) {
            cibles.add(c.getCible().getId());
        }
        for(int i:cibles) {
            if(i==j) {
                trouve=true;
            }

        }
        return trouve;


    }

    public String toString() {
        String successeurs="";
        for(Arc n:this.succ) {

            successeurs=successeurs+n.getCible().getId()+" ";
        }
        if(!this.succ.isEmpty()) {
            return id+ " ---> "+ successeurs;
        }else {
            return id+"";
        }
    }
   int couleur;
    public int getDegre() {
        int cpt=0;
        for(Arc arc : succ) {
            cpt++;
        }
        return cpt;
    }

    /*---------------récupération de l'identifiant de la couleur----------*/
    public int getCouleur() {
        return couleur;
    }

    /*---------------le noeud a t'il une couleur?----------*/
    public boolean hasCouleur() {
        return couleur != -1;
    }

    /*---------------définir la couleur du noeuds----------*/
    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public boolean hasUnmarkedSuccessor() {
        for (Arc a : getSuccesseurs()) {
            if (!a.getCible().isMark()) {
                return true;
            }
        }
        return false;
    }

}
