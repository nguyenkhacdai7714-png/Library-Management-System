package librarymanagement.borrowingmanagement;

import java.util.*;
import librarymanagement.bookmanagement.*;
import librarymanagement.membermanagement.*;

import librarymanagement.utils.Functions;
import librarymanagement.utils.BoardDrawer;

import librarymanagement.bookmanagement.BookManager;
import librarymanagement.membermanagement.MemberManager;

import abstractions.ObjectManager;

import java.time.LocalDate;
import java.util.Map;

public class BorrowingManager extends ObjectManager<BorrowingTransaction>{
    
    // singleton
    private static final BorrowingManager instance = new BorrowingManager();
    private BorrowingManager(){}
    public static BorrowingManager getInstance(){
        return instance;
    }
    // end singleton
    
    public void LoadCurrentBorrowingAll(){
        for(String id : MemberManager.getInstance().getList().keySet()){
            for(BorrowingTransaction transaction : getList().values()){
                if(!transaction.IsReturned() && transaction.getMemberID().equals(id)){
                    Member member = MemberManager.getInstance().getList().get(id);
                    member.addCurrentBorrowing();
                }
            }
            
        }
    }
    
    @Override
    public void View(){
        ViewList(getList().values(), "transaction list", "Transaction list is empty");
    }
    
    @Override
    public void ViewList(Collection<BorrowingTransaction> itemList, String title, String emptyAlert){
        BoardDrawer.SetBoard(5 + 9*3 + 13*2 + 12*3 +    9*3, "| %-5s | %-9s | %-9s | %-9s | %-13s | %-13s | %-12s | %-12s | %-12s |");
        BoardDrawer.PrintTitle(title, emptyAlert, itemList.isEmpty());
        if(!itemList.isEmpty()){
            BoardDrawer.PrintRow("No#","Trans. ID","Member ID", "Book ID","Borrow Date", "Overdue Date", "Return Date", "Is Returned", "Is Overdue");
            BoardDrawer.PrintWall();
            int count = 1;
            for (BorrowingTransaction transaction : itemList){
                BoardDrawer.PrintRow(count, 
                                    transaction.getId(), 
                                        transaction.getMemberID(), 
                                            transaction.getBookID(), 
                                                Functions.DateToString(transaction.getBorrowDate()), 
                                                    Functions.DateToString(transaction.getOverdueDate()), 
                                                        Functions.DateToString(transaction.getReturnDate()),
                                                            transaction.IsReturned()?"Yes":"No",
                                                                transaction.IsOverdue()?"Yes":"No");
                count++;
            }
            BoardDrawer.PrintSoftWall();
            BoardDrawer.PrintTotal(getList().size(), "transaction");
        }
        BoardDrawer.PrintWall();
    }
    
    public void ViewHistoryReading(String memberId){
        Member member = MemberManager.getInstance().SearchById(memberId);
        
        System.out.println("======================== READING HISTORY OF '" + member.getName() + "' ("+memberId+") " + "========================");
        member.ViewReadingHistory();
        System.out.println("------------------------------------------------------------");
        System.out.println("Total : " + member.getReadingHistoryLength() + " readings");
    }
    
    public void Borrow(String transactionId,String memberId, String bookId, LocalDate borrowDate, LocalDate overdueDate){     
        if(MemberManager.getInstance().IsIdExist(memberId) &&                  // check if memberID exist
           BookManager.getInstance().IsIdExist(bookId)    &&                  // check if bookID exist
           BookManager.getInstance().SearchById(bookId).getQuantity()>0   &&   // check if quantity is enough
           Functions.IsTwoDateValid(borrowDate, overdueDate))                            // check if two dates valid
        {
            BorrowingTransaction newTransaction = new BorrowingTransaction(transactionId, memberId, bookId, borrowDate, overdueDate);
            Add(transactionId, newTransaction);
            MemberManager.getInstance().SearchById(memberId).addCurrentBorrowing();
        }
    }
    
    public boolean IsMemberOnTransaction(String memberId){
        for (Map.Entry<String, BorrowingTransaction> entry : list.entrySet()) {
            String id = entry.getKey();
            BorrowingTransaction transaction = entry.getValue();
            
            if(transaction.getMemberID().equals(memberId)
                    && !transaction.IsReturned()){
                return true;
            }
        }
        return false;
    }
    public boolean IsBookOnATransaction(String bookId){
        for (Map.Entry<String, BorrowingTransaction> entry : list.entrySet()) {
            String id = entry.getKey();
            BorrowingTransaction transaction = entry.getValue();
            
            if(transaction.getBookID().equals(bookId)
                    && !transaction.IsReturned()){
                return true;
            }
        }
        return false;
    }
    
    public void AddReadingHistory(String memberId, String bookId){
        Member member = MemberManager.getInstance().SearchById(memberId);
        Book book = BookManager.getInstance().SearchById(bookId);
        
        String log = String.format(
                "[%s] '%s' (%s) has read '%s' (%s) by '%s' - pub.year %s (log version 1.0)", 
                Functions.DateToString(Functions.Today()),
                member.getName(),
                memberId,
                book.getTitle(),
                bookId,
                book.getAuthor(),
                book.getPublicationYear());
        member.AddReadingHistory(log);
    }
    
    public void Return(String transactionId, LocalDate returnDate){
        SearchById(transactionId).setReturnDate(returnDate);
        MemberManager.getInstance().SearchById(getList().get(transactionId).getMemberID()).removeCurrentBorrowing();
    }
    
    public void TakeBookOut(Book borrowedBook){
        if(borrowedBook.getQuantity() > 0){
            borrowedBook.setQuantity(borrowedBook.getQuantity() - 1);
            borrowedBook.addBorrowing();
        }
    }
    public void TakeBookIn(Book returnedBook){
        returnedBook.setQuantity(returnedBook.getQuantity() + 1);
    }
    
    public float GetOverdueFine(String transactionId){
        BorrowingTransaction transaction = SearchById(transactionId);
        
        if(!transaction.IsReturned()){       
            String memberId = transaction.getMemberID();
            Member member = MemberManager.getInstance().SearchById(memberId);   
            
            long daysBetween = Functions.DayBetween(transaction.getOverdueDate(),Functions.Today());
            if(daysBetween  > 0){
                return member.getMembership().getOverdueFine(daysBetween);
            }
            else{
                return 0f;
            }
            
        }
        else{
            return 0f;
        }
    }
    
    public void Update(String transacionId, String memberId, String bookId, LocalDate overdueDate){
        BorrowingTransaction transaction = SearchById(transacionId);
        if(transaction==null){
            return;
        }
        
        transaction.setBookID(bookId);
        transaction.setMemberID(memberId);
        transaction.setOverdueDate(overdueDate);
    }
    
    

}
