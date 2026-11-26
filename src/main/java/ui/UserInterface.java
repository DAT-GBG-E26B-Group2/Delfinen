package ui;

import domainmodel.Controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;
    private final Controller controller;

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";

    // Constructor
    public UserInterface() {
        scanner = new Scanner(System.in);
        controller = new Controller();
    }

    // Starts the application
    public void start() {

        if (!loadData()) {
            return;
        }

        boolean running = true;

        while (running) {

            showMainMenu();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showChairmanMenu();
                case "2" -> showTreasurerMenu();
                case "3" -> showCoachMenu();
                case "0" -> {
                    if (saveData()) {
                        running = false;
                        System.out.println(BLUE + "Programmet er afsluttet." + RESET);
                    }
                }
                default -> showInvalidChoice();
            }
        }
    }

    // Displays the main menu
    private void showMainMenu() {

        showHeader("DELFINEN");
        System.out.println("1. Formand");
        System.out.println("2. Kasserer");
        System.out.println("3. Træner");
        System.out.println();
        System.out.println("0. Afslut");
        showFooter();
        System.out.print("Vælg en mulighed: ");
    }

    // Displays the chairman menu
    private void showChairmanMenu() {

        boolean running = true;

        while (running) {

            showHeader("FORMAND");
            System.out.println("1. Opret medlem");
            System.out.println("2. Vis alle medlemmer");
            System.out.println();
            System.out.println("0. Tilbage");
            showFooter();
            System.out.print("Vælg en mulighed: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showNotImplemented();
                case "2" -> showNotImplemented();
                case "0" -> running = false;
                default -> showInvalidChoice();
            }
        }
    }

    // Displays the treasurer menu
    private void showTreasurerMenu() {

        boolean running = true;

        while (running) {

            showHeader("KASSERER");
            System.out.println("1. Beregn kontingent");
            System.out.println("2. Vis forventet indtægt");
            System.out.println("3. Registrer betaling");
            System.out.println("4. Vis medlemmer i restance");
            System.out.println();
            System.out.println("0. Tilbage");
            showFooter();
            System.out.print("Vælg en mulighed: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showNotImplemented();
                case "2" -> showNotImplemented();
                case "3" -> showNotImplemented();
                case "4" -> showNotImplemented();
                case "0" -> running = false;
                default -> showInvalidChoice();
            }
        }
    }

    // Displays the coach menu
    private void showCoachMenu() {

        boolean running = true;

        while (running) {

            showHeader("TRÆNER");
            System.out.println("1. Vis svømmehold");
            System.out.println("2. Registrer træningstid");
            System.out.println("3. Registrer stævneresultat");
            System.out.println("4. Vis medlemsresultater");
            System.out.println("5. Vis de fem hurtigste svømmere");
            System.out.println();
            System.out.println("0. Tilbage");
            showFooter();
            System.out.print("Vælg en mulighed: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showNotImplemented();
                case "2" -> showNotImplemented();
                case "3" -> showNotImplemented();
                case "4" -> showNotImplemented();
                case "5" -> showNotImplemented();
                case "0" -> running = false;
                default -> showInvalidChoice();
            }
        }
    }

    // Displays a colored menu header
    private void showHeader(String title) {
        System.out.println();
        System.out.println(GREEN + "========== " + title + " ==========" + RESET);
        System.out.println();
    }

    // Displays a colored menu footer
    private void showFooter() {
        System.out.println();
        System.out.println(GREEN + "================================" + RESET);
        System.out.print("Vælg en mulighed: ");
    }

    // Loads data when the application starts
    private boolean loadData() {

        try {
            controller.loadData();
            System.out.println(BLUE + "Data er indlæst." + RESET);
            return true;

        } catch (FileNotFoundException e) {
            System.out.println(RED + "Kunne ikke indlæse data." + RESET);

        } catch (IllegalArgumentException | java.time.DateTimeException e) {
            System.out.println(RED + "Datafilen indeholder ugyldige oplysninger." + RESET);
        }

        return false;
    }

    // Saves data before the application closes
    private boolean saveData() {

        try {
            controller.saveData();
            System.out.println(BLUE + "Data er gemt." + RESET);
            return true;

        } catch (FileNotFoundException e) {
            System.out.println(RED + "Kunne ikke gemme data." + RESET);
            return false;
        }
    }

    // Displays an invalid choice message
    private void showInvalidChoice() {
        System.out.println(RED + "Ugyldigt valg. Prøv igen." + RESET);
    }

    // Displays a message for unfinished features
    private void showNotImplemented() {
        System.out.println(YELLOW + "Funktionen er ikke implementeret endnu." + RESET);
    }
}
