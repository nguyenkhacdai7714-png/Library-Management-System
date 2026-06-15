package librarymanagement.membermanagement;

import java.util.HashMap;
import abstractions.ObjectManager;

public class MemberManager extends ObjectManager {
    
    private static final MemberManager instance = new MemberManager();
    private MemberManager(){}
    public static MemberManager getInstance(){
        return instance;
    }
    
    public HashMap<String, Member> memberList = new HashMap<String, Member>();
    
    public void setMemberList(HashMap<String, Member> memberList) {
        this.memberList = memberList;
    }

    public HashMap<String, Member> getMemberList() {
        return this.memberList;
    }
    
    public void View() {
        // 1. Kiểm tra HashMap hợp lệ (khác null và không rỗng)
        if (memberList == null || memberList.isEmpty()) {
            System.out.println("Member list is empty");
            return;
        }

        // 2. In tieu de bang
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-5s | %-18s | %-14s | %-35s |\n", "STT", "ID", "Full name", "Phone", "Email");
        System.out.println("--------------------------------------------------------------------------------------------");

        // 3. Duyet qua các Value (đối tượng Member) trong HashMap
        int stt = 1;
        for (Member member : memberList.values()) {
            System.out.printf("| %-4s | %-5s | %-18s | %-14s | %-35s |\n", 
                stt, 
                member.getId(), 
                member.getName(), 
                member.getPhone(), 
                member.getEmail()
            );
            stt++;
        }

        // 4. In tong so luong member từ HashMap
        System.out.printf("\ntotal : %d\n", memberList.size());
    }

    public boolean IsMemberIDExist(String memberID) {
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

    public void Add(Member member) {
        if (!IsMemberIDExist(member.getId())) {
            memberList.put(member.getId(), member);
        }
    }

    public void Remove(String memberID) {
        if (IsMemberIDExist(memberID)) {
            memberList.remove(memberID);
        }
    }

    public Member Search(String memberID) {
        if (IsMemberIDExist(memberID)) {
            return memberList.get(memberID);
        }
        return null;
    }

    public void Update(String memberID, String name, String email, String phone) {
        if (IsMemberIDExist(memberID)) {
            Member member = memberList.get(memberID);
            member.setName(name);
            member.setEmail(email);
            member.setPhone(phone);
        }
    }
}