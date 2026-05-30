package librarymanagement.membermanagement;

import java.util.*;
import librarymanagement.utils.*;

public class MemberManagement {
    
    // singleton
    private static final MemberManagement instance = new MemberManagement();
    private MemberManagement(){}
    public static MemberManagement getInstance(){
        return instance;
    }
    // end singleton
    
    public HashMap<String, Member> memberList = new HashMap<String, Member>();
    
    public void Run()
    {
        
    }
}
