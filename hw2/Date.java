/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
  private int month;
  private int day;
  private int year;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
    if (!isValidDate(month, day, year)) {
        System.exit(0);
    }

    this.month = month;
    this.day = day;
    this.year = year;
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
    String[] arr = s.split("/");
    if (arr.length != 3) {
        System.exit(0);
    }
    
    this.month = Integer.parseInt(arr[0]);
    this.day = Integer.parseInt(arr[1]);
    this.year = Integer.parseInt(arr[2]);
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
    if (year < 0) {
        return false;
    }

    if (year % 400 == 0) {
        return true;
    }

    if (year % 100 == 0) {
        return false;
    }

    if (year % 4 == 0) {
        return true;
    }

    return false;                        // replace this line with your solution
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
    switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;
        case 2:
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        default:
            break;
    }

    return 0;                           // replace this line with your solution
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
    if (year < 1) {
        return false;
    }

    if (month < 1 || month > 12) {
        return false;
    }
    
    if (day < 1 || day > daysInMonth(month, year)) {
        return false;
    }

    return true;                        // replace this line with your solution
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    return this.month + "/" + this.day + "/" + this.year;                     // replace this line with your solution
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
    if (equals(d)) {
        return false;
    }

    if (d.year > this.year) {
        return true;
    } else if (d.year < this.year) {
        return false;
    }

    if (d.month > this.month) {
        return true;
    } else if (d.month < this.month) {
        return false;
    }

    if (d.day > this.day) {
        return true;
    } else if (d.day < this.day) {
        return false;
    }

    return false;                        // replace this line with your solution
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
    if (equals(d)) {
        return false;
    }

    return !isBefore(d);                        // replace this line with your solution
  }

  public boolean equals(Object o) {
    if (!(o instanceof Date)) {
        return false;
    }
    Date d = (Date)o;
    return this.month == d.month && this.day == d.day && this.year == d.year;
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
    int ret = 0;
    for (int i = 1; i < this.month; i++) {
        ret += daysInMonth(i, this.year);
    }

    ret += this.day;

    return ret;                           // replace this line with your solution
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
    if (equals(d)) {
        return 0;
    }

    if (isBefore(d)) {
        return -d.difference(this);
    }

    if (this.year == d.year) {
        return this.dayInYear() - d.dayInYear();
    }

    int first_part = d.dayInYear();

    int second_part = this.dayInYear();

    int third_part = 0;
    for (int i = d.year; i < this.year; i++) {
        third_part += isLeapYear(i) ? 366 : 365;
    }

    return third_part + second_part - first_part;                           // replace this line with your solution
  }

  public static void main(String[] argv) {
    System.out.println("Testing isLeapYear:");
    System.out.println("2000 " + Date.isLeapYear(2000));
    System.out.println("1600 " + Date.isLeapYear(1600));
    System.out.println("1800 " + Date.isLeapYear(1800));
    System.out.println("1900 " + Date.isLeapYear(1900));
    System.out.println("2004 " + Date.isLeapYear(2004));
    System.out.println("1999 " + Date.isLeapYear(1999));

    System.out.println("\nTesting daysInMonth:");
    System.out.println(Date.daysInMonth(1, 2001));
    System.out.println(Date.daysInMonth(2, 2001));
    System.out.println(Date.daysInMonth(3, 2001));
    System.out.println(Date.daysInMonth(4, 2001));
    System.out.println(Date.daysInMonth(5, 2001));
    System.out.println(Date.daysInMonth(6, 2001));
    System.out.println(Date.daysInMonth(7, 2001));
    System.out.println(Date.daysInMonth(8, 2001));
    System.out.println(Date.daysInMonth(9, 2001));
    System.out.println(Date.daysInMonth(10, 2001));
    System.out.println(Date.daysInMonth(11, 2001));
    System.out.println(Date.daysInMonth(12, 2001));
    System.out.println(Date.daysInMonth(13, 2001));

    System.out.println(Date.daysInMonth(1, 2000));
    System.out.println(Date.daysInMonth(2, 2000));
    System.out.println(Date.daysInMonth(3, 2000));
    System.out.println(Date.daysInMonth(4, 2000));
    System.out.println(Date.daysInMonth(5, 2000));
    System.out.println(Date.daysInMonth(6, 2000));
    System.out.println(Date.daysInMonth(7, 2000));
    System.out.println(Date.daysInMonth(8, 2000));
    System.out.println(Date.daysInMonth(9, 2000));
    System.out.println(Date.daysInMonth(10, 2000));
    System.out.println(Date.daysInMonth(11, 2000));
    System.out.println(Date.daysInMonth(12, 2000));
    System.out.println(Date.daysInMonth(13, 2000));

    System.out.println("\nTesting isValidDate:");
    System.out.println(Date.isValidDate(2, 2, 2) + " true");
    System.out.println(Date.isValidDate(2, 2, 0) + " false");
    System.out.println(Date.isValidDate(13, 2, 2) + " false");
    System.out.println(Date.isValidDate(0, 2, 2) + " false");
    System.out.println(Date.isValidDate(2, 0, 2000) + " false");
    System.out.println(Date.isValidDate(2, 29, 2001) + " false");

    System.out.println("\nTesting toString");
    System.out.println(new Date(3, 2, 2021));
    // System.out.println(new Date(3, 32, 2021));

    System.out.println("\nTesting dayInYear");
    Date myDate1 = new Date(12, 31, 2000);
    System.out.println(myDate1.dayInYear());

    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}
