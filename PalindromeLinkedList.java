import mymethod.ListNode;
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if(head== null) return false;
        if(head.next == null) return true;
        ListNode first = head;
        ListNode mid = findMid(head);
        ListNode second =mid.next;
        mid.next = null;
        ListNode reverseSecond =reverse(second);
        ListNode cur =reverseSecond;
        while(cur!= null){
            if(cur.value!=first.value){
                return false;
            }
            cur=cur.next;
            first=first.next;
        }
        return true;

    }
    private ListNode findMid(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next != null){
            fast =fast.next.next;
            slow =slow.next;
        }
        return slow;
    }
    private ListNode reverse(ListNode head){
        if(head.next == null) return head;
        ListNode pre = null;
        ListNode cur= head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre =cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        PalindromeLinkedList pll =new PalindromeLinkedList();
       ListNode head = new ListNode(1);
       head.next =new ListNode(2);
       head.next.next=new ListNode(2);
       head.next.next.next=new ListNode(1);
       ListNode.printLinkedList(head);
        System.out.println(pll.isPalindrome(head));
    }
}

