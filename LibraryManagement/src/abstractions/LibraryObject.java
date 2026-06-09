package abstractions;

public abstract class LibraryObject {
    private String id;
    
    public String getId(){
        return id;
    }
    public void setId(String newId){
        id = newId;
    }
    
    public abstract void View();
}
