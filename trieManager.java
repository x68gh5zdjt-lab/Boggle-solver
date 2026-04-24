import java.util.*;

/**
 * Manages the Trie data structure operations
 * Provides methods to build the dictionary and efficiently query strings during the Boggle board traversal
 */
public class trieManager {
    /**
     * The starting point of the Trie. It represents an empty prefix
     */
    trieNode root = new trieNode();

    /**
     * Inserts a word into the Trie
     * Iterates through each character, creating new nodes as necessary
     * to form the path representing the word
     * * @param word The string to be added to the dictionary
     */
    public void insert(String word) {
        trieNode node = root;
        for (Character c : word.toCharArray()) {
            // If the character path doesn't exist and creates a new branch
            if (node.children.get(c) == null) {
                node.children.put(c, new trieNode());
            }
            // Moves deeper into the tree
            node = node.children.get(c);
        }
        // Marks the final node as the completion of a valid word
        node.endOfWord = true;
    }

    /**
     * Checks if a specific word exists in the dictionary.
     * Unlike hasPrefix, this only returns true if the final character 
     * was explicitly marked as the end of a word
     * * @param word The string to search for
     * @return     True if the full word exists else false
     */
    public Boolean hasWord(String word) {
        trieNode node = root;
        for (Character c : word.toCharArray()) {
            trieNode nextNode = node.children.get(c);
            if (nextNode != null) {
                node = nextNode;
            } else {
                // Character path is broken and thus word cannot exist
                return false;
            }
        }
        // Ensure the path represents a complete word, not just a prefix
        return node.endOfWord;
    }

    /**
     * Checks if any word in the dictionary  starts with the given sequence
     * This is the primary optimization tool for the Boggle solver to prune invalid search paths early
     * * @param word The prefix sequence to validate
     * @return     True if the prefix exists in the tree, false otherwise
     */
    public Boolean hasPrefix(String word) {
        trieNode node = root;
        for (Character c : word.toCharArray()) {
            trieNode nextNode = node.children.get(c);
            if (nextNode != null) {
                node = nextNode;
            } else {
                // No words in the dictionary start with this sequence
                return false;
            }
        }
        // If we successfully traversed the characters, the prefix is valid
        return true;
    }
}