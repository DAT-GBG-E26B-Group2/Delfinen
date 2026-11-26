package domainmodel;

import domainmodel.enums.MembershipStatus;

import java.time.LocalDate;
import java.time.Period;

public class MembershipFee {
    private static final int PASSIVE_FEE = 500;
    private static final int ACTIVE_UNDER_18_FEE = 1000;
    private static final int ACTIVE_18_TO_59_FEE = 1600;
    private static final int ACTIVE_60_PLUS_FEE = 1200;

    public static int calculate(Member member, LocalDate date) {
        return calculate(member.getMembershipStatus(), member.getDateOfBirth(), date);
    }

    public static int calculate(MembershipStatus status, LocalDate birthDate, LocalDate date) {
        if (birthDate.isAfter(date)) {
            throw new IllegalArgumentException("Fødselsdatoen ligger i fremtiden.");
        }

        if (status == MembershipStatus.PASSIVE) {
            return PASSIVE_FEE;
        }

        int age = Period.between(birthDate, date).getYears();

        if (age < 18) {
            return ACTIVE_UNDER_18_FEE;
        }

        if (age < 60) {
            return ACTIVE_18_TO_59_FEE;
        }

        return ACTIVE_60_PLUS_FEE;
    }
}

