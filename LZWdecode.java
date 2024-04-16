import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class LZWdecode {
    public static void main(String args[]) {
        Scanner reader = new Scanner(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        Trie trie = new Trie();
        
        //to build up phrases of varying length
        //ArrayList list = new ArrayList<Char>();
        LinkedList<Character> list = new LinkedList<Character>();
        String old = "";
        String neu = "";
        int counter = 0;
        try {
            while(reader.hasNext()) {
                String next = reader.next();
                int phrase = Integer.parseInt(next);
                //System.out.println();
                //current phrase refers to the last phrase
                if(phrase == trie.getNextPhraseNumber()) {
                    //System.out.println("here");
                    //append first letter of old to itself
                    trie.insert(old, old.substring(0,1));
                    writer.write(old);
                    old += old.substring(0,1);
                    
                } else {
                    
                    TrieNode node = trie.findPhrase(phrase);
                    char[] chars;
                    list.clear();
                    //System.out.println("PHRASE: " + phrase);
                    while(node != null) {
                        
                        list.add(new Character(node.getSuffix()));
                        //System.out.println(new Character(node.getSuffix()));
                        //System.out.println(node);
                        node = node.getParent();   
                    }
                    chars = new char[list.size()];
                    //System.out.println("Current Run: " + counter);
                    //to reverse the order of the chars
                    //need to change this because it takes up so much time to compute
                    for(int i = 0; i < chars.length; i++) {
                        chars[i] = list.get((list.size() - 1) - i).charValue();
                    }
                    neu = new String(chars);
                    writer.write(neu);
                    //System.out.println("OLD: " + old);
                    //System.out.println("NEU: " + neu);
                    //first letter of neu becomes end of old
                    //System.out.println("STORING: " + old + neu.substring(0,1));
                    //System.out.println(phrase);
                    //System.out.println("COUNTER: " + counter);
                    //System.out.println("CURR PHRASE " + trie.getNextPhraseNumber());
                    //System.out.println("INSERTING: " + old + neu);
                    trie.insert(old, neu.substring(0,1));

                    old = neu;

                }
                counter++;
                //writer.flush();
                
            }
            writer.close();
        } catch(IOException ex) {
        System.err.println("IOException: " + ex);
        }
    }
}



//add sequence from given number to new variable
//concatenate the first char onto the end of old variable
//this is the new phrase, add it to the trie
//old variable becomes new variable, as that's where the next sequence starts
