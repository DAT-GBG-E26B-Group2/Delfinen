package datasource;

import domainmodel.Member;
import domainmodel.SwimmingClub;
import domainmodel.enums.Discipline;

import domainmodel.enums.MemberType;
import domainmodel.enums.MembershipStatus;

import java.time.LocalDate;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class FileHandler {

    private static final String MEMBER_HEADER = "memberId;name;dateOfBirth;membershipStatus;memberType;disciplines";

    private String membersFile;

    public FileHandler(String membersFile) {
        this.membersFile = membersFile;
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

            // Load the next available member ID
            String[] metadata = scanner.nextLine().split(";");
            club.setNextMemberId(Integer.parseInt(metadata[1]));

            // Skip the column header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Clear existing members to avoid duplicates
            club.getMembers().clear();

            // Read each member
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (!line.isBlank()) {
                    Member member = parseMember(line);
                    club.getMembers().add(member);
                }
            }
        }
    }

    // Converts a CSV line into a Member object
    private Member parseMember(String line) {

        String[] values = line.split(";", -1);

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
}
