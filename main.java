import java.util.*;

class main {
    
    public static trieManager loadTrieManager(String rawData, HashSet<Character> uniqueChars) {
        trieManager trieTree = new trieManager();
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("boggleWords.txt"));
            System.out.println("Reading File!");
            for (String word : lines) {
                word = word.trim().toLowerCase();
                if (word.length() < 3) continue;
                boolean possible = true;
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

    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;
        String rawData = "ahaoaiidnputsogadoiacprko".toLowerCase();
        HashSet<Character> uniqueChars = new HashSet<>();
        HashMap<Integer, ArrayList<Character>> boardData = new HashMap<>();
        for (int y = 0; y < rows; y++) {
            ArrayList<Character> rowData = new ArrayList<>();
            for (int x = 0; x < cols; x++) {
                Character c = rawData.charAt(y * rows + x);
                rowData.add(c);
                uniqueChars.add(c);
            }
            boardData.put(y, rowData);
        }
        trieManager trieTree = loadTrieManager(rawData, uniqueChars);
        System.out.println("Finding Words");
        HashSet<String> results = Solver.findAllWords(rawData, uniqueChars, trieTree, boardData, rows, cols);
        System.out.println("Displaying Info");
        System.out.println(results);
    }
}