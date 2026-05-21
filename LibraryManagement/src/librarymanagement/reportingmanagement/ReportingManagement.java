package librarymanagement.reportingmanagement;

import java.util.*;
import librarymanagement.utils.*;

public class ReportingManagement {
    
    public static void Menu(){
        Functions.MenuGenerator(
                "REPORTING MANAGEMENT", 
                "Back", 
                
                "Show Borrowed Books",
                "Show Overdue Books",
                "Show Most Popular Books",
                "Show Most Borrowings Members");
    }
    
    public static void Run()
    {
        Menu();
        Functions.Pause();
    }
}
