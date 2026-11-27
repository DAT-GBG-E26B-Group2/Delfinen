package datasource;

import domainmodel.Member;
import domainmodel.SwimmingClub;
import domainmodel.enums.Discipline;
import domainmodel.enums.MemberType;
import domainmodel.enums.MembershipStatus;
import domainmodel.Payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

import java.io.PrintStream;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private static final String TEST_MEMBERS_FILE = "test-members.csv";
    private static final String TEST_PAYMENTS_FILE = "test-payments.csv";

    private FileHandler fileHandler;
    private SwimmingClub club;

    @BeforeEach
    void setUp() {
        club = new SwimmingClub();

        fileHandler = new FileHandler(
                TEST_MEMBERS_FILE,
                TEST_PAYMENTS_FILE
        );
    }

    @AfterEach
    void deleteTestFiles() {
        new File(TEST_MEMBERS_FILE).delete();
        new File(TEST_PAYMENTS_FILE).delete();
    }

    //Check that all member info is saved
    @Test
    void saveAndLoadMember() throws FileNotFoundException {

        // Arrange
        Member member = new Member(
                1,
                "Anna Jensen",
                LocalDate.of(2010, 3, 15),
                MembershipStatus.ACTIVE,
                MemberType.COMPETITIVE
        );

        member.getDisciplines().add(Discipline.CRAWL);
        member.getDisciplines().add(Discipline.BUTTERFLY);

        club.getMembers().add(member);
        club.setNextMemberId(2);

        // Act
        fileHandler.saveMembers(club);

        SwimmingClub loadedClub = new SwimmingClub();
        fileHandler.loadMembers(loadedClub);

        // Assert
        assertEquals(1, loadedClub.getMembers().size());

        Member loadedMember = loadedClub.getMembers().getFirst();

        assertEquals(member.getMemberId(), loadedMember.getMemberId());
        assertEquals(member.getName(), loadedMember.getName());
        assertEquals(member.getDateOfBirth(), loadedMember.getDateOfBirth());
        assertEquals(member.getMembershipStatus(), loadedMember.getMembershipStatus());
        assertEquals(member.getMemberType(), loadedMember.getMemberType());
        assertEquals(member.getDisciplines(), loadedMember.getDisciplines());
        assertEquals(2, loadedClub.getNextMemberId());
    }

    //CHeck that empty fields are handled correctly
    @Test
    void saveAndLoadPassiveMember() throws FileNotFoundException {

        // Arrange
        Member member = new Member(
                1,
                "Peter Hansen",
                LocalDate.of(1980, 5, 12),
                MembershipStatus.PASSIVE,
                null
        );

        club.getMembers().add(member);
        club.setNextMemberId(2);

        // Act
        fileHandler.saveMembers(club);

        SwimmingClub loadedClub = new SwimmingClub();
        fileHandler.loadMembers(loadedClub);

        // Assert
        Member loadedMember = loadedClub.getMembers().getFirst();

        assertEquals(MembershipStatus.PASSIVE, loadedMember.getMembershipStatus());

        assertNull(loadedMember.getMemberType());
        assertTrue(loadedMember.getDisciplines().isEmpty());
    }

    //Check that program handles a missing file
    @Test
    void loadMissingFile() throws FileNotFoundException {

        // Act
        fileHandler.loadMembers(club);

        // Assert
        assertTrue(club.getMembers().isEmpty());
        assertEquals(1, club.getNextMemberId());
    }

    //Check that member number counter is retained even without members
    @Test
    void saveAndLoadNextMemberId() throws FileNotFoundException {

        // Arrange
        club.setNextMemberId(10);

        // Act
        fileHandler.saveMembers(club);

        SwimmingClub loadedClub = new SwimmingClub();
        fileHandler.loadMembers(loadedClub);

        // Assert
        assertEquals(10, loadedClub.getNextMemberId());
    }

    //Check that the data import is rejected if a member is missing info
    @Test
    void loadInvalidMember() throws FileNotFoundException {

        // Arrange
        try (PrintStream output = new PrintStream(TEST_MEMBERS_FILE)) {
            output.println("nextMemberId;2");
            output.println("memberId;name;dateOfBirth;membershipStatus;memberType;disciplines");
            output.println("1;Anna Jensen");
        }

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> fileHandler.loadMembers(club));

        assertTrue(club.getMembers().isEmpty());
    }

    //Check the invalid date of birth
    @Test
    void loadInvalidDate() throws FileNotFoundException {

        // Arrange
        try (PrintStream output = new PrintStream(TEST_MEMBERS_FILE)) {
            output.println("nextMemberId;2");
            output.println("memberId;name;dateOfBirth;membershipStatus;memberType;disciplines");
            output.println("1;Anna Jensen;invalid-date;ACTIVE;RECREATIONAL;");
        }

        // Act and Assert
        assertThrows(DateTimeParseException.class,
                () -> fileHandler.loadMembers(club));

        assertTrue(club.getMembers().isEmpty());
    }

    //Check for duplicates in member numbers
    @Test
    void loadDuplicateMemberIds() throws FileNotFoundException {

        // Arrange
        try (PrintStream output = new PrintStream(TEST_MEMBERS_FILE)) {
            output.println("nextMemberId;3");
            output.println("memberId;name;dateOfBirth;membershipStatus;memberType;disciplines");
            output.println("1;Anna Jensen;2010-03-15;ACTIVE;RECREATIONAL;");
            output.println("1;Peter Hansen;2000-05-12;ACTIVE;RECREATIONAL;");
        }

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> fileHandler.loadMembers(club));

        assertTrue(club.getMembers().isEmpty());
        assertEquals(1, club.getNextMemberId());
    }

    // Checks that payments are saved correctly
    @Test
    void savePayments() throws FileNotFoundException {

        // Arrange
        Payment payment1 = new Payment(1, 2026, true);
        Payment payment2 = new Payment(2, 2026, false);

        club.getPayments().add(payment1);
        club.getPayments().add(payment2);

        // Act
        fileHandler.savePayments(club);

        // Assert
        File file = new File(TEST_PAYMENTS_FILE);

        assertTrue(file.exists());

        try (Scanner scanner = new Scanner(file)) {
            assertEquals("memberId;year;paid", scanner.nextLine());
            assertEquals("1;2026;true", scanner.nextLine());
            assertEquals("2;2026;false", scanner.nextLine());

            assertFalse(scanner.hasNextLine());
        }
    }

    // Checks that payments are saved and loaded correctly
    @Test
    void saveAndLoadPayments() throws FileNotFoundException {

        // Arrange
        Member member = new Member(
                1,
                "Anna Jensen",
                LocalDate.of(2010, 3, 15),
                MembershipStatus.ACTIVE,
                MemberType.RECREATIONAL
        );

        club.getMembers().add(member);
        club.setNextMemberId(2);

        club.getPayments().add(new Payment(1, 2025, true));
        club.getPayments().add(new Payment(1, 2026, false));

        // Act
        fileHandler.saveMembers(club);
        fileHandler.savePayments(club);

        SwimmingClub loadedClub = new SwimmingClub();

        fileHandler.loadMembers(loadedClub);
        fileHandler.loadPayments(loadedClub);

        // Assert
        assertEquals(2, loadedClub.getPayments().size());

        Payment firstPayment = loadedClub.getPayments().get(0);
        Payment secondPayment = loadedClub.getPayments().get(1);

        assertEquals(1, firstPayment.getMemberId());
        assertEquals(2025, firstPayment.getYear());
        assertTrue(firstPayment.isPaid());

        assertEquals(1, secondPayment.getMemberId());
        assertEquals(2026, secondPayment.getYear());
        assertFalse(secondPayment.isPaid());
    }

    // Checks that a missing payment file is handled correctly
    @Test
    void loadMissingPaymentFile() throws FileNotFoundException {

        // Arrange
        new File(TEST_PAYMENTS_FILE).delete();

        // Act
        fileHandler.loadPayments(club);

        // Assert
        assertTrue(club.getPayments().isEmpty());
    }

    // Checks that an invalid payment status is rejected
    @Test
    void loadInvalidPaymentStatus() throws FileNotFoundException {

        // Arrange
        try (PrintStream output = new PrintStream(TEST_PAYMENTS_FILE)) {
            output.println("memberId;year;paid");
            output.println("1;2026;invalid");
        }

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> fileHandler.loadPayments(club));

        assertTrue(club.getPayments().isEmpty());
    }

    // Checks that payments with unknown member IDs are rejected
    @Test
    void loadUnknownMemberPayment() throws FileNotFoundException {

        // Arrange
        try (PrintStream output = new PrintStream(TEST_PAYMENTS_FILE)) {
            output.println("memberId;year;paid");
            output.println("99;2026;true");
        }

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> fileHandler.loadPayments(club));

        assertTrue(club.getPayments().isEmpty());
    }

    // Checks that duplicate payments are rejected
    @Test
    void loadDuplicatePayments() throws FileNotFoundException {

        // Arrange
        Member member = new Member(
                1,
                "Anna Jensen",
                LocalDate.of(2010, 3, 15),
                MembershipStatus.ACTIVE,
                MemberType.RECREATIONAL
        );

        club.getMembers().add(member);
        club.setNextMemberId(2);

        try (PrintStream output = new PrintStream(TEST_PAYMENTS_FILE)) {
            output.println("memberId;year;paid");
            output.println("1;2026;true");
            output.println("1;2026;false");
        }

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> fileHandler.loadPayments(club));

        assertTrue(club.getPayments().isEmpty());
    }
}
