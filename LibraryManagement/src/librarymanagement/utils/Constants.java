package librarymanagement.utils;

public class Constants {
    
    // so luong member/book/transaction ID toi da
    public static final int MAX_IDS        =1000;
    
    // so luong sach toi da ma 1 member co the muon
    public static final int REGULAR_MEMBERSHIP_MAX_BORROWED_BOOKS = 3; 
    public static final int PREMIUM_MEMBERSHIP_MAX_BORROWED_BOOKS = 5; 
    
    // tien phat voi moi ngay tre han tra sach
    public static final float REGULAR_MEMBERSHIP_OVERDUE_FINE = 5000;
    public static final float PREMIUM_MEMBERSHIP_OVERDUE_FINE = 2000;
    
    // neu tra tre hen -> +1 vao overdues
    // neu tra dung hen -> +1 vao readings
    
    // tra ve neu ham bi loi
    public static final int ERROR_VALUE = -9999;
    
    // kich thuoc menu
    public static final int MENU_WIDTH =60;
    
    // data files
    public static final String bookFile = "bookDataFile.txt";
    public static final String memberFile = "memberDataFile.txt";;
    public static final String transactionFile = "transactionDataFile.txt";
    
    // membership tag
    public static final String regularMembershipTag = "REGULAR";
    public static final String premiumMembershipTag = "PREMIUM";
    
    
    // phone
    public static final int MAX_PHONE_LENGTH=15;
    public static final int MIN_PHONE_LENGTH=7;
    
    // reading history codes
    public static final String READING_HISTORY_SPLIT_CODE = "|";
    public static final String READING_HISTORY_EMPTY_CODE = "@@";
}
