package librarymanagement.utils;


public class BoardDrawer {
    
    private static int width;
    private static String format;
    private static String wall;
    private static String sideWall;
    private static String softWall;
    
    public static void SetBoard(int width, String format){
        BoardDrawer.width    = width;
        BoardDrawer.format   = format;
        BoardDrawer.wall     = Functions.CreateWall(BoardDrawer.width+1  , "=");
        BoardDrawer.softWall = Functions.CreateWall(BoardDrawer.width+1  , "-");
    }
    public static void PrintTitle(String title, String emptyAlert, boolean isEmpty){
        BoardDrawer.sideWall = Functions.CreateWall((BoardDrawer.width-title.length())/2, "=");
        System.out.println(sideWall +" "+ title.toUpperCase() +" "+ sideWall); 
        
        if(isEmpty){
            System.out.println(emptyAlert);
        }
    }
    public static void PrintRow(Object... elements){
        String line = String.format(BoardDrawer.format, elements);
        System.out.println(line);
    }
    public static void PrintTotal(int totalAmount, String itemName){
        System.out.println("=> Total " + itemName + "(s) : " + totalAmount);
    }
    public static void PrintWall(){
        System.out.println(wall);
    }
    public static void PrintSoftWall(){
        System.out.println(softWall);
    }
    
    
}
// NOTES Cach dung
/*



BoardDrawer.SetBoard(5+10+25+20+15+12+8 + 7*3, "| %-5s | %-10s | %-25s | %-20s | %-15s | %-12s | %-8s |");
BoardDrawer.PrintTitle("Book list", "Book list is empty", false);
BoardDrawer.PrintRow("No#","Book ID","Title", "Author","Genre", "Pub. Year", "Quantity");
for (Book book : hashMap.values()){
    BoardDrawer.PrintRow(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublicationYear(), book.getQuantity());
}
BoardDrawer.PrintSoftWall();
BoardDrawer.PrintTotal(hashMap.size(), "book");
BoardDrawer.PrintWall();
*/