package librarymanagement;

import librarymanagement.utils.*;

import librarymanagement.bookmanagement.*;
import librarymanagement.membermanagement.*;
import librarymanagement.borrowingmanagement.BorrowingManagement;
import librarymanagement.reportingmanagement.ReportingManagement;

public class LibraryManagement {
    
    static void Menu()
    {
        Functions.MenuGenerator(
                "LIBRARY MANAGEMENT SYSTEM", // title
                "Save and Quit", // end
                
                "Book Management", // functions... or WALL
                "Member Management", 
                "Borrowing Management", 
                "Reporting Management",
                "Filter Codes",
                "Save");
    }
    
    public static void ShowFilterCode(){
        System.out.println("BOOK MANAGEMENT");
        System.out.println("[+] title:X author:X genre:X pubyear:X");
        System.out.println("\nMEMBER MANAGEMENT");
        System.out.println("[+] name:X email:X phone:X membership:X");
        System.out.println("\nBORROWING MANAGEMENT");
        System.out.println("[+] bookid:X memberid:X borrowdate:X overduedate:X returndate:X returned:X overdue:X\n");
        
    }
    
    public static void main(String[] args) {
        
        Functions.StartFunctions();
        

        System.out.println("LOADING...");
        
        //DataManagement.LoadVirtualData();
        System.out.println("0%");
        DataManagement.LoadAllData();
        
        // Data always load first
        
        System.out.print("-50%");
        BorrowingManagement.getInstance().InitLoading();
        System.out.print("-65%");
        BookManagement.getInstance().InitLoading();
        System.out.print("-85%");
        MemberManagement.getInstance().InitLoading();
        System.out.println("-100%");
        
        String choice;
        
        do{
            
            Functions.Clear();
            
            Menu();
            choice = Functions.InputMenuChoice();
            
            switch(choice){
                case "1":
                    BookManagement.getInstance().Run();
                    break;
                case "2":
                    MemberManagement.getInstance().Run();
                    break;
                case "3":
                    BorrowingManagement.getInstance().Run();
                    break;
                case "4":
                    ReportingManagement.getInstance().Run();
                    break;
                case"5":
                    Functions.Clear();
                    ShowFilterCode();
                    Functions.Pause();
                    break;
                case"6":
                    DataManagement.SaveAllData();
                    Functions.Alert("Saved successfully!");
                    break;
                case "0":
                    break;
                default:
                    Functions.SystemAlert(SystemCode.UndefinedChoice);
                    break;
            }
            
        }while(!choice.equals("0"));
        Functions.Print("Saving...\n");
        DataManagement.SaveAllData();
        Functions.Print("Done!\n");
    }
    
}
