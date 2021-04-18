import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DegreDecroissant {
	
	public Graphe graphe;

	public DegreDecroissant(Graphe graphe) {
		// TODO Auto-generated constructor stub
		this.graphe = graphe;
		graphe.reinitNoeuds();
		this.colorationDegreDecroissant();
	}
	
	public void colorationDegreDecroissant() {

		int degreMax = Integer.MIN_VALUE; 
		Noeud noeudMax = null;
		
		for(Noeud n : graphe.getNoeuds()) {
			if(n.getDegre() > degreMax) {
				degreMax = n.getDegre();
				noeudMax = n;
			}
		}		

		System.out.println("Nombre de couleur trouv�e par colorationDegreDecroissant : "+(parcoursDegreDecroissant(noeudMax,0)+1));
	}
	
	public int parcoursDegreDecroissant (Noeud noeudMax, int couleurMax) {
		
		ArrayList<Noeud> voisins = new ArrayList<Noeud>();
		
		
		List<Integer> couleursUtilisees = new ArrayList<>();
		// On recupere tous les voisins du noeud et on les met dans une liste et on regarde la couleur qu'il utilise
		LinkedList<Arc> listelien = noeudMax.getSuccesseurs();
		for(Arc arc : listelien) {
			Noeud cible = arc.getCible();
			voisins.add(cible);
			if(cible.hasCouleur()) {
				couleursUtilisees.add(cible.getCouleur());
			}
		}
		
		// On cherche la couleur minimal que l'on va attribu� au noeud, en regardant les couleurs utilis�es par ses voisins
		int idCouleur =0;
		while(couleursUtilisees.contains(idCouleur)) {
			idCouleur++;
		}
		
		// On actualise couleurMax si la couleur actuelle du noeud est sup�rieure
		if(idCouleur> couleurMax) couleurMax = idCouleur;
		
		// on actualise le noeud
		noeudMax.setCouleur(idCouleur);
		noeudMax.setMark(true);

		
		//On tri les voisins du noeud dans l'ordre degr� d�croissant
		for (int i=voisins.size()-1;i>=1;i--) {
			for (int j=0;j<=i-1;j++) {
				if(voisins.get(j+1).getDegre() > voisins.get(j).getDegre()) {	
					Noeud temp = voisins.get(j+1);
					voisins.set(j+1, voisins.get(j));
					voisins.set(j, temp);
				}
			}
		}

		// On parcours ensuite les voisins
		for(Noeud voisin : voisins) {
			if (!voisin.isMark()) {
				couleurMax = parcoursDegreDecroissant(voisin, couleurMax);
			}
		}
		return couleurMax;
	}

}
