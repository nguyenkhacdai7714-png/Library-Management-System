package librarymanagement.membermanagement;

import java.util.HashMap;
import librarymanagement.membermanagement.Member;

public class MemberManager extends ObjectManager {
    
    private static final MemberManager instance = new MemberManager();
    private MemberManager(){}
    public static MemberManager getInstance(){
        return instance;
    }
    
    public HashMap<String, Member> memberList = new HashMap<String, Member>();
    private int idCounter = 1;

    public String GenerateMemberID() {
        return String.format("M%03d", idCounter++);
    }

    public boolean IsMemberIDValid(String memberID) {
        return memberList.containsKey(memberID);
    }

    public boolean IsDuplicateEmailOrPhone(String email, String phone) {
        for (Member m : memberList.values()) {
            if (m.getEmail().equalsIgnoreCase(email) || m.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public void AddMember(Member member) {
        memberList.put(member.getId(), member);
    }

    public void RemoveMember(String memberID) {
        memberList.remove(memberID);
    }

    public Member SearchMember(String memberID) {
        return memberList.get(memberID);
    }

    public void UpdateMember(String memberID, String name, String email, String phone) {
        Member member = memberList.get(memberID);
        if (member != null) {
            member.setName(name);
            member.setEmail(email);
            member.setPhone(phone);
        }
    }

    public void SetMemberList(HashMap<String, Member> memberList) {
        this.memberList = memberList;
    }

    public HashMap<String, Member> GetMemberList() {
        return this.memberList;
    }
}
