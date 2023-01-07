import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StdIn {
    private static Scanner scanner;
    private static boolean isInitialized;
    public static int readInt() {
        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from standard input, "
                    + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from standard input, "
                    + "but no more tokens are available");
        }

    }

    public static boolean isEmpty() {
        return !scanner.hasNext();
    }

}