import java.util.*;


class view {

    public record UserInputData(int y, int x, String board) {}

    public static int getNumberInput(Scanner scanner, String displayText, String errorText) {
        int s = 0;
        do { 
            System.out.println(displayText);
            try {
                s = scanner.nextInt();
                scanner.nextLine();
                return s;
            } catch (Exception e) {
                System.out.println(errorText);
            }
        } while (true);
    }

    public static String getBoard(Scanner scanner, int bSize) {
        do { 
            System.out.println("Input The Board: ");
            try {
                String s = scanner.nextLine();
                if (s.length() == bSize) {
                    return s;
                }
            } catch (Exception e) {
                System.out.println("Unable To Read Input!");
            }
        } while (true);
    }

    public static UserInputData getBoardSize() {
        Scanner scanner = new Scanner(System.in);
        int ySize = getNumberInput(scanner, "Rows: ", "Invalid Number!");
        int xSize = getNumberInput(scanner, "Columns: ", "Invalid Number!");
        int bSize = ySize * xSize;
        String board = getBoard(scanner, bSize);
        return new UserInputData(ySize, xSize, board);
    }

    public static void displayData(HashSet<String> data) {
        HashMap<Integer, ArrayList<String>> wordLengtHashMap = new HashMap<>();
        int totalPoints = 0;
        for (String word : data) {
            int wordSize = word.length();
            if (!wordLengtHashMap.containsKey(wordSize)) {
                wordLengtHashMap.put(wordSize, new ArrayList<String>());
            }
            wordLengtHashMap.get(wordSize).add(word);
            totalPoints += wordSize;
        }
        System.out.println("Total Words: " + data.size());
        System.out.println("Total Points: " + totalPoints);

        for (Map.Entry<Integer, ArrayList<String>> entry : wordLengtHashMap.entrySet()) {
            System.out.println("Length: " + entry.getKey());
            for (String word : entry.getValue()) {
                System.out.print(word + " ");
            }
            System.out.println("");
            System.out.println("");
        }
    }
}