import java.util.*;

/**
 * The view class handles all console-based interactions with the user.
 * It manages data input, validation for board dimensions, and the final formatting of the discovered words.
 */
class view {

    /**
     * A data carrier representing the board configuration 
     * @param y     Number of rows
     * @param x     Number of columns
     * @param board The string of characters representing the grid
     */
    public record UserInputData(int y, int x, String board) {}
    
    /**
     * Helper method to safely capture an integer from the user with error handling.
     * Includes validation to ensure the board dimensions are at least 3.
     * * @param scanner     The shared Scanner instance for reading input
     * @param displayText The prompt shown to the user
     * @param errorText   The message shown if the input is not a valid integer
     * @return            The valid integer provided by the user
     */
    public static int getNumberInput(Scanner scanner, String displayText, String errorText) {
        int s = 0;
        do { 
            System.out.println(displayText);
            try {
                if (scanner.hasNextInt()) { 
                    String ss = scanner.nextLine();
                    s = Integer.parseInt(ss);
                    // Logic check: dimensions smaller than 3 might not be viable for Boggle
                    if (s < 3) {
                        continue;
                    }
                    return s;
                } else {
                    // If it's not an int, we MUST consume the bad token to prevent infinite loops
                    scanner.next(); 
                    System.out.println(errorText);
                }
            } catch (Exception e) {
                // Backup clear the buffer on error
                if (scanner.hasNext()) scanner.next(); 
                System.out.println(errorText);
            }
        } while (true);
    }

    /**
     * Captures the board character string and ensures it matches the required grid size.
     * * @param scanner The shared Scanner instance
     * @param bSize   The expected total number of characters (rows * columns)
     * @return        A string representing the board 
     */
    public static String getBoard(Scanner scanner, int bSize) {
        do { 
            System.out.println("Input The Board (expected length " + bSize + "): ");
            String s = scanner.nextLine(); // Always consume the line
            if (s.length() == bSize) {
                return s;
            }
            System.out.println("Error: Board must be exactly " + bSize + " characters.");
        } while (true);
    }

    /**
     * Input process to gather rows, columns, and board characters.
     * * @return A UserInputData record containing the complete board setup
     */
    public static UserInputData getBoardSize() {
        Scanner scanner = new Scanner(System.in);
        int ySize = 0;
        do {
            ySize = getNumberInput(scanner, "Rows: ", "Invalid Number!");
            if (ySize >= 3) {
                break;
            }
        } while (true);

        int xSize = 0;
        do {
            xSize = getNumberInput(scanner, "Columns: ", "Invalid Number!");
            if (xSize >= 3) {
                break;
            }
        } while (true);
        int bSize = ySize * xSize;
        String board = getBoard(scanner, bSize);
        return new UserInputData(ySize, xSize, board);
    }

    /**
     * Formats & prints the found words to the console.
     * Words are grouped by their character length and a total score is calculated.
     * * @param data A set of all unique words found by the solver
     */
    public static void displayData(HashSet<String> data) {
        // Map used to group words by their length (Integer) for organized display
        HashMap<Integer, ArrayList<String>> wordLengtHashMap = new HashMap<>();
        int totalPoints = 0;

        for (String word : data) {
            int wordSize = word.length();
            
            // If this word length hasn't been seen yet, initialize a new list
            if (!wordLengtHashMap.containsKey(wordSize)) {
                wordLengtHashMap.put(wordSize, new ArrayList<String>());
            }
            
            wordLengtHashMap.get(wordSize).add(word);
            
            // Simple scoring logic: 1 point per character
            totalPoints += wordSize;
        }

        System.out.println("--- Boggle Results ---");
        System.out.println("Total Words: " + data.size());
        System.out.println("Total Points: " + totalPoints);

        // Iterate through the grouped words to print them length by length
        for (Map.Entry<Integer, ArrayList<String>> entry : wordLengtHashMap.entrySet()) {
            System.out.println("Length " + entry.getKey() + ":");
            for (String word : entry.getValue()) {
                System.out.print(word + " ");
            }
            // Spacing for better readability
            System.out.println("\n");
        }
    }
}
