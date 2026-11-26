package ui;

import domainmodel.Controller;
import domainmodel.Member;
import domainmodel.enums.MembershipStatus;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class UserInterface {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

    private final Scanner scanner;
    private final Controller controller;

    public UserInterface() {
        this(new Controller());
    }

    public UserInterface(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        if (!loadData()) {
            return;
        }

        boolean running = true;

        while (running) {
            showMainMenu();
            String choice = scanner.nextLine().trim();

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

    private void showMainMenu() {
        showHeader("DELFINEN");
        System.out.println("1. Formand");
        System.out.println("2. Kasserer");
        System.out.println("3. Træner");
        System.out.println();
        System.out.println("0. Afslut");
        showFooter();
    }

    private void showChairmanMenu() {
        boolean running = true;

        while (running) {
            showHeader("FORMAND");
            System.out.println("1. Opret medlem");
            System.out.println("2. Vis alle medlemmer");
            System.out.println();
            System.out.println("0. Tilbage");
            showFooter();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1", "2" -> showNotImplemented();
                case "0" -> running = false;
                default -> showInvalidChoice();
            }
        }
    }

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

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> showMembershipFeeMenu();
                case "2", "3", "4" -> showNotImplemented();
                case "0" -> running = false;
                default -> showInvalidChoice();
            }
        }
    }

    private void showMembershipFeeMenu() {
        boolean running = true;

        while (running) {
            showHeader("KONTINGENT");
            System.out.println("1. Se kontingent for et medlemsnummer");
            System.out.println("2. Beregn kontingent ud fra status og fødselsdato");
            System.out.println();
            System.out.println("0. Tilbage");
            showFooter();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> showMemberFee();
                case "2" -> calculateFee();
                case "0" -> running = false;
                default -> showInvalidChoice();
            }
        }
    }

    private void showMemberFee() {
        int id = readInt("Medlemsnummer: ");
        Member member = controller.findMember(id);

        if (member == null) {
            System.out.println("Der er intet medlem med nummer " + id + ".");
            return;
        }

        int fee = controller.calculateFee(member);
        System.out.println("Kontingent for " + member.getName()
                + ": " + fee + " kr. om året.");
    }

    private void calculateFee() {
        int choice = readInt("1. Aktiv  2. Passiv: ");

        while (choice != 1 && choice != 2) {
            choice = readInt("Vælg 1 for aktiv eller 2 for passiv: ");
        }

        MembershipStatus status = choice == 1
                ? MembershipStatus.ACTIVE
                : MembershipStatus.PASSIVE;

        while (true) {
            System.out.print("Fødselsdato (dd-mm-åååå): ");

            try {
                LocalDate birthDate = LocalDate.parse(scanner.nextLine().trim(), DATE_FORMAT);
                int fee = controller.calculateFee(status, birthDate);
                System.out.println("Kontingent: " + fee + " kr. om året.");
                return;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldig dato. Skriv fx 24-11-2008.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Skriv et helt tal.");
            }
        }
    }

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

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1", "2", "3", "4", "5" -> showNotImplemented();
                case "0" -> running = false;
                default -> showInvalidChoice();
            }
        }
    }

    private void showHeader(String title) {
        System.out.println();
        System.out.println(GREEN + "========== " + title + " ==========" + RESET);
        System.out.println();
    }

    private void showFooter() {
        System.out.println();
        System.out.println(GREEN + "================================" + RESET);
        System.out.print("Vælg en mulighed: ");
    }

    private boolean loadData() {
        try {
            controller.loadData();
            System.out.println(BLUE + "Data er indlæst." + RESET);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(RED + "Kunne ikke indlæse data." + RESET);
        } catch (IllegalArgumentException | DateTimeException e) {
            System.out.println(RED + "Datafilen indeholder ugyldige oplysninger." + RESET);
        }

        return false;
    }

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

    private void showInvalidChoice() {
        System.out.println(RED + "Ugyldigt valg. Prøv igen." + RESET);
    }

    private void showNotImplemented() {
        System.out.println(YELLOW + "Funktionen er ikke implementeret endnu." + RESET);
    }
}