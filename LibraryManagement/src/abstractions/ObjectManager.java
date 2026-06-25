package abstractions;
import java.util.HashMap;

// thêm 3 cái import 
    // import cau arraylist 
    import java.util.ArrayList;
    // them vi tri cua ham function de co the su dung 
    import librarymanagement.utils.Functions;
    // bao loi systemcode nen them import cua no 
    import librarymanagement.utils.SystemCode;

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
    protected abstract void PrintData(int stt, T item);
    
    public boolean IsIdExist(String id){
        return list.containsKey(id);
    }
    public boolean IsListEmpty(){
        return list.isEmpty(); 
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
   
    public void View() {
        // Nếu kho trống, lập tức gọi in bảng trống dạng NULL và dừng lại thông báo
        if (this.IsListEmpty()) {
            this.PrintData(0, null); 
            Functions.Pause();
            return;
        }

        while (true) {
            Functions.Clear();
            
            // Sử dụng MenuGenerator cau ben funtion de dong bo hao nhu book
            Functions.MenuGenerator("SELECT VIEW MODE", "Back to Management Menu", 
                    "View using HashMap (Direct Iterator)",
                    "View using ArrayList (Index Loop)");
            
            String choice = Functions.InputMenuChoice();
            
            if (choice.equals("0")) break;

            if (choice.equals("1")) {
                ViewHashMap(); 
                Functions.Pause();
            } else if (choice.equals("2")) {
                ViewArrayList(); 
                Functions.Pause();
            } else {     // giong lenh defaut nhu case choice
                Functions.SystemAlert(SystemCode.UndefinedChoice);
                Functions.Pause();
            }
        }
    }

    public void ViewHashMap() {
        Functions.Clear();
        this.PrintData(0, null); // Chỉ in Header tiêu chuẩn (không có dòng NULL vì có dữ liệu)
        
        int count = 1;
        for (T item : list.values()) {
            this.PrintData(count, item); 
            count++;
        }
        System.out.println("=> Total: " + (count - 1) + " item(s) found (Rendered via HashMap).");
        System.out.println("=================================================================================================================");
    }

    public void ViewArrayList() {
        Functions.Clear();
        this.PrintData(0, null); // Chỉ in Header tiêu chuẩn (không có dòng NULL vì có dữ liệu)

        ArrayList<T> temporaryList = new ArrayList<>(list.values());
        
        int count = 1;
        for (int i = 0; i < temporaryList.size(); i++) {
            this.PrintData(count, temporaryList.get(i)); 
            count++;
        }
        System.out.println("=> Total: " + (count - 1) + " item(s) found (Rendered via ArrayList).");
        System.out.println("=================================================================================================================");
    }
}
