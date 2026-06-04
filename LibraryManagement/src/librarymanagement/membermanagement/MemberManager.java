package librarymanagement.membermanagement;

import java.util.HashMap;

public class MemberManager {
    
    private static final MemberManager instance = new MemberManager();
    
    private HashMap<String, Member> memberList = new HashMap<String, Member>();
    private int idCounter = 1;

    private MemberManager() {}
    
    public static MemberManager GetInstance() {
        return instance;
    }

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
        if (member == null || member.getId() == null) {
            System.out.println("Error: Member data is invalid.");
            return;
        }
        if (IsMemberIDValid(member.getId())) {
            System.out.println("Error: Member ID already exists!");
            return;
        }
        memberList.put(member.getId(), member);
        System.out.println("Member added successfully.");
    }

    public void RemoveMember(String memberID) {
        if (!IsMemberIDValid(memberID)) {
            System.out.println("Error: Member ID does not exist.");
            return;
        }
        memberList.remove(memberID);
        System.out.println("Member removed successfully.");
    }

    public Member SearchMember(String memberID) {
        if (!IsMemberIDValid(memberID)) {
            return null;
        }
        return memberList.get(memberID);
    }

    public void UpdateMember(String memberID, String name, String email, String phone) {
        if (!IsMemberIDValid(memberID)) {
            System.out.println("Error: Member ID does not exist for update.");
            return;
        }
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
