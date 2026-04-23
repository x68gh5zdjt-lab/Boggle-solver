import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.*;

class Solver {
    public record Cord(int y, int x) {}

    public static void findAllWords(String rawData, HashSet<Character> uniqueChars, trieManager trieTree, int rows, int cols) {
        for (int y = 0; y < rows < y++) {
            for (int x = 0; x < cols; x++) {
                Cord masterCord = cord(y, x);
                HashSet<cord> usedCells = new HashSet<>(masterCord);
                ArrayList<cord> nextNodes = getNeighbors(masterCord, usedCells);
                ArrayList<ArrayList<cord>> masterBranch = new ArrayList<>();
                masterBranch.add(nextNodes);
            }
        }
    }

}