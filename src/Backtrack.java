import java.util.Arrays;
import java.util.Stack;

public class Backtrack extends Graphe {

    public Graphe graphe;

    public Backtrack(int k) {
        super(k);
        reinitNoeuds();
        algoBrown();
    }

    public int algoBrown() {
        int[] couleurs = new int[noeuds.size()];
        Stack<Noeud> stack = new Stack<>();
        if (backtrackColor(getNoeud(0), couleurs, stack)) {
            System.out.println(Arrays.toString(couleurs));
            System.out.println("Une solution existe.");
            return 1;
        } else {
            System.out.println("Pas de solution.");
            return 0;
        }
    }

    private boolean backtrackColor(Noeud n, int[] couleurs, Stack<Noeud> stack) {
        for (int c = 1; c < noeuds.size(); c++) {
            if (!couleursVoisins(n, couleurs).contains(c)) {
                couleurs[n.getId()] = c;
                n.setMark(true);
                stack.add(n);
                if (!n.hasUnmarkedSuccessor()) {
                    System.out.println("return true");
                    return true;
                }
                for (Arc a : n.getSuccesseurs()) {
                    if (!a.getCible().isMark()) {
                        if (!backtrackColor(a.getCible(), couleurs, stack)) {
                            break;
                        }
                    }
                }
                rollback(n, couleurs, stack);
            }
        }
        System.out.println("Return false");
        couleurs[n.getId()] = -1;
        n.setMark(false);
        return false;
    }

    private void rollback(Noeud n, int[] couleurs, Stack<Noeud> stack) {
        Noeud popped = stack.pop();
        while (popped.equals(n)) {
            popped.setMark(false);
            couleurs[popped.getId()] = 0;
        }
    }

}
