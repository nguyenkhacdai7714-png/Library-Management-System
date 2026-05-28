
package librarymanagement.bookmanagement;

// Khoi chay cac du lieu nhap
import java.util.*;
import librarymanagement.utils.*;

public class BookManagement {
    
    // --- Cac ham thanh phan chinh cua BookManagement
    
    // -- Ham menu them sach 
    public static void AddBook() 
    {
        Scanner sc = new Scanner(System.in);
        Functions.Clear();
        System.out.println("========================================");
        System.out.println("--------------- ADD BOOK ---------------");
        System.out.println("========================================");
        
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();
        
        // 1. Kiem tra trung ma ID trong Hashmap
        if (bookList.containsKey(id)) {
        System.out.println("Error: Book ID already exists!");
        Functions.Pause();
        return;
        }
        
        // 2. Trien khai ham de nhap thong tin sach nhu binh thuong 
            /*
                * Dung chu viet thuong cho bien tam *
                vi du: 
                    - "title" này là biến tạm thời chỉ nằm trong hàm AddBook
                    - Lấy giá trị từ biến tạm "title" truyền vào thuộc tính "Title" của đối tượng
            */
            // 2.1 Nhap ten cuon sach 
            System.out.print("Enter Title: ");
            String title = sc.nextLine();             
        
            // 2.2 Nhap ten tac gia 
            System.out.print("Enter Author: ");
            String author = sc.nextLine();
            
            // 2.3 Nhap the loai cua cuon sach
            System.out.print("Enter Genre: ");
            String genre = sc.nextLine();
            
            // 2.4 Nhap nam xuat ban 
                // Kiem tra dau vao cua so lieu xem dung ko ??
                int year = 0 ;
                while (true) {
                    try {
                        System.out.print("Enter Publication Year: ");
                        year = sc.nextInt();
                        sc.nextLine(); // Xoa bo nho dem
                    
                        if ( year <= 0 ) {
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
                // Kiem tra dau vao cua so lieu xem dung ko ??
                int quantity = 0 ;
                while (true) {
                    try {
                        System.out.print("Enter Quantity: ");
                        quantity = sc.nextInt();
                        sc.nextLine();
                        
                        if ( quantity < 0 ) {
                            System.out.println("Error: Quantity cannot be negative!");
                            continue;
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid quantity! Please enter a number.");
                        sc.nextLine(); // xoa ky tu loi 
                    }
                }
                    
        // 3. tao nen doi tuong newbook bang contructor co tham so
        Book newBook = new Book(id, title, author, genre, year, quantity);
        
        // 4. Luu vào HashMap
        bookList.put(id, newBook);
        System.out.println("Book added successfully!");
       
       Functions.Pause();
    }
    
    // -- Ham menu cap nhat thong tin sach 
    public static void UpdateBook() 
    {
        Scanner sc = new Scanner(System.in);
        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- UPDATE BOOK ---------------");
        System.out.println("===========================================");
        
        Functions.Pause();

    }
    
    // -- Ham menu xoa sach 
    public static void RemoveBook() 
    {
        Scanner sc = new Scanner(System.in);
        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- REMOVE BOOK ---------------");
        System.out.println("===========================================");
        
        Functions.Pause();

    }
    
    // -- Ham menu xem thong tin sach 
    public static void ViewBook() 
    {
        Scanner sc = new Scanner(System.in);
        Functions.Clear();
        System.out.println("=========================================");
        System.out.println("--------------- BOOK LIST ---------------");
        System.out.println("=========================================");
        
        // Hien thi bang danh sach cua cac cuon sach da duoc nhap trong du lieu 
        
        
        Functions.Pause();

    }
    
    // -- Ham menu tim kiem sach 
    public static void SearchBook() 
    {
        Scanner sc = new Scanner(System.in);
        Functions.Clear();
        System.out.println("===========================================");
        System.out.println("--------------- SEARCH BOOK ---------------");
        System.out.println("===========================================");
        
        Functions.Pause();

    }
    
    public static HashMap<String, Book> bookList = new HashMap<String, Book>();
    
    // --- Hàm main chính cua Book Management
    // Hien thi bang menu cua ham Book Management
    /*
    1. Add new book with details (ID, title, author, genre, publication year, quantity, …).
    2. Update book information.
    3. Remove a book (only if not currently borrowed).
    4. View all books.
    5. Search books by title, author, or genre.
    */
    
    public static void Run()    // chay ham "run" dau tien khi vo ham BookManagement
    {
        Scanner sc = new Scanner(System.in);
        int choice = -1;   // Khởi tạo giá trị mặc định cho biến local
        
        // Vòng lặp do-while giữ cho chương trình chạy liên tục cho đến khi bạn bấm Thoát
        do {
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
            
            // Xử lý chống crash khi người dùng nhập chữ thay vì nhập số
            try {
                choice = sc.nextInt();
                sc.nextLine(); // Xóa bộ nhớ đệm / nút Enter thừa
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer number!");
                sc.nextLine(); // Đọc bỏ ký tự lỗi đang kẹt trong bộ nhớ đệm
                choice = -1;   // Đặt lại giá trị để vòng lặp tiếp tục chạy
                continue;      // Bỏ qua phần switch-case bên dưới, quay lại lặp tiếp
            }
            
            // Switch-case để gọi đúng hàm tương ứng với lựa chọn
            switch (choice) {
                case 1:
                    AddBook();
                    break;
                case 2:
                    UpdateBook();
                    break;
                case 3:
                    RemoveBook();
                    break;
                case 4:
                    ViewBook();
                    break;
                case 5:
                    SearchBook();
                    break;
                case 0:
                    Functions.Pause(); 
                    break;
                default:
                    System.out.println("Invalid selection! Please re-enter a number from 0 to 5.");
//                    SystemAlert(SystemCode.UndefinedChoice);   // dua ra thong bao gi
            }
            
        } while (choice != 0);
    }
}
