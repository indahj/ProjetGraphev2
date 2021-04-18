import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class IndiceCroissant {
	
	public Graphe graphe;
	
	public IndiceCroissant(Graphe graphe) {
		this.graphe=graphe;
		graphe.reinitNoeuds();
		this.colorationIndiceCroissant();
	}
	

	public void colorationIndiceCroissant() {

		int indiceMin = Integer.MAX_VALUE;
		Noeud noeudMin = null;
		
		for(Noeud n : graphe.getNoeuds()) {
			if(n.getId() < indiceMin) {
				indiceMin = n.getId();
				noeudMin = n;
			}
		}		

		System.out.println("Nombre de couleur trouvée par coloration IndiceCroissant : "+(parcoursIndiceCroissant(noeudMin,0)+1));
	}

	public int parcoursIndiceCroissant(Noeud noeudMin,int couleurMax) {

		ArrayList<Noeud> voisins = new ArrayList<Noeud>();

		
		List<Integer> couleursUtilisees = new ArrayList<>();
		// On recupere tous les voisins du noeud et on les met dans une liste et on regarde la couleur qu'il utilise
		LinkedList<Arc> listelien = noeudMin.getSuccesseurs();
		for(Arc arc : listelien) {
			Noeud cible = arc.getCible();
			voisins.add(cible);
			if(cible.hasCouleur()) {
				couleursUtilisees.add(cible.getCouleur());
			}
		}
		
		// On cherche la couleur minimal que l'on va attribuï¿½ au noeud, en regardant les couleurs utilisï¿½es par ses voisins
		int idCouleur =0;
		while(couleursUtilisees.contains(idCouleur)) {
			idCouleur++;
		}
		
		// On actualise couleurMax si la couleur actuelle du noeud est supï¿½rieure
		if(idCouleur> couleurMax) couleurMax = idCouleur;
		
		// on actualise le noeud
		noeudMin.setCouleur(idCouleur);
		noeudMin.setMark(true);

		
		//On tri les voisins du noeud dans l'ordre croissant des IDs
		for (int i=voisins.size()-1;i>=1;i--) {
			for (int j=0;j<=i-1;j++) {
				if(voisins.get(j+1).getId()<voisins.get(j).getId()) {	
					Noeud temp = voisins.get(j+1);
					voisins.set(j+1, voisins.get(j));
					voisins.set(j, temp);
				}
			}
		}

		// On parcours ensuite les voisins
		for(Noeud voisin : voisins) {
			if (!voisin.isMark()) {
				couleurMax = parcoursIndiceCroissant(voisin, couleurMax);
			}
		}
		return couleurMax;
	}

}
