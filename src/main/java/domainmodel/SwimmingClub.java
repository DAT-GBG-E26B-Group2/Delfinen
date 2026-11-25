package domainmodel;

import java.util.ArrayList;
import java.util.List;

//Manages the swimming club's members and payments.
public class SwimmingClub {

    // Stores all registered members
    private final List<Member> members;

    // Stores membership payments
    private final List<Payment> payments;

    // Keeps track of the next available member ID
    private int nextMemberId;

    // Constructor
    public SwimmingClub() {
        this.members = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.nextMemberId = 1;
    }

    // Getters
    public List<Member> getMembers() {
        return members;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public int getNextMemberId() {
        return nextMemberId;
    }

    // Setter
    public void setNextMemberId(int nextMemberId) {
        this.nextMemberId = nextMemberId;
    }
}
