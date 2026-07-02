package librarymanagement.membermanagement;

import java.util.ArrayList;
import abstractions.MembershipType;

public class Member {

    private String id;
    private String name;
    private String phone;
    private String email;
    private ArrayList<String> readingHistory;
    
    private MembershipType membershipType;

    public Member() {
        this.id = "";
        this.name = "";
        this.phone = "";
        this.email = "";
        this.readingHistory = new ArrayList<String>();
        
        membershipType = new RegularMembership();
    }

    public Member(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.readingHistory = new ArrayList<>();
        
        membershipType = new RegularMembership();
    }
    public Member(String id, String name, String phone, String email, ArrayList<String> readingHistory) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.readingHistory = readingHistory;
        
        membershipType = new RegularMembership();
    }
    
    public Member(String id, String name, String phone, String email, ArrayList<String> readingHistory, MembershipType membership) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.readingHistory = readingHistory;
        
        membershipType = membership;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
    public void AddReadingHistory(String bookId){
        readingHistory.add(bookId);
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
    
    public void View(){
        System.out.printf("ID               : %s\n", getId());
        System.out.printf("Full name        : %s\n", getName());
        System.out.printf("Phone number     : %s\n", getPhone());
        System.out.printf("Email            : %s\n", getEmail());
        System.out.printf("Membership type  : %s\n", getMembership().MembershipTypeName());
        System.out.printf("Readings         : %s\n", getReadingHistoryLength());
        System.out.println();
    }
}