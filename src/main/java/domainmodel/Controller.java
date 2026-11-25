package domainmodel;

import domainmodel.enums.MembershipStatus;

import java.time.LocalDate;

public class Controller {
    private final SwimmingClub club;

    public Controller(SwimmingClub club) {
        this.club = club;
    }

    public Member findMember(int memberId) {
        for (Member member : club.getMembers()) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public int calculateFee(Member member) {
        return MembershipFee.calculate(member, LocalDate.now());
    }

    public int calculateFee(MembershipStatus status, LocalDate birthDate) {
        return MembershipFee.calculate(status, birthDate, LocalDate.now());
    }
}

