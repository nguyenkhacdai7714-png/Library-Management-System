package librarymanagement.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import abstractions.MembershipType;
import librarymanagement.membermanagement.RegularMembership;
import librarymanagement.membermanagement.PremiumMembership;

import java.util.*;

public class Functions {
    
    static Scanner input = new Scanner(System.in);
    static int menuWidth = Constants.MENU_WIDTH;
    static String menuWall = "";
    
    public static void StartFunctions(){
        menuWall = CreateWall(menuWidth, "=");
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
        
       String sideWall = CreateWall(sideWallSize, "=");
        
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
    public static boolean IsStringNoDigit(String s){ 
        return s != null && s.matches("\\D*"); 
    }
    public static boolean IsDateValid(LocalDate date){
        if(date==null) return false;
        if(DayBetween(Today(), date) < 0) return false;
        return true;
    }
    
    public static long DayBetween(LocalDate startDate, LocalDate endDate){
        if(startDate==null || endDate==null) return 0;
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    public static String DateToString(LocalDate date){
        if(date==null){
            return null;
        }
        return String.format("%d/%d/%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }
    public static LocalDate StringToDate(String content){
        if(content == null || content.trim().equals("null") || content.trim().isEmpty()){
            return null;
        }

        int[] dateArray = StringToDateArray(content);

        if(dateArray != null){
            try {
                return LocalDate.of(dateArray[2], dateArray[1], dateArray[0]);
            } catch (Exception e) {
                return null; 
            }
        }
        return null;
    }

    public static int[] StringToDateArray(String content){
        String delimiter = "";

        if (content.matches("\\d+/\\d+/\\d+")) {
            delimiter = "/";
        } 
        else {
            return null; 
        }

        try {
            String[] contentList = content.split(delimiter);
            if(contentList.length == 3){
                return new int[] {
                    Integer.parseInt(contentList[0].trim()), 
                    Integer.parseInt(contentList[1].trim()), 
                    Integer.parseInt(contentList[2].trim())  
                };
            }
        } 
        catch (NumberFormatException e) {}
        return null;
    }
    
    public static LocalDate InputDate(String content){
        String inp;
        LocalDate inputDate;
        int[] arr;
        LocalDate today = Today();
        
        inp = InputString(content + "[dd/mm/yyyy] or [today]: ");
        if(inp.equals("today")){
            return today;
        }
        else{
            arr = StringToDateArray(inp);
        }
        if(arr!=null){
            inputDate = LocalDate.of(arr[2],arr[1],arr[0]);
            if(DayBetween(today, inputDate) >= 0){
                return inputDate;
            }
        }
        return null;
    }
    
    public static LocalDate Today(){
        return LocalDate.now();
    }
    
    public static String YNQuestion(String content){
        String choice;
        do{
            choice = InputString(content + " [Y/N]: ").toLowerCase();
            
        }while(!choice.equals("y") && !choice.equals("n"));
        
        return choice;
    }
    
    public static boolean IsTwoDateValid(LocalDate borrowDate, LocalDate overdueDate){
        return IsDateValid(borrowDate) && IsDateValid(overdueDate) && DayBetween(borrowDate, overdueDate) > 0;
    }
  
    public static String CreateWall(int wallWidth, String wallChar){
        return String.format("%" + wallWidth + "s", " ").replace(" ", wallChar);
    }
    
    public static String ReadingHistoryToString(ArrayList<String> history){
        if(history.size()==0){
            return "@@";
        }
        
        String line="";
        for(String act : history){
            line += act + "/";
        }
        line += "@";
        line = line.replace("/@", "");
        
        return line;
    }
    public static ArrayList<String> StringToReadingHistory(String content){
        
        if(content.equals("@@")){
            return new ArrayList<String>();
        }
        
        ArrayList<String> newList = new ArrayList<String>();
        String[] line = content.split("/");
        for(int i=0; i<line.length; i++){
            newList.add(line[i]);
        }
        return newList;
    }
    
    public static MembershipType TagToMembership(String tag){
        if(tag.equals(Constants.premiumMembershipTag)){
            return new PremiumMembership();
        }
        else{
            return new RegularMembership();
        }
    }
    public static String MembershipToTag(MembershipType membership){
        return membership.getMembershipTag();
    }
    
}
