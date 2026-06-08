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
        Scanner sc = new Scanner(System.in);
        BookManager manager = BookManager.getInstance(); // Gọi cụm quản lý dữ liệu
        
        Functions.Clear();
        System.out.println("========================================");
        System.out.println("--------------- ADD BOOK ---------------");
        System.out.println("========================================");
        
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine().trim();
        
        // 1. Kiem tra trung ma ID thong qua BookManager
        if (manager.isIdExists(id)) {
            System.out.println("Error: Book ID already exists!");
            Functions.Pause();
            return;
        }
        
        // 2. Trien khai nhap thong tin sach
        System.out.print("Enter Title: ");
        String title = sc.nextLine().trim();             
        
        System.out.print("Enter Author: ");
        String author = sc.nextLine().trim();
        
        System.out.print("Enter Genre: ");
        String genre = sc.nextLine().trim();
        
        // 2.4 Nhap nam xuat ban 
        int year = 0 ;
        while (true) {
            try {
                System.out.print("Enter Publication Year: ");
                year = sc.nextInt();
                sc.nextLine(); // Xoa bo nho dem
                
                if (year <= 0) {
                    System.out.println("Error: Publication year must be greater than 0!");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid year! Please enter a number.");
                sc.nextLine();  // Xoa bo nho dem
            }  
        }
        
        // 2.5 Nhap so luong cua cuon sach do 
        int quantity = 0 ;
        while (true) {
            try {
                System.out.print("Enter Quantity: ");
                quantity = sc.nextInt();
                sc.nextLine(); // Xoa bo nho dem
                
                if (quantity < 0) {
                    System.out.println("Error: Quantity cannot be negative!");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid quantity! Please enter a number.");
                sc.nextLine(); // xoa ky tu loi 
            }
        }
                    
        // 3. Tao doi tuong bang constructor co tham so
        Book newBook = new Book(id, title, author, genre, year, quantity);
        
        // 4. Luu vao HashMap cua BookManager thay vi dung bien static tai day
        manager.Add(newBook);
        System.out.println("Book added successfully!");
       
        Functions.Pause();
    }
    
    // -- Ham menu cap nhat thong tin sach 
    public void Updating() 
    {
        BookManager manager = BookManager.getInstance();
        Scanner sc = new Scanner(System.in);
        
        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- UPDATE BOOK ---------------");
        System.out.println("===========================================");
        
        // 1. Kiem tra dau vao xem da co book trong kho dữ liệu chưa
        if (manager.isStorageEmpty()) {
            System.out.println("Notification: Library storage is empty! Nothing to update.");
            Functions.Pause();
            return;
        }
            
        // 2. Nhap va kiem tra mã ID sách
            System.out.print("Enter Book ID to update: ");
            String id = sc.nextLine().trim();
        
            if (!manager.isIdExists(id)) {
                System.out.println("Error: Book ID does not exist!");
               Functions.Pause();
               return;
            }
            
            // dung bien "currentBook" de chi dinh cuon sach hien tai va se thao tac trc tiep thong qua Id
            Book currentBook = manager.getBookById(id);
           
        // 3. Vong lap do-while dung de lien tuc nhap va sua doi du lieu truc tiep tren mang hinh
        int choice = -1 ;
        do {
            Functions.Clear();
            System.out.println("------- CURRENT INFORMATION OF BOOK -------");
            System.out.println("Book ID: " + currentBook.getId());
            System.out.println("[1]. Title: " + currentBook.getTitle());
            System.out.println("[2]. Author: " + currentBook.getAuthor());
            System.out.println("[3]. Genre: " + currentBook.getGenre());
            System.out.println("[4]. Publication Year: " + currentBook.getPublicationYear());
            System.out.println("[5]. Quantity: " + currentBook.getQuantity());
            System.out.println("-------------------------------------------");
            System.out.println("[0]. Exit & Save update.");
            System.out.println("-------------------------------------------");
            System.out.print("Select the field number to update: ");
            
            // 3.1 tu dong bao loi neu nhap sai so ( hoac nhap chu )
            try {
                choice = sc.nextInt();
                sc.nextLine(); // Xóa bộ nhớ đệm
                
                if (choice < 0 || choice > 5) {
                    System.out.println("Error: Please select a number from 0 to 5!");
                    Functions.Pause();
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer number!");
                sc.nextLine(); // Xóa ký tự lỗi kẹt trong bộ đệm
                choice = -1;
                Functions.Pause();
                continue;
            }
            
            // 3.2 chon muc can update va chinh sua
            /*
                - tu dong thay doi noi dung thong tin truc tiep  tren mang hinh
                - su dung bien "new...." dung de hay doi noi dung du lieu truc tiep tren mang hinh
                - thay doi dua tren setter cua moi du lieu "Title", "Author",...
                - he thong se tu dong luu vao hashmap
            */
            switch (choice) {
                case 1:
                    System.out.print("Enter NEW Title: ");
                    String newTitle = sc.nextLine();
                    currentBook.setTitle(newTitle); // Thay đổi trực tiếp thông qua Setter
                    break;
                    
                case 2:
                    System.out.print("Enter NEW Author: ");
                    String newAuthor = sc.nextLine();
                    currentBook.setAuthor(newAuthor); // Thay đổi trực tiếp thông qua Setter
                    break;
                    
                case 3:
                    System.out.print("Enter NEW Genre: ");
                    String newGenre = sc.nextLine();
                    currentBook.setGenre(newGenre); // Thay đổi trực tiếp thông qua Setter
                    break;
                    
                case 4:
                    while (true) {
                        try {
                            System.out.print("Enter NEW Publication Year: ");
                            int newYear = sc.nextInt();
                            sc.nextLine(); // Xóa bộ nhớ đệm
                            if (newYear <= 0) {
                                System.out.println("Error: Publication year must be greater than 0!");
                                continue;
                            }
                            currentBook.setPublicationYear(newYear); // Thay đổi trực tiếp thông qua Setter
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Invalid year! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    break;
                    
                case 5:
                    while (true) {
                        try {
                            System.out.print("Enter NEW Quantity: ");
                            int newQuantity = sc.nextInt();
                            sc.nextLine(); // Xóa bộ nhớ đệm
                            if (newQuantity < 0) {
                                System.out.println("Error: Quantity cannot be negative!");
                                continue;
                            }
                            currentBook.setQuantity(newQuantity); // Thay đổi trực tiếp thông qua Setter
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Invalid quantity! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    break;
                    
                case 0:
                    // Thoát switch để vòng lặp do-while kết thúc
                    break; 
            }
            
        } while (choice != 0);
        
        // 4. Hiện trực tiếp dòng chữ cập nhật thành công sau khi bấm số 0
        System.out.println("\n=> Book updated successfully!");
      
        Functions.Pause();
    }
    
    // -- Ham menu xoa sach 
    public void Removing()
    {
        BookManager manager = BookManager.getInstance();
        Scanner sc = new Scanner(System.in);
        
        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- REMOVE BOOK ---------------");
        System.out.println("===========================================");
             
        // 1. kiem tra co ton tai "book" trong hashmap khong 
            // 1.1 --khong => thong bao thu vien khong co sach 
            if (manager.isStorageEmpty()) {
                System.out.println("Notification: Library storage is currently empty! Nothing to remove.");
                Functions.Pause();
                return;
             }
        
            // 1.2 --co => hien vi tri nhap id de co the xoa 
            String id = "";
            while (true) {
                System.out.print("Enter Book ID you want to remove: ");
                id = sc.nextLine().trim();
            
                if (id.isEmpty()) {
                    System.out.println("Error: Book ID cannot be empty!");
                    continue;
                }
            
        // 2. Neu id sai, bat nhap lai ngay lập tức tren mang hinh
            if (!manager.isIdExists(id)) {
                System.out.println("Error: No book found with the matching ID [" + id + "]. Please re-enter!");
                System.out.println("-------------------------------------------");
            } else {
                break; // Nhập đúng ID, thoát vòng lặp để hien thong tin
            }
        }
        
        // 3.  in ra thong tin hien tai cua sach khi nhap id dung 
            Book currentBook = manager.getBookById(id);
            int choice = -1;
        
            do {
                Functions.Clear();
                System.out.println("===========================================");
                System.out.println("------- CONFIRM REMOVAL INFOMATION -------");
                System.out.println("===========================================");
                System.out.println("Book ID: " + currentBook.getId());
                System.out.println("Title: " + currentBook.getTitle());
                System.out.println("Author: " + currentBook.getAuthor());
                System.out.println("Genre: " + currentBook.getGenre());
                System.out.println("Publication Year: " + currentBook.getPublicationYear());
                System.out.println("Quantity: " + currentBook.getQuantity());
                System.out.println("-------------------------------------------");
                System.out.println("[0]. Confirm DELETE this book.");
                System.out.println("[1]. Cancel and Back to menu.");
                System.out.println("-------------------------------------------");
                System.out.print("Enter your choice: ");
            
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); // Xóa nút Enter thừa
                
                    if (choice < 0 || choice > 1) {
                        System.out.println("Error: Invalid selection! Please choose 0 or 1.");
                        Functions.Pause();
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number!");
                    sc.nextLine(); // Dọn dẹp ký tự lỗi
                    choice = -1;
                    Functions.Pause();
                    continue;
                }
            
        // 4. hoi lai lan nua co that su muon xoa khong 
        // su dung "yes" hoac "no" de ho tro khi chon phim "0"
            if (choice == 0) {
                String confirm = "";
                while (true) {
                    System.out.print("Are you absolutely sure you want to DELETE this book? (yes/no): ");
                    confirm = sc.nextLine().trim().toLowerCase();
                    if (confirm.equals("yes") || confirm.equals("no")) {
                        break;
                    }
                    System.out.println("Error: Please enter only 'yes' or 'no'!");
                }
                
                // @@@ chon "yes" tu dong xoa thong tin du lieu cua "id" da nhap 
                if (confirm.equals("yes")) {
                    manager.Remove(id);
                    System.out.println("\n=> Success: Book [" + id + "] deleted successfully from system!");
                } else {
                    System.out.println("\n=> Removal operation canceled. Book is safe!");
                }
                break; // Thoát khỏi vòng lặp do-while để kết thúc hàm
            } else if (choice == 1) {
                System.out.println("\n=> Action canceled. Return to book management menu.");
                break;
            }
            
        } while (choice != 1);
   
        Functions.Pause();
    }
    
    // -- Ham menu xem thong tin sach 
    public void Viewing() {
        BookManager.getInstance().View(); // Lấy instance trước rồi mới .View()
    }
    
    // -- Ham menu tim kiem sach 
    public void Searching() 
    {
        Scanner sc = new Scanner(System.in);
        BookManager manager = BookManager.getInstance();
        
        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- SEARCH BOOK ---------------");
        System.out.println("===========================================");
        
        // 1. kiem tra dau vao
        if (manager.isStorageEmpty()) {
            System.out.println("Notification: Library storage is empty! Nothing to search.");
            Functions.Pause();
            return;
        }
        
        String id = "";
        // vong lap tim kiem den khi nhap o de thoat 
        while (true) {
            System.out.print("Enter Book ID to search (or type '0' to Back): ");
            id = sc.nextLine().trim();
            
            if (id.isEmpty()) {
                System.out.println("Error: Book ID cannot be empty!");
                System.out.println("-------------------------------------------");
                continue;
            }
            
            // Lệnh thoát chức năng
            if (id.equals("0")) {
                System.out.println("Returning to main menu...");
                break;
            }
            
            // 2. goi tu manager de tim kiem thong tin torg hashmap
            Book foundBook = manager.getBookById(id);
            
            // 3. xu lt ket qua dau ra va hien thi tren man hinh
            if (foundBook == null) {
                // neu ko tim thay "foundbook bao null" hien thi ko tim duoc id book da nhap
                System.out.println("=> Error: No book found with the matching ID [" + id + "].");
                System.out.println("-------------------------------------------");
            } else {
                // tim thay --> hien thi bang thong tin book tuong ung
                Functions.Clear();
                System.out.println("===========================================");
                System.out.println("----------- BOOK SEARCH RESULT ------------");
                System.out.println("===========================================");
                System.out.println("-> [FOUND] Book details inside the storage:");
                System.out.println("- Book ID:          " + foundBook.getId());
                System.out.println("- Title:            " + foundBook.getTitle());
                System.out.println("- Author:           " + foundBook.getAuthor());
                System.out.println("- Genre:            " + foundBook.getGenre());
                System.out.println("- Publication Year: " + foundBook.getPublicationYear());
                System.out.println("- Quantity:         " + foundBook.getQuantity());
                System.out.println("===========================================");
                
                break; 
            }
        }
        
        Functions.Pause(); 
    }
    
    // --- Hàm main chính cua Book Management
    public void Run()     
    {
        Scanner sc = new Scanner(System.in);
        String choice = "";   
        
        do {
            /*
            Functions.Clear();
            System.out.println("===============================================");
            System.out.println("--------------- BOOK MANAGEMENT ---------------");
            System.out.println("===============================================");
            System.out.println("[1]. Add new book with details.");
            System.out.println("[2]. Update book information.");
            System.out.println("[3]. Remove a book.");
            System.out.println("[4]. View all books.");
            System.out.println("[5]. Search books by title, author, or genre.");
            System.out.println("[0]. Quit.");
            System.out.println("-----------------------------------------------");
            System.out.print("Enter your choice: ");
            */
            Functions.MenuGenerator("BOOK MANAGEMENT", "Quit", 
                    "Add new book",
                    "Update book information",
                    "Remove book",
                    "View book",
                    "Search book by ID"
                    );
            
            // Goi truc tiep menuchoice de he thong tu dong xu ly loi 
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