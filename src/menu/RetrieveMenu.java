package menu;

import utils.Common;

public class RetrieveMenu {
    /**
     * Display the retrieve menu
     */
    public static void retrieve() {
        System.out.println("--- Retrieve password ---\n");
        boolean running = true;

        while (running){
            // Ask the name of the service
            String serviceName = Common.getServiceName();

            // Retrieve the password
            System.out.println("Retrieving password for service: " + serviceName);
            // TODO: Implement password retrieval

            // Exit the menu
            running = false;
        }
    }
}
