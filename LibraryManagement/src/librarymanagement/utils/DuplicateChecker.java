package librarymanagement.utils;

import java.util.HashSet;

public class DuplicateChecker {
    private HashSet<String> checkList = new HashSet<String>();

    public boolean Check(String content){
        return checkList.contains(content.toLowerCase()); // true if contain 
    }
    public void Add(String content){
        if(Check(content)) return;
        
        checkList.add(content.toLowerCase());
    }
    public void Remove(String content){
        if(!Check(content)) return;
        
        checkList.remove(content);
    }
}
