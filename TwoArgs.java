package edu.cmu.cs.cs214.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * enum for two args operation
 */
public enum TwoArgs {
    CALL {
        /**
         * execution for CALL
         * @param addr address of subroutine
         * @param processor processor contains register
         */
        public void execute(String addr, Processor processor) {
            Memory memory = processor.getMemory();
            int pcOldVal = processor.getRegister("PC").getValue();
            memory.push(pcOldVal + 1, processor.getRegister("SP"));
//            System.out.println("Call push addr" + pcOldVal);
            processor.setRegister("PC", Integer.parseInt(addr));
            int spNewVal = processor.getRegister("SP").getValue() - 1;
            processor.setRegister("SP", spNewVal);
        }
    },
    JUMP {
        /**
         * execution for JUMP
         * @param addr address for PC to jump
         * @param processor processor contains register
         */
        public void execute(String addr, Processor processor) {
            processor.setRegister("PC", Integer.parseInt(addr));
        }
    },
    PUSH {
        /**
         * execution for PUSH
         * @param reg register to push to stack
         * @param processor processor contains register
         */
        public void execute(String reg, Processor processor) {
            Memory memory = processor.getMemory();
            memory.push(processor.getRegister(reg).getValue(), processor.getRegister("SP"));
            // decrement SP
//            System.out.println("push value" + reg.getValue());
            int newVal = processor.getRegister("SP").getValue() - 1;
            processor.setRegister("SP", newVal);
        }
    },
    POP {
        /**
         * execution for POP
         * @param reg register to store the poped value
         * @param processor processor contains register
         */
        public void execute(String reg, Processor processor) {
            Memory memory = processor.getMemory();
            int value = memory.pop(processor.getRegister("SP"));
//            reg.setValue(value);
            processor.setRegister(reg, value);
            int newVal = processor.getRegister("SP").getValue() + 1;
            processor.setRegister("SP", newVal);
        }
    },
    READ {
        /**
         * execution for READ
         * @param reg set the register to the readed value
         * @param processor processor contains register
         * @throws IOException throw exception
         */
        public void execute(String reg, Processor processor) throws IOException {
            BufferedReader br = processor.getBufferReader();
            int readVal = br.read();
            processor.setRegister(reg, readVal);
        }
    },
    WRITE {
        /**
         * execution for WRITE
         * @param reg write the value of the reg to console
         * @param processor processor contains register
         * @throws IOException throw exception
         */
        public void execute(String reg, Processor processor) throws IOException {
//            System.out.println(reg.getValue());
            short lower = (short)processor.getRegister(reg).getValue();
            System.out.print((char)lower);
            return;
        }
    };

    /**
     * execution method
     * @param addr String for reg or addr
     * @param processor processor contains register
     * @throws IOException throw exception
     */
    abstract void execute(String addr, Processor processor) throws IOException;
}
