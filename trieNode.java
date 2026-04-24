import java.util.HashMap;

/**
 * Individual node within the Trie structure
 * Each node acts as a single character in a word that links with other characters
 */
public class trieNode {
    /**
     * A hashmap where each key is a character and each value is the corresponding 
     * child node. This allows for around O(1) average complexity when searching for the next character in a sequence
     */
    HashMap<Character, trieNode> children = new HashMap<>();

    /**
     * A flag that indicates whether the path of characters from the root to this specific node constitutes a complete, valid word in the dictionary
     */
    Boolean endOfWord = false;
}