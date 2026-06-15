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
    
    private static ArrayList<Book> borrowedBookList = new ArrayList<Book>();
    private static ArrayList<Book> overdueBookList = new ArrayList<Book>();
    private static ArrayList<Member> mostActiveMemberList = new ArrayList<Member>();
    private static ArrayList<Book> mostPopularBookList = new ArrayList<Book>();
    
    public static void GenerateBorrowedBookList(){
        if(IsArrayListExist(borrowedBookList))
        {
            
        }
    }
    public static void GenerateMostActiveMemberList(){
        if(IsArrayListExist(overdueBookList)){
            
        }
    }
    public static void GenerateMostPopularBookList(){
        if(IsArrayListExist(mostActiveMemberList)){
            
        }
    }
    public static void GenerateOverdueBookList(){
        if(IsArrayListExist(mostPopularBookList)){
            
        }
    }
    
    public static <T> boolean IsArrayListExist(ArrayList<T> array){
        return array!=null;
    }
    
    // cac ham prints
    
    public static void ClearReports(){
        ClearBorrowedBookList();
        ClearOverdueBookList();
        ClearMostActiveMemberList();
        ClearMostPopularBookList();
    }
    
    public static void ClearBorrowedBookList(){
        if(IsArrayListExist(borrowedBookList)){
            borrowedBookList.clear();
        }
        else
        {
            borrowedBookList = new ArrayList<Book>();
        }
    }
    public static void ClearOverdueBookList(){
        if(IsArrayListExist(overdueBookList)){
            overdueBookList.clear();
        }
        else
        {
            overdueBookList = new ArrayList<Book>();
        }
    }
    public static void ClearMostActiveMemberList(){
        if(IsArrayListExist(mostActiveMemberList)){
            mostActiveMemberList.clear();
        }
        else
        {
            mostActiveMemberList = new ArrayList<Member>();
        }
    }
    public static void ClearMostPopularBookList(){
        if(IsArrayListExist(mostPopularBookList)){
            mostPopularBookList.clear();
        }
        else
        {
            mostPopularBookList = new ArrayList<Book>();
        }
    }
}
