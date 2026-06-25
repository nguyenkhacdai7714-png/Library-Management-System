package librarymanagement.bookmanagement;

import java.util.HashMap;
import librarymanagement.utils.Functions;

// Kế thừa ObjectManager<Book> từ package abstractions theo đúng lớp cha 
public class BookManager extends abstractions.ObjectManager<Book> {
    
    // singleton
    private static final BookManager instance = new BookManager();
    private BookManager(){}
    public static BookManager getInstance(){
        return instance;
    }
    // end singleton
   
    // lenh view 
    @Override
    protected void PrintData(int stt, Book book) {
        // 1. dùng if-else de xet truong hop co và không có 
        // 1.1 khong có => báo null
        if (stt == 0) {
            // 1.1.1 IN PHẦN TIÊU ĐỀ (HEADER) CHUNG
            System.out.println("=================================================================================================================");
            System.out.println("--------------------------------------------------- BOOK LIST ---------------------------------------------------");
            System.out.println("=================================================================================================================");
            System.out.printf("%-5s | %-10s | %-25s | %-20s | %-15s | %-12s | %-8s \n", 
                    "STT", 
                        "Book ID", 
                            "Title", 
                                "Author", 
                                    "Genre", 
                                        "Pub. Year", 
                                            "Quantity");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            
            // 1.1.2. Nếu phát hiện kho trống thực sự, in kèm dòng NULL và thông báo báo động
            if (this.IsListEmpty()) {
                System.out.printf("%-5s | %-10s | %-25s | %-20s | %-15s | %-12s | %-8s \n",
                                  "NULL", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL");
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
                System.out.println("Notification: The library storage is currently empty!");
            }
        } 
        // 2. truong hop có 
        else {
            // IN DỮ LIỆU THẬT KHI CÓ data
            System.out.printf("%-5d | %-10s | %-25s | %-20s | %-15s | %-12d | %-8d \n", 
                    stt,
                        book.getId(),
                            book.getTitle(),
                                book.getAuthor(),
                                    book.getGenre(),
                                        book.getPublicationYear(),
                                            book.getQuantity());
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
        }
    }
    
    // lenh update
    public void Update(String bookId, String newTitle, String newAuthor, String newGenre, int newYear, int newQuantity){
        // Sử dụng hàm IsIdExist(bookId) kế thừa từ lớp cha để kiểm tra tính hợp lệ
        if(this.IsIdExist(bookId)){
            // CLEAN: Truy cập trực tiếp vào biến list protected của lớp cha thay vì gọi thông qua getList()
            Book currentBook = list.get(bookId);
            currentBook.setTitle(newTitle);
            currentBook.setAuthor(newAuthor);
            currentBook.setGenre(newGenre);
            currentBook.setPublicationYear(newYear);
            currentBook.setQuantity(newQuantity);
        }
    }
    
}