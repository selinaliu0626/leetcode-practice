public class PlusOneLinkedList {
    static class SinglyLinkedListNode{
        int data;
         SinglyLinkedListNode next;
         SinglyLinkedListNode (int data){
             this.data =data;
             next= null;
         }

    }
    public static SinglyLinkedListNode addOne(SinglyLinkedListNode A) {
        if(A == null) return new SinglyLinkedListNode(1);
        int carry =0;
        SinglyLinkedListNode reversedhead =reverse(A);
        SinglyLinkedListNode cur =reversedhead;
        cur.data =cur.data+1;
        while(cur!= null){
            int num =cur.data+carry;
            carry =num/10;
            cur.data=num%10;
            cur=cur.next;
        }
        if(carry!=0){
            cur.next = new SinglyLinkedListNode(carry);
        }
        return reverse(reversedhead);


    }
    private static SinglyLinkedListNode reverse(SinglyLinkedListNode a){
        if(a.next == null) return a ;
        SinglyLinkedListNode pre = null;
        SinglyLinkedListNode cur =a;
        while(cur!= null){
            SinglyLinkedListNode next = cur.next;
            cur.next =pre;
            pre = cur;
            cur=next;
        }
        return pre;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next =new SinglyLinkedListNode(2);
        head.next.next =new SinglyLinkedListNode(3);
        SinglyLinkedListNode res = addOne(head);
        while(res!= null){
            System.out.print(res.data +"->");
            res =res.next;
        }
    }
}
