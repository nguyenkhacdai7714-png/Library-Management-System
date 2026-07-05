package librarymanagement.bookmanagement;

import java.util.*;
import librarymanagement.utils.BoardDrawer;
import librarymanagement.utils.DuplicateChecker;

// Kế thừa ObjectManager<Book> từ package abstractions theo đúng lớp cha 
public class BookManager extends abstractions.ObjectManager<Book> {
    
    // singleton
    private static final BookManager instance = new BookManager();
    private BookManager(){}
    public static BookManager getInstance(){
        return instance;
    }
    // end singleton
    
    // Duplicate Checker : BookManager
    private DuplicateChecker titleChecker = new DuplicateChecker();
    private DuplicateChecker authorChecker = new DuplicateChecker();
    
    // Load 1 lan duy nhat khi bat app
    public void LoadTitleChecker(){
        for(Book book : getList().values()){
            titleChecker.Add(book.getTitle());
        }
    }
    public void LoadAuthorChecker(){
        for(Book book : getList().values()){
            authorChecker.Add(book.getAuthor());
        }
    }
    
    // check nhanh
    public boolean IsDuplicateAuthor(String author){
        return authorChecker.Check(author);
    }
    
    public boolean IsDuplicateTitle(String title){
        return titleChecker.Check(title);
    }
    
    // them
    public void AddDuplicateChecker(String memberId){
        Book book = SearchById(memberId);
        titleChecker.Add(book.getTitle());
        authorChecker.Add(book.getAuthor());
    }
    // xoa
    public void RemoveDuplicateChecker(String memberId){
        Book book = SearchById(memberId);
        titleChecker.Remove(book.getTitle());
        authorChecker.Remove(book.getAuthor());
    }
    // End
    
    
    // search by all
    public ArrayList<Book> SearchByAll(String inp){
        
        return super.SearchByAll(inp, book -> String.format("title:%s author:%s genre:%s pubyear:%d",
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublicationYear()));
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
        // Khoi tao thong so cho bang : chieu rong (width), format
        BoardDrawer.SetBoard(5+10+25+20+15+12+8+10 + 8*3, "| %-5s | %-10s | %-25s | %-20s | %-15s | %-12s | %-8s | %-10s |");
        // In title (va thong bao empty neu list empty)
        BoardDrawer.PrintTitle(title, emptyAlert, itemList.isEmpty());
        
        // Neu list khong empty
        if(!itemList.isEmpty()){
            // In ra dong dau tien la cac cot STT, ID, TITLE, ....
            BoardDrawer.PrintRow("No#","Book ID","Title", "Author","Genre", "Pub. Year", "Quantity", "Borrowings");
            // Ve tuong`
            BoardDrawer.PrintWall();
            
            // Bat dau vong lap lay tung Book ra tu itemList
            int count = 1;
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
                count++;
            }
            // In ra tuong` nhung mong hon
            BoardDrawer.PrintSoftWall();
            // In ra total
            BoardDrawer.PrintTotal(itemList.size(), "book");
        }
        // In ra tuong`
        BoardDrawer.PrintWall();
    }
    
    /* NOTES
    + Bo PrintData/PrintTitle
    + Chi co View/ViewList
    
    + Ve co ban la gop ViewArrayList + ViewHashMap = ViewList
       - Cach dung la : ViewList(arrayList) hoac ViewList(hashMap.values())
    
    
    */
}