package librarymanagement.bookmanagement;

import java.util.HashMap;
import librarymanagement.utils.Functions;

// Kế thừa ObjectManager<Book> từ package abstractions theo đúng code lớp cha bạn gửi
public class BookManager extends abstractions.ObjectManager<Book> {
    
    // singleton
    private static final BookManager instance = new BookManager();
    private BookManager(){}
    public static BookManager getInstance(){
        return instance;
    }
    // end singleton
    
    // 5. ham hien thi 
    @Override    
    public void View() {
      
        BookManager manager = BookManager.getInstance();
        
        Functions.Clear();
        System.out.println("=================================================================================================================");
        System.out.println("--------------------------------------------------- BOOK LIST ---------------------------------------------------");
        System.out.println("=================================================================================================================");
        
        // 1. kiem tra kho co dang trong hay khong 
        // neu trong in ra vi tri tren bang la "NULL"
        // su dung bo dem "count" de de dang quan ly so thu tu cac book co trong danh sach
        int count = 1;
        // Gọi hàm IsListEmpty() đã được kế thừa từ lớp cha để bẫy lỗi kho trống
        if (this.IsListEmpty()) {
            System.out.printf("%-5s | %-10s | %-25s | %-20s | %-15s | %-12s | %-10s \n", 
                    "STT", 
                        "Book ID", 
                            "Title", 
                                "Author",  
                                    "Genre", 
                                        "Pub. Year",
                                            "Quantity");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-10s | %-25s | %-20s | %-15s | %-12s | %-10s \n",
                              "NULL", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println("Notification: The library storage is currently empty!");
        } 
        else {
            // In tiêu đề bảng (Căn chỉnh khoảng cách theo ký tự trắng)
            System.out.printf("%-5s | %-10s | %-25s | %-20s | %-15s | %-12s | %-8s \n", 
                    "STT", 
                        "Book ID", 
                            "Title", 
                                "Author",
                                    "Genre",
                                        "Pub. Year", 
                                            "Quantity");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            
            // 2. vong lap de in ra cac book va thong tin du lieu theo tung book
            // Duyệt trực tiếp qua các value nằm trong biến 'list' (được kế thừa từ lớp cha dưới dạng protected)
            for (Book book : this.getList().values()) {
                System.out.printf("%-5d | %-10s | %-25s | %-20s | %-15s | %-12d | %-8d \n", 
                        count,
                            book.getId(),
                                book.getTitle(),
                                    book.getAuthor(),
                                        book.getGenre(),
                                            book.getPublicationYear(),
                                                book.getQuantity());
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
                count++; // tang bo dem 
            }
            
            // in ra so cac cuon sach ( chi tinh mot loai sach, khong tinh so luong )
            System.out.println("=> Total: " + (count - 1) + " book(s) found in the storage.");
        }
        
    }
    
    public void Update(String bookId, String newTitle, String newAuthor, String newGenre, int newYear, int newQuantity){
        // Sử dụng hàm IsIdExist(bookId) kế thừa từ lớp cha để kiểm tra tính hợp lệ
        if(this.IsIdExist(bookId)){
            Book currentBook = this.getList().get(bookId);
            currentBook.setTitle(newTitle);
            currentBook.setAuthor(newAuthor);
            currentBook.setGenre(newGenre);
            currentBook.setPublicationYear(newYear);
            currentBook.setQuantity(newQuantity);
        }
    }
    
}