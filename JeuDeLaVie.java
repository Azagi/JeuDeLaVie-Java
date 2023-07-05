import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * La classe JeuDeLaVie représente l'automate cellulaire du jeu de la vie.
 * Elle implémente l'interface AutomateCellulaire et définit la méthode de simulation
 * d'une génération du jeu de la vie.
 */
public class JeuDeLaVie implements AutomateCellulaire {
    private Grille grille;

    /**
     * Construit un objet JeuDeLaVie avec la grille donnée.
     *
     * @param grille la grille représentant l'état initial du jeu de la vie
     */
    public JeuDeLaVie(Grille grille) {
        this.grille = grille;
    }

    /**
     * cette fonction utilise des threads pour diviser le travail de calcul de la génération suivante du jeu de la vie entre plusieurs threads,
     * * ce qui permet de paralléliser le traitement et potentiellement d'accélérer l'exécution. *
     * Chaque thread traite une zone distincte de la grille, puis les résultats sont combinés pour former la nouvelle génération
     */
    @Override
    public void simulerGeneration() {
        int tailleX = grille.getTailleX();
        int tailleY = grille.getTailleY();
        Cellule[][] cellules = grille.getCellules();
        Cellule[][] nouvelleGeneration = new Cellule[tailleX][tailleY];

        int nombreThreads = Runtime.getRuntime().availableProcessors(); // Obtenir le nombre de threads disponibles
        
        ExecutorService threadPool = Executors.newFixedThreadPool(nombreThreads);
        System.out.println(nombreThreads);
        int tailleZoneX = tailleX / nombreThreads; 
        int tailleZoneY = tailleY / nombreThreads; 

        
        for (int i = 0; i < nombreThreads; i++) {
            final int debutX = i * tailleZoneX;
            final int finX = (i == nombreThreads - 1) ? tailleX : (i + 1) * tailleZoneX;
            System.out.println(debutX);
            System.out.println(finX);
            for (int j = 0; j < nombreThreads; j++) {
                final int debutY = j * tailleZoneY;
                final int finY = (j == nombreThreads - 1) ? tailleY : (j + 1) * tailleZoneY;

                
                Runnable tache = () -> {
                    //System.out.println("Thread " + Thread.currentThread().getId() + " - Tache demarree.");
                    
                    for (int x = debutX; x < finX; x++) {
                        for (int y = debutY; y < finY; y++) {
                            Cellule celluleCourante = cellules[x][y];
                            int voisinsVivants = Grille.compterVoisinsVivants(grille, x, y);

                            // Appliquer les regles du jeu de la vie
                            if (celluleCourante.getEtat()) {
                                if (voisinsVivants < 2 || voisinsVivants > 3) {
                                    // La cellule meurt
                                    nouvelleGeneration[x][y] = new Cellule(false);
                                } else {
                                    // La cellule reste vivante
                                    nouvelleGeneration[x][y] = celluleCourante;
                                }
                            } else {
                                if (voisinsVivants == 3) {
                                    // Une nouvelle cellule naît
                                    nouvelleGeneration[x][y] = new Cellule(true);
                                } else {
                                    // La cellule reste morte
                                    nouvelleGeneration[x][y] = celluleCourante;
                                }
                            }
                        }
                    }
                    //System.out.println("Thread " + Thread.currentThread().getId() + " - Tache terminee.");
                };

                // Soumettre la tâche au thread pool
                threadPool.execute(tache);
            }
        }

        threadPool.shutdown(); // Arrêter le thread pool une fois toutes les tâches soumises

        // Attendre que toutes les tâches se terminent
        while (!threadPool.isTerminated()) {
        }

        // Mettre a jour la grille avec la nouvelle generation
        grille.setCellules(nouvelleGeneration);
    }

}
