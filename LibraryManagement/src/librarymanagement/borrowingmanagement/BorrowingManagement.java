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
    
    public ArrayList<BorrowingTransaction> borrowingList = new ArrayList<BorrowingTransaction>();
    
    public void Run()
    {
        
    }
}
