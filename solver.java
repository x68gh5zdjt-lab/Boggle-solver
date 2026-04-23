import java.util.*;
import java.util.stream.Collectors;

class Solver {
    public record Cord(int y, int x) {}

    public static ArrayList<Cord> getNeighbors(Cord masterCord, HashSet<Cord> usedCells, int rows, int cols) {
        int mY = masterCord.y();
        int mX = masterCord.x();
        ArrayList<Cord> directions = new ArrayList<>();
        for (int y = mY - 1; y <= mY + 1; y++) {
            if (y >= 0 && y < rows) {
                for (int x = mX - 1; x <= mX + 1; x++) {
                    if (x >= 0 && x < cols) {
                        Cord target = new Cord(y, x);
                        if ((x == mX && y == mY) || usedCells.contains(target)) {
                            continue;
                        }
                        directions.add(target);
                    }
                }
            }
        }
        return directions;
    }

    public static void backTrack(ArrayList<ArrayList<Cord>> masterBranch, ArrayList<Character> masterWord, HashSet<Cord> usedCells) {
        if (!masterBranch.isEmpty()) {
            int lastBranchIdx = masterBranch.size() - 1;
            ArrayList<Cord> currentList = masterBranch.get(lastBranchIdx);
            
            Cord removed = currentList.remove(currentList.size() - 1);
            usedCells.remove(removed);
            masterWord.remove(masterWord.size() - 1);

            if (currentList.isEmpty()) {
                masterBranch.remove(lastBranchIdx);
            }
        }
    }

    public static HashSet<String> findAllWords(String rawData, HashSet<Character> uniqueChars, trieManager trieTree, HashMap<Integer, ArrayList<Character>> boardData, int rows, int cols) {
        HashSet<String> allFoundWords = new HashSet<>();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Cord masterCord = new Cord(y, x);
                HashSet<Cord> usedCells = new HashSet<>();
                usedCells.add(masterCord);
                
                ArrayList<Cord> nextNodes = getNeighbors(masterCord, usedCells, rows, cols);
                ArrayList<ArrayList<Cord>> masterBranch = new ArrayList<>();
                
                ArrayList<Character> masterWord = new ArrayList<>();
                masterWord.add(boardData.get(y).get(x));

                if (!nextNodes.isEmpty()) {
                    masterBranch.add(nextNodes);
                    int lastIndex = nextNodes.size() - 1;
                    Cord lastNode = nextNodes.get(lastIndex);
                    masterWord.add(boardData.get(lastNode.y()).get(lastNode.x()));
                    usedCells.add(lastNode);
                } else {
                    continue;
                }

                while (!masterBranch.isEmpty()) {
                    String currentWord = masterWord.stream().map(String::valueOf).collect(Collectors.joining());

                    int lastArrayIdx = masterBranch.size() - 1;
                    ArrayList<Cord> currentBranch = masterBranch.get(lastArrayIdx);
                    int lastNodeIdx = currentBranch.size() - 1;

                    if (trieTree.hasPrefix(currentWord)) {
                        if (trieTree.hasWord(currentWord)) {
                            allFoundWords.add(currentWord);
                        }

                        Cord currentCord = currentBranch.get(lastNodeIdx);
                        ArrayList<Cord> next = getNeighbors(currentCord, usedCells, rows, cols);

                        if (!next.isEmpty()) {
                            masterBranch.add(next);
                            Cord currentNext = next.get(next.size() - 1);
                            masterWord.add(boardData.get(currentNext.y()).get(currentNext.x()));
                            usedCells.add(currentNext);
                        } else {
                            backTrack(masterBranch, masterWord, usedCells);
                        }
                    } else {
                        backTrack(masterBranch, masterWord, usedCells);
                    }
                }
            }
        }
        return allFoundWords;
    }
}