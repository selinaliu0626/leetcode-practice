package mymethod;

public class ListNode {
    public int value;
    public ListNode next;
    public ListNode(int data){
        value =data;
        next=null;
    }

    public static ListNode fromArray(int[] nums) {
        if(nums==null ||nums.length==0)return null;
        ListNode head= new ListNode(nums[0]);
        ListNode cur= head;
        for(int i =1;i<nums.length;i++){
            cur.next = new ListNode(nums[i]);
        }
        return head;
    }
    public  static void printLinkedList(ListNode head){
        if(head== null) System.out.println("head==null");
        if(head.next == null) System.out.println("head = "+head.value);
        while(head!=null){
            System.out.print(head.value+"->");
                    head=head.next;
        }
        System.out.println("end of print");
    }
}
