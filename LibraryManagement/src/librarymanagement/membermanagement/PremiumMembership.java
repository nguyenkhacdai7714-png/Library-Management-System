package librarymanagement.membermanagement;

import abstractions.MembershipType;
import librarymanagement.utils.Constants;

public class PremiumMembership implements MembershipType{
    
    @Override
    public String MembershipTypeName(){
        return "Premium Membership";
    }
    
    @Override
    public float getOverdueFine(long overdueDays){
        return overdueDays*Constants.PREMIUM_MEMBERSHIP_OVERDUE_FINE;
    }
}
