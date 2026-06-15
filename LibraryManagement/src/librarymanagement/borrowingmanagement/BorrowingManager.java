package librarymanagement.borrowingmanagement;

import java.util.HashMap;
import librarymanagement.bookmanagement.Book;
import librarymanagement.membermanagement.Member;

import librarymanagement.utils.Functions;

import librarymanagement.bookmanagement.BookManager;
import librarymanagement.membermanagement.MemberManager;

import abstractions.ObjectManager;

import java.time.LocalDate;

public class BorrowingManager extends ObjectManager<BorrowingTransaction>{
    
    // singleton
    private static final BorrowingManager instance = new BorrowingManager();
    private BorrowingManager(){}
    public static BorrowingManager getInstance(){
        return instance;
    }
    // end singleton
    public boolean IsTwoDateValid(LocalDate borrowDate, LocalDate overdueDate){
        return Functions.DayBetween(borrowDate, overdueDate) > 0;
    }
    
    public void Borrow(String transactionId,String memberId, String bookId, LocalDate borrowDate, LocalDate overdueDate){
        if(MemberManager.getInstance().IsIdExist(memberId) && 
           BookManager.getInstance().IsIdExist(bookId) && 
           IsTwoDateValid(borrowDate, overdueDate))
        {
            BorrowingTransaction newTransaction = new BorrowingTransaction(transactionId, memberId, bookId, borrowDate, overdueDate);
            Add(transactionId, newTransaction);
        }
    }
    public void Return(String transactionId){
        
    }
    
    @Override
    public void View(){
        
    }

}
