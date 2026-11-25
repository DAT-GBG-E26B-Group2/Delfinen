package domainmodel;

import domainmodel.enums.Discipline;
import domainmodel.enums.MemberType;
import domainmodel.enums.MembershipStatus;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Member {

    // Unique member ID that cannot be changed after creation
    private final int memberId;

    // Personal information
    private String name;
    private LocalDate dateOfBirth;

    // Membership information
    private MembershipStatus membershipStatus;
    private MemberType memberType;

    // Stores swimming disciplines without duplicates
    private final Set<Discipline> disciplines;

    // Constructor
    public Member(int memberId, String name, LocalDate dateOfBirth,
                  MembershipStatus membershipStatus, MemberType memberType) {
        this.memberId = memberId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.membershipStatus = membershipStatus;
        this.memberType = memberType;

        // Initialize an empty set of swimming disciplines
        this.disciplines = new HashSet<>();
    }

    //Getters
    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public Set<Discipline> getDisciplines() {
        return disciplines;
    }
}
