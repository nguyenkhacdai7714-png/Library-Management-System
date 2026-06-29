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
                "Reporting Management");
    }
    
    public static void main(String[] args) {
        
        Functions.StartFunctions();
        
//        DataManagement.LoadVirtualData();
        DataManagement.LoadAllData();
        
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
