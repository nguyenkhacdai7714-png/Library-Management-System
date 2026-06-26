
package librarymanagement.bookmanagement;

// Kế thừa lớp cha LibraryObject từ package abstractions theo cấu trúc mới
import abstractions.LibraryObject;

public class Book extends LibraryObject {
    
    // Khai bao bien dung de luu tru du lieu thong tin cua moi cuoc sach
    // private String id;                   // Chu + so ---> dung String
    private String title;                // Chu + so ---> dung String 
    private String author;               // Chu + so ---> dung String
    private String genre;                // Chu + so ---> dung String
    private int publicationYear;         // so       ---> dung int
    private int quantity;                // so       ---> dung int
    private int borrowings;
    
    // Methods
    // 1. Constructor ( phuong thuc thiet lap)
        // 1.1 Phuong thuc thiet lap mac dinh 
        public Book(){
            // Gọi constructor mặc định của lớp cha abstractions.LibraryObject
            super("");
            // this.id = "";             
            this.title = "";            
            this.author = "";           
            this.genre = "";             
            this.publicationYear = 0;    
            this.quantity = 0;    
            
            this.borrowings = 0;
        }
        // 1.2 Phuong thuc thiet lap co tham so 
        public Book(String id, String title, String author, String genre, int publicationYear, int quantity) {
            // Đẩy tham số id lên cho constructor của lớp cha xử lý nạp vùng nhớ
            super(id);
            //this.id = id;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.publicationYear = publicationYear;
            this.quantity = quantity;
            
            this.borrowings = 0;
        }
        
         public Book(String id, String title, String author, String genre, int publicationYear, int quantity, int borrowedTime) {
            // Đẩy tham số id lên cho constructor của lớp cha xử lý nạp vùng nhớ
            super(id);
            //this.id = id;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.publicationYear = publicationYear;
            this.quantity = quantity;
            
            this.borrowings = borrowedTime;
        }
        
    // 2. Getter / Setter (Dung de truy xuat va cap nhat du lieu tu ben ngoai)
        // id
//      public String getId() { 
//          return id; 
//      }
//      public void setId(String id) { 
//          this.id = id; 
//      }
        
        // title
        public String getTitle() { 
            return title; 
        }
        public void setTitle(String title) { 
            this.title = title; 
        }
        
        // author
        public String getAuthor() { 
            return author; 
        }
        public void setAuthor(String author) { 
            this.author = author; 
        }
        
        // genre
        public String getGenre() { 
            return genre; 
        }
        public void setGenre(String genre) { 
            this.genre = genre; 
        }
        
        // publicationYear
        public int getPublicationYear() { 
            return publicationYear; 
        }
        public void setPublicationYear(int publicationYear) { 
            this.publicationYear = publicationYear; 
        }
        
        // quantity
        public int getQuantity() { 
            return quantity; 
        }
        public void setQuantity(int quantity) { 
            this.quantity = quantity; 
        }

        public int getBorrowings() {
            return borrowings;
        }

        public void setBorrowings(int borrowings) {
            this.borrowings = borrowings;
        }
        
        public void addBorrowing(){
            this.borrowings++;
        }
        
        
    
    // 3. output    
        // Thực hiện @Override hàm View() trừu tượng bắt buộc từ lớp cha LibraryObject
        @Override
        public void View(){
            System.out.println("Book's title               : " + title);
            System.out.println("Book's author              : " + author);
            System.out.println("Book's genre               : " + genre);
            System.out.println("Book's publication year    : " + publicationYear);
            System.out.println("Book's quantity            : " + quantity);
            System.out.println("Borrowing(s)               : " + borrowings);
        }
}