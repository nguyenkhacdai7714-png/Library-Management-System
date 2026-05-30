package librarymanagement.reportingmanagement;

import java.util.*;
import librarymanagement.utils.*;

import librarymanagement.bookmanagement.Book;
import librarymanagement.membermanagement.Member;

public class ReportingManagement {
    
    // singleton
    private static final ReportingManagement instance = new ReportingManagement();
    private ReportingManagement(){}
    public static ReportingManagement getInstance(){
        return instance;
    }
    // end singleton
    
    public void Menu(){
        Functions.MenuGenerator(
                "REPORTING MANAGEMENT", // title
                "Back", // end
                
                "Show Borrowed Books", // functions...
                "Show Overdue Books",
                "Show Most Popular Books",
                "Show Most Borrowings Members");
    }
    
    public void Run()
    {
        Functions.Print("Generating reports");
        ReportingManager.GenerateOverdueBookList();
        ReportingManager.GenerateBorrowedBookList();
        ReportingManager.GenerateMostActiveMemberList();
        ReportingManager.GenerateMostPopularBookList();
        Functions.Print("-> Successfully!");
        
        String choice;
        do{
            Functions.Clear();
            Menu();
            choice = Functions.InputMenuChoice();
            
            switch(choice)
            {      
                case "1":
                    Functions.Clear();
                    PrintBorrowedBookList();
                    Functions.Pause();
                    break;
                case "2":
                    Functions.Clear();
                    PrintOverdueBookList();
                    Functions.Pause();
                    break;
                case "3":
                    Functions.Clear();
                    PrintMostPopularBookList();
                    Functions.Pause();
                    break;
                case "4":
                    Functions.Clear();
                    PrintMostBorrowingMemberList();
                    Functions.Pause();
                    break;
                    
                case "0":
                    break;
                default:
                    Functions.SystemAlert(SystemCode.UndefinedChoice);
                    break;
            }
        
        
        }while(!choice.equals("0"));
    }

    void PrintBorrowedBookList(){
    
    }
    void PrintOverdueBookList(){
    
    }
    void PrintMostPopularBookList(){
    
    }
    void PrintMostBorrowingMemberList(){
    
    }

}
