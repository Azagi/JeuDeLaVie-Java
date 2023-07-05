public enum Direction {
    HAUT,
    DROITE,
    BAS,
    GAUCHE;

    public Direction tournerDroite() {
        int newIndex = (this.ordinal() + 1) % 4;
        return Direction.values()[newIndex];
    }

    public Direction tournerGauche() {
        int newIndex = (this.ordinal() + 3) % 4;
        return Direction.values()[newIndex];
    }
}