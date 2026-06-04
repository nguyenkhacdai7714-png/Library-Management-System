package librarymanagement.membermanagement;

import java.util.*;

public class MemberManagement {
    
    private static final MemberManagement instance = new MemberManagement();
    private MemberManagement(){}
    public static MemberManagement getInstance(){
        return instance;
    }
    
    public HashMap<String, Member> memberList = new HashMap<String, Member>();
    
    public void Run()
    {
        Menu();
    }

    public void Menu() {
        while (true) {
            Functions.Clear();
            System.out.println("=== MEMBER MANAGEMENT SYSTEM ===");
            String[] options = {"Back", "Add Member", "Remove Member", "Update Member", "View Members", "Search Member"};
            Functions.MenuGenerator(options);
            
            int choice = Functions.InputMenuChoice(0, 5);
            switch (choice) {
                case 0: return; 
                case 1: AddingMember(); break;
                case 2: RemovingMember(); break;
                case 3: UpdatingMember(); break;
                case 4: ViewMemberList(); break;
                case 5: SearchingMember(); break;
            }
        }
    }

    public void AddingMember() {
        Functions.Clear();
        System.out.println("--- Add New Member ---");
        
        System.out.print("Enter Member ID: ");
        String id = Functions.InputString();

        if (!Functions.IsStringValid(id)) {
            Functions.Alert("Invalid ID string input format.");
            Functions.Pause();
            return;
        }

        if (MemberManager.GetInstance().IsMemberIDValid(id)) {
            Functions.Alert("Error: This Member ID already exists! Overlap blocked.");
            Functions.Pause();
            return;
        }

        System.out.print("Enter Name: ");
        String name = Functions.InputString();
        System.out.print("Enter Email: ");
        String email = Functions.InputString();
        System.out.print("Enter Phone: ");
        String phone = Functions.InputString();

        if (Functions.IsStringValid(name) && Functions.IsStringValid(email) && Functions.IsStringValid(phone)) {
            if (MemberManager.GetInstance().IsDuplicateEmailOrPhone(email, phone)) {
                Functions.Alert("Error: Email or Phone number already registered!");
                Functions.Pause();
                return;
            }
            
            Member newMember = new Member(id, name, phone, email);
            MemberManager.GetInstance().AddMember(newMember);
        } else {
            Functions.Alert("Invalid text detected across entry fields. Registration canceled.");
        }
        Functions.Pause();
    }

    public void RemovingMember() {
        Functions.Clear();
        System.out.println("--- Remove Member ---");
        System.out.print("Enter Member ID to delete: ");
        String id = Functions.InputString();

        if (Functions.IsStringValid(id) && MemberManager.GetInstance().IsMemberIDValid(id)) {
            MemberManager.GetInstance().RemoveMember(id);
        } else {
            Functions.Alert("Error: Member ID not found or invalid input pattern!");
        }
        Functions.Pause();
    }

    private void UpdatingMenu(Member oldMember, String nName, String nEmail, String nPhone) {
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

    public void UpdatingMember() {
        Functions.Clear();
        System.out.println("--- Update Member System ---");
        System.out.print("Enter Member ID to modify: ");
        String id = Functions.InputString();

        if (!Functions.IsStringValid(id) || !MemberManager.GetInstance().IsMemberIDValid(id)) {
            Functions.Alert("Error: Member ID not found or invalid reference!");
            Functions.Pause();
            return;
        }

        Member target = MemberManager.GetInstance().SearchMember(id);
        
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
                case 1:
                    System.out.print("Enter New Name: ");
                    String tempName = Functions.InputString();
                    if (Functions.IsStringValid(tempName)) {
                        newName = tempName;
                    } else {
                        Functions.Alert("Invalid input! Name cannot be blank.");
                        Functions.Pause();
                    }
                    break;
                case 2:
                    System.out.print("Enter New Email: ");
                    String tempEmail = Functions.InputString();
                    if (Functions.IsStringValid(tempEmail)) {
                        newEmail = tempEmail;
                    } else {
                        Functions.Alert("Invalid input! Email cannot be blank.");
                        Functions.Pause();
                    }
                    break;
                case 3:
                    System.out.print("Enter New Phone: ");
                    String tempPhone = Functions.InputString();
                    if (Functions.IsStringValid(tempPhone)) {
                        newPhone = tempPhone;
                    } else {
                        Functions.Alert("Invalid input! Phone cannot be blank.");
                        Functions.Pause();
                    }
                    break;
                case 4:
                    if (Functions.IsStringValid(newName) && Functions.IsStringValid(newEmail) && Functions.IsStringValid(newPhone)) {
                        MemberManager.GetInstance().UpdateMember(id, newName, newEmail, newPhone);
                        System.out.println("Database values updated completely.");
                    } else {
                        Functions.Alert("Cannot save data: Invalid fields detected.");
                    }
                    Functions.Pause();
                    return;
            }
        }
    }

    public void ViewMemberList() {
        Functions.Clear();
        HashMap<String, Member> list = MemberManager.GetInstance().GetMemberList();
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

    public void SearchingMember() {
        Functions.Clear();
        System.out.println("--- System Search Engine ---");
        System.out.print("Enter Target Search ID: ");
        String id = Functions.InputString();

        if (Functions.IsStringValid(id)) {
            Member match = MemberManager.GetInstance().SearchMember(id);
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
