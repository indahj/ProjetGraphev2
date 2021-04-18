import java.util.Random;

public class RandomGraphe extends Graphe{

    /*-----------création d'un graphe selon la méthode de Gilbert-----------*/
    public RandomGraphe(int noeuds, double probabilite, boolean oriente) {
        super("class Graphe.csv");
        Random random = new Random();
        if (noeuds > 0 && probabilite >= 0 && probabilite <= 1) {
            double a = -1;
            int b = 1;
            for (int i = 0; i < noeuds; i++)
                addNoeud(i);
            while (b < noeuds) {
                double c = random.nextDouble();
                a = a + 1 + (Math.log(1 - c) / Math.log(1 - probabilite));

                while (a >= b && b < noeuds) {
                    a -= b;
                    b += 1;
                }

                if (b < noeuds) {
                    addArc(b, (int) Math.floor(a));
                    if (!oriente) {
                        addArc((int) Math.floor(a), b);
                    }
                }
            }
        }
    }
}