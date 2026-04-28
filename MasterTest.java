import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

public class MasterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void checkTrieWorks() {
        trieManager tm = new trieManager();
        tm.insert("apple");
        
        assertTrue(tm.hasWord("apple"));
        assertTrue(tm.hasPrefix("app"));
        assertFalse(tm.hasWord("app"));
        assertFalse(tm.hasPrefix("orange"));
    }

    @Test
    void checkSolverFindsWords() {
        trieManager tm = new trieManager();
        tm.insert("cat");
        tm.insert("car");

        HashMap<Integer, ArrayList<Character>> boardData = new HashMap<>();
        boardData.put(0, new ArrayList<>(Arrays.asList('c', 'a')));
        boardData.put(1, new ArrayList<>(Arrays.asList('t', 'r')));
        HashSet<Character> unique = new HashSet<>(Arrays.asList('c', 'a', 't', 'r'));

        HashSet<String> results = Solver.findAllWords("catr", unique, tm, boardData, 2, 2);

        assertTrue(results.contains("cat"));
        assertTrue(results.contains("car"));
        assertEquals(2, results.size());
    }

    @Test
    void checkNeighborBoundaries() {
        HashSet<Solver.Cord> used = new HashSet<>();
        
        ArrayList<Solver.Cord> cornerNeighbors = Solver.getNeighbors(new Solver.Cord(0, 0), used, 3, 3);
        assertEquals(3, cornerNeighbors.size());
        
        ArrayList<Solver.Cord> centerNeighbors = Solver.getNeighbors(new Solver.Cord(1, 1), used, 3, 3);
        assertEquals(8, centerNeighbors.size());
    }

    @Test
    void checkInputHandling() {
        String simulatedInput = "invalid\n3\nabcd\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        int num = view.getNumberInput(scanner, "Enter:", "Error!");
        assertEquals(3, num);
        assertTrue(outContent.toString().contains("Error!"));

        String board = view.getBoard(scanner, 4);
        assertEquals("abcd", board);
    }

    @Test
    void checkDataDisplay() {
        HashSet<String> words = new HashSet<>(Arrays.asList("CAT", "DOGS"));
        view.displayData(words);
        String out = outContent.toString();
        
        assertTrue(out.contains("Total Words: 2"));
        assertTrue(out.contains("Length: 3"));
        assertTrue(out.contains("Length: 4"));
    }

    @Test
    void makeSureMainExists() {
        main m = new main();
        assertNotNull(m);
        
        HashSet<Character> emptyUnique = new HashSet<>();
        trieManager tm = main.loadTrieManager("", emptyUnique);
    }

    @Test
    void checkBoardSizeIntegration() {
        String input = "2\n2\nABCD\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        view.UserInputData result = view.getBoardSize();
        assertEquals(2, result.y());
        assertEquals("ABCD", result.board());
    }
}