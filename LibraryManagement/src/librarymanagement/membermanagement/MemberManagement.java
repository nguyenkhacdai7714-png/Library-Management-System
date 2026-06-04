package librarymanagement.membermanagement;

import java.util.HashMap;

public class MemberManager extends ObjectManager {
    
    private static final MemberManager instance = new MemberManager();
    private MemberManager(){}
    public static MemberManager getInstance(){
        return instance;
    }
    
    public HashMap<String, Member> memberList = new HashMap<String, Member>();

    public boolean IsMemberIDValid(String memberID) {
        return memberList.containsKey(memberID);
    }

    public boolean IsDuplicateEmailOrPhone(String email, String phone) {
        for (Member m : memberList.values()) {
            if (m.GetEmail().equalsIgnoreCase(email) || m.GetPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public void AddMember(Member member) {
        memberList.put(member.GetID(), member);
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
            member.SetName(name);
            member.SetEmail(email);
            member.SetPhone(phone);
        }
    }

    public HashMap<String, Member> GetMemberList() {
        return this.memberList;
    }
}
