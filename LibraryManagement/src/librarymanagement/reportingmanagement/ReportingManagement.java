package librarymanagement.reportingmanagement;

import java.util.*;
import librarymanagement.utils.*;

import librarymanagement.bookmanagement.Book;
import librarymanagement.membermanagement.Member;

public class ReportingManagement {
    
    static ArrayList<Book> borrowedBookList = new ArrayList<Book>();
    static ArrayList<Book> overdueBookList = new ArrayList<Book>();
    static ArrayList<Member> mostBorrowingMemberList = new ArrayList<Member>();
    static ArrayList<Book> mostPopularBookList = new ArrayList<Book>();

    
    public static void Menu(){
        Functions.MenuGenerator(
                "REPORTING MANAGEMENT", // title
                "Back", // end
                
                "Show Borrowed Books", // functions...
                "Show Overdue Books",
                "Show Most Popular Books",
                "Show Most Borrowings Members");
    }
    
    public static void Run()
    {
        Functions.Print("Generating reports...");
        GenerateBorrowedBookList();
        GenerateMostBorrowingMemberList();
        GenerateMostPopularBookList();
        GenerateOverdueBookList();
        Functions.Print("Successfully!");
        
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
    
    
    static void GenerateBorrowedBookList(){
        
    }
    static void GenerateOverdueBookList(){
    
    }
    static void GenerateMostPopularBookList(){
    
    }
    static void GenerateMostBorrowingMemberList(){
    
    }

    static void PrintBorrowedBookList(){
    
    }
    static void PrintOverdueBookList(){
    
    }
    static void PrintMostPopularBookList(){
    
    }
    static void PrintMostBorrowingMemberList(){
    
    }

}
