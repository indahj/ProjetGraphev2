import java.util.ArrayList;

public class sudoku extends Graphe{

	Graphe g;

	public sudoku(int k) 
	{
		super(k);
	}


	/* Fonction d'affichage*/
	static void affichage (int grille[][])
	{
		for (int i=0; i<9; i++)
		{
			for (int j=0; j<9; j++)
			{
				System.out.printf( ((j+1)%3)==0 ? "%d " : "%d|", grille[i][j]);
			}
			System.out.println('\n');
			if ((i+1)%3!=0)
				System.out.println("------------------");
		}
		System.out.println("\n");
	}


	@SuppressWarnings("null")
	public ArrayList<Integer> creeNoeuds(int grille[][]) {
		ArrayList<Integer> noeuds = new ArrayList<>();
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				g.addNoeud(grille[i][j]); //on ajoute un noeuds
				noeuds.add(grille[i][j]); //liste de noeuds pour visualiser
			}
		}
		return noeuds;
	}

	/*public List<String> créaArc(int grille[][])
	{
		List<String> arc =null;
		for(int i = 0; i<grille.length;i++)
		{
			for(int j= 0 ; j<grille[i].length;j++)
			{
				for(int x= 1; x<10;x++)	
				{
					g.addArc(i, j*9);
				}
			}
		}
		return arc;
	}*/

	/*On vérifie que la couleur est absente sur la ligne*/
	static boolean absentSurLigne (int k, int grille[][], int i)
	{
		for (int j=0; j < 9; j++)
			if (grille[i][j] == k)
				return false;
		return true;
	}

	/*On vérifie que la couleur est absente sur la colonne*/
	static boolean absentSurColonne (int k, int grille[][], int j)
	{
		for (int i=0; i < 9; i++)
			if (grille[i][j] == k)
				return false;
		return true;
	}

	/*On vérifie que la couleur est absente sur le bloc*/
	static boolean absentSurBloc (int k, int grille[][], int i, int j)
	{
		int _i = i-(i%3), _j = j-(j%3);  // ou encore : _i = 3*(i/3), _j = 3*(j/3);
		for (i=_i; i < _i+3; i++)
			for (j=_j; j < _j+3; j++)
				if (grille[i][j] == k)
					return false;
		return true;
	}

	/*On vérifie que la grille est valide(tous les nombres ne sont qu'une seule fois par ligne, colonne et bloc*/
	static boolean estValide (int grille[][], int position)
	{
		if (position == 9*9)
			return true;

		int i = position/9, j = position%9;

		if (grille[i][j] != 0)
			return estValide(grille, position+1);

		for (int k=1; k <= 9; k++)
		{
			if (absentSurLigne(k,grille,i) && absentSurColonne(k,grille,j) && absentSurBloc(k,grille,i,j))
			{
				grille[i][j] = k;

				if ( estValide (grille, position+1) )
					return true;
			}
		}
		grille[i][j] = 0;

		return false;
	}


	public static void main(String[] args) 
	{
		int[][] grille =
			{
					{9,0,0,1,0,0,0,0,5},
					{0,0,5,0,9,0,2,0,1},
					{8,0,0,0,4,0,0,0,0},
					{0,0,0,0,8,0,0,0,0},
					{0,0,0,7,0,0,0,0,0},
					{0,0,0,0,2,6,0,0,9},
					{2,0,0,3,0,0,0,0,6},
					{0,0,0,2,0,0,9,0,0},
					{0,0,1,9,0,4,5,7,0}
			};

		System.out.println("Grille avant\n");
		affichage(grille);

		estValide(grille,0);

		System.out.println("-----------------------------------------------------------\n");
		System.out.println("Grille apres\n");
		affichage(grille);
	}
}