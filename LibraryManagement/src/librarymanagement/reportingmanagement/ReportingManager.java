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
    
    private static ArrayList<BorrowingTransaction> activeTransactionList = new ArrayList<BorrowingTransaction>();
    private static ArrayList<BorrowingTransaction> overdueTransactionList = new ArrayList<BorrowingTransaction>();
    private static ArrayList<Member> mostActiveMemberList = new ArrayList<Member>();
    private static ArrayList<Book> mostPopularBookList = new ArrayList<Book>();

    public static ArrayList<BorrowingTransaction> getActiveTransactionList() {
        return activeTransactionList;
    }

    public static void setActiveTransactionList(ArrayList<BorrowingTransaction> activeTransactionList) {
        ReportingManager.activeTransactionList = activeTransactionList;
    }

    public static ArrayList<BorrowingTransaction> getOverdueTransactionList() {
        return overdueTransactionList;
    }

    public static void setOverdueTransaction(ArrayList<BorrowingTransaction> overdueTransactionList) {
        ReportingManager.overdueTransactionList = overdueTransactionList;
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
    
    
    
    public static void GenerateActiveTransactionList(){
        if(IsArrayListExist(activeTransactionList))
        {
            for (Map.Entry<String, BorrowingTransaction> entry: BorrowingManager.getInstance().getList().entrySet()) {
                BorrowingTransaction value = entry.getValue();
                
                if(!value.IsReturned()){
                    activeTransactionList.add(value);
                }
            }
        }
    }
    public static void GenerateMostActiveMemberList(){
        if(IsArrayListExist(mostActiveMemberList)){
            for (Map.Entry<String, Member> entry: MemberManager.getInstance().getList().entrySet()) {
                Member value = entry.getValue();
                
                if(!value.IsReadingHistoryEmpty()){
                    mostActiveMemberList.add(value);
                }
            }
            mostActiveMemberList.sort(Comparator.comparingInt(Member::getReadingHistoryLength).reversed());
        }
    }
    public static void GenerateMostPopularBookList(){
        if(IsArrayListExist(mostPopularBookList)){
            for (Map.Entry<String, Book> entry: BookManager.getInstance().getList().entrySet()) {
                Book value = entry.getValue();
                
                if(value.getBorrowings()>0){
                    mostPopularBookList.add(value);
                }
            }
            mostPopularBookList.sort(Comparator.comparingInt(Book::getBorrowings).reversed());
        }
    }
    public static void GenerateOverdueTransactionList(){
        if(IsArrayListExist(overdueTransactionList)){
            for (Map.Entry<String, BorrowingTransaction> entry: BorrowingManager.getInstance().getList().entrySet()) {
                BorrowingTransaction value = entry.getValue();
                
                if(value.IsOverdue() && !value.IsReturned()){
                    overdueTransactionList.add(value);
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
    public static void PrintTransactionList(List<BorrowingTransaction> transactionList, String title, String emptyAlert){
        BorrowingManager.getInstance().ViewList(transactionList, title, emptyAlert);
    }

    
    // clears
    
    public static void ClearReports(){
        ClearactiveTransactionList();
        ClearOverdueTransactionList();
        ClearMostActiveMemberList();
        ClearMostPopularBookList();
    }
    
    public static void ClearactiveTransactionList(){
        if(IsArrayListExist(activeTransactionList)){ 
            activeTransactionList.clear(); 
        }
        else
        {
            activeTransactionList = new ArrayList<BorrowingTransaction>(); 
        }
    }
    public static void ClearOverdueTransactionList(){
        if(IsArrayListExist(overdueTransactionList)){
            overdueTransactionList.clear();
        }
        else
        {
            overdueTransactionList = new ArrayList<BorrowingTransaction>();
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
