package librarymanagement.membermanagement;

import java.util.*;
import librarymanagement.utils.*;

public class MemberManagement extends ObjectManagement {
    
    private static final MemberManagement instance = new MemberManagement();
    private MemberManagement(){}
    public static MemberManagement getInstance(){
        return instance;
    }
    
    public void Run() {
        Menu();
    }

    public void Menu() {
        while (true) {
            Functions.Clear();
            System.out.println("----------------------------------------");
            System.out.println("---------- MEMBER MANAGEMENT -----------");
            System.out.println("----------------------------------------");
            System.out.println("[1]. Add new member with details.");
            System.out.println("[2]. Update member information.");
            System.out.println("[3]. Remove a member.");
            System.out.println("[4]. View all members.");
            System.out.println("[5]. Search members.");
            System.out.println("[0]. Quit.");
            System.out.println("----------------------------------------");
            System.out.print("Enter your choice: ");
            
            int choice = Functions.InputMenuChoice(0, 5);
            switch (choice) {
                case 0 -> { return; }
                case 1 -> AddingMember();
                case 2 -> UpdatingMember();
                case 3 -> RemovingMember();
                case 4 -> ViewMemberList();
                case 5 -> SearchingMember();
            }
        }
    }

    public void AddingMember() {
        Functions.Clear();
        System.out.println("----------------------------------------");
        System.out.println("------------ ADD NEW MEMBER ------------");
        System.out.println("----------------------------------------");
        
        String id = Functions.InputString("Enter Member ID: ");

        if (!Functions.IsStringValid(id)) {
            Functions.Alert("Invalid ID string input format.");
            Functions.Pause();
            return;
        }

        if (MemberManager.getInstance().IsMemberIDValid(id)) {
            Functions.Alert("Error: This Member ID already exists!");
            Functions.Pause();
            return;
        }

        String name = Functions.InputString("Enter Name: ");
        String email = Functions.InputString("Enter Email: ");
        String phone = Functions.InputString("Enter Phone: ");

        if (Functions.IsStringValid(name) && Functions.IsStringValid(email) && Functions.IsStringValid(phone)) {
            if (MemberManager.getInstance().IsDuplicateEmailOrPhone(email, phone)) {
                Functions.Alert("Error: Email or Phone number already registered!");
                Functions.Pause();
                return;
            }
            
            Member newMember = new Member();
            newMember.setId(id);
            newMember.setName(name);
            newMember.setEmail(email);
            newMember.setPhone(phone);
            MemberManager.getInstance().AddMember(newMember);
            Functions.Alert("Member registered successfully.");
        } else {
            Functions.Alert("Invalid text detected across entry fields.");
        }
        Functions.Pause();
    }

    public void RemovingMember() {
        Functions.Clear();
        System.out.println("----------------------------------------");
        System.out.println("------------ REMOVE MEMBER -------------");
        System.out.println("----------------------------------------");
        String id = Functions.InputString("Enter Member ID to delete: ");

        if (Functions.IsStringValid(id) && MemberManager.getInstance().IsMemberIDValid(id)) {
            MemberManager.getInstance().RemoveMember(id);
            Functions.Alert("Member removed successfully.");
        } else {
            Functions.Alert("Error: Member ID not found or invalid input!");
        }
        Functions.Pause();
    }

    private void UpdatingMenu(Member oldMember, String nName, String nEmail, String nPhone) {
        System.out.println("----------------------------------------");
        System.out.println("------- UPDATING MEMBER INFO MENU ------");
        System.out.println("----------------------------------------");
        
        System.out.println("[1]. Name : " + oldMember.getName() + (oldMember.getName().equals(nName) ? "" : " -> " + nName));
        System.out.println("[2]. Email: " + oldMember.getEmail() + (oldMember.getEmail().equals(nEmail) ? "" : " -> " + nEmail));
        System.out.println("[3]. Phone: " + oldMember.getPhone() + (oldMember.getPhone().equals(nPhone) ? "" : " -> " + nPhone));
        System.out.println("[4]. Update Changed Data");
        System.out.println("[0]. Back");
        
        System.out.println("----------------------------------------");
        System.out.print("Enter your choice: ");
    }

    public void UpdatingMember() {
        Functions.Clear();
        System.out.println("----------------------------------------");
        System.out.println("------------ UPDATE MEMBER -------------");
        System.out.println("----------------------------------------");
        String id = Functions.InputString("Enter Member ID to modify: ");

        if (!Functions.IsStringValid(id) || !MemberManager.getInstance().IsMemberIDValid(id)) {
            Functions.Alert("Error: Member ID not found or invalid reference!");
            Functions.Pause();
            return;
        }

        Member target = MemberManager.getInstance().SearchMember(id);
        String newName = target.getName();
        String newEmail = target.getEmail();
        String newPhone = target.getPhone();

        while (true) {
            Functions.Clear();
            UpdatingMenu(target, newName, newEmail, newPhone);
            int choice = Functions.InputMenuChoice(0, 4);
            if (choice == 0) return;
            
            switch (choice) {
                case 1 -> {
                    String temp = Functions.InputString("Enter New Name: ");
                    if (Functions.IsStringValid(temp)) newName = temp;
                }
                case 2 -> {
                    String temp = Functions.InputString("Enter New Email: ");
                    if (Functions.IsStringValid(temp)) newEmail = temp;
                }
                case 3 -> {
                    String temp = Functions.InputString("Enter New Phone: ");
                    if (Functions.IsStringValid(temp)) newPhone = temp;
                }
                case 4 -> {
                    MemberManager.getInstance().UpdateMember(id, newName, newEmail, newPhone);
                    Functions.Alert("Database updated successfully.");
                    return;
                }
            }
        }
    }

    public void ViewMemberList() {
        Functions.Clear();
        System.out.println("----------------------------------------");
        System.out.println("------------- MEMBER LIST --------------");
        System.out.println("----------------------------------------");
        HashMap<String, Member> list = MemberManager.getInstance().getMemberList();
        if (list.isEmpty()) {
            System.out.println("No members found.");
        } else {
            System.out.printf("%-10s | %-20s | %-25s | %-15s\n", "ID", "Name", "Email", "Phone");
            System.out.println("----------------------------------------------------------------------------");
            for (Member m : list.values()) {
                System.out.printf("%-10s | %-20s | %-25s | %-15s\n", m.getId(), m.getName(), m.getEmail(), m.getPhone());
            }
        }
        Functions.Pause();
    }

    public void SearchingMember() {
        Functions.Clear();
        System.out.println("----------------------------------------");
        System.out.println("------------ SEARCH MEMBER -------------");
        System.out.println("----------------------------------------");
        String id = Functions.InputString("Enter Target Search ID: ");

        if (Functions.IsStringValid(id)) {
            Member match = MemberManager.getInstance().SearchMember(id);
            if (match != null) {
                System.out.println("\nMatching Profile Found:");
                match.PrintInfo(); 
            } else {
                Functions.Alert("No member entry exists.");
            }
        }
        Functions.Pause();
    }
}
