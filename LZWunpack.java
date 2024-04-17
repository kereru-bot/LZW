import java.io.*;

class LZWunpack {

    public static void main(String args[]) {
        //InputStreamReader reader = new InputStreamReader(System.in);
        BufferedInputStream input = new BufferedInputStream(System.in);
        BufferedOutputStream output = new BufferedOutputStream(System.out);
        int firstByte = 0;
        int secondByte = 0;
        int phraseNum = 0;
        int mask = 65535;
        byte[] bytes = new byte[2];
        try {
            int bytesRead = 0;
            while((bytesRead = input.read(bytes, 0, 2)) != -1) {
                //int phrase = 0;
                //phrase |= bytes[1];
                //phrase <<= 8;
                //phrase |= bytes[0];
                //System.out.println("FIRST BYTE: " + bytes[0]);
                //System.out.println("SECOND BYTE: " + bytes[1]);
                //int byte2 = bytes[1];
                //phraseNum = bytes[0];
                //byte2 <<=8;
                //phraseNum |= byte2;
                
                //because java sign extends byte -> int by the MSB (very annoying)
                phraseNum = (bytes[0] & 0xFF) | ((bytes[1] & 0xFF) << 8);
                
                //phraseNum &= mask;
                System.out.println(phraseNum);
            }

            //while((firstByte = reader.read()) != -1) {
                //there will be at least 1 byte afterwards
            //    secondByte = reader.read();
                //System.out.println(firstByte);
                //System.out.println(secondByte);
            //    System.out.println(firstByte | (secondByte << 8));
                //System.out.println(firstByte);
                //lsb comes first
                //secondByte <<= 8;
                //phraseNum = (firstByte | secondByte);
                //System.out.println(phraseNum);
            //}
            //reader.close();
            input.close();
        } catch(IOException ex) {
            System.err.println("IOException: " + ex);
        }


        
    }
}