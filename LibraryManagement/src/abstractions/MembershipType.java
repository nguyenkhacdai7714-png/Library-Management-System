package abstractions;

public interface MembershipType {
    public String MembershipTypeName();
    public float getOverdueFine(long overdueDays);
    public int getBorrowingLimit();
    public String getMembershipTag();
}
