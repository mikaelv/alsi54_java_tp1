package jeu.grille.v2;

public class Personnage {
    private int ligne;
    private int colonne;
    private char symbole;

    public Personnage(int ligne, int colonne, char symbole) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.symbole = symbole;
    }

    public void deplacer(int dligne, int dcolonne) {
        this.ligne += dligne;
        this.colonne += dcolonne;
    }

    public boolean memeCase(Personnage autre) {
        return (this.ligne == autre.ligne && this.colonne == autre.colonne);
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public char getSymbole() {
        return symbole;
    }
}
