
package librarymanagement.bookmanagement;

public class Book {
    
    // Khai bao bien dung de luu tru du lieu thong tin cua moi cuoc sach
    private String Id;                   // Chu + so ---> dung String
    private String Title;                // Chu + so ---> dung String 
    private String Author;               // Chu + so ---> dung String
    private String Genre;                // Chu + so ---> dung String
    private int Publicationyear;         // so       ---> dung int
    private int Quantity;                // so       ---> dung int
    
    // Methods
    // 1. Constructor ( phuong thuc thiet lap)
        // 1.1 Phuong thuc thiet lap mac dinh 
        public Book(){
            
        }
        // 1.2 Phuong thuc thiet lap co tham so 
        public Book(String Id, String Title, String Author, String Genre, int PublicationYear, int Quantity) {
            this.Id = Id;
            this.Title = Title;
            this.Author = Author;
            this.Genre = Genre;
            this.Publicationyear = Publicationyear;
            this.Quantity = Quantity;
        }
        
    // 2. Getter / Setter (Dung de truy xuat va cap nhat du lieu tu ben ngoai)
        // Id
        public String getId() { 
            return Id; 
        }
        public void setId(String Id) { 
            this.Id = Id; 
        }
        
        // Title
        public String getTitle() { 
            return Title; 
        }
        public void setTitle(String Title) { 
            this.Title = Title; 
        }
        
        // Author
        public String getAuthor() { 
            return Author; 
        }
        public void setAuthor(String Author) { 
            this.Author = Author; 
        }
        
        // Genre
        public String getGenre() { 
            return Genre; 
        }
        public void setGenre(String Genre) { 
            this.Genre = Genre; 
        }
        
        // Publicationyear
        public int getPublicationyear() { 
            return Publicationyear; 
        }
        public void setPublicationyear(int Publicationyear) { 
            this.Publicationyear = Publicationyear; 
        }
        
        // Quantity
        public int getQuantity() { 
            return Quantity; 
        }
        public void setQuantity(int Quantity) { 
            this.Quantity = Quantity; 
        }
    
        
    /*
        // 3. input / output
        // 4. Cac phuong thuc nghiep
        ---> Su dung trong cac ham chinh nhu addbook hay viewbook de de dang kiem soat
    */
    
}
