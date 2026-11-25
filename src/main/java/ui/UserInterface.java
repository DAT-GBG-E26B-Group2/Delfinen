package ui;

import domainmodel.Controller;
import domainmodel.Member;
import domainmodel.enums.MembershipStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class UserInterface {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller;

    public UserInterface(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        System.out.println("Delfinen");

        while (true) {
            System.out.println("1. Se kontingent for et medlemsnummer");
            System.out.println("2. Beregn kontingent ud fra status og fødselsdato");
            System.out.println("0. Afslut");

            switch (readInt("Vælg: ")) {
                case 1 -> showMemberFee();
                case 2 -> calculateFee();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Vælg 0, 1 eller 2.");
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
}
