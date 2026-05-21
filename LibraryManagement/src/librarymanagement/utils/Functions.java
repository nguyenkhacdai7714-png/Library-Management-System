package librarymanagement.utils;

import java.util.Scanner;

public class Functions {
    public static void Clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    public static void Pause(){
        System.out.println("Press Enter to continue...");
        new Scanner(System.in).nextLine();
    }
    public static void Alert(String text){
        System.out.println(text.toUpperCase());
        Pause();
    }
    public static void SystemAlert(SystemCode systemCode)
    {
        String alert = "[ERROR] System code not found";
        switch(systemCode)
        {
            case UndefinedChoice:
                alert = "Undefined Choice";
                break;
        }
        Alert("[STATUS] " + alert);
    }
}
