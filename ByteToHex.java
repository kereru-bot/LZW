import java.io.*;

class ByteToHex {
    public static void main(String args[]) {
        final int bufferSize = 2097152;

        String filename = args[0];
        FileInputStream byteReader; 
        
        try {
            byteReader = new FileInputStream(filename);
        } catch(FileNotFoundException ex) {
            System.err.println("FileNotFoundException: " + ex);
            return;
        }

        byte bytes[] = new byte[bufferSize];
        int read = 0;
        try {
            while((read = byteReader.read(bytes, 0, bufferSize)) != -1) {
                for(int i = 0; i < read; i++) {
                    System.out.print(String.format("%02X", bytes[i]));
                }
            }
            byteReader.close();
        } catch(IOException ex) {
            System.err.println("IOException: " + ex);
        }

        //byte[] bytes = args[0].getBytes();
        
        //maybe read in bytes from a file to convert to hex?
        //for(byte b : bytes) {
        //    System.out.println(String.format("%02X", b));
        //}
    }
}