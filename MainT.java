import java.io.IOException;

public class MainT {
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Format: java MainT <typeautomate> <fichier> <nbgeneration>");
            return;
        }

        int typeAutomate = Integer.parseInt(args[0]);
        String fichier = args[1];
        int nbGeneration = Integer.parseInt(args[2]);

        Grille grille = Grille.creerGrilleDepuisFichier(fichier);

        AutomateCellulaire automate;

        switch (typeAutomate) {
            case 0:
                automate = new AutomateUnidimensionnel(grille);
                break;
            case 1:
                automate = new JeuDeLaVie(grille);
                break;
            case 2:
                int positionX = grille.getTailleX() / 2;
                int positionY = grille.getTailleY() / 2;
                Direction direction = Direction.HAUT;
                automate = new Fourmi(grille, positionX, positionY, direction);
                break;
            default:
                System.out.println("Type d'automate invalide.");
                return;
        }

        System.out.println("Grille initiale:");
        grille.afficherGrille();

        for (int etape = 1; etape <= nbGeneration; etape++) {
            automate.simulerGeneration();

            System.out.println("Etape " + etape + ":");
            grille.afficherGrille();
        }
    }
}