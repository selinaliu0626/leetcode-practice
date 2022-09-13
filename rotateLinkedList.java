import mymethod.ListNode;

//61. Rotate List
public class rotateLinkedList {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next ==null) return head;
        int length =getLen(head);
        if(k % length==0) return head;
        // k 一定要做剪枝操作
        k=k%length;
        ListNode tail= head;
        ListNode find=head;
        while(k>0){
            tail=tail.next;
            k--;
        }
        while(tail.next!= null){
            tail= tail.next;
            find=find.next;
        }
        ListNode newHead=find.next;
        find.next=null;
        tail.next=head;


        return newHead;

    }
    private int getLen(ListNode head){
        int len=0;
        while(head!= null){
            head =head.next;
            len++;
        }

        return len;
    }

    public static void main(String[] args) {
        rotateLinkedList rll =new rotateLinkedList();
        ListNode head=new ListNode(0);
        head.next=new ListNode(1);
        head.next.next=new ListNode(2);
        ListNode.printLinkedList(rll.rotateRight(head,4));
    }
}
