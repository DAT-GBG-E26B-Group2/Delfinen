package domainmodel;

import datasource.FileHandler;
import domainmodel.enums.MembershipStatus;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Controller {
    private final SwimmingClub swimmingClub;
    private final FileHandler fileHandler;

    public Controller() {
        this(new SwimmingClub());
    }

    public Controller(SwimmingClub swimmingClub) {
        this.swimmingClub = swimmingClub;
        this.fileHandler = new FileHandler("data/members.csv");
    }

    public void loadData() throws FileNotFoundException {
        fileHandler.loadMembers(swimmingClub);
    }

    public void saveData() throws FileNotFoundException {
        fileHandler.saveMembers(swimmingClub);
    }

    public Member findMember(int memberId) {
        for (Member member : swimmingClub.getMembers()) {
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
