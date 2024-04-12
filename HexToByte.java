import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.*;

class HexToByte {
    public static void main(String args[]) {
        final int bufferSize = 2097152;

        InputStreamReader reader;
        reader = new InputStreamReader(System.in);
        
        OutputStreamWriter writer;
        //need this charset because otherwise images wont be encoded properly
        try {
            writer = new OutputStreamWriter(System.out, "ISO8859-1");
        } catch(UnsupportedEncodingException ex) {
            System.err.println("UnsupportedEncodingException: " + ex);
            return;
        }
        //int counter = 0;
        char buf[] = new char[bufferSize];
        int read = 0;
        try {
            while((read = reader.read(buf, 0, bufferSize)) != -1) {
                for(int i = 0; i < read; i+=2) {
                    char c1 = buf[i];
                    char c2 = buf[i + 1];
                    //counter++;
                    if(c1 > 57) {
                        c1 -= 55;
                    } else {
                        c1 -= 48;
                    }
                    
                    if(c2 > 57) {
                        c2 -= 55;
                    } else {
                        c2 -= 48;
                    }

                    c1 = (char)(((int)c1 << 4) + (int)c2);
                    //convert hex back into decimal
                    //String s = String.valueOf((c1 << 4) + c2);
                    //s = new String(s);
                    //c1 = (c1 << 4) + c2; 
                    writer.write(c1);
                }
            }
            //be tidy
            writer.close();
            reader.close();
        } catch(IOException ex) {
            System.err.println("IOException: " + ex);
        } 
    }
}