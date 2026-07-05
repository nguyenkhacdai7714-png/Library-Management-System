package librarymanagement.membermanagement;

import java.util.ArrayList;

import abstractions.MembershipType;
import abstractions.LibraryObject;

public class Member extends LibraryObject{

    private String name;
    private String phone;
    private String email;
    private ArrayList<String> readingHistory;
    private int currentBorrowingCount;
    
    private MembershipType membershipType;

    public Member() {
        super("");
        this.name = "";
        this.phone = "";
        this.email = "";
        this.readingHistory = new ArrayList<String>();
        
        membershipType = new RegularMembership();
    }

    public Member(String id, String name, String phone, String email) {
        super(id);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.readingHistory = new ArrayList<>();
        
        membershipType = new RegularMembership();
        setCurrentBorrowingCount(0);
    }
    public Member(String id, String name, String phone, String email, ArrayList<String> readingHistory) {
        super(id);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.readingHistory = readingHistory;
        
        membershipType = new RegularMembership();
        setCurrentBorrowingCount(0);
    }
    
    public Member(String id, String name, String phone, String email, ArrayList<String> readingHistory, MembershipType membership) {
        super(id);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.readingHistory = readingHistory;
        
        membershipType = membership;
        
        setCurrentBorrowingCount(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getReadingHistory() {
        return readingHistory;
    }

    public void setReadingHistory(ArrayList<String> readingHistory) {
        this.readingHistory = readingHistory;
    }
    
    public int getReadingHistoryLength(){
        return this.readingHistory.size();
    }
    
    public void AddReadingHistory(String log){
        readingHistory.add(log);
    }
    
    public void setMembership(MembershipType newMembership){
        membershipType = newMembership;
    }
    public MembershipType getMembership(){
        return membershipType;
    }
    
    public boolean IsReadingHistoryEmpty(){
        return readingHistory.isEmpty();
    }

    public int getCurrentBorrowingCount() {
        return currentBorrowingCount;
    }

    public void setCurrentBorrowingCount(int currentBorrowingCount) {
        this.currentBorrowingCount = currentBorrowingCount;
    }
    
    public void addCurrentBorrowing(){
        this.currentBorrowingCount++;
    }
    public void removeCurrentBorrowing(){
        this.currentBorrowingCount--;
    }
    
    public int getBorrowingLimit(){
        return membershipType.getBorrowingLimit();
    }
    
    public boolean IsUnderBorrowingLimit(){
        return getCurrentBorrowingCount() < getBorrowingLimit();
    }
    
    
    public void View(){
        System.out.printf("ID                : %s\n", getId());
        System.out.printf("Full name         : %s\n", getName());
        System.out.printf("Phone number      : %s\n", getPhone());
        System.out.printf("Email             : %s\n", getEmail());
        System.out.printf("Membership type   : %s\n", getMembership().MembershipTypeName());
        System.out.printf("Has read          : %s\n", getReadingHistoryLength() + " book(s)");
        System.out.printf("Is borrowing      : %s\n", getCurrentBorrowingCount() + " book(s)");
        System.out.println();
    }
    public void ViewReadingHistory(){
        for(String i:readingHistory){
            System.out.println(i);
        }
    }
}