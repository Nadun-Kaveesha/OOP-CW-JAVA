
# Ticketing Sytem

This project is a ticketing system implemented in Java using Maven and Ant. It includes classes for managing configurations, customers, vendors, and ticket pools.

Setup Instructions

    1. Clone the Repository:  
        git clone https://github.com/Nadun-Kaveesha/OOP-CW-JAVA.git
        cd ticketing-system

    2. Database Setup:  
        Ensure you have MySQL installed and running.
        Create a database named ticketing_system_java.
        Update the database connection details in src/com/iit/oop/cw/DBConfig.java if necessary.

    3. Build the Project:  
        Using Maven:
            mvn clean install
        Using Ant:
            ant build

    4. Run the Application:  
        Using Maven:
            mvn exec:java -Dexec.mainClass="com.iit.oop.cw.App"
        Using Ant:
            ant run

CLI Usage Guidelines

    1. Main Menu:  

        - Customer:
            Press [1] to buy tickets.

        - Vendor:
            Press [2] to add tickets to the system.

        - Admin:
            Press [3] to access admin functionalities.

        - Exit:
            Press [0] to exit the application.

    2. Admin Menu:  

        - Start Releasing Tickets:
            Press [1] and enter the release interval in milliseconds.

        - Start Retrieving Tickets:
            Press [2] and enter the retrieval interval in milliseconds.

        - Start Both:
            Press [3] and enter both release and retrieval intervals in milliseconds.

        - Reconfigure Releasing Rate:
            Press [4] and enter the new ticket release rate.

        - Reconfigure Retrieving Rate:
            Press [5] and enter the new customer retrieval rate.

        - Show Status:
            Press [6] to display the current status.

        - Stop a Running Process:
            Press [7] to stop a running process.

Troubleshooting

    1. Database Connection Issues:  
        - Ensure MySQL is running.
        - Verify the database connection details in src/com/iit/oop/cw/DBConfig.java.
        - Check if the database ticketing_system_java exists.

    2. Build Failures:  
        - Ensure Maven or Ant is installed and properly configured.
        - Run mvn clean install or ant build to rebuild the project.

    3. Runtime Errors:  
        - Check the log file src/com/iit/oop/cw/log.txt for detailed error messages.
        - Ensure all required configurations are set correctly.

For further assistance, refer to the project documentation or contact the project maintainer.

## Author

- Author : Nadun Kaveesha

- GitHub : [@Nadun-Kaveesha](https://github.com/Nadun-Kaveesha)

- Emai   : nadunkaveesha2018@gmail.com
