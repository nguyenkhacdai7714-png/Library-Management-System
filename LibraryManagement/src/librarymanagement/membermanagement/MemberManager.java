package librarymanagement.membermanagement;

import java.util.HashMap;

public class MemberManager {
    private static MemberManager instance;
    private static HashMap<String, Member>memberList;

    private MemberManager() {
        memberList = new HashMap<String, Member>();
    }

    public static MemberManager getInstance() {
        if (instance == null) {
            instance = new MemberManager();
        }
        return instance;
    }

    public boolean IsIDValid(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            return false;
        }
        return memberList.containsKey(memberId);
    }

    public void Add(Member member) {
        if (member == null || member.getId() == null) {
            System.out.println("Error: Member data is null.");
            return;
        }
        if (IsIDValid(member.getId())) {
            System.out.println("Error: Member ID already exists (Overlap).");
            return;
        }
        memberList.put(member.getId(), member);
        System.out.println("Member added successfully to database.");
    }

    public void Remove(String memberId) {
        if (!IsIDValid(memberId)) {
            System.out.println("Error: Member ID does not exist.");
            return;
        }
        memberList.remove(memberId);
        System.out.println("Member removed successfully from database.");
    }

    public Member Search(String memberId) {
        if (!IsIDValid(memberId)) {
            return null;
        }
        return memberList.get(memberId);
    }

    public void Update(String memberId, String name, String email, String phone) {
        if (!IsIDValid(memberId)) {
            System.out.println("Error: Member ID does not exist for update.");
            return;
        }
        Member member = memberList.get(memberId);
        if (member != null) {
            member.setName(name);
            member.setEmail(email);
            member.setPhone(phone);
        }
    }

    public void View() {
        if (memberList.isEmpty()) {
            System.out.println("No members found in the system.");
            return;
        }
        System.out.printf("%-10s | %-20s | %-25s | %-15s\n", "ID", "Name", "Email", "Phone");
        System.out.println("----------------------------------------------------------------------------");
        for (Member member : memberList.values()) {
            System.out.printf("%-10s | %-20s | %-25s | %-15s\n", 
                    member.getId(), member.getName(), member.getEmail(), member.getPhone());
        }
    }
}
