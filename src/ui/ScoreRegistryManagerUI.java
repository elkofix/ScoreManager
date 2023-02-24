package ui;
import model.*;

import java.util.List;
import java.util.Scanner;

public class ScoreRegistryManagerUI {

    private static ScoreRegistryManager scoreRegistryManager = new ScoreRegistryManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option = 0;
        do {
            System.out.println("\n1. Agregar registro");
            System.out.println("2. Buscar registro por puntaje");
            System.out.println("3. Jugadores por orden alfabético");
            System.out.println("4. Ver TOP 5 puntajes");
            System.out.println("5. Salir");
            System.out.print("\nSeleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.print("\nIngrese el nombre del jugador: ");
                    String username = scanner.nextLine();
                    System.out.print("Ingrese el puntaje: ");
                    int score = scanner.nextInt();
                    ScoreRegistry scoreRegistry = new ScoreRegistry(username, score);

                    scoreRegistryManager.addScoreRegistry(scoreRegistry);
                    System.out.println("\nRegistro agregado correctamente.");
                    break;
                case 2:
                    System.out.print("\nIngrese el puntaje a buscar: ");
                    int searchScore = scanner.nextInt();
                    ScoreRegistry searchResult = scoreRegistryManager.searchByScore(searchScore);
                    if (searchResult == null) {
                        System.out.println("No se encontró ningún registro con ese puntaje.");
                    } else {
                        System.out.printf("El usuario con puntaje %d es: %s\n", searchScore, searchResult.getUsername());
                    }
                    break;
                case 3:
                    System.out.println("\nJugadores por orden alfabético:");
                    List<ScoreRegistry> alphabeticalOrder = scoreRegistryManager.alphabeticalOrder();
                    for (ScoreRegistry sr : alphabeticalOrder) {
                        System.out.printf("%-20s %d\n", sr.getUsername(), sr.getScore());
                    }
                    break;
                case 4:
                    System.out.println("\nTOP 5 puntajes:");
                    List<ScoreRegistry> top5 = scoreRegistryManager.top5();
                    for (ScoreRegistry sr : top5) {
                        System.out.printf("%-20s %d\n", sr.getUsername(), sr.getScore());
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 5);
    }

}

