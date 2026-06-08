package librarymanagement.bookmanagement;

import java.util.HashMap;
import librarymanagement.utils.Functions;

public class BookManager {
    
    // singleton
    private static final BookManager instance = new BookManager();
    private BookManager(){}
    public static BookManager getInstance(){
        return instance;
    }
    // end singleton
    
    private HashMap<String, Book> bookList = new HashMap<String, Book>();
    
    // getter/ setter dung trong viec dong goi book
    public void setList(HashMap<String, Book> bookList) {
        this.bookList = bookList;
    }

    public HashMap<String, Book> getBookList() {
        return this.bookList;
    }
    
    // 1. Kiểm tra kho trống
    public boolean IsStorageEmpty() {
        return bookList.isEmpty();
    }
    
    // 2. Kiểm tra mã ID tồn tại hay không
    public boolean IsIdExists(String id) {
        return bookList.containsKey(id);
    }
    
    // 3. Lấy đối tượng Book từ kho ra bằng ID
    public Book SearchById(String id) {
        return bookList.get(id);
    }
    
    // 4. Add, Remove, Update, View, Search
        // 1. ham cho addbook
    public void Add(Book book) {
        bookList.put(book.getId(), book);
    }

    // 2. ham cho updatebook
    // trong ham updatebook da co vi tri cho bien "current" 
    // bien do truc tiep thuc hien thay doi thong tin khong can thong qua bookmanager
    // tu dong thay doi khi nhap xong va an enter 


    // 3. ham cho removebook
    public void Remove(String id) {    // truc tiep loai bo "id" va du lieu cua id do ra khoi hashmap
        bookList.remove(id);
    }

    // 4. ham cho viewbook
        // ( lenh cho management )
        // Lấy ra toàn bộ danh sách cuốn sách dưới dạng một Collection (Tập hợp)
        // @return Tập hợp tất cả các đối tượng Book trong kho
    public java.util.Collection<Book> getList() {
        return bookList.values();
    }
        // 5. ham hien thi     
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
        if (manager.IsStorageEmpty()) {
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
            for (Book book : manager.getList()) {
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
        
        Functions.Pause();
    }
    
    void Update(String bookId, String newTitle, String newAuthor, String newGenre, int newYear, int newQuantity){
        if(IsIdExists(bookId)){
            Book currentBook = bookList.get(bookId);
            currentBook.setTitle(newTitle);
            currentBook.setAuthor(newAuthor);
            currentBook.setGenre(newGenre);
            currentBook.setPublicationYear(newYear);
            currentBook.setQuantity(newQuantity);
        }
    }
    
}