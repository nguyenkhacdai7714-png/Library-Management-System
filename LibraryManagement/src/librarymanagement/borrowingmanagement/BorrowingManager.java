package librarymanagement.borrowingmanagement;

import java.util.HashMap;
import librarymanagement.bookmanagement.Book;
import librarymanagement.membermanagement.Member;

public class BorrowingManager {
    
    // singleton
    private static final BorrowingManager instance = new BorrowingManager();
    private BorrowingManager(){}
    public static BorrowingManager getInstance(){
        return instance;
    }
    // end singleton
    
    public HashMap<String, BorrowingTransaction> borrowingList = new HashMap<String, BorrowingTransaction>();

}
