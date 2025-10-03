package jeu.grille.v2;

import java.io.IOException;
import java.util.Scanner;

public class JeuMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        Plateau plateau = new Plateau(5, 5);
        plateau.afficher();
        boolean quitter = false;
        Scanner scanner = new Scanner(System.in);
        while (!quitter) {
            String choix = scanner.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // clavier QWERTY
            switch (choix) {
                case "w" -> plateau.deplacerJoueur(-1, 0);
                case "a" -> plateau.deplacerJoueur(0, -1);
                case "s" -> plateau.deplacerJoueur(+1, 0);
                case "d" -> plateau.deplacerJoueur(0, +1);
                case "q" -> quitter = true;
            }
            if (plateau.detecterCollision()) {
                quitter = true;
            }
            plateau.deplacerEnnemis();
            if (plateau.detecterCollision()) {
                quitter = true;
            }
            plateau.afficher();
        }
        System.out.println("GAME OVER !");
    }
}
