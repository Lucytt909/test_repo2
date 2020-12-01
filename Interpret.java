package edu.cmu.cs.cs214.hw2;

import java.io.File;
import java.io.IOException;


/**
 * Main program that runs the assembly language interpreter
 */
public class Interpret {
    public static final int CAPACITY = 100;
    /**
     * The main method to run the assembly language interpreter
     * @param args program arguments
     * @throws IOException throw exception
     */
    public static void main(String[] args) throws IOException {
//        String path = "src/main/resources/gcd.asm";
        String path = args[0];

        Processor processor = new Processor(CAPACITY, path);

        Memory memory = processor.getMemory();
        processor.run();
    }
}
