import java.util.*;

/**
 * Core for the Boggle Solver
 * Coordinates board initialization, hashmap loading into a Trie, and the execution of the main algorithm
 */
class main {
    
    /**
     * Loads words from a dictionary file into a Trie structure, filtering for words that could appear on the board
     * * @param rawData     The raw string representaion of the board characters
     * @param uniqueChars A set of characters present on the board used for pre-filtering
     * @return            A trieManager containing valid dictionary words
     */
    public static trieManager loadTrieManager(String rawData, HashSet<Character> uniqueChars) {
        trieManager trieTree = new trieManager();
        try {
            // Reads the dictionary file; ensure "boggleWords.txt" is in the root directory
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("boggleWords.txt"));
            System.out.println("Reading File!");
            
            for (String word : lines) {
                word = word.trim().toLowerCase();
                
                // Boggle rules only allow words greater than or equal to 3 characters in length
                if (word.length() < 3) continue;
                
                boolean possible = true;
                // If the word contains a character not on the board it is skiped
                for (int i = 0; i < word.length(); i++) {
                    if (!uniqueChars.contains(word.charAt(i))) {
                        possible = false;
                        break;
                    }
                }
                
                if (possible) {
                    trieTree.insert(word);
                }
            }
        } catch (Exception e) {
            System.err.println("Unable to read file!");
            return null;
        }
        return trieTree;
    }

    /**
     * Collects board data from the view
     * Feeds it to the solver
     * And outputs it in the view
     * * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Retrieves the board dimensions and characters from the UI layer
        view.UserInputData boardInfo = view.getBoardSize();
        int rows = boardInfo.y();
        int cols = boardInfo.x();
        String rawData = boardInfo.board().toLowerCase();
        
        HashSet<Character> uniqueChars = new HashSet<>();
        HashMap<Integer, ArrayList<Character>> boardData = new HashMap<>();
        
        // Map the flat rawData string into a 2D-like HashMap structure for the solver
        for (int y = 0; y < rows; y++) {
            ArrayList<Character> rowData = new ArrayList<>();
            for (int x = 0; x < cols; x++) {
                Character c = rawData.charAt(y * rows + x);
                rowData.add(c);
                uniqueChars.add(c);
            }
            boardData.put(y, rowData);
        }
        
        // Initializes the Trie
        trieManager trieTree = loadTrieManager(rawData, uniqueChars);
        
        System.out.println("Finding Words");
        // Executes the algorithm
        HashSet<String> results = Solver.findAllWords(rawData, uniqueChars, trieTree, boardData, rows, cols);
        
        System.out.println("Displaying Info");
        view.displayData(results);
    }
}