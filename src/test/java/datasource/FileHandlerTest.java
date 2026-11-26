package datasource;

import domainmodel.Member;
import domainmodel.SwimmingClub;
import domainmodel.enums.Discipline;
import domainmodel.enums.MemberType;
import domainmodel.enums.MembershipStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private static final String TEST_MEMBERS_FILE = "test-members.csv";

    private FileHandler fileHandler;
    private SwimmingClub club;

    @BeforeEach
    void setUp() {
        club = new SwimmingClub();

        fileHandler = new FileHandler(
                TEST_MEMBERS_FILE
        );
    }

    @AfterEach
    void deleteTestFiles() {
        new File(TEST_MEMBERS_FILE).delete();
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
}
