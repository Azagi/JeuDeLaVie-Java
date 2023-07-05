import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * La classe Grille représente une grille de cellules dans un système.
 * Elle permet de créer une grille à partir d'un fichier, d'afficher la grille sur le terminal,
 * de compter le nombre de voisins vivants pour une cellule donnée.
 */
public class Grille {
    private int tailleX;
    private int tailleY;
    private Cellule[][] cellules;


    /**
     * Constructeur de la classe Grille.
     *
     * @param tailleX la taille horizontale de la grille
     * @param tailleY la taille verticale de la grille
     */
    public Grille(int tailleX, int tailleY) {
        this.tailleX = tailleX;
        this.tailleY = tailleY;
        this.cellules = new Cellule[tailleX][tailleY];
    }

    // Setter pour la variable tailleX
    public void setTailleX(int tailleX) {
        this.tailleX = tailleX;
    }

    // Getter pour la variable tailleX
    public int getTailleX() {
        return tailleX;
    }

    // Setter pour la variable tailleY
    public void setTailleY(int tailleY) {
        this.tailleY = tailleY;
    }

    // Getter pour la variable tailleY
    public int getTailleY() {
        return tailleY;
    }

    // Getter pour la variable cellules
    public Cellule[][] getCellules() {
        return cellules;
    }

    // Setter pour la variable cellules
    public void setCellules(Cellule[][] cellules) {
        this.cellules = cellules;
    }

    public Cellule getCellule(int x, int y) {
        return cellules[x][y];
    }

    
    /**
     * Crée une grille de cellules à partir d'un fichier.
     *
     * @param cheminFichier le chemin du fichier contenant les données de la grille
     * @return la grille créée à partir du fichier
     * @throws IOException si une erreur de lecture du fichier se produit
     */
    // Cree une grille de cellules a partir d'un fichier
    public static Grille creerGrilleDepuisFichier(String cheminFichier) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));

        int tailleX = Integer.parseInt(reader.readLine()); // Lecture de la taille X de la grille a partir du fichier
        int tailleY = Integer.parseInt(reader.readLine()); // Lecture de la taille Y de la grille a partir du fichier

        Grille grille = new Grille(tailleX, tailleY); // Creation d'une nouvelle grille avec les tailles lues
        Cellule[][] cellules = new Cellule[tailleX][tailleY]; // Tableau pour stocker les cellules de la grille

        for (int i = 0; i < tailleX; i++) {
            String ligne = reader.readLine(); // Lecture de la ligne du fichier representant une ligne de la grille
            for (int j = 0; j < tailleY; j++) {
                char celluleChar = ligne.charAt(j); // Obtention du caractere representant une cellule
                boolean estVivante = (celluleChar == '1'); // Verification si la cellule est vivante ou non

                cellules[i][j] = new Cellule(estVivante); // Creation d'une nouvelle cellule aux coordonnees (i, j)
            }
        }

        grille.setCellules(cellules); // Attribution des cellules a la grille

        reader.close(); 

        return grille; // Retour de la grille creee a partir du fichier
    }

    /**
     * Affiche la grille sur le terminal.
     */
    // Affiche la grille sur le terminal
    public void afficherGrille() {
        for (int i = 0; i < tailleX; i++) {
            for (int j = 0; j < tailleY; j++) {
                if (cellules[i][j].getEtat()) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println(); 
        }
    }


    /**
     * Compte le nombre de cellules voisins vivants pour une cellule donnée dans la grille.
     *
     * @param grille la grille contenant la cellule
     * @param x la position horizontale de la cellule
     * @param y la position verticale de la cellule
     * @return le nombre de voisins vivants pour la cellule donnée
     */
    public static int compterVoisinsVivants(Grille grille, int x, int y) {
        int compteur = 0;
        int tailleX = grille.getTailleX();
        int tailleY = grille.getTailleY();

        // Verifier les cellules voisines dans un carre de 3x3 
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                // Verifier que les coordonnees (i, j) sont valides dans la grille
                if (i >= 0 && i < tailleX && j >= 0 && j < tailleY) {
                    // Exclure la cellule en (x, y) elle-même
                    if (i != x || j != y) {
                        Cellule cellule = grille.getCellule(i, j);
                        if (cellule.getEtat()) {
                            compteur++;
                        }
                    }
                }
            }
        }

        return compteur;
    }


}