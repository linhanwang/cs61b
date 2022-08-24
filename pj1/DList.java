/* DList.java */

/**
 *  A DList is a mutable doubly-linked list.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected long size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel
   *      (denoted by "head"), that can be accessed from the sentinel by
   *      a sequence of "next" references.
   */

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    head = new DListNode();
    head.item = Integer.MIN_VALUE;
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  DList() constructor for a one-node DList.
   */
  public DList(int a) {
    head = new DListNode();
    head.item = Integer.MIN_VALUE;
    head.next = new DListNode();
    head.next.item = a;
    head.prev = head.next;
    head.next.prev = head;
    head.prev.next = head;
    size = 1;
  }

  /**
   *  DList() constructor for a two-node DList.
   */
  public DList(int a, int b) {
    head = new DListNode();
    head.item = Integer.MIN_VALUE;
    head.next = new DListNode();
    head.next.item = a;
    head.prev = new DListNode();
    head.prev.item = b;
    head.next.prev = head;
    head.next.next = head.prev;
    head.prev.next = head;
    head.prev.prev = head.next;
    size = 2;
  }

  /**
   *  insertFront() inserts an item at the front of a DList.
   */
  public void insertFront(int i) {
    // Your solution here.
    DListNode node = new DListNode(i);
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
    size++;
  }

  /**
   *  removeFront() removes the first item (and first non-sentinel node) from
   *  a DList.  If the list is empty, do nothing.
   */
  public void removeFront() {
    // Your solution here.
    if (size == 0) {
        return;
    }

    head.next.next.prev = head;
    head.next = head.next.next;
    size--;
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

  public static void main(String[] args) {
    // DO NOT CHANGE THE FOLLOWING CODE.

    DList l = new DList();
    System.out.println("### TESTING insertFront ###\nEmpty list is " + l);

    l.insertFront(9);
    System.out.println("\nInserting 9 at front.\nList with 9 is " + l);
    if (l.head.next.item != 9) {
      System.out.println("head.next.item is wrong.");
    }
    if (l.head.next.prev != l.head) {
      System.out.println("head.next.prev is wrong.");
    }
    if (l.head.prev.item != 9) {
      System.out.println("head.prev.item is wrong.");
    }
    if (l.head.prev.next != l.head) {
      System.out.println("head.prev.next is wrong.");
    }
    if (l.size != 1) {
      System.out.println("size is wrong.");
    }

    l.insertFront(8);
    System.out.println("\nInserting 8 at front.\nList with 8 and 9 is " + l);
    if (l.head.next.item != 8) {
      System.out.println("head.next.item is wrong.");
    }
    if (l.head.next.prev != l.head) {
      System.out.println("head.next.prev is wrong.");
    }
    if (l.head.prev.item != 9) {
      System.out.println("head.prev.item is wrong.");
    }
    if (l.head.prev.next != l.head) {
      System.out.println("head.prev.next is wrong.");
    }
    if (l.head.next.next != l.head.prev) {
      System.out.println("l.head.next.next != l.head.prev.");
    }
    if (l.head.prev.prev != l.head.next) {
      System.out.println("l.head.prev.prev != l.head.next.");
    }
    if (l.size != 2) {
      System.out.println("size is wrong.");
    }



    l = new DList(1, 2);
    System.out.println("\n\n### TESTING removeFront ###\nList with 1 and 2 is "
                       + l);

    l.removeFront();
    System.out.println("\nList with 2 is " + l);
    if (l.head.next.item != 2) {
      System.out.println("head.next.item is wrong.");
    }
    if (l.head.next.prev != l.head) {
      System.out.println("head.next.prev is wrong.");
    }
    if (l.head.prev.item != 2) {
      System.out.println("head.prev.item is wrong.");
    }
    if (l.head.prev.next != l.head) {
      System.out.println("head.prev.next is wrong.");
    }
    if (l.size != 1) {
      System.out.println("size is wrong.");
    }

    l.removeFront();
    System.out.println("\nEmpty list is " + l);
    if (l.head.next != l.head) {
      System.out.println("head.next is wrong.");
    }
    if (l.head.prev != l.head) {
      System.out.println("head.prev is wrong.");
    }
    if (l.size != 0) {
      System.out.println("size is wrong.");
    }

    l.removeFront();
    System.out.println("\nEmpty list is " + l);
    if (l.head.next != l.head) {
      System.out.println("head.next is wrong.");
    }
    if (l.head.prev != l.head) {
      System.out.println("head.prev is wrong.");
    }
    if (l.size != 0) {
      System.out.println("size is wrong.");
    }
  }

}
