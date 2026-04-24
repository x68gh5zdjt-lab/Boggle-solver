import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

class viewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        // Captures System.out so we can check what your code prints
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        // Puts System.out back to normal after each test
        System.setOut(originalOut);
    }

    @Test
    void testGetNumberInput_Valid() {
        String input = "5\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        
        int result = view.getNumberInput(scanner, "Enter:", "Error!");
        
        assertEquals(5, result);
    }

    @Test
    void testGetBoard_ValidLength() {
        String input = "ABCDEFGHI\n"; // 9 chars
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        
        String result = view.getBoard(scanner, 9);
        
        assertEquals("ABCDEFGHI", result);
    }

    @Test
    void testDisplayData_LogicAndOutput() {
        HashSet<String> testData = new HashSet<>(Arrays.asList("DOG", "CAT", "BIRD"));
        
        view.displayData(testData);
        String output = outContent.toString();

        assertTrue(output.contains("Total Words: 3"), "Should count 3 words");
        assertTrue(output.contains("Total Points: 10"), "Should calculate 10 points total");
        assertTrue(output.contains("Length: 3"), "Should show length 3 category");
        assertTrue(output.contains("Length: 4"), "Should show length 4 category");
    }

    @Test
    void testUserInputDataRecord() {
        // Tests the record functionality
        view.UserInputData data = new view.UserInputData(3, 3, "ABCDEFGHI");
        assertEquals(3, data.y());
        assertEquals(3, data.x());
        assertEquals("ABCDEFGHI", data.board());
    }
}