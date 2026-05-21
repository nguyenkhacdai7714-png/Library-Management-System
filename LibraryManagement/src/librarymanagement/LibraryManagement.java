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
        Functions.MenuGenerator(
                "LIBRARY MANAGEMENT SYSTEM", // title
                "Save and Quit", // end
                
                "Book Management", // functions...
                "Member Management", 
                "Borrowing Management", 
                "Reporting Management");
    }
    
    public static void main(String[] args) {
        
        DataManagement.LoadAllData();
        
        String choice;
        
        do{
            
            Functions.Clear();
            
            Menu();
            choice = Functions.InputString("Enter your choice: ");
            
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
