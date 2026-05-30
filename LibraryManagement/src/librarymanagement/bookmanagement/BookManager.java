package librarymanagement.bookmanagement;

import java.util.HashMap;
public class BookManager {
    
    // singleton
    private static final BookManager instance = new BookManager();
    private BookManager(){}
    public static BookManager getInstance(){
        return instance;
    }
    // end singleton
    
    public HashMap<String, Book> bookList = new HashMap<String, Book>();
    
}
