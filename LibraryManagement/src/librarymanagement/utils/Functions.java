package librarymanagement.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.Scanner;

public class Functions {
    
    static Scanner input = new Scanner(System.in);
    static int menuWidth = Constants.MENU_WIDTH;
    static String menuWall = "";
    
    public static void StartFunctions(){
        menuWall = String.format("%" + (menuWidth) + "s", "").replace(' ', '=');
    }
    
    public static void Clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    public static void Pause(){
        System.out.println("Press Enter to continue...");
        input.nextLine();
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
        
       int sideWallSize = (menuWidth - title.length() - 2)/2;
        
       String sideWall = String.format("%" + (sideWallSize) + "s", "").replace(' ', '=');
        
       System.out.println(sideWall +" " +  title.toUpperCase() + " " + sideWall);
       for(int i=0; i<functions.length; i++)
       {
           if(functions[i].equals("WALL"))
           {
               System.out.println(menuWall);
           }
           else
           {
                System.out.println(String.format("[%d] %s", i+1, functions[i]));
           }
           
       }
       System.out.println(menuWall);
       System.out.println("[0] " + end);
       System.out.println(menuWall);
    }
    
    public static void Print(String text)
    {
        System.out.print(text);
    }
    
    public static String InputString(String content){
        Print(content);
        return input.nextLine();
    }
    public static int InputInt(String content){
        Print(content);
        try{
            return Integer.parseInt(input.nextLine());
        }
        catch(NumberFormatException e){
            return Constants.ERROR_VALUE;
        }
    }
    public static float InputFloat(String content){
        Print(content);
        try{
            return Float.parseFloat(input.nextLine());
        }
        catch(NumberFormatException e){
            return (float)Constants.ERROR_VALUE;
        }
    }
    
    public static String InputMenuChoice(){
        return InputString("Enter your choice: ");
    }
    
    
    public static boolean IsStringValid(String s){
        return s!=null && !s.trim().isEmpty();
    }
    
    public static long DayBetween(LocalDate startDate, LocalDate endDate){
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    
}
