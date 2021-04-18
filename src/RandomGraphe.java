

public class RandomGraphe extends Graphe{
	
	/*-----------création d'un graphe selon la méthode de Gilbert-----------*/
    public RandomGraphe(int noeuds, float probabilité){
    	super("class Graphe.csv");
    	if(noeuds > 0 && probabilité >= 0 && probabilité <= 1){
            float a = -1;
            int b = 1;
            for(int i = 0;i<noeuds;i++)
                addNoeud(i);
            while(b < noeuds){
                float c = (float) Math.random();
                a = a + 1 + (float)(Math.log(1-c)/Math.log(1-probabilité));
                
                while(a >= b && b < noeuds)
                {
                    a -= b;
                    b += 1;
                }

                if(b < noeuds)
                    addArc(b, (int) Math.floor(a));
            }
        }
    }
}