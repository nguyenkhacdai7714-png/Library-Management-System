package librarymanagement.borrowingmanagement;

import java.time.LocalDate;

public class BorrowingTransaction {
    // 1. Khai bao cac bien private de an toan
    private String transactionID;
    private String memberID;
    private String bookID;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean isReturned;

    // 2. Constructor mac din
    public BorrowingTransaction() {
        this.transactionID = "";
        this.memberID = "";
        this.bookID = "";
        this.borrowDate = null;
        this.dueDate = null;
        this.isReturned = false;
    }

    // 3. Constructor co tham so 
    public BorrowingTransaction(String transactionID, String memberID, String bookID, LocalDate borrowDate, LocalDate dueDate) {
        this.transactionID = transactionID;
        this.memberID = memberID;
        this.bookID = bookID;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.isReturned = false;
    }
// 4.Getter/Setter
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isIsReturned() {
        return isReturned;
    }

    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }
    
}