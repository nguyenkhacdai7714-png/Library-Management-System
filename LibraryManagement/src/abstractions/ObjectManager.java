package abstractions;
import java.util.HashMap;

// thêm 3 cái import 
    // import cau arraylist
import java.util.Collection; 
import librarymanagement.utils.Constants;

public abstract class ObjectManager<T> {
    
    protected HashMap<String, T> list = new HashMap<String, T>();
    
    public void setList(HashMap<String, T> newList){
        list = newList;
    }
    public HashMap<String, T> getList(){
        return list;
    }
    
    // abstract public void View();
    // thay doi cách su dung view nen bo 
    
    // 1 hàm trừu tượng duy nhất: stt = 0 là in Header va báo NULL, stt > 0 là in dữ liệu
    
    public boolean IsIdExist(String id){
        return list.containsKey(id);
    }
    public boolean IsListEmpty(){
        return list.isEmpty(); 
    }
    public boolean IsListFull(){
        return list.size() >= Constants.MAX_IDS;
    }
    
    public void Add(String id, T object){
        if(!IsIdExist(id)){
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
    
    public String IdGenerator(String preflix){
        if(IsListFull()) return null;
        
        String newId;
        int i =0;
        do{
            newId = String.format(preflix+"%03d", i);
            i++;
        }while(list.containsKey(newId) && i<Constants.MAX_IDS);

        if(i>=Constants.MAX_IDS){
            return null;
        }
        
        return newId;
    }
   
    
    // EDITED
    public abstract void View();
    public abstract void ViewList(Collection<T> itemList, String title, String emptyAlert);
    /* NOTES
    
    + Chi giu lai 3 ham nay.
    + Xoa het PrintData/PrintTitle
    + Ben tren co import BoardDrawer
    
    */
}