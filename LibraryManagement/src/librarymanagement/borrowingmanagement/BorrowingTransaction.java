package librarymanagement.borrowingmanagement;

import java.time.LocalDate;
import abstractions.LibraryObject;

import librarymanagement.utils.Functions;

public class BorrowingTransaction extends LibraryObject{
    // 1. Khai bao cac bien private de an toan
    private String memberID;
    private String bookID;
    private LocalDate borrowDate;
    private LocalDate overdueDate;
    private LocalDate returnDate;

    // 2. Constructor mac din
    public BorrowingTransaction() {
        super.setId("");
        this.memberID = "";
        this.bookID = "";
        this.borrowDate = null;
        this.overdueDate = null;
        
        this.returnDate = null;
    }

    // 3. Constructor co tham so 
    public BorrowingTransaction(String transactionID, String memberID, String bookID, LocalDate borrowDate, LocalDate overdueDate) {
        super.setId(transactionID);
        this.memberID = memberID;
        this.bookID = bookID;
        this.borrowDate = borrowDate;
        this.overdueDate = overdueDate;
        
        this.returnDate = null;
    }
    
    public BorrowingTransaction(String transactionID, String memberID, String bookID, LocalDate borrowDate, LocalDate overdueDate, LocalDate returnDate) {
        super.setId(transactionID);
        this.memberID = memberID;
        this.bookID = bookID;
        this.borrowDate = borrowDate;
        this.overdueDate = overdueDate;
        
        this.returnDate = returnDate;
    }
// 4.Getter/Setter

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getOverdueDate() {
        return overdueDate;
    }

    public void setOverdueDate(LocalDate overdueDate) {
        this.overdueDate = overdueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean IsReturned() {
        return returnDate!=null;
    }

    public boolean IsOverdue(){
        if(Functions.IsDateValid(returnDate)){
            return Functions.DayBetween(Functions.Today(), overdueDate) > 0;
        }
        return Functions.DayBetween(returnDate, overdueDate) > 0;
    }
    
    @Override
    public void View(){
        System.out.println("Transaction ID    : " + getId());
        System.out.println("Member ID         : " + getMemberID());
        System.out.println("Book ID           : " + getBookID());
        System.out.println("Borrow Date       : " + Functions.DateToString(getBorrowDate())); 
        System.out.println("Overdue Date      : " + Functions.DateToString(getOverdueDate())); 
        System.out.println("Return Date       : " + Functions.DateToString(getReturnDate()));
        System.out.println("Is returned       : " + (IsReturned() ? "Yes" : "No"  ));
        System.out.println("Is overdue        : " + (IsOverdue()  ? "Yes" : "No"  ));
    }
    
}