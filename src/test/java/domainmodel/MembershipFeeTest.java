package domainmodel;

import domainmodel.enums.MemberType;
import domainmodel.enums.MembershipStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MembershipFeeTest {
    private static final LocalDate DATE = LocalDate.of(2026, 11, 24);

    @ParameterizedTest(name = "{0}, født {1}: {2} kr.")
    @CsvSource({
            "ACTIVE, 2008-11-25, 1000",
            "ACTIVE, 2008-11-24, 1600",
            "ACTIVE, 1966-11-25, 1600",
            "ACTIVE, 1966-11-24, 1200",
            "ACTIVE, 1961-11-24, 1200",
            "PASSIVE, 2009-11-24, 500",
            "PASSIVE, 2008-11-24, 500",
            "PASSIVE, 1966-11-24, 500"
    })
    void calculatesAnnualFee(MembershipStatus status, LocalDate birthDate, int expectedFee) {
        Member member = new Member(
                1, "Alex", birthDate, status, MemberType.RECREATIONAL
        );

        int actualFee = MembershipFee.calculate(member, DATE);

        assertEquals(expectedFee, actualFee);
    }
}