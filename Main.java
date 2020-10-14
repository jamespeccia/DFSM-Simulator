/*
 * Name: James Peccia
 * Course: CS 41000
 * Email: jpeccia@purdue.edu
 * Assignment: DFSM Simulator
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = initializeScanner("src/example1.txt"); // Initializes scanner object on given configuration file
        if (in == null) return; // If configuration file could not be located, end program

        HashSet<Character> alphabet = getAlphabet(in); // Get all characters of the alphabet from file and save to an alphabet set
        State[] states = initializeStates(in); // Get number of states, activation states, and edges for each state from file

        //visualizeStates(states); // Visualize each state in memory

        in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String word = in.nextLine();
            boolean accepted = true;
            int currentState = 0;
            for (char c : word.toCharArray()) {
                if (alphabet.contains(c)) // Check if character is in language
                    currentState = states[currentState].move(c);
                else {
                    accepted = false;
                    break;
                }

            }

            if (!accepted)
                System.out.println("\"" + word + "\" is not accepted.");
            else if (states[currentState].isActivationState())
                System.out.println("\"" + word + "\" is accepted.");
            else
                System.out.println("\"" + word + "\" is not accepted.");
        }
    }

    public static Scanner initializeScanner(String filename) {
        File file = new File(filename);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file!");
        }
        return in;
    }

    public static HashSet<Character> getAlphabet(Scanner in) {
        HashSet<Character> alphabet = new HashSet<>(); // Set that contains all the characters of the alphabet
        String alphabetLine = in.nextLine(); // Scans in line that contains characters of the alphabet
        for (int i = 0; i < alphabetLine.length(); i += 2) // Adds every other character to alphabet set (avoids commas)
            alphabet.add(alphabetLine.charAt(i)); // Adds characters of the alphabet to an alphabet set

        return alphabet;
    }

    public static State[] initializeStates(Scanner in) {
        int numberOfStates = in.nextInt(); // Read in number of states
        State[] states = new State[numberOfStates]; // Create an array of size 'numberOfStates'
        for (int i = 0; i < states.length; i++)
            states[i] = new State(); // Initialize each index to an empty state

        in.nextLine();
        in.useDelimiter("\\D");
        while (in.hasNextInt()) { // Run until all accepting states are updated
            int acceptingStateIndex = in.nextInt(); // Get index of accepting state
            states[acceptingStateIndex].setActivationState(); // Set state to an accepting state
        }

        in.nextLine();

        in.useDelimiter("\\W");
        while (in.hasNextInt()) { // Read paths from configuration file and update states
            try {
                int startState = in.nextInt();
                char edge = in.next().charAt(0);
                int endState = in.nextInt();

                states[startState].addPath(edge, endState); // Add path to state

                in.next();
                in.next();
            } catch (Exception e) { // End of file
                continue;
            }
        }

        return states;
    }

    public static void visualizeStates(State[] states) {
        for (State s : states)
            System.out.println(s.toString());
    }
}
