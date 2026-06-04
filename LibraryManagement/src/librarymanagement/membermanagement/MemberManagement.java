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
                case 1 -> AddMember();
                case 2 -> UpdateMember();
                case 3 -> RemoveMember();
                case 4 -> ViewMemberList();
                case 5 -> SearchMember();
            }
        }
    }

        public void AddMember() {
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
            Functions.Alert("Error: This Member ID already exists! Overlap blocked.");
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
            Functions.Alert("Invalid text detected across entry fields. Registration canceled.");
        }
        Functions.Pause();
    }

        public void RemoveMember() {
        Functions.Clear();
        System.out.println("----------------------------------------");
        System.out.println("------------ REMOVE MEMBER -------------");
        System.out.println("----------------------------------------");
        String id = Functions.InputString("Enter Member ID to delete: ");

        if (Functions.IsStringValid(id) && MemberManager.getInstance().IsMemberIDValid(id)) {
            MemberManager.getInstance().RemoveMember(id);
            Functions.Alert("Member removed successfully.");
        } else {
            Functions.Alert("Error: Member ID not found or invalid input pattern!");
        }
        Functions.Pause();
    }

    private void UpdatingMenu(Member oldMember, String nName, String nEmail, String nPhone) {
        System.out.println("----------------------------------------");
        System.out.println("------- Updating Member Info Menu ------");
        System.out.println("----------------------------------------");
        
        String nameLine = "1. Member Name : " + oldMember.getName();
        if (!oldMember.getName().equals(nName)) nameLine += " -> " + nName;
        
        String emailLine = "2. Member Email: " + oldMember.getEmail();
        if (!oldMember.getEmail().equals(nEmail)) emailLine += " -> " + nEmail;
        
        String phoneLine = "3. Member Phone: " + oldMember.getPhone();
        if (!oldMember.getPhone().equals(nPhone)) phoneLine += " -> " + nPhone;

        System.out.println(nameLine);
        System.out.println(emailLine);
        System.out.println(phoneLine);
        System.out.println("4. [Update Changed Data]");
        System.out.println("0. Back");
    }

        public void UpdateMember() {
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

            if (choice == 0) {
                return; 
            }
            
            switch (choice) {
                case 1 -> {
                    String tempName = Functions.InputString("Enter New Name: ");
                    if (Functions.IsStringValid(tempName)) {
                        newName = tempName;
                    } else {
                        Functions.Alert("Invalid input! Name cannot be blank.");
                        Functions.Pause();
                    }
                }
                case 2 -> {
                    String tempEmail = Functions.InputString("Enter New Email: ");
                    if (Functions.IsStringValid(tempEmail)) {
                        newEmail = tempEmail;
                    } else {
                        Functions.Alert("Invalid input! Email cannot be blank.");
                        Functions.Pause();
                    }
                }
                case 3 -> {
                    String tempPhone = Functions.InputString("Enter New Phone: ");
                    if (Functions.IsStringValid(tempPhone)) {
                        newPhone = tempPhone;
                    } else {
                        Functions.Alert("Invalid input! Phone cannot be blank.");
                        Functions.Pause();
                    }
                }
                case 4 -> {
                    if (Functions.IsStringValid(newName) && Functions.IsStringValid(newEmail) && Functions.IsStringValid(newPhone)) {
                        MemberManager.getInstance().UpdateMember(id, newName, newEmail, newPhone);
                        System.out.println("Database values updated completely.");
                    } else {
                        Functions.Alert("Cannot save data: Invalid fields detected.");
                    }
                    Functions.Pause();
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
            System.out.println("No members found in the system.");
        } else {
            System.out.printf("%-10s | %-20s | %-25s | %-15s\n", "ID", "Name", "Email", "Phone");
            System.out.println("----------------------------------------------------------------------------");
            for (Member member : list.values()) {
                System.out.printf("%-10s | %-20s | %-25s | %-15s\n", 
                        member.getId(), member.getName(), member.getEmail(), member.getPhone());
            }
        }
        Functions.Pause();
    }

        public void SearchMember() {
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
                Functions.Alert("No member entry exists mapping to that ID.");
            }
        } else {
            Functions.Alert("Invalid parameters supplied.");
        }
        Functions.Pause();
    }
}
