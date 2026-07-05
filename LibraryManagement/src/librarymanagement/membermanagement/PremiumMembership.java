package librarymanagement.membermanagement;

import abstractions.MembershipType;
import librarymanagement.utils.Constants;

public class PremiumMembership implements MembershipType{
    
    @Override
    public String MembershipTypeName(){
        return "Premium";
    }
    
    @Override
    public float getOverdueFine(long overdueDays){
        return overdueDays*Constants.PREMIUM_MEMBERSHIP_OVERDUE_FINE;
    }
    
    @Override
    public String getMembershipTag(){
        return Constants.premiumMembershipTag;
    }
    
    @Override
    public int getBorrowingLimit(){
        return Constants.PREMIUM_MEMBERSHIP_MAX_BORROWED_BOOKS;
    }
}
