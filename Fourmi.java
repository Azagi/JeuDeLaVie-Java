public class Fourmi implements AutomateCellulaire {
    private Grille grille;
    private int positionX;
    private int positionY;
    private Direction direction;

    public Fourmi(Grille grille, int positionX, int positionY, Direction direction) {
        this.grille = grille;
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
    }

    public void simulerGeneration() {
        Cellule[][] nouvelleGeneration = grille.getCellules();

        // Obtenir la cellule actuelle de la position de la fourmi
        Cellule celluleActuelle = nouvelleGeneration[positionY][positionX];

        // Inverser l'etat de la cellule actuelle
        celluleActuelle.setEtat(!celluleActuelle.getEtat());

        // Tourner la fourmi selon l'etat de la cellule actuelle
        if (celluleActuelle.getEtat()) {
            direction = direction.tournerDroite();
        } else {
            direction = direction.tournerGauche();
        }

        // Avancer la fourmi dans la direction 
        avancer();

        // Mettre a jour la grille avec la nouvelle generation
        grille.setCellules(nouvelleGeneration);
    }

    private void avancer() {
        // Deplacer la fourmi d'une case dans la direction actuelle
        switch (direction) {
            case HAUT:
                positionY--;
                break;
            case DROITE:
                positionX++;
                break;
            case BAS:
                positionY++;
                break;
            case GAUCHE:
                positionX--;
                break;
        }

        // Verifier si la position est en dehors des limites de la grille et ajuster si necessaire
        if (positionX < 0) {
            positionX = grille.getTailleX() - 1;
        } else if (positionX >= grille.getTailleX()) {
            positionX = 0;
        }

        if (positionY < 0) {
            positionY = grille.getTailleY() - 1;
        } else if (positionY >= grille.getTailleY()) {
            positionY = 0;
        }
    }
}