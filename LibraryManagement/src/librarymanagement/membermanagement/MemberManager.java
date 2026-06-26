package librarymanagement.membermanagement;

import java.util.*;
import abstractions.ObjectManager;
import librarymanagement.utils.BoardDrawer;

public class MemberManager extends ObjectManager<Member> {
    
    private static final MemberManager instance = new MemberManager();
    private MemberManager(){}
    public static MemberManager getInstance(){
        return instance;
    }
    
    @Override
    public void View(){
        ViewList(getList().values(), "member list", "Member list is empty");
    }
    
    @Override
    public void ViewList(Collection<Member> itemList, String title, String emptyAlert){
        BoardDrawer.SetBoard(5+10+20+15+23+9+11 + 7*3, "| %-5s | %-10s | %-20s | %-15s | %-23s | %-9s | %-11s |");
        BoardDrawer.PrintTitle(title, emptyAlert, itemList.isEmpty());
        if(!itemList.isEmpty()){
            BoardDrawer.PrintRow("No#","Member ID","Fullname", "Phone","Email", "Readings", "Membership");
            BoardDrawer.PrintWall();
            int count = 1;
            for (Member member : itemList){
                BoardDrawer.PrintRow(count, member.getId(), member.getName(), member.getPhone(), member.getEmail(), member.getReadingHistoryLength(), member.getMembership().MembershipTypeName());
                count++;
            }
            BoardDrawer.PrintSoftWall();
            BoardDrawer.PrintTotal(getList().size(), "member");
        }
        BoardDrawer.PrintWall();
    }

    public boolean IsDuplicateEmailOrPhone(String email, String phone) {
        for (Member m : list.values()) {
            if (m.getEmail().equalsIgnoreCase(email) || m.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public void Update(String memberID, String name, String email, String phone) {
        if (IsIdExist(memberID)) {
            Member member = list.get(memberID);
            member.setName(name);
            member.setEmail(email);
            member.setPhone(phone);
        }
    }
}