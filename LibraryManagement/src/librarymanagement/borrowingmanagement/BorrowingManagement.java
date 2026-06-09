package librarymanagement.borrowingmanagement;

import java.util.*;
import librarymanagement.utils.*;

public class BorrowingManagement {
    
    // singleton
    private static final BorrowingManagement instance = new BorrowingManagement();
    private BorrowingManagement(){}
    public static BorrowingManagement getInstance(){
        return instance;
    }
    // end singleton
    
    public HashMap<String, BorrowingTransaction> borrowingList = new HashMap<String, BorrowingTransaction>();
    
    public void Run()
    {
        
    }
}
