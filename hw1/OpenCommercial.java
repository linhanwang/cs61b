/* OpenCommercial.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {

  /** Prompts the user for the name X of a company (a single string), opens
   *  the Web site corresponding to www.X.com, and prints the first five lines
   *  of the Web page in reverse order.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the 
   *             user's input or opening the connection.
   */
  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please enter the name of a company (without spaces): ");
    System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();

    /* Replace this comment with your solution.  */
    String urlStr = "https://www." + inputLine + ".com/";
    URL url = new URL(urlStr);
    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
    
    String[] webContents = new String[5];
    int i = 0;
    while ((webContents[i++] = in.readLine()) != null)
        if (i > 4)
            break;
    
    in.close();
    
    if (i != 5) System.out.println("Something weired happened!");

    while (i > 0) 
        System.out.println(webContents[--i]);
  }
}
