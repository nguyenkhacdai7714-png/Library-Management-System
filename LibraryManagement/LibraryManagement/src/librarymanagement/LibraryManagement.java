package librarymanagement;

import java.util.*;
import librarymanagement.utils.*;

import librarymanagement.bookmanagement.*;
import librarymanagement.membermanagement.*;
import librarymanagement.borrowingmanagement.BorrowingManagement;
import librarymanagement.reportingmanagement.ReportingManagement;

public class LibraryManagement {
    
    static Scanner input = new Scanner(System.in);
    
    static void Menu()
    {
        System.out.println("======== LIBRARY MANAGEMENT SYSTEM ========");
        System.out.println("[1] Book Management");
        System.out.println("[2] Member Management");
        System.out.println("[3] Borrowing Management");
        System.out.println("[4] Reporting Management");
        System.out.println("===========================================");
        System.out.println("[0] Save and Quit");
        System.out.println("===========================================");
    }
    
    public static void main(String[] args) {
        
        DataManagement.LoadAllData();
        
        String choice;
        
        do{
            
            Functions.Clear();
            
            Menu();
            choice = input.nextLine();
            
            switch(choice){
                case "1":
                    BookManagement.Run();
                    break;
                case "2":
                    MemberManagement.Run();
                    break;
                case "3":
                    BorrowingManagement.Run();
                    break;
                case "4":
                    ReportingManagement.Run();
                    
                    break;
                case "0":
                    break;
                default:
                    Functions.SystemAlert(SystemCode.UndefinedChoice);
                    break;
            }
            
        }while(!choice.equals("0"));
        
        DataManagement.SaveAllData();
    }
    
}
