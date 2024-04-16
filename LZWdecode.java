import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class LZWdecode {
    public static void main(String args[]) {
        Scanner reader = new Scanner(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        LinkedList<Character> list = new LinkedList<Character>();
        HashMap<Integer, String> phrases = new HashMap<Integer, String>();

        String old = "";
        String neu = "";
        int phraseNum = 0;

        //initialise hashmap
        for(int i = 0; i < 17; i++) {
            if(i < 10) {
                phrases.put(i, String.valueOf(i));
            } else {
                char[] chars = new char[1];
                chars[0] = (char)(i + 55);
                phrases.put(i, new String(chars));
            }
            phraseNum++;
        }
      
        try {
            while(reader.hasNext()) {
                String next = reader.next();
                int phrase = Integer.parseInt(next);
    
                //current phrase refers to the last phrase
                if(phrase == phraseNum) {

                    //end of phrase is the same as the first character in it
                    old += old.substring(0,1);
                    phrases.put(phraseNum, old);
                    writer.write(old);
 
                    phraseNum++;
                    
                } else {

                    TrieNode node = null;
                    char[] chars;

                    while(node != null) {
                        
                        list.add(new Character(node.getSuffix()));
                        node = node.getParent();   
                    }
                    
                    neu = phrases.get(phrase);
                    writer.write(neu);
          
                    if(old != "") {
                        phrases.put(phraseNum, old += neu.substring(0,1));
                        phraseNum++;
                    }
                    old = neu;

                }
            }
            writer.close();
        } catch(IOException ex) {
            System.err.println("IOException: " + ex);
        }
    }
}