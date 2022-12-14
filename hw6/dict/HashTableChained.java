/* HashTableChained.java */

package dict;

import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  private int size;
  private List[] buckets;

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    int prime = searchNearestPrime(2 * sizeEstimate);
    size = 0;
    buckets = new DList[prime];
  }

  int searchNearestPrime(int number) {
    boolean[] isPrime = new boolean[number + 1];
    for (int i = 0; i <= number; ++i) {
        isPrime[i] = true;
    }

    for (int i = 2; i * i <= number; ++i) {
        if (isPrime[i]) {
            for (int j = 2 * i; j <= number; j += i) {
                isPrime[j] = false;
            }
        }
    }

    int res = 2;
    for (int i = number; i > 2; --i) {
        if (isPrime[i]) {
            res = i;
            break;
        }
    }
    return res;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    size = 0;

    buckets = new DList[97];
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    int res = code % buckets.length;
    if (res < 0) res += buckets.length;

    return res;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return size == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry entry = find(key);
    if (entry != null) return entry;

    entry = new Entry();
    entry.key = key;
    entry.value = value;

    int index = compFunction(key.hashCode());
    if (buckets[index] == null) buckets[index] = new DList();
    buckets[index].insertFront(entry);
   
    size++;
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    ListNode node = findNode(key);
    if (node == null) return null;

    try {
        return (Entry)node.item();
    } catch (Exception e) {
        System.err.println(e);
    }

    return null;
  }
  
  private ListNode findNode(Object key) {
    int index = compFunction(key.hashCode());
    if (buckets[index] == null) return null;

    try {
        ListNode node = buckets[index].front();
        while (node.isValidNode()) {
            if (((Entry)node.item()).key().equals(key)) {
                return node;
            }
            node = node.next();
        }
    } catch (Exception e) {
        System.err.println(e);
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    ListNode node = findNode(key);
    if (node == null) return null;

    try {
        Entry res =  (Entry)node.item();
        node.remove();
        size--;
        return res;
    } catch (Exception e) {
        System.err.println(e);
    }

    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    for (int i = 0; i < buckets.length; ++i) {
        buckets[i] = null;
    }
    size = 0;
  }
  
  public void printHist() {
    int collisions = 0;
    String str = "{";
    for (int i = 0; i < buckets.length; i++) {
        if (buckets[i] == null) {
            str += "0";
        } else {
            str += buckets[i].length();
            if (buckets[i].length() > 1) {
                collisions = collisions + buckets[i].length() - 1;
            }
        }
        str += ",";
    }

    System.out.println(str + "} buckets: " + buckets.length + " collisions: " 
            + collisions);
  }
}
