package librarymanagement.membermanagement;

import librarymanagement.utils.Functions;
import abstractions.ObjectManagement;

import abstractions.MembershipType;

import librarymanagement.borrowingmanagement.BorrowingManager;

public class MemberManagement implements ObjectManagement {
    
    private static final MemberManagement instance = new MemberManagement();
    private MemberManagement(){}
    public static MemberManagement getInstance(){
        return instance;
    }
    @Override
    public void Run() {
        Menu();
    }
    @Override
    public void Menu() {
        while (true) {
            Functions.Clear();
            
            Functions.MenuGenerator("MEMBER MANAGEMENT", "Back", 
                    "Add Member",
                    "Update Member",
                    "Remove Member",
                    "View Member List",
                    "Search Member");
            
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
                    Viewing();
                    break;
                case "5" :
                    Searching();
                    break;
            }
        }
    }
    @Override
    public void Adding() {
        
        String name, email, phone;
        boolean isDuplicateEmail, isDuplicatePhone;
        MemberManager manager = MemberManager.getInstance();
        
        Functions.Clear();
        System.out.println("------------ ADD NEW MEMBER ------------");
        
        String id = manager.IdGenerator("M");

        if (!Functions.IsStringValid(id)) {
            Functions.Alert("Invalid ID string input format.");
            return;
        }
        
        System.out.println("New member ID : " + id);

        if (manager.IsIdExist(id)) {
            Functions.Alert("Error: This Member ID already exists!");
            return;
        }
        
        do{
            name = Functions.InputString("Enter Name ('0' to quit): ");
            if(name.equals("0")){
                return;
            }
            if(!Functions.IsStringValid(name)){
                System.out.println("Do not leave blank this information!");
            }
            else if(!Functions.IsStringNoDigit(name)){
                System.out.println("Full name can not contain numbers!");
            }
            
        }while(!Functions.IsStringValid(name) || !Functions.IsStringNoDigit(name));
        
        
        do{
            email = Functions.InputString("Enter Email ('0' to quit): ");
            isDuplicateEmail = manager.IsDuplicateEmail(email);

            if(email.equals("0")){
                return;
            }
            if(!Functions.IsStringValid(email)){
                System.out.println("Do not leave blank this information!");
            }
            else if(isDuplicateEmail){
                System.out.println("This email number has already used!");
            }
            
        }while(!Functions.IsStringValid(email)|| isDuplicateEmail);
        
        do{
            phone = Functions.InputString("Enter Phone ('0' to quit): ");
            isDuplicatePhone = manager.IsDuplicatePhone(phone);
            
            if(phone.equals("0")){
                return;
            }
            if(!Functions.IsStringValid(phone)){
                System.out.println("Do not leave blank this information!");
            }
            else if(!Functions.IsStringNoLetter(phone)){
                System.out.println("Phone can not contain letters!");
            }
            else if(!Functions.IsPhoneValid(phone)){
                System.out.println("Invalid phone number!");
            }
            else if(isDuplicatePhone){
                System.out.println("This phone number has already used!");
            }
        }while(!Functions.IsStringNoLetter(phone)|| 
                isDuplicatePhone || 
                !Functions.IsStringValid(phone)||
                !Functions.IsPhoneValid(phone));

        
        Member newMember = new Member(id, name, phone, email);
        manager.Add(id,newMember);
        Functions.Alert("Member registered successfully.");

    }
    @Override
    public void Removing() {
        Functions.Clear();
        System.out.println("------------ REMOVE MEMBER -------------");
        String id = Functions.InputString("Enter Member ID to delete: ");
        Member member = MemberManager.getInstance().SearchById(id);
        
        if(BorrowingManager.getInstance().IsMemberOnTransaction(id)){
            Functions.Alert("Can not remove because this member is on a transaction!");
            return;
        }
        
        if (Functions.IsStringValid(id) && MemberManager.getInstance().IsIdExist(id) && member!=null) {
            
            System.out.println("==== MEMBER INFORMATION ====");
            member.View();
            String choice = Functions.YNQuestion("Remove this member?");
            if(choice.equals("y")){
                MemberManager.getInstance().Remove(id);
                Functions.Alert("Member removed successfully.");
            }
            else{
                Functions.Alert("Cancelled!");
            }
        } 
        else {
            Functions.Alert("Error: Member ID not found or invalid input!");
        }
    }

    private void UpdatingMenu(Member oldMember, String newName, String newEmai, String newPhone, MembershipType newMembership) {
        System.out.println("------- UPDATING MEMBER INFO MENU ------");
        
        System.out.println("[1] Name        : " + oldMember.getName() + (oldMember.getName().equals(newName) ? "" : " -> " + newName));
        System.out.println("[2] Email       : " + oldMember.getEmail() + (oldMember.getEmail().equals(newEmai) ? "" : " -> " + newEmai));
        System.out.println("[3] Phone       : " + oldMember.getPhone() + (oldMember.getPhone().equals(newPhone) ? "" : " -> " + newPhone));
        System.out.println("[4] Membership  : " + oldMember.getMembership().MembershipTypeName() + (oldMember.getMembership()==newMembership?"":("->" + newMembership.MembershipTypeName())));
        System.out.println();
        System.out.println("[5] Update Changed Data");
        System.out.println("[0] Back");
        
        System.out.println("----------------------------------------");
    }
    
    @Override
    public void Updating() {
        Functions.Clear();
        System.out.println("------------ UPDATE MEMBER -------------");
        String id = Functions.InputString("Enter Member ID to modify: ");

        if (!Functions.IsStringValid(id) || !MemberManager.getInstance().IsIdExist(id)) {
            Functions.Alert("Error: Member ID not found or invalid reference!");
            return;
        }

        Member target = MemberManager.getInstance().SearchById(id);
        String newName = target.getName();
        String newEmail = target.getEmail();
        String newPhone = target.getPhone();
        MembershipType newMembership = target.getMembership();

        while (true) {
            Functions.Clear();
            UpdatingMenu(target, newName, newEmail, newPhone, newMembership);
            String choice = Functions.InputMenuChoice();
            if (choice.equals("0")) return;
            
            switch (choice) {
                case "1" : {
                    String temp = Functions.InputString("Enter New Name: ");
                    if (Functions.IsStringValid(temp)) newName = temp;
                    else{
                        Functions.Alert("Do not leave blank!");
                    }
                    break;
                }
                case "2" : {
                    String temp = Functions.InputString("Enter New Email: ");
                    if (Functions.IsStringValid(temp)
                            && !MemberManager.getInstance().IsDuplicatePhone(temp)) newEmail = temp;
                    else if(MemberManager.getInstance().IsDuplicatePhone(temp)){
                        Functions.Alert("This email has already used!");
                    }
                    else{
                        Functions.Alert("Do not leave blank!");
                    }
                    break;
                }
                case "3" : {
                    String temp = Functions.InputString("Enter New Phone: ");
                    if (Functions.IsStringValid(temp) 
                            && Functions.IsPhoneValid(temp)
                            && !MemberManager.getInstance().IsDuplicatePhone(temp)) newPhone = temp;
                    else if(!Functions.IsPhoneValid(temp)){
                        Functions.Alert("Invalid phone!");
                    }
                    else if(MemberManager.getInstance().IsDuplicatePhone(temp)){
                        Functions.Alert("This phone number has already used!");
                    }
                    else{
                        Functions.Alert("Do not leave blank!");
                    }
                    break;
                }
                case"4":{
                    int temp = Functions.InputInt("Enter membership - [1] Regular, [2] Premium : ");
                    if(temp==1){
                        newMembership = new RegularMembership();
                    }
                    else if(temp==2){
                        newMembership = new PremiumMembership();
                    }
                    else{
                        Functions.Alert("Invalid choice!");
                    }
                    break;
                }
                case "5" : {
                    MemberManager.getInstance().Update(id, newName, newEmail, newPhone, newMembership);
                    Functions.Alert("Database updated successfully.");
                    return;
                }
                case "0" : {
                    return;
                }
            }
        }
    }
    @Override
    public void Viewing() {
        Functions.Clear();
        MemberManager.getInstance().View();
        Functions.Pause();
    }
    @Override
    public void Searching() {
        Functions.Clear();
        System.out.println("------------ SEARCH MEMBER -------------");
        String id = Functions.InputString("Enter Target Search ID: ");

        if (Functions.IsStringValid(id)) {
            Member match = MemberManager.getInstance().SearchById(id);
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