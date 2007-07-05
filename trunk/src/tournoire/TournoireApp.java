/*
 * TournoireApp.java
 * $Id$
 */

package tournoire;

import application.Application;
import application.ApplicationContext;
import application.SingleFrameApplication;
import application.View;
import java.awt.Window;

/**
 * The main class of the application.
 */
public class TournoireApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
       show(new TournoireView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of TournoireApp
     */
    public static TournoireApp getApplication() {
        return Application.getInstance(TournoireApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(TournoireApp.class, args);
    }
}
