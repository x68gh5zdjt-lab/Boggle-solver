
public  class  trieManager {
    trieNode root = new trieNode();

    public void insert(String word) {
        trieNode node = root;
        for (Character c : word.toCharArray()) {
            if (node.children.get(c) == null) {
                node.children.put(c, new trieNode());
            }
            node = node.children.get(c);
        }
        node.endOfWord = true;
    }

    public Boolean hasWord(String word) {
        trieNode node = root;
        for (Character c : word.toCharArray()) {
            trieNode nextNode = node.children.get(c);
            if (nextNode != null) {
                node = nextNode;
            } else {
                return false;
            }
        }
        return node.endOfWord;
    }

    public Boolean hasPrefix(String word) {
        trieNode node = root;
        for (Character c : word.toCharArray()) {
            trieNode nextNode = node.children.get(c);
            if (nextNode != null) {
                node = nextNode;
            } else {
                return false;
            }
        }
        return true;
    }
    
}