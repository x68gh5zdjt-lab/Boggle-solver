import java.util.*;

/**
 * The Solver class contains the logic for traversing the Boggle board.
 * Uses an iterative backtracking approach to explore all possible character paths and validates them against a Trie dictionary
 */
class Solver {
    /**
     * A record representing a 2D coordinate on the Boggle board
     * @param y The row index
     * @param x The column index
     */
    public record Cord(int y, int x) {}

    /**
     * Identifies all valid adjacent cells that have not been visited yet in the current path.
     * * @param masterCord The current cell being explored
     * @param usedCells  A set of coordinates already part of the current word path
     * @param rows       Total rows in the board
     * @param cols       Total columns in the board
     * @return           A list of available neighboring coordinates
     */
    public static ArrayList<Cord> getNeighbors(Cord masterCord, HashSet<Cord> usedCells, int rows, int cols) {
        int mY = masterCord.y();
        int mX = masterCord.x();
        ArrayList<Cord> directions = new ArrayList<>();
        
        // Loops through the 3x3 grid surrounding the masterCord
        for (int y = mY - 1; y <= mY + 1; y++) {
            if (y >= 0 && y < rows) { // Boundary check: Rows
                for (int x = mX - 1; x <= mX + 1; x++) {
                    if (x >= 0 && x < cols) { // Boundary check: Columns
                        Cord target = new Cord(y, x);
                        
                        // Skip if the target is the center cell itself or if already used
                        if ((x == mX && y == mY) || usedCells.contains(target)) {
                            continue;
                        }
                        directions.add(target);
                    }
                }
            }
        }
        return directions;
    }

    /**
     * Performs an iterative search starting from every cell on the board to find all valid dictionary words
     * * @param rawData     The raw string of board data
     * @param uniqueChars Set of characters on the board
     * @param trieTree    The dictionary tree used for prefix and word lookups
     * @param boardData   A map representing the board rows and columns
     * @param rows        The height of the board
     * @param cols        The width of the board
     * @return            A HashSet of all unique words found
     */
    public static HashSet<String> findAllWords(String rawData, HashSet<Character> uniqueChars, trieManager trieTree, HashMap<Integer, ArrayList<Character>> boardData, int rows, int cols) {
        HashSet<String> allFoundWords = new HashSet<>();

        // Start a search from every single cell on the board
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Cord startCord = new Cord(y, x);
                HashSet<Cord> usedCells = new HashSet<>();
                ArrayList<Character> masterWord = new ArrayList<>();
                ArrayList<Cord> pathCords = new ArrayList<>();
                
                // masterBranch acts as a manual Stack to manage DFS state
                ArrayList<ArrayList<Cord>> masterBranch = new ArrayList<>();
                
                // Initializes the starting point
                usedCells.add(startCord);
                masterWord.add(boardData.get(y).get(x));
                pathCords.add(startCord);

                String startWord = String.valueOf(boardData.get(y).get(x));
                if (trieTree.hasWord(startWord)) {
                    allFoundWords.add(startWord);
                }

                // Adds the first set of neighbors to the stack
                masterBranch.add(getNeighbors(startCord, usedCells, rows, cols));
                
                // Iterative Backtracking Loop
                while (!masterBranch.isEmpty()) {
                    ArrayList<Cord> currentBranch = masterBranch.get(masterBranch.size() - 1);

                    // If no more neighbors to explore at this depth then backtrack
                    if (currentBranch.isEmpty()) {
                        masterBranch.remove(masterBranch.size() - 1);
                        Cord removed = pathCords.remove(pathCords.size() - 1);
                        usedCells.remove(removed);
                        masterWord.remove(masterWord.size() - 1);
                        continue;
                    }

                    // Explore the next neighbor in the branch
                    Cord nextCord = currentBranch.remove(currentBranch.size() - 1);
                    
                    // Build the current string from the character list
                    StringBuilder sb = new StringBuilder();
                    for (Character c : masterWord) { sb.append(c); }
                    sb.append(boardData.get(nextCord.y()).get(nextCord.x()));
                    String currentWord = sb.toString();

                    // Prunning: Only continue if the current sequence is a prefix of a real word
                    if (trieTree.hasPrefix(currentWord)) {
                        // If it's a complete word and meets length requirements, save it
                        if (currentWord.length() >= 3 && trieTree.hasWord(currentWord)) {
                            allFoundWords.add(currentWord);
                        }
                        
                        // Push state and move deeper into the search (DFS)
                        masterWord.add(boardData.get(nextCord.y()).get(nextCord.x()));
                        usedCells.add(nextCord);
                        pathCords.add(nextCord);
                        masterBranch.add(getNeighbors(nextCord, usedCells, rows, cols));
                    }
                }
            }
        }
        return allFoundWords;
    }
}