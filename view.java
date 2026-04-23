import java.util.*;


class view {

    public static int getNumberInput(Scanner scanner, String displayText, String errorText) {
        int s = 0;
        do { 
            System.out.println(displayText);
            try {
                s = scanner.nextInt();
                return s;
            } catch (Exception e) {
                System.out.println(errorText);
            }
        } while (true);
    }

    public static void getBoardSize() {
        Scanner scanner = new Scanner(System.in);
        int ySize = getNumberInput(scanner, "Y: ", "Invalid Number");
        int xSize = getNumberInput(scanner, "X: ", "Invalid Number");
    }
}