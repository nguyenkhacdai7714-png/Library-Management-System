package librarymanagement.membermanagement;

import java.util.HashMap;
import librarymanagement.membermanagement.Member;

public class MemberManager {
    
    // singleton
    private static final MemberManager instance = new MemberManager();
    private MemberManager(){}
    public static MemberManager getInstance(){
        return instance;
    }
    // end singleton
    
    public HashMap<String, Member> memberList = new HashMap<String, Member>();
}
