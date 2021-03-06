package kpi.abstr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by andrew_yashin on 2/23/17.
 */
public abstract class SymbolicAlgorithm {
    public static LinkedList<Character> alphabet;

    public static void initAlphabet(){
        Set<Character> set = new HashSet<>();
        for (int i = 32; i < 127; i++) {
            set.add((char) i);
        }

        for (char i = 'А'; i <= 'я'; i++) {
            set.add(i);
        }
        set.addAll(Arrays.asList('ї', 'Ї', 'ю', 'є', 'Ю', 'Є', 'і', 'І', 'ё', 'Ё'));

        alphabet = new LinkedList<>(set);
    }

    public abstract void encrypt(String from, String to, int key, String text);
    public abstract void decrypt(String from, String to, int key, String text);

    protected static char getCharFromAlhabet(char prevChar, int key, boolean add) {

        int position = 0;
        position = Math.abs(key) % alphabet.size();
        return alphabet.get(position);
    }

    protected static void writeToFile(String data, String nameFile){
        try(BufferedWriter out = new BufferedWriter(new FileWriter(nameFile))){
            out.write(data);
        } catch (IOException e){
            System.err.println("Something wrong with writeToFile() method");
            e.printStackTrace();
        }
    }

    public static int getIndexOfChar(int prevChar, int a, int b, int c, boolean encrypt){
        if(encrypt)
            return prevChar + a^3 + b^2 + c;
        else
            return prevChar - a^3 - b^2 - c;
    }
}
