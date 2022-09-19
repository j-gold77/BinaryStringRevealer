/**
 *
 *  @author jgold
 *   Joseph Goldberger 
 *
 *    My program has a
 *   static void RevealStr method has takes a binary string as input and an index of 0.
 *   It will then detect any masks (*) in the binary string and output all possible
 *   binary combinations into either a file or just the console depending on length
 *   . It does this by recursion. In the main part of the program,
 *   i generate a random binary number and convert it to a string and then randomly
 *   place stars in this String. I also will measure the time of the program.
 *
 */




package programmingQuestion;

import java.io.*;


public class Version1 {

    /**
     * @param binary - binary number with masks as a string
     * @param index - index of 0 to keep track of position in String
     * @throws FileNotFoundException - opening and reading to file
     */

    public static void RevealStr (String binary,int index) throws FileNotFoundException {

        //Can uncomment this part if need to output to file
        /* File file = new File("out.txt");
        PrintWriter pw;
        pw = new PrintWriter(new FileOutputStream(file,true));
        */

        char[] word = binary.toCharArray();

        if (index == word.length){
            System.out.println(String.valueOf(word));

            //can uncomment if need to output to file
            /*
            pw.println(String.valueOf(word));
            pw.close();

             */
            return;
        }

        if(word[index] == '*'){
            word[index] = '0';

            RevealStr(String.valueOf(word), index + 1);

            word[index] = '1';
            RevealStr(String.valueOf(word), index+1);

            word[index] = '*';
        }
        else
            RevealStr(binary,index+1);


        //pw.close();

    }

    public static void main (String[]args) throws IOException {

        // record the starting time
        long startTime = System.currentTimeMillis( );

        //making a random binary number
        int random = (int) (Math.random() * 10000);
        String binary = Integer.toBinaryString(random);

        //opening output file
        File file = new File("out.txt");
        PrintWriter pw;

        pw = new PrintWriter(new FileOutputStream(file,true));

        //amount of masks to put in the String
        String masks = "**";



        //setting masks to random position in String

        for(int i=0; i<masks.length();i++)
        {
            random = (int) (binary.length() *Math.random());
            binary = binary.substring(0,random) + "*" + binary.substring(random,binary.length());
        }

        //message i used in out file
           /*
            pw.println("\n\nAt this point showing all the combinations will take far too long to output in" +
                    " this document. " +
                            "\nso im going to just print the elapsed time to this document for numbers of stars\n" +
                    "and print the stars to my console");

            */



        pw.close();
        RevealStr(binary,0);


        long endTime = System.currentTimeMillis( ); // record the ending time
        long elapsed = (endTime - startTime);

        pw = new PrintWriter(new FileOutputStream(file,true));
        pw.println("\nThis current run of " +masks.length() + " masks took " + elapsed + "ms");
        pw.close();



    }
}

