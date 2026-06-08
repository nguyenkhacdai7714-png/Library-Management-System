package librarymanagement.membermanagement;

import java.util.*;
import librarymanagement.utils.*;
import abstractions.ObjectManagement;

public class MemberManagement implements ObjectManagement {
    
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
            
            Functions.MenuGenerator("MEMBER MANAGEMENT", "Back", 
                    "Add Member",
                    "Update Member",
                    "Remove Member",
                    "Search Member",
                    "View Member List");
            
            String choice = Functions.InputMenuChoice();
            switch (choice) {
                case "0":
                    return;
                case "1":
                    Adding();
                    break;
                case "2" :
                    Updating();
                    break;
                case "3":
                    Removing();
                    break;
                case "4" :
                    Searching();
                    break;
                case "5" :
                    Viewing();
                    break;
            }
        }
    }

    public void Adding() {
        Functions.Clear();
        System.out.println("------------ ADD NEW MEMBER ------------");
        
        String id = Functions.InputString("Enter Member ID: ");

        if (!Functions.IsStringValid(id)) {
            Functions.Alert("Invalid ID string input format.");
            return;
        }

        if (MemberManager.getInstance().IsMemberIDExist(id)) {
            Functions.Alert("Error: This Member ID already exists!");
            return;
        }

        String name = Functions.InputString("Enter Name: ");
        String email = Functions.InputString("Enter Email: ");
        String phone = Functions.InputString("Enter Phone: ");

        if (Functions.IsStringValid(name) && Functions.IsStringValid(email) && Functions.IsStringValid(phone)) {
            if (MemberManager.getInstance().IsDuplicateEmailOrPhone(email, phone)) {
                Functions.Alert("Error: Email or Phone number already registered!");
                return;
            }
            
            Member newMember = new Member(id, name, phone, email);
            MemberManager.getInstance().Add(newMember);
            Functions.Alert("Member registered successfully.");
        } else {
            Functions.Alert("Invalid text detected across entry fields.");
        }
    }

    public void Removing() {
        Functions.Clear();
        System.out.println("------------ REMOVE MEMBER -------------");
        String id = Functions.InputString("Enter Member ID to delete: ");

        if (Functions.IsStringValid(id) && MemberManager.getInstance().IsMemberIDExist(id)) {
            MemberManager.getInstance().Remove(id);
            Functions.Alert("Member removed successfully.");
        } else {
            Functions.Alert("Error: Member ID not found or invalid input!");
        }
    }

    private void UpdatingMenu(Member oldMember, String nName, String nEmail, String nPhone) {
        System.out.println("------- UPDATING MEMBER INFO MENU ------");
        
        System.out.println("[1]. Name : " + oldMember.getName() + (oldMember.getName().equals(nName) ? "" : " -> " + nName));
        System.out.println("[2]. Email: " + oldMember.getEmail() + (oldMember.getEmail().equals(nEmail) ? "" : " -> " + nEmail));
        System.out.println("[3]. Phone: " + oldMember.getPhone() + (oldMember.getPhone().equals(nPhone) ? "" : " -> " + nPhone));
        System.out.println();
        System.out.println("[4]. Update Changed Data");
        System.out.println("[0]. Back");
        
        System.out.println("----------------------------------------");
    }

    public void Updating() {
        Functions.Clear();
        System.out.println("------------ UPDATE MEMBER -------------");
        String id = Functions.InputString("Enter Member ID to modify: ");

        if (!Functions.IsStringValid(id) || !MemberManager.getInstance().IsMemberIDExist(id)) {
            Functions.Alert("Error: Member ID not found or invalid reference!");
            return;
        }

        Member target = MemberManager.getInstance().Search(id);
        String newName = target.getName();
        String newEmail = target.getEmail();
        String newPhone = target.getPhone();

        while (true) {
            Functions.Clear();
            UpdatingMenu(target, newName, newEmail, newPhone);
            String choice = Functions.InputMenuChoice();
            if (choice == "0") return;
            
            switch (choice) {
                case "1" : {
                    String temp = Functions.InputString("Enter New Name: ");
                    if (Functions.IsStringValid(temp)) newName = temp;
                    break;
                }
                case "2" : {
                    String temp = Functions.InputString("Enter New Email: ");
                    if (Functions.IsStringValid(temp)) newEmail = temp;
                    break;
                }
                case "3" : {
                    String temp = Functions.InputString("Enter New Phone: ");
                    if (Functions.IsStringValid(temp)) newPhone = temp;
                    break;
                }
                case "4" : {
                    MemberManager.getInstance().Update(id, newName, newEmail, newPhone);
                    Functions.Alert("Database updated successfully.");
                    return;
                }
                case "0" : {
                    return;
                }
            }
        }
    }

    public void Viewing() {
        Functions.Clear();
        System.out.println("-------------- MEMBER LIST ------------------");
        MemberManager.getInstance().View();
        Functions.Pause();
    }

    public void Searching() {
        Functions.Clear();
        System.out.println("------------ SEARCH MEMBER -------------");
        String id = Functions.InputString("Enter Target Search ID: ");

        if (Functions.IsStringValid(id)) {
            Member match = MemberManager.getInstance().Search(id);
            if (match != null) {
                System.out.println("\nMatching Profile Found:");
                match.View(); 
                Functions.Pause();
            } else {
                Functions.Alert("No member entry exists.");
            }
        }
    }
}