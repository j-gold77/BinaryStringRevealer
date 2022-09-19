/**
 *
 *  @author jgold
 *   Joseph Goldberger - 21958283
 *   For COMP 352 Section H - Fall 2020
 *   Assignment 1 - version 2
 *   Due Date: September 28, 2020
 *
 *   This is version 2 of the programming part for assignment 1. My program has a
 *   static void RevealStr method has takes a binary string.
 *   It will then detect any masks (*) in the binary string and output all possible
 *   binary combinations into either a file or just the console depending on length
 *   . It does this by iteratily by using stacks. In the main part of the program,
 *   i generate a random binary number and convert it to a string and then randomly
 *   place stars in this String. I also will measure the time of the program.
 *
 */



package programmingQuestion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Stack;

public class Version2 {

    /**
     * @param binary - string of binary numbers and masks (*)
     * @throws FileNotFoundException - opening a file to output data
     */

    public static void RevealStr(String binary) throws FileNotFoundException {

        //can uncomment this if need to read into file
        /*
        File file = new File("out2.txt");
        PrintWriter pw;

        pw = new PrintWriter(new FileOutputStream(file,true));
         */

        //creating a stack of strings
        Stack<String> stack = new Stack();


        //putting binary string into stack
        stack.push(binary);

        int index;


        //will check until stack is empty
        while (!stack.isEmpty()) {
            String current = stack.pop();

            if (current.contains("*")) {
                index = current.indexOf('*');
                current = current.substring(0, index) + "0" + current.substring(index + 1);
                stack.push(current);
                current = current.substring(0, index) + "1" + current.substring(index + 1);
                stack.push(current);

            } else {
                //pw.println(current);
                System.out.println(current);
            }

        }

        // pw.close();

    }

    public static void main(String[] args) throws FileNotFoundException {

        // record the starting time
        long startTime = System.currentTimeMillis( );

        //random number to binary
        int random = (int) (Math.random() * 10000);
        String binary = Integer.toBinaryString(random);

        //opening out file
        File file = new File("out2.txt");
        PrintWriter pw;

        pw = new PrintWriter(new FileOutputStream(file,true));
        String masks = "**";




        for(int i=0; i<masks.length();i++)
        {
            random = (int) (binary.length() *Math.random());
            binary = binary.substring(0,random) + "*" + binary.substring(random,binary.length());
        }

       /* pw.println("\n\nWith a randomly generated binary number with " +masks.length() +
                        "masks. All the possible combinations are as follows for: " + binary+ "\n");

        */

           /* pw.println("\n\nAt this point showing all the combinations will take far too long to output in" +
                    " this document. " +
                            "\nso im going to just print the elapsed time to this document for numbers of stars\n" +
                    "and print the stars to my console");

            */



        pw.close();
        RevealStr(binary);


        long endTime = System.currentTimeMillis( ); // record the ending time
        long elapsed = (endTime - startTime);

        pw = new PrintWriter(new FileOutputStream(file,true));
        pw.println("\nThis current run of " +masks.length() + " masks took " + elapsed + "ms");
        pw.close();



    }



}
