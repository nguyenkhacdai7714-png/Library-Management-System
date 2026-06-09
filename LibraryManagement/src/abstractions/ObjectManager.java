package abstractions;
import java.util.HashMap;

public abstract class ObjectManager<T> {
    
    protected HashMap<String, T> list = new HashMap<String, T>();
    
    public void setList(HashMap<String, T> newList){
        list = newList;
    }
    public HashMap<String, T> getList(){
        return list;
    }
    abstract public void View();
    
    public boolean IsIdExist(String id){
        return list.containsKey(id);
    }
    public boolean IsListEmpty(){
        return list.isEmpty(); 
    }
    
    public void Add(String id, T object){
        if(IsIdExist(id)){
            list.put(id, object);
        }
    }
    
    public void Remove(String id){
        if(IsIdExist(id)){
            list.remove(id);
        }
    }
    
    public T SearchById(String id){
        if(IsIdExist(id))
        {
            return list.get(id);
        }
        return null;
    }
}
