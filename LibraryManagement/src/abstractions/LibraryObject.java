package abstractions;

public abstract class LibraryObject {
    private String id;
    
    public LibraryObject(String id) {
        this.id = id;
    }
    
    public LibraryObject() {
        this.id = "";
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String newId){
        id = newId;
    }
    
    public abstract void View();
}
