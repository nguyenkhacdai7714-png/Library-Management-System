package librarymanagement.reportingmanagement;

import java.util.ArrayList;
import librarymanagement.bookmanagement.Book;
import librarymanagement.membermanagement.Member;

public class ReportingManager {
    
    // singleton
    private static final ReportingManager instance = new ReportingManager();
    private ReportingManager(){}
    public static ReportingManager getInstance(){
        return instance;
    }
    // end singleton
    
    static ArrayList<Book> borrowedBookList = new ArrayList<Book>();
    static ArrayList<Book> overdueBookList = new ArrayList<Book>();
    static ArrayList<Member> mostActiveMemberList = new ArrayList<Member>();
    static ArrayList<Book> mostPopularBookList = new ArrayList<Book>();
    
    public static void GenerateBorrowedBookList(){
    
    }
    public static void GenerateMostActiveMemberList(){
    
    }
    public static void GenerateMostPopularBookList(){
    
    }
    public static void GenerateOverdueBookList(){
    
    }
    
    public static void PrintBookList(ArrayList<Book> list){
        
    }
    public static void PrintMemberList(ArrayList<Member> list){
        
    }
}
