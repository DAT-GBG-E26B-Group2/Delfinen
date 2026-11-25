package domainmodel;

//Represents a membership payment for a specific year.
public class Payment {

    // Identifies the member associated with the payment
    private final int memberId;

    // The year the payment belongs to
    private final int year;

    // Indicates whether the membership fee has been paid
    private boolean paid;

    // Constructor
    public Payment(int memberId, int year, boolean paid) {
        this.memberId = memberId;
        this.year = year;
        this.paid = paid;
    }

    // Getters
    public int getMemberId() {
        return memberId;
    }

    public int getYear() {
        return year;
    }

    public boolean isPaid() {
        return paid;
    }

    // Setter
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
