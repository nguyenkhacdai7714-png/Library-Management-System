package librarymanagement.reportingmanagement;

import java.util.*;
import librarymanagement.utils.Functions;
import librarymanagement.utils.SystemCode;

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
    
    private static ReportingManager reportingManager = ReportingManager.getInstance();
    
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
        Functions.Print("Generating reports...\n");
        ReportingManager.GenerateOverdueBookList();
        ReportingManager.GenerateBorrowedBookList();
        ReportingManager.GenerateMostActiveMemberList();
        ReportingManager.GenerateMostPopularBookList();
        Functions.Print("-> Successfully!\n");
        
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
                    PrintMostActiveMemberList();
                    Functions.Pause();
                    break;
                    
                case "0":
                    break;
                default:
                    Functions.SystemAlert(SystemCode.UndefinedChoice);
                    break;
            }
        
        
        }while(!choice.equals("0"));
        
        ReportingManager.getInstance().ClearReports();
    }

    void PrintBorrowedBookList(){
        Functions.Clear();
        reportingManager.PrintBookList(reportingManager.getBorrowedBookList(), "ALL BORROWED BOOKS", "There is no borrowed book");
        Functions.Pause();
    }

    void PrintOverdueBookList(){
        Functions.Clear();
        reportingManager.PrintBookList(reportingManager.getOverdueBookList(), "ALL OVERDUE BOOKS LIST", "There is no overdue book");
        Functions.Pause();
    }

    void PrintMostPopularBookList(){
        Functions.Clear();
        reportingManager.PrintBookList(reportingManager.getMostPopularBookList(), "ALL MOST POPULAR BOOKS", "There is no popular books");
        Functions.Pause();
    }

    void PrintMostActiveMemberList(){
        Functions.Clear();
        reportingManager.PrintMemberList(reportingManager.getMostActiveMemberList(), "ALL MOST ACTIVE MEMBERS", "There is no active members");
        Functions.Pause();
    }



}
