package librarymanagement.bookmanagement;

import java.util.*;
import librarymanagement.utils.Functions;
import librarymanagement.utils.SystemCode;

// -------
// chuyen hoa Interface ObjectManagement tu package abstractions
public class BookManagement implements abstractions.ObjectManagement {
// -------
    
    // singleton
    private static final BookManagement instance = new BookManagement();
    private BookManagement(){}
    public static BookManagement getInstance(){
        return instance;
    }
    // end singleton
    
    // --- Cac ham thanh phan chinh cua BookManagement
    
    // Ham menu tach biet voi ham Run
    @Override
    public void Menu() {
        Functions.Clear();
        
        Functions.MenuGenerator("BOOK MANAGEMENT", "Back", 
                "Add new book",
                "Update book information",
                "Remove book",
                "View book",
                "Search book by ID"
        );
    }
    
    // -- Ham menu them sach 
    @Override
    public void Adding()  
    {
        BookManager manager = BookManager.getInstance(); 
        String title;
        String author;
        String genre;
        
        Functions.Clear();
        System.out.println("========================================");
        System.out.println("--------------- ADD BOOK ---------------");
        System.out.println("========================================");
        
        String id = manager.IdGenerator("B");
        
        // 1. Kiem tra trung ma ID thong qua BookManager
        if (manager.IsIdExist(id)) {
            Functions.Alert("Error: Book ID already exists!");
            return;
        }
        
        System.out.println("New book ID : " + id);
        
        // 2. Trien khai nhap thong tin sach ---> se bao loi khi de trong ( cu the la toi muc maf bam enter se bao loi )
        // 2.1 nhap ID
        do{
            title = Functions.InputString("Enter book title: ");    
            if(!Functions.IsStringValid(title)){
                System.out.println("Do not leave blank this information !");
            }
        }while(!Functions.IsStringValid(title));
        
        // 2.2 nhap ten tác gia
        do{
            author = Functions.InputString("Enter book author: ");    
            if(!Functions.IsStringValid(author)){
                System.out.println("Do not leave blank this information !");
            }
        }while(!Functions.IsStringValid(author));
        
        // 2.3 nhap the loai cau book
        do{
            genre = Functions.InputString("Enter book genre: ");    
            if(!Functions.IsStringValid(genre)){
                System.out.println("Do not leave blank this information !");
            }
            if(!Functions.IsStringNoDigit(genre)){
                System.out.println("Genre does not have numbers!");
            }
        }while(!Functions.IsStringValid(genre) || !Functions.IsStringNoDigit(genre));
        
        // 2.4 Nhap nam xuat ban 
        int year;
        do{
            year = Functions.InputInt("Enter book's publication year: ");
            if(year > 0){
                break;
            }
            else{
                Functions.Print("Year must be a positive number!\n");
            }
        }
        while(true);
        
        // 2.5 Nhap so luong cua cuon sach do 
        int quantity;
        do{
            quantity = Functions.InputInt("Enter book's quantity: ");
            if(quantity > 0){
                break;
            }
            else{
                Functions.Print("Quantity must be a positive number!\n");
            }
        }
        while(true);
                    
        // 3. Tao doi tuong bang constructor co tham so
        Book newBook = new Book(id, title, author, genre, year, quantity);
        
        // 4. Luu vao HashMap cua BookManager thay vi dung bien static tai day
        // -------
        // Nạp trực tiếp qua Map bang cach thong qua Add(id, newbook) (tránh lỗi logic hàm Add của lớp cha)
        manager.Add(id, newBook);
        // -------
        Functions.Alert("Book added successfully!");
        
    }
    
    // -- Vi tri hien thi cua ham updating se duoc ap dung hien thi cua UpdatingMenu
    private void UpdatingMenu(Book oldBook, String nTitle, String nAuthor, String nGenre, int nYear, int nQuantity) {
        System.out.println("------- UPDATING BOOK INFO MENU --------");
        System.out.println("Book ID: " + oldBook.getId());
        System.out.println();

        System.out.println("[1]. Title : " + oldBook.getTitle() + (oldBook.getTitle().equals(nTitle) ? "" : " -> " + nTitle));
        System.out.println("[2]. Author: " + oldBook.getAuthor() + (oldBook.getAuthor().equals(nAuthor) ? "" : " -> " + nAuthor));
        System.out.println("[3]. Genre : " + oldBook.getGenre() + (oldBook.getGenre().equals(nGenre) ? "" : " -> " + nGenre));
        System.out.println("[4]. Year  : " + oldBook.getPublicationYear() + (oldBook.getPublicationYear() == nYear ? "" : " -> " + nYear));
        System.out.println("[5]. Quant : " + oldBook.getQuantity() + (oldBook.getQuantity() == nQuantity ? "" : " -> " + nQuantity));
        System.out.println();
        System.out.println("[6]. Update Changed Data");
        System.out.println("[0]. Back");

        System.out.println("----------------------------------------");
    }

    
    // -- Ham menu cap nhat thong tin sach 
    @Override
    public void Updating() {
        BookManager manager = BookManager.getInstance();

        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- UPDATE BOOK ---------------");
        System.out.println("===========================================");

        // 1. kiem tra dau vao
        // Cập nhật tên hàm kiểm tra trống từ IsStorageEmpty sang IsListEmpty theo lớp cha mới
        if (manager.IsListEmpty()) {
            Functions.Alert("Notification: Library storage is empty! Nothing to update.");
            return;
        }

        // 2. Nhập và kiểm tra mã ID sách
        String id = Functions.InputString("Enter Book ID to modify: ");

        // Cập nhật tên hàm kiểm tra ID từ IsIdExists sang IsIdExist theo lớp cha mới
        if (!Functions.IsStringValid(id) || !manager.IsIdExist(id)) {
           Functions.Alert("Error: Book ID not found or invalid reference!");
           return;
        }

        // 3. Khởi tạo các biến tạm lưu thông tin hiện tại 
        // Cập nhật gọi hàm SearchById có sẵn từ lớp cha nghiệp vụ để bốc đối tượng
        Book target = manager.SearchById(id);      // su dung bien target de goi book dang duoc de cap toi 
        
        String newTitle = target.getTitle();
        String newAuthor = target.getAuthor();
        String newGenre = target.getGenre();
        int newYear = target.getPublicationYear();
        int newQuantity = target.getQuantity();

        // 4. Vòng lặp điều khiển menu cập nhật dữ liệu qua biến tạm
        while (true) {
            Functions.Clear();
            UpdatingMenu(target, newTitle, newAuthor, newGenre, newYear, newQuantity);

            String choice = Functions.InputMenuChoice();
            if (choice.equals("0")) return; // Thoát ngay du da hien thi du lieu tren man hinh 

            switch (choice) {
                case "1" : {
                    String temp = Functions.InputString("Enter NEW Title: ");
                    if (Functions.IsStringValid(temp)) newTitle = temp;
                    break;
                }
                case "2" : {
                    String temp = Functions.InputString("Enter NEW Author: ");
                    if (Functions.IsStringValid(temp)) newAuthor = temp;
                    break;
                }
                case "3" : {
                    String temp = Functions.InputString("Enter NEW Genre: ");
                    if (Functions.IsStringValid(temp)) newGenre = temp;
                    break;
                }
                case "4" : {
                    // lop Function da co the ep kieu int tuong tu nhu inputint 
                    int temp = Functions.InputInt("Enter NEW Publication Year: "); 
                    if (temp > 0) newYear = temp;
                    break;
                }
                case "5" : {
                    int temp = Functions.InputInt("Enter NEW Quantity: ");
                    if (temp >= 0) newQuantity = temp;
                    break;
                }
                case "6" : {
                    // chon "6" de "that su" cap nhat du lieu hashmap 
                    manager.Update(id, newTitle, newAuthor, newGenre, newYear, newQuantity);
                    Functions.Alert("Database updated successfully.");
                    
                    return;
                }
            }
        }
    }
    
    // -- Ham menu xoa sach 
    @Override
    public void Removing() {
        BookManager manager = BookManager.getInstance();

        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- REMOVE BOOK ---------------");
        System.out.println("===========================================");

        // 1. check trong kho xem co book ko 
        if (manager.IsListEmpty()) {
            Functions.Alert("Notification: Library storage is currently empty! Nothing to remove.");
            
            return;
        }
        
        // 2. nhap id book
        // 2.1 nhap sai id book
        String id = Functions.InputString("Enter Book ID to remove: ");

        if (!manager.IsIdExist(id)) {
            Functions.SystemAlert(SystemCode.BookNotFound);
            
            return;
        }
        
        // 2.2 nhap dung id book
        // Cập nhật đồng bộ hàm lấy đối tượng SearchById(id)
        Book currentBook = manager.SearchById(id);

        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("------- CONFIRM REMOVAL INFORMATION -------");
        System.out.println("===========================================");
        currentBook.View();
        System.out.println("-------------------------------------------");
        System.out.println("[1]. Confirm DELETE this book");
        System.out.println("[0]. Cancel and Back");
        System.out.println("-------------------------------------------");

        String choice = Functions.InputMenuChoice();

        if (choice.equals("1")) {
            // Gọi hàm Remove kế thừa từ lớp cha ObjectManager
            manager.Remove(id);
            
            Functions.Alert("Success: Book [" + id + "] deleted successfully from system!");
        } else {
            Functions.Alert("Removal operation canceled. Book is safe!");
        }
    }

    // -- Ham menu xem thong tin sach 
    @Override
    public void Viewing() {
        Functions.Clear();
        BookManager.getInstance().View(); // Lấy instance trước rồi mới .View()
        Functions.Pause();
    }
    
    // -- Ham menu tim kiem sach 
    @Override
    public void Searching() {
        BookManager manager = BookManager.getInstance();

        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- SEARCH BOOK ---------------");
        System.out.println("===========================================");

        // 1. kiem tra trong kho da co book chua
        if (manager.IsListEmpty()) {
            Functions.Alert("Notification: Library storage is empty! Nothing to search.");
            return;
        }

        // 2. nhap id book de tim 
        while (true) {
            String id = Functions.InputString("Enter Book ID to search (or type '0' to Back): ");

            if (id.equals("0")) {
                System.out.println("Returning to main menu...");
                break;
            }

            // 2.1 tim dc book
            // Cập nhật đồng bộ hàm SearchById(id) kế thừa từ lớp cha nghiệp vụ
            Book foundBook = manager.SearchById(id);
            
            // 2.2 khong tim dc book
            if (foundBook == null) {
                Functions.SystemAlert(SystemCode.BookNotFound);
                
            // 3. hien thi thong tin book can tim
            } else {
                Functions.Clear();
                System.out.println("===========================================");
                System.out.println("----------- BOOK SEARCH RESULT ------------");
                System.out.println("===========================================");
                foundBook.View();
                System.out.println("===========================================");
                Functions.Pause();
                break; 
            }
        }
    }

    // --- Hàm main chính cua Book Management
    @Override
    public void Run()     
    {
        String choice;   
        
        do {
            
            // Thay thế bằng lệnh gọi hàm Menu() đã được tách riêng ở phía trên theo chuẩn thiết kế Interface
            Menu();
            
            choice = Functions.InputMenuChoice();
            
            switch (choice) {
                case "1":
                    Adding();
                    break;
                case "2":
                    Updating();
                    break;
                case "3":
                    Removing();
                    break;
                case "4":
                    Viewing();
                    break;
                case "5":
                    Searching();
                    break;
                case "0":
                    break;
                default:
//                   Functions.Alert("Invalid selection! Please re-enter a number from 0 to 5.");
                   Functions.SystemAlert(SystemCode.UndefinedChoice);
                    
                    
            }
            
        } while(!choice.equals("0"));
    }
}