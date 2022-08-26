/* LockDList.java */

package list;

public class LockDList extends DList {
    
    public void lockNode(DListNode node) {
        ((LockDListNode)node).isLocked = true;
    }

    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, prev, next);
    }

    public void remove(DListNode node) {
        if (node == null) {
            return;
        }

        if (((LockDListNode)node).isLocked) {
            return;
        }

        super.remove(node);
    }

    public static void main(String[] args) {
        DList l = new LockDList();

        System.out.println("empty should be true: " + l.isEmpty());
        System.out.println("sentinel points to itself: " + (l.prev(l.head) == l.prev(l.head)));

        l.insertFront(0);
        l.insertFront(1);
        l.insertFront(2);
        l.insertFront(3);
        System.out.println(l);
        
        l.insertBack(0);
        l.insertBack(1);
        l.insertBack(2);
        l.insertBack(3);
        System.out.println(l);
        
        DListNode front = l.front();
        System.out.println("Front shoud be 3: " + front.item);
        System.out.println("Front shoud be 3: " + l.back().item);
        l.insertBefore(4, front);
        l.insertAfter(5, front);
        System.out.println(l);

        l.remove(front);
        System.out.println(l);

        System.out.println("Length should be 9: " + l.length());
        System.out.println("Node should be 2: " + l.prev(l.next(l.next(l.next(l.front())))).item);
    
        ((LockDList)l).lockNode(l.next(l.next(l.front())));
        l.remove(l.front());
        l.remove(l.front());
        l.remove(l.front());
        l.remove(l.front());
        l.remove(l.front());
        System.out.println(l);
        
        l.remove(l.back());
        l.remove(l.back());
        l.remove(l.back());
        l.remove(l.back());
        l.remove(l.back());
        l.remove(l.back());
        l.remove(l.back());
        l.remove(l.back());
        System.out.println(l);
    }
}
