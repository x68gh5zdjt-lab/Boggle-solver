
public  class  trieManager {
    trieNode root = trieNode();

    public static void insert(String word) {
        trieNode node = root;
        for (Character c : word.toCharArray()) {
            if (node.children[c] == null) {
                node.children[c] = trieNode();
            }
            node = node.children[c];
        }
        node.endOfWord = true;
    }

    public static Boolean hasWord(String word) {
        trieNode node = root;
        for (Character c : word.toCharArray()) {
            trieNode nextNode = node.children[c];
            if (nextNode) {
                node = nextNode;
            } else {
                return false;
            }
        }
        return node.endOfWord;
    }

    public static Boolean hasPrefix(String word) {
        trieNode node = root;
        for (Character c : word.toCharArray()) {
            trieNode nextNode = node.children[c];
            if (nextNode) {
                node = nextNode;
            } else {
                return false;
            }
        }
        return true;
    }
    
}