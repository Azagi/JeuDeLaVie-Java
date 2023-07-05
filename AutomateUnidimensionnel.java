/**
 * La classe AutomateUnidimensionnel représente un automate cellulaire unidimensionnel.
 * Elle implémente l'interface AutomateCellulaire et définit la méthode de simulation
 * d'une génération de l'automate.
 */

public class AutomateUnidimensionnel implements AutomateCellulaire {
    private Grille grille;

    /**
     * Construit un objet AutomateUnidimensionnel avec la grille donnée.
     *
     * @param grille la grille représentant l'état initial de l'automate
     */
    public AutomateUnidimensionnel(Grille grille) {
        this.grille = grille;
    }
    @Override
    public void simulerGeneration() {
        int tailleX = grille.getTailleX();
        int tailleY = grille.getTailleY();
        Cellule[][] cellules = grille.getCellules();

        // Creer une nouvelle grille pour stocker la prochaine generation
        Cellule[][] nouvelleGeneration = new Cellule[tailleX][tailleY];

        for (int i = 0; i < tailleX; i++) {
            for (int j = 0; j < tailleY; j++) {
                Cellule celluleCourante = cellules[i][j];
                boolean etatCelluleCourante = celluleCourante.getEtat();

                // Compter le nombre de voisins vivants pour la cellule courante
                int voisinsVivants = Grille.compterVoisinsVivants(grille, i, j);

                boolean nouvelEtat = false;

                if (etatCelluleCourante) {
                    //Une cellule vivante reste vivante si elle a 1 ou 2 voisins vivants
                    if (voisinsVivants == 1 || voisinsVivants == 2) {
                        nouvelEtat = true;
                    }
                } else {
                    //Une cellule morte devient vivante si elle a exactement 1 voisin vivant
                    if (voisinsVivants == 1) {
                        nouvelEtat = true;
                    }
                }

                // Creer la nouvelle cellule avec le nouvel etat
                nouvelleGeneration[i][j] = new Cellule(nouvelEtat);
            }
        }

        // Mettre a jour la grille avec la nouvelle generation
        grille.setCellules(nouvelleGeneration);
    }
}
