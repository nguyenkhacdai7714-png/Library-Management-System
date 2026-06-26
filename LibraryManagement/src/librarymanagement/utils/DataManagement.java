package librarymanagement.utils;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.util.*;

import librarymanagement.bookmanagement.*;
import librarymanagement.membermanagement.*;
import librarymanagement.borrowingmanagement.*;

import java.time.LocalDate;


public class DataManagement {
    
    private static File bookDataFile;
    private static File memberDataFile;
    private static File borrowingTransactionDataFile;
    
    public static void LoadVirtualData(){
        // Tao member ao?
        MemberManager.getInstance().Add("M000", new Member("M000", "Nguyen Khac Dai", "01234", "dai@gmail.com"));
        MemberManager.getInstance().Add("M001", new Member("M001", "Pham Ngoc Minh", "45678", "minh@gmail.com"));
        MemberManager.getInstance().Add("M002", new Member("M002", "Gia Khanh", "010101", "khanh@gmail.com"));
        MemberManager.getInstance().Add("M003", new Member("M003", "Vu Hung", "090909", "hung@gmail.com"));
        
        // Tao book ao?
        BookManager.getInstance().Add("B000",new Book("B000", "Toan 12", "NXB Kim Dong", "SGK", 2000, 5));
        BookManager.getInstance().Add("B001",new Book("B001", "Ngu Van 12", "NXB Kim Dong", "SGK", 2000, 2));
        BookManager.getInstance().Add("B002",new Book("B002", "English B2", "NXB Ha Noi", "SGK", 2005, 8));
        BookManager.getInstance().Add("B003",new Book("B003", "Truyen Kieu", "Nguyen Du", "Truyen", 1801, 1));
    }
    
    public static HashMap<String, Book> LoadBook(){
        
        HashMap<String, Book> list = new HashMap<String, Book>();
        
        try{
           
        
            bookDataFile = new File(Constants.bookFile);
            Scanner sc = new Scanner(bookDataFile);

            while(sc.hasNextLine()){
                
                String line = sc.nextLine();
                String[] parts = line.split(",");
                
                Book book = new Book(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        Integer.parseInt(parts[4]),
                        Integer.parseInt(parts[5]),
                        Integer.parseInt(parts[6])
                );
                list.put(parts[0], book);
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        
        return list;
    }
    public static HashMap<String, Member> LoadMember(){
        
        HashMap<String, Member> list = new HashMap<String, Member>();
        
        try{
           
        
            memberDataFile = new File(Constants.memberFile);
            Scanner sc = new Scanner(memberDataFile);

            while(sc.hasNextLine()){
                
                String line = sc.nextLine();
                String[] parts = line.split(",");
                
                Member member = new Member(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        
                        Functions.StringToReadingHistory(parts[4]),
                        Functions.TagToMembership(parts[5])
                        
                );
                list.put(parts[0], member);
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        
        return list;
    }
    public static HashMap<String, BorrowingTransaction> LoadBorrowingTransaction(){
        
        HashMap<String, BorrowingTransaction> list = new HashMap<String, BorrowingTransaction>();
        
        try{
           
        
            borrowingTransactionDataFile = new File(Constants.transactionFile);
            Scanner sc = new Scanner(borrowingTransactionDataFile);

            while(sc.hasNextLine()){
                
                String line = sc.nextLine();
                String[] parts = line.split(",");
                
                
                
                BorrowingTransaction borrowingTransaction = new BorrowingTransaction(
                        parts[0],
                        parts[1],
                        parts[2],
                        Functions.StringToDate(parts[3]),
                        Functions.StringToDate(parts[4]),
                        Functions.StringToDate(parts[5])
                );
                list.put(parts[0], borrowingTransaction);
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        
        return list;
    }
    
    
    public static void SaveBook(){
        HashMap<String, Book> list = BookManager.getInstance().getList();
        
        bookDataFile = new File(Constants.bookFile);
        
        try (FileWriter writer = new FileWriter(bookDataFile)) {
            
            for (Book book : list.values()) {
                
                String line = String.format("%s,%s,%s,%s,%d,%d,%d\n",
                        book.getId(),          
                        book.getTitle(),       
                        book.getAuthor(),      
                        book.getGenre(),    
                        book.getPublicationYear(), 
                        book.getQuantity(),
                        book.getBorrowings()
                );
                
                writer.write(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file lưu sách: " + e);
        } catch (EOFException e) {
            System.out.println("Lỗi kết thúc file đột ngột: " + e);
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống khi ghi file sách: " + e);
        }   
    }
    
    public static void SaveMember(){
        HashMap<String, Member> list = MemberManager.getInstance().getList();
        
        memberDataFile = new File(Constants.memberFile);
        
        try {
            if (memberDataFile.getParentFile() != null && !memberDataFile.getParentFile().exists()) {
                memberDataFile.getParentFile().mkdirs();
            }
            
            if (!memberDataFile.exists()) {
                memberDataFile.createNewFile();
            }
            
            try (FileWriter writer = new FileWriter(memberDataFile)) {
                for (Member member : list.values()) {
                    String line = String.format("%s,%s,%s,%s,%s,%s\n",
                            member.getId(),       
                            member.getName(),     
                            member.getPhone(),
                            member.getEmail(),
                            Functions.ReadingHistoryToString(member.getReadingHistory()),
                            Functions.MembershipToTag(member.getMembership())
                    );
                    writer.write(line);
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file lưu thành viên: " + e);
        } catch (EOFException e) {
            System.out.println("Lỗi kết thúc file đột ngột: " + e);
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống khi ghi file thành viên: " + e);
        }
    }
    
    public static void SaveBorrowingTransaction(){
        HashMap<String, BorrowingTransaction> list = BorrowingManager.getInstance().getList();
        
        borrowingTransactionDataFile = new File(Constants.transactionFile);
        
        try {
            if (borrowingTransactionDataFile.getParentFile() != null && !borrowingTransactionDataFile.getParentFile().exists()) {
                borrowingTransactionDataFile.getParentFile().mkdirs();
            }
            
            if (!borrowingTransactionDataFile.exists()) {
                borrowingTransactionDataFile.createNewFile();
            }
            
            // Ghi file
            try (FileWriter writer = new FileWriter(borrowingTransactionDataFile)) {
                for (BorrowingTransaction transaction : list.values()) {
                    String borrowDateStr = Functions.DateToString(transaction.getBorrowDate());
                    String overdueDateStr = Functions.DateToString(transaction.getOverdueDate());
                    String returnDateStr = Functions.DateToString(transaction.getReturnDate());
                    
                    String line = String.format("%s,%s,%s,%s,%s,%s\n",
                            transaction.getId(),         
                            transaction.getMemberID(),
                            transaction.getBookID(),
                            borrowDateStr,
                            overdueDateStr,
                            returnDateStr!=null?returnDateStr:"null"
                    );
                    writer.write(line);
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file lưu giao dịch: " + e);
        } catch (EOFException e) {
            System.out.println("Lỗi kết thúc file đột ngột: " + e);
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống khi ghi file giao dịch: " + e);
        }
    }


    
    public static void LoadAllData(){
        
      BookManager bookManager = BookManager.getInstance();
      MemberManager memberManager = MemberManager.getInstance();
      BorrowingManager borrowingManager = BorrowingManager.getInstance();
      
      bookManager.setList(LoadBook());
      memberManager.setList(LoadMember());
      borrowingManager.setList(LoadBorrowingTransaction());
    }
    public static void SaveAllData(){
        SaveBook();
        SaveMember();
        SaveBorrowingTransaction();
    }
}
