package librarymanagement.membermanagement;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String id;
    private String name;
    private String phone;
    private String email;
    private List<String> readingHistory;

    public Member() {
        this.id = "";
        this.name = "";
        this.phone = "";
        this.email = "";
        this.readingHistory = new ArrayList<>();
    }

    public Member(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.readingHistory = new ArrayList<>();
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

    public List<String> getReadingHistory() {
        return readingHistory;
    }

    public void setReadingHistory(List<String> readingHistory) {
        this.readingHistory = readingHistory;
    }
    
    public void View(){
        System.out.printf("ID           : %s\n", getId());
        System.out.printf("Full name    : %s\n", getName());
        System.out.printf("Phone number : %s\n", getPhone());
        System.out.printf("Email        : %s \n", getEmail());
    }
}