import java.util.*;

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

    public static HashSet<String> findAllWords(String rawData, HashSet<Character> uniqueChars, trieManager trieTree, HashMap<Integer, ArrayList<Character>> boardData, int rows, int cols) {
        HashSet<String> allFoundWords = new HashSet<>();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Cord startCord = new Cord(y, x);
                HashSet<Cord> usedCells = new HashSet<>();
                ArrayList<Character> masterWord = new ArrayList<>();
                ArrayList<Cord> pathCords = new ArrayList<>();
                ArrayList<ArrayList<Cord>> masterBranch = new ArrayList<>();
                usedCells.add(startCord);
                masterWord.add(boardData.get(y).get(x));
                pathCords.add(startCord);

                String startWord = String.valueOf(boardData.get(y).get(x));
                if (trieTree.hasWord(startWord)) {
                    allFoundWords.add(startWord);
                }

                masterBranch.add(getNeighbors(startCord, usedCells, rows, cols));
                while (!masterBranch.isEmpty()) {
                    ArrayList<Cord> currentBranch = masterBranch.get(masterBranch.size() - 1);

                    if (currentBranch.isEmpty()) {
                        masterBranch.remove(masterBranch.size() - 1);
                        Cord removed = pathCords.remove(pathCords.size() - 1);
                        usedCells.remove(removed);
                        masterWord.remove(masterWord.size() - 1);
                        continue;
                    }

                    Cord nextCord = currentBranch.remove(currentBranch.size() - 1);
                    StringBuilder sb = new StringBuilder();
                    for (Character c : masterWord) {sb.append(c);}
                    sb.append(boardData.get(nextCord.y()).get(nextCord.x()));
                    String currentWord = sb.toString();

                    if (trieTree.hasPrefix(currentWord)) {
                        if (currentWord.length() >= 3 && trieTree.hasWord(currentWord)) {
                            allFoundWords.add(currentWord);
                        }

                        masterWord.add(boardData.get(nextCord.y()).get(nextCord.x()));
                        usedCells.add(nextCord);
                        pathCords.add(nextCord);
                        masterBranch.add(getNeighbors(nextCord, usedCells, rows, cols));
                    }
                }
            }
        }
        return allFoundWords;
    }
}