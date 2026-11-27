package datasource;

import domainmodel.Member;
import domainmodel.SwimmingClub;
import domainmodel.enums.Discipline;
import domainmodel.Payment;

import domainmodel.enums.MemberType;
import domainmodel.enums.MembershipStatus;

import java.time.LocalDate;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class FileHandler {

    private static final String MEMBER_HEADER = "memberId;name;dateOfBirth;membershipStatus;memberType;disciplines";
    private static final String PAYMENT_HEADER = "memberId;year;paid";

    private String membersFile;
    private final String paymentsFile;

    public FileHandler(String membersFile, String paymentsFile) {
        this.membersFile = membersFile;
        this.paymentsFile = paymentsFile;
    }

    // Saves all members to the CSV file
    public void saveMembers(SwimmingClub club) throws FileNotFoundException {

        PrintStream output = new PrintStream(new File(membersFile));

        // Save the next available member ID
        output.println("nextMemberId;" + club.getNextMemberId());

        // Write the column header
        output.println(MEMBER_HEADER);

        // Write each member to the file
        for (Member member : club.getMembers()) {
            output.println(toCsvLine(member));
        }

        output.close();
    }

    // Converts a member into a CSV line
    private String toCsvLine(Member member) {

        String memberType = "";

        if (member.getMemberType() != null) {
            memberType = member.getMemberType().name();
        }

        // Convert swimming disciplines into a single string
        String disciplines = "";

        for (Discipline discipline : member.getDisciplines()) {

            if (!disciplines.isEmpty()) {
                disciplines += "|";
            }

            disciplines += discipline.name();
        }

        return member.getMemberId() + ";"
                + member.getName() + ";"
                + member.getDateOfBirth() + ";"
                + member.getMembershipStatus() + ";"
                + memberType + ";"
                + disciplines;
    }

    // Loads all members from the CSV file
    public void loadMembers(SwimmingClub club) throws FileNotFoundException {

        File file = new File(membersFile);

        // Return if the file does not exist
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {

            // Return if the file is empty
            if (!scanner.hasNextLine()) {
                return;
            }

            // Load and validate the next member ID
            String[] metadata = scanner.nextLine().split(";");

            if (metadata.length != 2 || !metadata[0].equals("nextMemberId")) {
                throw new IllegalArgumentException("Invalid member metadata.");
            }

            int nextMemberId = Integer.parseInt(metadata[1]);

            if (nextMemberId < 1) {
                throw new IllegalArgumentException("Invalid next member ID.");
            }

            // Validate the column header
            if (!scanner.hasNextLine()
                    || !scanner.nextLine().equals(MEMBER_HEADER)) {
                throw new IllegalArgumentException("Invalid member header.");
            }

            // Temporarily store loaded members
            List<Member> loadedMembers = new ArrayList<>();
            Set<Integer> memberIds = new HashSet<>();

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (!line.isBlank()) {
                    Member member = parseMember(line);

                    // Validate member ID
                    if (member.getMemberId() < 1
                            || member.getMemberId() >= nextMemberId
                            || !memberIds.add(member.getMemberId())) {
                        throw new IllegalArgumentException("Invalid member ID.");
                    }

                    loadedMembers.add(member);
                }
            }

            // Replace existing data after successful loading
            club.getMembers().clear();
            club.getMembers().addAll(loadedMembers);
            club.setNextMemberId(nextMemberId);
        }
    }

    // Converts a CSV line into a Member object
    private Member parseMember(String line) {

        String[] values = line.split(";", -1);

        // Validate the number of fields
        if (values.length != 6) {
            throw new IllegalArgumentException("Invalid member data.");
        }

        int memberId = Integer.parseInt(values[0]);
        String name = values[1];
        LocalDate dateOfBirth = LocalDate.parse(values[2]);

        MembershipStatus membershipStatus =
                MembershipStatus.valueOf(values[3]);

        // Passive members do not have a member type
        MemberType memberType = null;

        if (!values[4].isEmpty()) {
            memberType = MemberType.valueOf(values[4]);
        }

        // Create the member
        Member member = new Member(
                memberId,
                name,
                dateOfBirth,
                membershipStatus,
                memberType
        );

        // Load swimming disciplines
        if (!values[5].isEmpty()) {

            String[] disciplines = values[5].split("\\|");

            for (String discipline : disciplines) {
                member.getDisciplines().add(
                        Discipline.valueOf(discipline)
                );
            }
        }

        return member;
    }

    // Saves all payments to the CSV file
    public void savePayments(SwimmingClub club) throws FileNotFoundException {

        PrintStream output = new PrintStream(new File(paymentsFile));

        // Write the column header
        output.println(PAYMENT_HEADER);

        // Write each payment to the file
        for (Payment payment : club.getPayments()) {
            output.println(toCsvLine(payment));
        }

        output.close();
    }

    // Converts a payment into a CSV line
    private String toCsvLine(Payment payment) {

        return payment.getMemberId() + ";"
                + payment.getYear() + ";"
                + payment.isPaid();
    }

    // Converts a CSV line into a Payment object
    private Payment parsePayment(String line) {

        String[] values = line.split(";", -1);

        // Validate the number of fields
        if (values.length != 3) {
            throw new IllegalArgumentException("Invalid payment data.");
        }

        int memberId = Integer.parseInt(values[0]);
        int year = Integer.parseInt(values[1]);

        // Validate member ID
        if (memberId < 1) {
            throw new IllegalArgumentException("Invalid member ID.");
        }

        // Validate payment year
        if (year < 1) {
            throw new IllegalArgumentException("Invalid payment year.");
        }

        // Validate payment status
        if (!values[2].equals("true") && !values[2].equals("false")) {
            throw new IllegalArgumentException("Invalid payment status.");
        }

        boolean paid = Boolean.parseBoolean(values[2]);

        return new Payment(memberId, year, paid);
    }

    // Loads all payments from the CSV file
    public void loadPayments(SwimmingClub club) throws FileNotFoundException {

        File file = new File(paymentsFile);

        // Return if the file does not exist
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {

            // Return if the file is empty
            if (!scanner.hasNextLine()) {
                return;
            }

            // Validate the column header
            if (!scanner.nextLine().equals(PAYMENT_HEADER)) {
                throw new IllegalArgumentException("Invalid payment header.");
            }

            // Temporarily store loaded payments
            List<Payment> loadedPayments = new ArrayList<>();
            Set<String> paymentKeys = new HashSet<>();

            // Store existing member IDs
            Set<Integer> memberIds = new HashSet<>();

            for (Member member : club.getMembers()) {
                memberIds.add(member.getMemberId());
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (!line.isBlank()) {

                    Payment payment = parsePayment(line);

                    // Validate that the member exists
                    if (!memberIds.contains(payment.getMemberId())) {
                        throw new IllegalArgumentException("Unknown member ID.");
                    }

                    // Check for duplicate payments
                    String paymentKey = payment.getMemberId() + ";"
                            + payment.getYear();

                    if (!paymentKeys.add(paymentKey)) {
                        throw new IllegalArgumentException("Duplicate payment.");
                    }

                    loadedPayments.add(payment);
                }
            }

            // Replace existing data after successful loading
            club.getPayments().clear();
            club.getPayments().addAll(loadedPayments);
        }
    }
}
