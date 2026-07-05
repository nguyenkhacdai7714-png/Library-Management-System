package librarymanagement.membermanagement;

import abstractions.MembershipType;
import librarymanagement.utils.Constants;

public class RegularMembership implements MembershipType{
    
    @Override
    public String MembershipTypeName(){
        return "Regular";
    }
    
    @Override
    public float getOverdueFine(long overdueDays){
        return overdueDays*Constants.REGULAR_MEMBERSHIP_OVERDUE_FINE;
    }
    
    @Override
    public String getMembershipTag(){
        return Constants.regularMembershipTag;
    }
    
    @Override
    public int getBorrowingLimit(){
        return Constants.REGULAR_MEMBERSHIP_MAX_BORROWED_BOOKS;
    }
}
