package librarymanagement.reportingmanagement;

import librarymanagement.utils.Functions;
import librarymanagement.utils.SystemCode;

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
                "Back (the reports will be regenerated)", // end
                
                "Show Active Transaction", // functions...
                "Show Overdue Transaction",
                "Show Most Popular Books",
                "Show Most Active Members");
    }
    
    public void Run()
    {   
        System.out.println("Generating reports...");
        ReportingManager.GenerateActiveTransactionList();
        System.out.print("25%");
        ReportingManager.GenerateOverdueTransactionList();
        System.out.print(" 50%");
        ReportingManager.GenerateMostPopularBookList();
        System.out.print(" 75%");
        ReportingManager.GenerateMostActiveMemberList();
        System.out.print(" 100%");
        
        String choice;
        do{
            Functions.Clear();
            Menu();
            choice = Functions.InputMenuChoice();
            
            switch(choice)
            {      
                case "1":
                    PrintBorrowedBookList();
                    break;
                case "2":
                    PrintOverdueBookList();
                    break;
                case "3":
                    PrintMostPopularBookList();
                    break;
                case "4":
                    PrintMostActiveMemberList();
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
        reportingManager.PrintTransactionList(reportingManager.getActiveTransactionList(), "ALL BORROWED BOOKS", "There is no borrowed book");
        Functions.Pause();
    }

    void PrintOverdueBookList(){
        Functions.Clear();
        reportingManager.PrintTransactionList(reportingManager.getOverdueTransactionList(), "ALL OVERDUE BOOKS LIST", "There is no overdue book");
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
