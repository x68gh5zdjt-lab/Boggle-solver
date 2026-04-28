//THIS IS AN EXAMPLE I GOT ONLINE!! THIS IS TO MAKE SURE TESTING WORKS 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class trieManagerTest {

    @Test
    public void testHasWord() {
        trieManager trie = new trieManager();
        trie.addWord("cat");

        assertTrue(trie.hasWord("cat"));
        assertFalse(trie.hasWord("dog"));
    }

    @Test
    public void testHasPrefix() {
        trieManager trie = new trieManager();
        trie.addWord("apple");

        assertTrue(trie.hasPrefix("app"));
        assertFalse(trie.hasPrefix("xyz"));
    }
}
