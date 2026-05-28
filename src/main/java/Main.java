import com.pluralsight.ui.HomeScreen;

/**
 * Entry point for the PIZZAlicious POS application.
 * Starts the HomeScreen and hands control to the UI layer.
 */
public class Main {

    public static void main(String[] args) {
        HomeScreen home = new HomeScreen();
        home.show();
    }
}
