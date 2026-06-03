package librarymanagement.membermanagement;

import java.util.*;
import librarymanagement.utils.*; 

public class MemberManagement {
    
    public static void run() {
        menu();
    }

    public static void menu() {
        while (true) {
            Functions.clear();
            System.out.println("=== MEMBER MANAGEMENT SYSTEM ===");
            String[] options = {"Back", "Add Member", "Remove Member", "Update Member", "View Members", "Search Member"};
            Functions.menuGenerator(options);
            
            int choice = Functions.inputMenuChoice(0, 5);
            switch (choice) {
                case 0: return; 
                case 1: adding(); break;
                case 2: removing(); break;
                case 3: updating(); break;
                case 4: viewing(); break;
                case 5: searching(); break;
            }
        }
    }

    public static void adding() {
        Functions.clear();
        System.out.println("--- Add New Member ---");
        
        System.out.print("Enter Member ID: ");
        String id = Functions.inputString();

        if (!Functions.isStringValid(id)) {
            Functions.alert("Invalid ID string input format.");
            Functions.pause();
            return;
        }

        if (MemberManager.isIdValid(id)) {
            Functions.alert("Error: This Member ID already exists! Overlap blocked.");
            Functions.pause();
            return;
        }

        System.out.print("Enter Name: ");
        String name = Functions.inputString();
        System.out.print("Enter Email: ");
        String email = Functions.inputString();
        System.out.print("Enter Phone: ");
        String phone = Functions.inputString();

        if (Functions.isStringValid(name) && Functions.isStringValid(email) && Functions.isStringValid(phone)) {
            Member newMember = new Member(id, name, phone, email);
            MemberManager.add(newMember);
            System.out.println("Member record created successfully!");
        } else {
            Functions.alert("Invalid text detected across entry fields. Registration canceled.");
        }
        Functions.pause();
    }

    public static void removing() {
        Functions.clear();
        System.out.println("--- Remove Member ---");
        System.out.print("Enter Member ID to delete: ");
        String id = Functions.inputString();

        if (Functions.isStringValid(id) && MemberManager.isIdValid(id)) {
            MemberManager.remove(id);
            System.out.println("Member profile successfully purged.");
        } else {
            Functions.alert("Error: Member ID not found or invalid input pattern!");
        }
        Functions.pause();
    }

    private static void updatingMenu(Member oldMember, String nName, String nEmail, String nPhone) {
        System.out.println("--- Updating Member Info Menu ---");
        
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

    public static void updating() {
        Functions.clear();
        System.out.println("--- Update Member System ---");
        System.out.print("Enter Member ID to modify: ");
        String id = Functions.inputString();

        if (!Functions.isStringValid(id) || !MemberManager.isIdValid(id)) {
            Functions.alert("Error: Member ID not found or invalid structural reference!");
            Functions.pause();
            return;
        }

        Member target = MemberManager.search(id);
        
        String newName = target.getName();
        String newEmail = target.getEmail();
        String newPhone = target.getPhone();

        while (true) {
            Functions.clear();
            updatingMenu(target, newName, newEmail, newPhone);
            int choice = Functions.inputMenuChoice(0, 4);

            if (choice == 0) {
                return; 
            }
            
            switch (choice) {
                case 1:
                    System.out.print("Enter New Name: ");
                    String tempName = Functions.inputString();
                    if (Functions.isStringValid(tempName)) {
                        newName = tempName;
                    } else {
                        Functions.alert("Invalid input! Name cannot be blank.");
                        Functions.pause();
                    }
                    break;
                case 2:
                    System.out.print("Enter New Email: ");
                    String tempEmail = Functions.inputString();
                    if (Functions.isStringValid(tempEmail)) {
                        newEmail = tempEmail;
                    } else {
                        Functions.alert("Invalid input! Email cannot be blank.");
                        Functions.pause();
                    }
                    break;
                case 3:
                    System.out.print("Enter New Phone: ");
                    String tempPhone = Functions.inputString();
                    if (Functions.isStringValid(tempPhone)) {
                        newPhone = tempPhone;
                    } else {
                        Functions.alert("Invalid input! Phone cannot be blank.");
                        Functions.pause();
                    }
                    break;
                case 4:
                    if (Functions.isStringValid(newName) && Functions.isStringValid(newEmail) && Functions.isStringValid(newPhone)) {
                        MemberManager.update(id, newName, newEmail, newPhone);
                        System.out.println("Database context values updated completely.");
                    } else {
                        Functions.alert("Cannot save data: Invalid or empty properties detected across fields.");
                    }
                    Functions.pause();
                    return;
            }
        }
    }

    public static void viewing() {
        Functions.clear();
        MemberManager.printList(); 
        Functions.pause();
    }

    public static void searching() {
        Functions.clear();
        System.out.println("--- System Search Engine ---");
        System.out.print("Enter Target Search ID: ");
        String id = Functions.inputString();

        if (Functions.isStringValid(id)) {
            Member match = MemberManager.search(id);
            if (match != null) {
                System.out.println("\nMatching Profile Found:");
                match.view(); 
            } else {
                Functions.alert("No member entry exists mapping to that identity footprint.");
            }
        } else {
            Functions.alert("Invalid parameters supplied.");
        }
        Functions.pause();
    }
}
