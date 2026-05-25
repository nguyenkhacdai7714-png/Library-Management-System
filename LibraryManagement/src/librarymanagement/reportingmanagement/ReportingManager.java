package librarymanagement.reportingmanagement;

import java.util.ArrayList;
import librarymanagement.bookmanagement.Book;
import librarymanagement.membermanagement.Member;
import static librarymanagement.reportingmanagement.ReportingManagement.GenerateBorrowedBookList;
import static librarymanagement.reportingmanagement.ReportingManagement.GenerateMostBorrowingMemberList;
import static librarymanagement.reportingmanagement.ReportingManagement.GenerateMostPopularBookList;
import static librarymanagement.reportingmanagement.ReportingManagement.GenerateOverdueBookList;

public class ReportingManager {
    static ArrayList<Book> borrowedBookList = new ArrayList<Book>();
    static ArrayList<Book> overdueBookList = new ArrayList<Book>();
    static ArrayList<Member> mostBorrowingMemberList = new ArrayList<Member>();
    static ArrayList<Book> mostPopularBookList = new ArrayList<Book>();
    
    public static void GenerateBorrowedBookList(){
    
    }
    public static void GenerateMostBorrowingMemberList(){
    
    }
    public static void GenerateMostPopularBookList(){
    
    }
    public static void GenerateOverdueBookList(){
    
    }
}
