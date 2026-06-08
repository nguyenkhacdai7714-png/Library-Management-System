package librarymanagement.bookmanagement;

import java.util.*;
import librarymanagement.utils.*;

public class BookManagement {
    
    // singleton
    private static final BookManagement instance = new BookManagement();
    private BookManagement(){}
    public static BookManagement getInstance(){
        return instance;
    }
    // end singleton
    
    // --- Cac ham thanh phan chinh cua BookManagement
    
    // -- Ham menu them sach 
    public void Adding() 
    {
        BookManager manager = BookManager.getInstance(); // Gọi cụm quản lý dữ liệu
        
        Functions.Clear();
        System.out.println("========================================");
        System.out.println("--------------- ADD BOOK ---------------");
        System.out.println("========================================");
        
        String id = Functions.InputString("Enter book ID: ");
        
        // 1. Kiem tra trung ma ID thong qua BookManager
        if (manager.IsIdExists(id)) {
            Functions.Alert("Error: Book ID already exists!");
            return;
        }
        
        // 2. Trien khai nhap thong tin sach
        String title = Functions.InputString("Enter book title: ");    
        String author = Functions.InputString("Enter book author: ");    
        String genre = Functions.InputString("Enter book genre: ");    
        
        // 2.4 Nhap nam xuat ban 
        int year;
        do{
            year = Functions.InputInt("Enter book's publication year: ");
            if(year >=0){
                break;
            }
            else{
                Functions.Print("Year must be larger or equals than 0");
            }
        }
        while(true);
        
        // 2.5 Nhap so luong cua cuon sach do 
        int quantity;
        do{
            quantity = Functions.InputInt("Enter book's quantity: ");
            if(quantity >=0){
                break;
            }
            else{
                Functions.Print("Quantity must be larger or equals than 0");
            }
        }
        while(true);
                    
        // 3. Tao doi tuong bang constructor co tham so
        Book newBook = new Book(id, title, author, genre, year, quantity);
        
        // 4. Luu vao HashMap cua BookManager thay vi dung bien static tai day
        manager.Add(newBook);
        Functions.Alert("Book added successfully!");
    }
    
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
    public void Updating() {
        BookManager manager = BookManager.getInstance();

        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- UPDATE BOOK ---------------");
        System.out.println("===========================================");

        // 1. Kiểm tra kho dữ liệu trống
        if (manager.IsStorageEmpty()) {
            Functions.Alert("Notification: Library storage is empty! Nothing to update.");
            return;
        }

        // 2. Nhập và kiểm tra mã ID sách
        String id = Functions.InputString("Enter Book ID to modify: ");

        if (!Functions.IsStringValid(id) || !manager.IsIdExists(id)) {
           Functions.Alert("Error: Book ID not found or invalid reference!");
           return;
        }

        // 3. Khởi tạo các biến tạm lưu thông tin hiện tại (Format giống Member)
        Book target = manager.SearchById(id);
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
            if (choice.equals("0")) return; // Thoát ngay không lưu

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
                    // Giả định lớp Functions của bạn đã có hàm ép kiểu int an toàn tương tự InputString
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
                    // Gọi Manager thực hiện cập nhật toàn bộ vào Database/HashMap một lần duy nhất
                    manager.Update(id, newTitle, newAuthor, newGenre, newYear, newQuantity);
                    Functions.Alert("Database updated successfully.");
                    return;
                }
            }
        }
    }


    
    // -- Ham menu xoa sach 
    public void Removing() {
        BookManager manager = BookManager.getInstance();

        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- REMOVE BOOK ---------------");
        System.out.println("===========================================");

        if (manager.IsStorageEmpty()) {
            Functions.Alert("Notification: Library storage is currently empty! Nothing to remove.");
            return;
        }

        String id = Functions.InputString("Enter Book ID to remove: ");

        // Kiểm tra ID tồn tại để tránh crash NullPointer
        if (!manager.IsIdExists(id)) {
            Functions.SystemAlert(SystemCode.BookNotFound);
            return;
        }

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
            manager.Remove(id);
            Functions.Alert("Success: Book [" + id + "] deleted successfully from system!");
        } else {
            Functions.Alert("Removal operation canceled. Book is safe!");
        }
    }

    
    // -- Ham menu xem thong tin sach 
    public void Viewing() {
        Functions.Clear();
        BookManager.getInstance().View(); // Lấy instance trước rồi mới .View()
        Functions.Pause();
    }
    
    // -- Ham menu tim kiem sach 
    public void Searching() {
        BookManager manager = BookManager.getInstance();

        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- SEARCH BOOK ---------------");
        System.out.println("===========================================");

        if (manager.IsStorageEmpty()) {
            Functions.Alert("Notification: Library storage is empty! Nothing to search.");
            return;
        }

        while (true) {
            String id = Functions.InputString("Enter Book ID to search (or type '0' to Back): ");

            if (id.equals("0")) {
                System.out.println("Returning to main menu...");
                break;
            }

            Book foundBook = manager.SearchById(id);

            if (foundBook == null) {
                Functions.SystemAlert(SystemCode.BookNotFound);
                System.out.println("-------------------------------------------");
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
    public void Run()     
    {
        String choice;   
        
        do {
            Functions.MenuGenerator("BOOK MANAGEMENT", "Quit", 
                    "Add new book",
                    "Update book information",
                    "Remove book",
                    "View book",
                    "Search book by ID"
            );
            
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
                    System.out.println("Invalid selection! Please re-enter a number from 0 to 5.");
            }
            
        } while(!choice.equals("0"));
    }
}