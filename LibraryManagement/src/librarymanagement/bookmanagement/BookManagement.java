
package librarymanagement.bookmanagement;

import java.util.*;
import librarymanagement.utils.*;

public class BookManagement {
    
    // singleton
    private static final BookManagement instance = new BookManagement();
    private BookManagement(){}
    public static BookManagement getInstance(){
        return instance;
    }
    // end singleton
    
    public void Run()
    {
        
    }
}
