import  java.util.HashMap;

public class trieNode {
    HashMap<Character, trieNode> children = new HashMap<>();
    Boolean endOfWord = false;
}