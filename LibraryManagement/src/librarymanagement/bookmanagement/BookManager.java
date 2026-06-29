package librarymanagement.bookmanagement;

import java.util.*;
import librarymanagement.utils.Functions;
import librarymanagement.utils.BoardDrawer;

// Kế thừa ObjectManager<Book> từ package abstractions theo đúng lớp cha 
public class BookManager extends abstractions.ObjectManager<Book> {
    
    // singleton
    private static final BookManager instance = new BookManager();
    private BookManager(){}
    public static BookManager getInstance(){
        return instance;
    }
    // end singleton
    
    // lenh update khi trong updatingmenu 
    public void Update(String bookId, String newTitle, String newAuthor, String newGenre, int newYear, int newQuantity){
        // Sử dụng hàm IsIdExist(bookId) kế thừa từ lớp cha để kiểm tra tính hợp lệ
        if(this.IsIdExist(bookId)){
            // Xoa: Truy cập trực tiếp vào biến list protected của lớp cha thay vì gọi thông qua getList()
            Book currentBook = list.get(bookId);
            currentBook.setTitle(newTitle);
            currentBook.setAuthor(newAuthor);
            currentBook.setGenre(newGenre);
            currentBook.setPublicationYear(newYear);
            currentBook.setQuantity(newQuantity);
        }
    }
    
    // EDITED ----
    @Override
    public void View(){
        /* Bo vao : 
        1. arrayList hoac hashMap.values()
        2. title
        3. emptyAlert (thong bao neu danh sach trong)
        */
        ViewList(getList().values(), "book list", "Book list is empty");
    }
    
    @Override
    public void ViewList(Collection<Book> itemList, String title, String emptyAlert){
        
        // mo hinh tuong tu nhu format cu la PrintData/PrintTitle 
        
        // 1. Khoi tao thong so cho bang : chieu rong (width), format
        BoardDrawer.SetBoard(5+10+25+20+15+12+8+10 + 8*3, "| %-5s | %-10s | %-25s | %-20s | %-15s | %-12s | %-8s | %-10s |");
        // 2. In title (va thong bao empty neu list empty)
        BoardDrawer.PrintTitle(title, emptyAlert, itemList.isEmpty());
        
        // 3. Neu list khong empty ( trong ko co gi ca)
        if(!itemList.isEmpty()){
            // 3.1 in ra giong tieu de cua moi du lieu 
            BoardDrawer.PrintRow("No#","Book ID","Title", "Author","Genre", "Pub. Year", "Quantity", "Borrowings");
            // 3.2 Ve tuong chanh 2 ben 
            BoardDrawer.PrintWall();
            
            // 3.3  Bat dau vong lap lay tung Book ra tu itemList
            int count = 1; // stt
            for (Book book : itemList){
                // Moi vong lap in ra 1 row cua bang. Tu dong theo format da thiet lap.
                BoardDrawer.PrintRow(count, 
                                    book.getId(), 
                                        book.getTitle(), 
                                            book.getAuthor(), 
                                                book.getGenre(), 
                                                    book.getPublicationYear(), 
                                                        book.getQuantity(),
                                                            book.getBorrowings());
                count++; // tang bien stt 
            }
            // 3.4 In ra tuong` nhung mong hon
            BoardDrawer.PrintSoftWall();
            // 3.5 In ra dong thong bao total cua nhung book yeu cau theo input 
            BoardDrawer.PrintTotal(itemList.size(), "book");
        }
        // 4. In ra tuong (nhiem vu tuong tu 3.2)
        BoardDrawer.PrintWall();
    }
    
    /* NOTES
    + Bo PrintData/PrintTitle
    + Chi co View/ViewList
    
    + Ve co ban la gop ViewArrayList + ViewHashMap = ViewList
       - Cach dung la : ViewList(arrayList) hoac ViewList(hashMap.values())
    
    
    */
}