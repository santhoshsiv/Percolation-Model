import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class In {
    // assume Unicode UTF-8 encoding
    private static final String CHARSET_NAME = "UTF-8";
    // assume language = English, country = US for consistency with System.out.
    private static final Locale LOCALE = Locale.US;

    private Scanner scanner;
    /**
     * Initializes an input stream from a file.
     *
     * @param  file the file
     * @throws IllegalArgumentException if cannot open {@code file}
     * @throws IllegalArgumentException if {@code file} is {@code null}
     */
    public In(File file) {
        if (file == null) throw new IllegalArgumentException("file argument is null");
        try {
            // for consistency with StdIn, wrap with BufferedInputStream instead of use
            // file as argument to Scanner
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + file, ioe);
        }
    }
//    public In(String filename) {
//        scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
//        scanner.useLocale(LOCALE);
//    }
public In(String name) {
    if (name == null) throw new IllegalArgumentException("argument is null");
    if (name.length() == 0) throw new IllegalArgumentException("argument is the empty string");
    try {
        // first try to read file from local file system
        File file = new File(name);
        if (file.exists()) {
            // for consistency with StdIn, wrap with BufferedInputStream instead of use
            // file as argument to Scanner
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
            scanner.useLocale(LOCALE);
            return;
        }

        // resource relative to .class file
        URL url = getClass().getResource(name);

        // resource relative to classloader root
        if (url == null) {
            url = getClass().getClassLoader().getResource(name);
        }

        // or URL from web
        if (url == null) {
            url = new URL(name);
        }

        URLConnection site = url.openConnection();



        InputStream is     = site.getInputStream();
        scanner            = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
        scanner.useLocale(LOCALE);
    }
    catch (IOException ioe) {
        throw new IllegalArgumentException("Could not open " + name, ioe);
    }
}
    public int readInt() {
        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from the input stream, "
                    + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from the input stream, "
                    + "but no more tokens are available");
        }
    }

    public boolean isEmpty() {
        return !scanner.hasNext();
    }
    public static void main(String[] args) {
        In in;

    }

}
