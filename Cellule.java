/**
 * La classe Cellule représente une cellule dans un système.
 * Une cellule peut être dans un état vivante ou morte.
 */
public class Cellule {
    private boolean etat;

    /**
     * Constructeur de la classe Cellule.
     *
     * @param etat l'état initial de la cellule (true pour vivante, false pour morte)
     */
    public Cellule(boolean etat) {
        this.etat = etat;
    }

    /**
     * Obtient l'état actuel de la cellule.
     *
     * @return true si la cellule est vivante, false si la cellule est inactive
     */
    public boolean getEtat() {
        return etat;
    }

    /**
     * Modifie l'état de la cellule.
     *
     * @param etat le nouvel état de la cellule (true pour vivante, false pour morte)
     */
    public void setEtat(boolean etat) {
        this.etat = etat;
    }
}
