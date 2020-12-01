package edu.cmu.cs.cs214.hw2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * @author: Sitian Shen -- sitians@andrew.cmu.edu
 * @date: 9/12/20
 * @time: 8:44 下午
 */
public class Parser {
    // parse the instruction and store the instruction into memory
    private List<String> instruction;
    private Map<String, SingleOps> singleTrans;
    private Map<String, ThreeArgs> threeTrans;
    private Map<String, TwoArgs> twoTrans;
    private static final int NUM = 3;

    /**
     * constructor for Parser
     */
    public Parser() {
        this.instruction = new ArrayList<>();
        OpsMap map = new OpsMap();
        this.singleTrans = map.getSingleTrans();
        this.twoTrans = map.getTwoTrans();
        this.threeTrans = map.getThreeTrans();
    }

    /**
     * parse asm file
     * @param path path for the file
     * @return return list of instructions
     * @throws IOException throw exception
     */
    public List<String> parse(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));
        int counter = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.trim().equals("") || s.trim().startsWith(";"))
                continue;
            int i = s.indexOf(";");
            String tmp = i == -1 ? s.trim() : s.substring(0, i).trim();
            String[] tokens = tmp.split("\\s+");
            // there is one more instructions in the line
            if (tokens.length >= NUM) {
                processToken(tokens);
            } else {
                instruction.add(tmp);
                counter++;
            }

        }
        return instruction;
    }

    private void processToken(String[] tokens) {
        int i = 0;
        while (i < tokens.length) {
            if (singleTrans.containsKey(tokens[i])) {
                String tmp = tokens[i];
                instruction.add(tmp);
                i++;
            } else if (twoTrans.containsKey(tokens[i])) {
                String tmp = tokens[i] + " " + tokens[i + 1];
                instruction.add(tmp);
                i += 2;
            } else {
                String tmp = tokens[i] + " " + tokens[i + 1] + " " + tokens[i + 2];
                instruction.add(tmp);
                i += NUM;
            }
        }
    }
}
