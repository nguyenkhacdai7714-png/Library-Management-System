package librarymanagement.borrowingmanagement;

import java.util.*;
import librarymanagement.utils.Functions;
import librarymanagement.utils.SystemCode;
import abstractions.ObjectManagement;

import java.time.LocalDate;

import librarymanagement.bookmanagement.*;
import librarymanagement.membermanagement.*;

public class BorrowingManagement implements ObjectManagement{
    
    // singleton
    private static final BorrowingManagement instance = new BorrowingManagement();
    private BorrowingManagement(){}
    public static BorrowingManagement getInstance(){
        return instance;
    }
    // end singleton
    
    private BorrowingManager manager = BorrowingManager.getInstance();
    
    public void InitLoading(){
        manager.LoadCurrentBorrowingAll();
    }
    
    @Override
    public void Adding(){
        
        String transactionId;
        String bookId;
        String memberId;
        LocalDate borrowDate;
        LocalDate overdueDate;
        
        Member member=null;
        
        BorrowingManager borrowingManager = manager;
        BookManager bookManager = BookManager.getInstance();
        MemberManager memberManager = MemberManager.getInstance();
        
        if(borrowingManager.IsListFull()){
            Functions.Alert("The transaction storage is full!");
            return;
        }
        
        if(bookManager.IsListEmpty()){
            Functions.Alert("The book list is empty!");
            return;
        }
        if(memberManager.IsListEmpty()){
            Functions.Alert("The member list is empty!");
            return;
        }
        
        Functions.Clear();
        
        Functions.Print("---------- BORROWING BOOK -----------\n");
        
        transactionId = manager.IdGenerator("T");
        if(borrowingManager.IsIdExist(transactionId)){
           Functions.Alert("ID is existed!");
           return;
        }
        System.out.println("New transaction ID : " + transactionId);
        
        do{
            bookId = Functions.InputString("Enter book ID ('0' to quit):");
            if(bookId.equals("0")){
                return;
            }
            if(!Functions.IsStringValid(bookId) || !bookManager.IsIdExist(bookId)){
                Functions.Print("The book ID is not exist!\n");
            }
            else{
                if(bookManager.SearchById(bookId).getQuantity()>0){
                    break;
                }
                else{
                    Functions.Print("Book is out of stock!\n");
                }
            }
        }while(true);
        
        do{
            memberId = Functions.InputString("Enter member ID ('0' to quit):");
            if(memberId.equals("0")){
                return;
            }
            if(!Functions.IsStringValid(memberId) || !memberManager.IsIdExist(memberId)){
                Functions.Print("The member ID is incorrect or it is not exist!\n");
            }
            else{
                member = memberManager.SearchById(memberId);
                if(!member.IsUnderBorrowingLimit()){
                    Functions.Print("This member has reached borrowing limit (" + member.getBorrowingLimit() + " books) \n");
                }
                else{
                    break;
                }
            }
        }while(true);
        
        do{
            borrowDate = Functions.InputDate("Enter Borrow Day");
            if(!Functions.IsDateValid(borrowDate)){
            Functions.Print("The 'borrow day' you entered is incorrect!\n");
        }
        }while(!Functions.IsDateValid(borrowDate));
     
        do{
            overdueDate = Functions.InputDate("Enter overdue Day");
            if(!Functions.IsDateValid(overdueDate)){
            Functions.Print("The 'overdue day' you entered is incorrect!\n");
        }
        }while(!Functions.IsDateValid(overdueDate));
        
        borrowingManager.Borrow(transactionId, memberId, bookId, borrowDate, overdueDate); // update for transaction, member
        borrowingManager.TakeBookOut(bookManager.SearchById(bookId)); // update for book
        borrowingManager.AddReadingHistory(memberId, bookId);         // update for member
        Functions.Alert("Transaction added successfully!");
        
    }
    @Override
    public void Removing(){
        
        String transactionId;
        String answer;
        float fine;
        BorrowingTransaction matchedTransaction;
        String bookId;
        String memberId;
        LocalDate returnDate;
        
        BorrowingManager borrowingManager = manager;
        
        if(borrowingManager.IsListEmpty()){
            Functions.Alert("The transaction storage is empty!");
            return;
        }
        
        Functions.Print("---------- RETURNING BOOK -----------\n");
        
        transactionId = Functions.InputString("Enter transaction ID : ");
        
        matchedTransaction = borrowingManager.SearchById(transactionId);
        
        if(matchedTransaction==null){
            Functions.Alert("Transaction ID is in valid!");
            return;
        }
        else if(matchedTransaction.IsReturned()){
            Functions.Alert("The book of this transaction was returned!");
            return;
        }
        else{
            
            bookId = matchedTransaction.getBookID();
            memberId = matchedTransaction.getMemberID();
            
            returnDate = Functions.InputDate("Enter return date");
            if(!Functions.IsDateValid(returnDate)){
                Functions.Alert("Invalid return date");
                return;
            }
            
            fine = borrowingManager.GetOverdueFine(transactionId, returnDate);
            matchedTransaction.View();
            answer = Functions.YNQuestion("Return the book? You have to pay "+ String.format("%.0f", fine) + " vnd");
            
            if(answer.equals("y")){
                borrowingManager.Return(transactionId, returnDate);
                borrowingManager.TakeBookIn(BookManager.getInstance().SearchById(bookId));
            }
            else{
                Functions.Alert("Cancelled!");
                return;
            }
        }
        
        Functions.Alert("Return the book successfully!");
    }
    @Override
    public void Viewing(){
        Functions.Clear();
        manager.View();
        Functions.Pause();
    }
    @Override
    public void Searching(){
        String inp;
        ArrayList<BorrowingTransaction> transaction;
        BorrowingManager borrowingManager = manager;
        
        if(borrowingManager.IsListEmpty()){
            Functions.Alert("Transaction list is empty!");
            return;
        }
        
        Functions.Print("---------- SEARCH TRANSACTION -----------\n");
        
        inp = Functions.InputString("Enter any transaction information :");
        
        transaction = borrowingManager.SearchByAll(inp);
        if(transaction==null || transaction.isEmpty()){
            Functions.Alert("Transaction not found!");
            return;
        }
        
        int length = transaction.size();
        if(length==1){
            transaction.get(0).View();
            Functions.Pause();
        }
        else{
            Functions.Clear();
            manager.ViewList(transaction, "ALL TRANSACTION FOUNDS", "Nothing here!");
            Functions.Pause();
        }
        
    }
    
    void UpdatingMenu(BorrowingTransaction currentTrans, String newMemberId, String newBookId, LocalDate newOverdueDate){
        
        
        boolean isMemberChanged = !currentTrans.getMemberID().equals(newMemberId);
        boolean isBookChanged = !currentTrans.getBookID().equals(newBookId);
        boolean isOverdueDateChanged = (currentTrans.getOverdueDate()!=newOverdueDate);
        
        Functions.Print("----------------------------------- UPDATING TRANSACTION ------------------------------------\n");
        Functions.Print("Editing Transaction ID [" + currentTrans.getId() + "]                      \n");      
        System.out.printf("[1] Member ID     : %-12s", currentTrans.getMemberID());
        System.out.println(isMemberChanged?(" -> "+newMemberId): "");
        
        System.out.printf("[2] Book ID       : %-12s", currentTrans.getBookID());
        System.out.println(isBookChanged?(" -> "+newBookId): "");
        
        System.out.printf("[3] Overdue Date  : %-12s", Functions.DateToString(currentTrans.getOverdueDate()));
        System.out.println(isOverdueDateChanged?(" -> "+Functions.DateToString(newOverdueDate)): "");
        
        Functions.Print("---------------------------------------------------------------------------------------------\n");
        Functions.Print("[0] Save / Quit"+ "\n");
    }
    @Override
    public void Updating(){
        
        String transactionId;
        
        String newMemberId;
        String newBookId;
        LocalDate newOverdueDate;
        LocalDate borrowDate;
        
        BorrowingTransaction transaction;
        BorrowingManager borrowingManager = manager;
        MemberManager memberManager = MemberManager.getInstance();
        BookManager bookManager = BookManager.getInstance();
        
        String choice;
        String inp;
        String answer;
        LocalDate inpDate;
        
        // -----------------
        
        Functions.Print("---------- UPDATING TRANSACTION -----------\n");
        transactionId = Functions.InputString("Enter transaction ID: ");
        transaction = borrowingManager.SearchById(transactionId);
        if(transaction==null){
            Functions.Alert("Transaction not found!");
            return;
        }
        
        newMemberId = transaction.getMemberID();
        newBookId = transaction.getBookID();
        newOverdueDate = transaction.getOverdueDate();
        borrowDate = transaction.getBorrowDate();
        
        do{
            Functions.Clear();
            UpdatingMenu(transaction, newMemberId, newBookId, newOverdueDate);
            choice = Functions.InputMenuChoice();
            switch(choice){
                case"1":
                    inp = Functions.InputString("Enter new member ID: ");
                    
                    if(inp.equals(newMemberId)){
                        Functions.Alert("Nothing changed!");
                    }
                    else if(memberManager.IsIdExist(inp)){
                        newMemberId = inp;
                    }
                    else
                    {
                        Functions.Alert("Member not found!");
                    }
                    break;
                case"2":
                    inp = Functions.InputString("Enter new book ID: ");
                    if(inp.equals(newBookId)){
                        Functions.Alert("Nothing changed!");
                    }
                    else if(bookManager.IsIdExist(inp)){
                        newBookId = inp;
                    }
                    else
                    {
                        Functions.Alert("Book not found!");
                    }
                    break;
                case"3":
                    inpDate = Functions.InputDate("Enter new overdue date");
                    if (inpDate == newOverdueDate){
                        Functions.Alert("Nothing changed!");
                    }
                    else if(Functions.IsTwoDateValid(borrowDate, inpDate)){
                        newOverdueDate = inpDate;
                    }
                    else{
                        Functions.Alert("Date not valid!");
                    }
                    break;
                case"0":
                    break;
                default:
                    Functions.SystemAlert(SystemCode.UndefinedChoice);
            }
            
        }while(!choice.equals("0"));
        
        boolean isMemberChanged = !transaction.getMemberID().equals(newMemberId);
        boolean isBookChanged = !transaction.getBookID().equals(newBookId);
        boolean isOverdueDateChanged = (transaction.getOverdueDate()!=newOverdueDate);
        
        if(isMemberChanged || isBookChanged || isOverdueDateChanged){
            answer = Functions.YNQuestion("Do you want to override the data?");
            if(answer.equals("y")){

                borrowingManager.Update(transactionId, newBookId, newMemberId, newOverdueDate);
                Functions.Alert("Updated successfully!");
                return;
            }
            else{
                Functions.Alert("Cancelled");
                return;
            }
        }
        Functions.Alert("Nothing changed, quit update mode!");
        
    }
    
    public void ViewReadingHistory(){
        
        String memberId;
        Member member;
        MemberManager memberManager = MemberManager.getInstance();
        BorrowingManager borrowingManager = manager;
        
        Functions.Print("---------- VIEW READING HISTORY -----------\n");
        memberId = Functions.InputString("Enter member ID: ");
        member = memberManager.SearchById(memberId);
        if(member==null){
            Functions.Alert("Member not found!");
            return;
        }
        if(member.IsReadingHistoryEmpty()){
            Functions.Alert("Member has no reading history!");
            return;
        }
        
        Functions.Clear();
        borrowingManager.ViewHistoryReading(memberId);
        Functions.Pause();
        
    }
    
    @Override
    public void Menu(){
        Functions.MenuGenerator(
                "BORROWING MANAGEMENT",
                "Back",
                "Borrow",
                "Return",
                "Update Transaction",
                "View All Transaction",
                "View Reading History of a Member",
                "Search Transaction");
    }
    
    public void Run()
    {
        String choice;
        
        do{
            Functions.Clear();
            Menu();
            choice = Functions.InputMenuChoice();
            
            switch(choice){
                case"1":
                    Adding();
                    break;
                case"2":
                    Removing();
                    break;
                case"3":
                    Updating();
                    break;
                case"4":
                    Viewing();
                    break;
                case"5":
                    ViewReadingHistory();
                    break;
                case"6":
                    Searching();
                    break;
                case"0":
                    break;
                default:
                    Functions.SystemAlert(SystemCode.UndefinedChoice);
            }
            
        }while(!choice.equals("0"));
    }
}
