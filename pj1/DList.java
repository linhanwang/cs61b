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
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  DList() constructor for a one-node DList.
   */
  public DList(Object a) {
    head = new DListNode();
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
  public DList(Object a, Object b) {
    head = new DListNode();
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
  public void insertFront(Object o) {
    // Your solution here.
    DListNode node = new DListNode(o);
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
    size++;
  }

  /**
   *  insertEnd() inserts an item at the end of a DList.
   */
  public void insertEnd(Object o) {
      DListNode node = new DListNode(o);
      node.prev = head.prev;
      node.next = head;
      head.prev.next = node;
      head.prev = node;
      size++;
  }

  /**
   *  insertBefore() inserts an item before a node.
   * */
  public void insertBefore(DListNode node, Object o) {
    DListNode newNode = new DListNode(o);
    newNode.next = node;
    newNode.prev = node.prev;
    node.prev.next = newNode;
    node.prev = newNode;
  }

  /**
   *  insertAfter() inserts an item after a node.
   * */
  public void insertAfter(DListNode node, Object o) {
      DListNode newNode = new DListNode(o);
      newNode.prev = node;
      newNode.next = node.next;
      node.next.prev = newNode;
      node.next = newNode;
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
   *  removeEnd() removes the last item (and last non-sentinel node) from a 
   *  DList. If the list is empty, do nothing.
   * */
  public void removeEnd() {
      if (size == 0) return;

      head.prev.prev.next = head;
      head.prev = head.prev.prev;
      size--;
  }

  /**
   *  removeBefore() removes the item before a node.
   * */
  public void removeBefore(DListNode node) {
      node.prev.prev.next = node;
      node.prev = node.prev.prev;
  }
  /**
   *  removeAfter() removes the item after a node.
   * */
  public void removeAfter(DListNode node) {
      node.next.next.prev = node;
      node.next = node.next.next;
  }

  public DListNode getHead() {
      return head;
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
    if ((Integer)l.head.next.item != 9) {
      System.out.println("head.next.item is wrong.");
    }
    if (l.head.next.prev != l.head) {
      System.out.println("head.next.prev is wrong.");
    }
    if ((Integer)l.head.prev.item != 9) {
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
    if ((Integer)l.head.next.item != 8) {
      System.out.println("head.next.item is wrong.");
    }
    if (l.head.next.prev != l.head) {
      System.out.println("head.next.prev is wrong.");
    }
    if ((Integer)l.head.prev.item != 9) {
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
    if ((Integer)l.head.next.item != 2) {
      System.out.println("head.next.item is wrong.");
    }
    if (l.head.next.prev != l.head) {
      System.out.println("head.next.prev is wrong.");
    }
    if ((Integer)l.head.prev.item != 2) {
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

    l.insertEnd(2);
    l.insertEnd(3);
    l.insertEnd(4);
    l.insertEnd(5);
    l.insertEnd(6);
    System.out.println(l);
    
    l.removeEnd();
    l.removeEnd();
    System.out.println(l);
    
    l.insertBefore(l.head.prev, 10);
    l.insertBefore(l.head.prev, 11);
    System.out.println(l);
    
    l.insertAfter(l.head.next, 100);
    l.insertAfter(l.head.next, 101);
    System.out.println(l);
    
    l.removeBefore(l.head.prev);
    l.removeBefore(l.head.prev);
    System.out.println(l);

    l.removeAfter(l.head.next);
    l.removeAfter(l.head.next);
    System.out.println(l);
  }

}
