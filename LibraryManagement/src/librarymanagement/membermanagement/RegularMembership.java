package librarymanagement.membermanagement;

import abstractions.MembershipType;
import librarymanagement.utils.Constants;

public class RegularMembership implements MembershipType{
    
    @Override
    public String MembershipTypeName(){
        return "Regular Membership";
    }
    
    @Override
    public float getOverdueFine(long overdueDays){
        return overdueDays*Constants.REGULAR_MEMBERSHIP_OVERDUE_FINE;
    }
}
