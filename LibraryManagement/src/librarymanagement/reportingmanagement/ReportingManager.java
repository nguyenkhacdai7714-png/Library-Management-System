package librarymanagement.reportingmanagement;

import java.util.*;
import librarymanagement.bookmanagement.*;
import librarymanagement.membermanagement.*;
import librarymanagement.borrowingmanagement.*;

import java.util.Comparator;

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

    public static ArrayList<Book> getBorrowedBookList() {
        return borrowedBookList;
    }

    public static void setBorrowedBookList(ArrayList<Book> borrowedBookList) {
        ReportingManager.borrowedBookList = borrowedBookList;
    }

    public static ArrayList<Book> getOverdueBookList() {
        return overdueBookList;
    }

    public static void setOverdueBookList(ArrayList<Book> overdueBookList) {
        ReportingManager.overdueBookList = overdueBookList;
    }

    public static ArrayList<Member> getMostActiveMemberList() {
        return mostActiveMemberList;
    }

    public static void setMostActiveMemberList(ArrayList<Member> mostActiveMemberList) {
        ReportingManager.mostActiveMemberList = mostActiveMemberList;
    }

    public static ArrayList<Book> getMostPopularBookList() {
        return mostPopularBookList;
    }

    public static void setMostPopularBookList(ArrayList<Book> mostPopularBookList) {
        ReportingManager.mostPopularBookList = mostPopularBookList;
    }
    
    
    
    public static void GenerateBorrowedBookList(){
        if(IsArrayListExist(borrowedBookList))
        {
            for (Map.Entry<String, BorrowingTransaction> entry: BorrowingManager.getInstance().getList().entrySet()) {
                String key = entry.getKey();
                BorrowingTransaction value = entry.getValue();
                
                if(!value.IsReturned()){
                    borrowedBookList.add( BookManager.getInstance().SearchById(value.getBookID()));
                }
            }
        }
    }
    public static void GenerateMostActiveMemberList(){
        if(IsArrayListExist(mostActiveMemberList)){
            for (Map.Entry<String, Member> entry: MemberManager.getInstance().getList().entrySet()) {
                String key = entry.getKey();
                Member value = entry.getValue();
                
                mostActiveMemberList.add(value);
            }
            mostActiveMemberList.sort(Comparator.comparingInt(Member::getReadingHistoryLength));
        }
    }
    public static void GenerateMostPopularBookList(){
        if(IsArrayListExist(mostPopularBookList)){
            
        }
    }
    public static void GenerateOverdueBookList(){
        if(IsArrayListExist(overdueBookList)){
            for (Map.Entry<String, BorrowingTransaction> entry: BorrowingManager.getInstance().getList().entrySet()) {
                String key = entry.getKey();
                BorrowingTransaction value = entry.getValue();
                
                if(value.IsOverdue() && !value.IsReturned()){
                    overdueBookList.add(BookManager.getInstance().SearchById(value.getBookID()));
                }
            }
        }
    }
    
    public static <T> boolean IsArrayListExist(ArrayList<T> array){
        return array!=null;
    }
    
    // cac ham prints
    public static void PrintMemberList(List<Member> memberList, String title, String emptyAlert) {
        MemberManager.getInstance().ViewList(memberList, title, emptyAlert);
    }

    public static void PrintBookList(List<Book> bookList, String title, String emptyAlert) {
        BookManager.getInstance().ViewList(bookList, title, emptyAlert);
    }

    
    // clears
    
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
