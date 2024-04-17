import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

class LZWpack {

    public static void main(String[] args) {
        final int maxPhrases = 65535;
        final int bitsPerPhrase = 16;
        final int bytePerPhrase = 2;
        final int endOfLine = 262143;

        Scanner reader = new Scanner(System.in);
        //OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedOutputStream output = new BufferedOutputStream(System.out);
        byte currByte = 0;
        int byteSpace = 8;
        int currWrittenBits;
        byte[] bytes = new byte[1];
        try {
            while(reader.hasNext()) {
                String phrase = reader.next();
                int phraseNum = Integer.parseInt(phrase);
                


                currWrittenBits = 0;
                int mask = 255;
                //LSBits are output first
                for(int i = 0; i < 2; i++) {
                    currByte |= (phraseNum & mask);
                    bytes[0] = currByte;
                    phraseNum >>= 8;
                    //writer.write(currByte);
                    output.write(bytes, 0, 1);
                    currByte = 0;
                }

                //System.out.println(phraseNum);
            }
            
            reader.close();
            output.close();
            //writer.close();
            //write end of line
        } catch (IOException ex) {
            System.err.println("IOException: " + ex);
        }

    


    }
}