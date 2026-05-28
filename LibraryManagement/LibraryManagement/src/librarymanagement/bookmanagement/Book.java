
package librarymanagement.bookmanagement;

public class Book {
    
    // Khai bao bien dung de luu tru du lieu thong tin cua moi cuoc sach
    private String id;                   // Chu + so ---> dung String
    private String title;                // Chu + so ---> dung String 
    private String author;               // Chu + so ---> dung String
    private String genre;                // Chu + so ---> dung String
    private int publicationYear;         // so       ---> dung int
    private int quantity;                // so       ---> dung int
    
    // Methods
    // 1. Constructor ( phuong thuc thiet lap)
        // 1.1 Phuong thuc thiet lap mac dinh 
        public Book(){
            
        }
        // 1.2 Phuong thuc thiet lap co tham so 
        public Book(String id, String title, String author, String genre, int publicationYear, int quantity) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.publicationYear = publicationYear;
            this.quantity = quantity;
        }
        
    // 2. Getter / Setter (Dung de truy xuat va cap nhat du lieu tu ben ngoai)
        // id
        public String getid() { 
            return id; 
        }
        public void setid(String id) { 
            this.id = id; 
        }
        
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
    
        
    /*
        // 3. input / output
        // 4. Cac phuong thuc nghiep
        ---> Su dung trong cac ham chinh nhu addbook hay viewbook de de dang kiem soat
    */
    
}
