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
            case BookNotFound:
                alert = "Book not Found";
                break;
            case MemberNotFound:
                alert = "Member not Found";
                break;
        }
        Alert("[STATUS] " + alert);
    }
    public static void MenuGenerator(String title, String end, String... functions){
        
       int sideWallSize = 8;
        
       String wall = String.format("%" + (title.length()+(sideWallSize*2)+2) + "s", "").replace(' ', '=');
       String sideWall = String.format("%" + (sideWallSize) + "s", "").replace(' ', '=');
        
       System.out.println(sideWall +" " +  title.toUpperCase() + " " + sideWall);
       for(int i=0; i<functions.length; i++)
       {
           System.out.println(String.format("[%d] %s", i+1, functions[i]));
           
       }
       System.out.println(wall);
       System.out.println("[0] " + end);
       System.out.println(wall);
    }
}
