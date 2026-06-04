package librarymanagement.membermanagement;

import java.util.HashMap;

public class MemberManager {
    
    private static final MemberManager instance = new MemberManager();
    private MemberManager(){}
    public static MemberManager getInstance(){
        return instance;
    }
    
    private HashMap<String, Member> memberList = new HashMap<String, Member>();
    private int idCounter = 1;

    public String GenerateMemberID() {
        return "M" + String.format("%03d", idCounter++);
    }

    public boolean IsMemberIDValid(String memberID) {
        if (memberID == null || memberID.trim().isEmpty()) {
            return false;
        }
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
