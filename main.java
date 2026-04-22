import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.*;

class Main {
    
    public static trieManager loadTrieManager(String rawData) {
        String contents;
        try {
            contents = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("boggleWords.txt")));
        } catch (Exception e) {
            System.err.println("Unable to read words from file");
            return null;
        }
        System.out.println("Getting possible words");

        HashSet<Character> uniqueChars = new HashSet<>();
        for (char c : rawData.toCharArray()) {
            uniqueChars.add(c);
        }
        trieManager trieTree;
        int uniqueCharsCount = uniqueChars.size();
        HashSet<String> words = Arrays.stream(contents.split("\n")).filter(word -> word.length() > 3).collect(Collectors.toSet());
        for (String word : words) {
            for ()
        }
    }

    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;
        String rawData = "trqpniararteoirtlfdismumu".toLowerCase();
        HashMap<Integer, ArrayList<Character>> boardData = new HashMap<>();
        for (int y = 0; y < rows; y++) {
            ArrayList<Character> rowData = new ArrayList<>();
            for (int x = 0; x < cols; x++) {
                rowData.add(rawData.charAt(y * rows + x));
            }
            boardData.put(y, rowData);
        }
        HashSet<String> allFoundWords = new HashSet<>();
        trieManager trieTree = loadTrieManager(rawData);
    }
}