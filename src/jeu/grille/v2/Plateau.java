package jeu.grille.v2;

public class Plateau {
    private char[][] grille;
    private int largeur;
    private int hauteur;
    private Personnage joueur;
    private Personnage[] ennemis;

    public Plateau(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        grille = new char[hauteur][largeur];
        joueur = new Personnage(hauteur / 2, largeur / 2, '@');
        ennemis = new Personnage[]{
                new Personnage(0, 0, (char) 0x03A8),
                new Personnage(hauteur - 1, largeur - 1, (char) 0x03A9)
        };
        initialiser();
    }

    private void initialiser() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                grille[i][j] = '.';
            }
        }
        afficherPersonnage(joueur);
        for (int i = 0; i < ennemis.length; i++) {
            afficherPersonnage(ennemis[i]);
        }
    }

    private void afficherPersonnage(Personnage personnage) {
        grille[personnage.getLigne()][personnage.getColonne()] = personnage.getSymbole();
    }

    public void afficher() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                System.out.print(grille[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void deplacerJoueur(int dligne, int dcolonne) {
        deplacerPersonnage(joueur, dligne, dcolonne);
    }

    public void deplacerPersonnage(Personnage personnage, int dligne, int dcolonne) {
        int ligne = personnage.getLigne();
        int colonne = personnage.getColonne();
        int nouveauL = ligne + dligne;
        int nouveauC = colonne + dcolonne;

        if (nouveauL >= 0 && nouveauL < hauteur && nouveauC >= 0 && nouveauC < largeur) {
            grille[ligne][colonne] = '.';
            personnage.deplacer(dligne, dcolonne);
            afficherPersonnage(personnage);
        }
    }

    public boolean detecterCollision() {
        boolean found = false;
        for (int i = 0; i < ennemis.length && !found; i++) {
            Personnage ennemi = ennemis[i];
            if (joueur.memeCase(ennemi))
                found = true;
        }
        return found;
    }


    public void deplacerEnnemi(Personnage ennemi) {
        int rand = getRandomNumber(5);
        switch (rand) {
            case 0: break;
            case 1: deplacerPersonnage(ennemi, -1, 0); break;
            case 2: deplacerPersonnage(ennemi, +1, 0); break;
            case 3: deplacerPersonnage(ennemi,  0, +1); break;
            case 4: deplacerPersonnage(ennemi,  0, -1); break;
        }
    }

    public void deplacerEnnemis() {
        for (int i = 0; i < ennemis.length; i++) {
            Personnage ennemi = ennemis[i];
            deplacerEnnemi(ennemi);
        }
    }

    /**
     * @return un nombre aléatoire n avec 0 <= n < max
     */
    public int getRandomNumber(int max) {
        return (int) (Math.random() * max);
    }
}
