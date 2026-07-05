package librarymanagement.membermanagement;

import java.util.*;
import abstractions.ObjectManager;
import librarymanagement.utils.BoardDrawer;
import librarymanagement.utils.DuplicateChecker;

import abstractions.MembershipType;

public class MemberManager extends ObjectManager<Member> {
    
    private static final MemberManager instance = new MemberManager();
    private MemberManager(){}
    public static MemberManager getInstance(){
        return instance;
    }
    
    private DuplicateChecker emailChecker = new DuplicateChecker();
    private DuplicateChecker phoneChecker = new DuplicateChecker();
    
    public void LoadEmailChecker(){
        for(Member member : getList().values()){
            emailChecker.Add(member.getEmail());
        }
    }
    public void LoadPhoneChecker(){
        for(Member member : getList().values()){
            phoneChecker.Add(member.getPhone());
        }
    }
    
    public boolean IsDuplicatePhone(String phone){
        return phoneChecker.Check(phone);
    }
    
    public boolean IsDuplicateEmail(String email){
        return emailChecker.Check(email);
    }
    
    public void AddDuplicateChecker(String memberId){
        Member member = SearchById(memberId);
        emailChecker.Add(member.getEmail());
        phoneChecker.Add(member.getPhone());
    }
    
    public void RemoveDuplicateChecker(String memberId){
        Member member = SearchById(memberId);
        emailChecker.Remove(member.getEmail());
        phoneChecker.Remove(member.getPhone());
    }
    
    @Override
    public void View(){
        ViewList(getList().values(), "member list", "Member list is empty");
    }
    
    @Override
    public void ViewList(Collection<Member> itemList, String title, String emptyAlert){
        BoardDrawer.SetBoard(5+10+20+15+23+9+11+17 + 8*3, "| %-5s | %-10s | %-20s | %-15s | %-23s | %-9s | %-11s | %-17s |");
        BoardDrawer.PrintTitle(title, emptyAlert, itemList.isEmpty());
        
        
        if(!itemList.isEmpty()){
            BoardDrawer.PrintRow("No#","Member ID","Fullname", "Phone","Email", "Readings", "Membership", "Curr. Borrowings");
            BoardDrawer.PrintWall();
            int count = 1;
            for (Member member : itemList){
                
                BoardDrawer.PrintRow(count, 
                        member.getId(), 
                        member.getName(), 
                        member.getPhone(), 
                        member.getEmail(), 
                        member.getReadingHistoryLength(), 
                        member.getMembership().MembershipTypeName(), 
                        member.getCurrentBorrowingCount()+"/"+member.getBorrowingLimit());
                count++;
            }
            BoardDrawer.PrintSoftWall();
            BoardDrawer.PrintTotal(getList().size(), "member");
        }
        BoardDrawer.PrintWall();
    }
    

    public void Update(String memberID, String name, String email, String phone, MembershipType membershipType) {
        if (IsIdExist(memberID)) {
            Member member = list.get(memberID);
            member.setName(name);
            member.setEmail(email);
            member.setPhone(phone);
            member.setMembership(membershipType);
        }
    }
    
    public void AddCurrentBorrowing(String memberId){
        Member member = SearchById(memberId);
        if(member!=null){
            member.addCurrentBorrowing();
        }
    }
    public void RemoveCurrentBorrowing(String memberId){
        Member member = SearchById(memberId);
        if(member!=null){
            member.removeCurrentBorrowing();
        }
    }
    
    public void ViewMembershipBenefits(){
        
        MembershipType regular = new RegularMembership();
        MembershipType premium = new PremiumMembership();
        
        System.out.println("REGULAR MEMBERSHIP");
        System.out.println("+ Borrowing Limit : "  + regular.getBorrowingLimit() + " books");
        System.out.printf("+ Overdue fine    : %.0f vnd/day", regular.getOverdueFine(1));
        
        System.out.println("\n");
        
        System.out.println("PREMIUM MEMBERSHIP");
        System.out.println("+ Borrowing Limit : "  + premium.getBorrowingLimit() + " books");
        System.out.printf("+ Overdue fine    : %.0f vnd/day", premium.getOverdueFine(1));
        
        
        System.out.println("\n");
    }
}