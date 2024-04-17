import java.io.*;

class LZWencode {

    public static void main(String[] args) {
        //final int bufferSize = 2048;
        //log2(262143) = about 18 (so it's nice to work with)
        final int maxPhrases = 262143;

        InputStreamReader reader;
        reader = new InputStreamReader(System.in);
        //OutputStreamWriter writer;
        //writer = new OutputStreamWriter(System.out);

        Trie trie = new Trie(maxPhrases);
        //char[] buf = new char[bufferSize];
        //int bytesRead = 0;
        String prefix = "";
        String suffix = "";

        try {
            char next;
            next = (char)reader.read();
            char[] chars = new char[1];
            chars[0] = next;
            //start prefix with the first char in the sequence
            prefix += new String(chars);


            while((next = (char)reader.read()) != 65535) {
                chars[0] = next;
                suffix = new String(chars); 

                int phraseNum = 0;
                //System.out.println("Prefix: " + prefix);
                //System.out.println("Suffix: " + (int)suffix.charAt(0));
                if((phraseNum = trie.insert(prefix, suffix)) == -1) {
                    //sequence is already in the trie
                    prefix += suffix;
                } else {
                    //System.out.println("Prefix: " + prefix);
                    //System.out.println("Suffix: " + suffix);
                    System.out.println(phraseNum);
                    prefix = suffix;
                }
            }
            suffix = "G";
            //System.out.println("Prefix: " + prefix);
            //System.out.println("Suffix: " + suffix);
            System.out.println(trie.insert(prefix, suffix));
        } catch(IOException ex) {
            System.err.println("IOException: " + ex);
        }
    }
}