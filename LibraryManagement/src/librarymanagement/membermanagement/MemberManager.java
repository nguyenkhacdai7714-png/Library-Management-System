package librarymanagement.membermanagement;

import java.util.HashMap;

public class MemberManager {
    private static HashMap<String, Member> memberList = new HashMap<String, Member>();
    
    public static void add(Member member){
        if (member != null && member.getId() != null) {
            memberList.put(member.getId(), member);
        }
    }
    
    public static void remove(String memberId){
        memberList.remove(memberId);
    }
    
    public static void printList(){
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

    public static Member search(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            return null;
        }
        return memberList.get(memberId);
    }

    public static boolean isIdValid(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            return false;
        }
        return memberList.containsKey(memberId);
    }

    public static void update(String memberId, String name, String email, String phone) {
        Member member = memberList.get(memberId);
        if (member != null) {
            member.setName(name);
            member.setEmail(email);
            member.setPhone(phone);
        }
    }
}
