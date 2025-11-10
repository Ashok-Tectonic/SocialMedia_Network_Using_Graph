import java.util.*;

public SocialNetworkApp {
    private static SocialNetwork socialNetwork = new SocialNetwork();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        defaultNodes(socialNetwork);
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: createGraphMenu(); break;
                case 2: searchPersonNetwork(); break;
                case 3: visualizeNetwork(); break;
                case 4: viewAllNodes(); break;
                case 5: findPath(); break;
                case 0: System.out.println("Exiting the program. Goodbye!"); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n===== Social Network Application =====");
        System.out.println("1. Create Graph");
        System.out.println("2. Search for a Person's Social Network");
        System.out.println("3. Visualize the Social Network");
        System.out.println("4. View all Social Network Nodes");
        System.out.println("5. Find Path from Node to Node");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createGraphMenu() {
        while (true) {
            System.out.println("\n===== Create Graph Menu =====");
            System.out.println("1. Add a Node");
            System.out.println("2. Remove a Node");
            System.out.println("3. Add an Edge");
            System.out.println("4. Remove an Edge");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: addNode(); break;
                case 2: removeNode(); break;
                case 3: addEdge(); break;
                case 4: removeEdge(); break;
                case 5: return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addNode() {
        System.out.print("Enter the name of the person to add: ");
        String name = scanner.nextLine();
        socialNetwork.makeNode(name);
        System.out.println(name + " has been added.");
    }

    private static void removeNode() {
        System.out.print("Enter the name of the person to remove: ");
        String name = scanner.nextLine();
        socialNetwork.deleteNode(name);
    }

    private static void addEdge() {
        System.out.print("Enter person 1 to connect: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter person 2 to connect: ");
        String name2 = scanner.nextLine();
        socialNetwork.edgeConnect(name1, name2);
    }

    private static void removeEdge() {
        System.out.print("Enter person 1 to remove edge: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter person 2 to remove edge: ");
        String name2 = scanner.nextLine();
        socialNetwork.deleteEdge(name1, name2);
    }

    private static void searchPersonNetwork() {
        System.out.print("Enter name to view its Social Network: ");
        String name = scanner.nextLine();
        List<String> connections = socialNetwork.getConnections(name);
        System.out.println("Connections: " + connections);
    }

    private static void visualizeNetwork() {
        String graph = socialNetwork.toGraphviz();
        System.out.println("GraphViz representation of the Social Network:\n" + graph);
    }

    private static void viewAllNodes() {
        List<String> nodes = socialNetwork.getAllNodes();
        System.out.println("Nodes in the network:");
        for (String node : nodes) {
            System.out.println(node);
        }
    }

    private static void findPath() {
        System.out.print("Enter start node: ");
        String start = scanner.nextLine();
        System.out.print("Enter end node: ");
        String end = scanner.nextLine();
        List<String> path = socialNetwork.findPath(start, end);
        System.out.println("Path: " + path);
    }

    private static void defaultNodes(SocialNetwork socialNetwork) {
        String[] names = {
            "Arjun", "Aishwarya", "Karthik", "Meera", "Rajesh", "Priya", "Suresh",
            "Lakshmi", "Ramesh", "Anjali", "Vinoth", "Sneha", "Manoj", "Deepa",
            "Ravi", "Nandini", "Vijay", "Kavitha", "Harish", "Sanjana", "Gokul",
            "Keerthi", "Arvind", "Divya", "Selvam", "Malar", "Surya", "Swathi",
            "Ganesh", "Aarthi"
        };

        for (String name : names) {
            socialNetwork.makeNode(name);
        }

        String[][] connections = {
            {"Arjun", "Aishwarya"}, {"Arjun", "Karthik"}, {"Arjun", "Meera"},
            {"Aishwarya", "Karthik"}, {"Aishwarya", "Rajesh"}, {"Karthik", "Meera"},
            {"Meera", "Rajesh"}, {"Rajesh", "Priya"}, {"Priya", "Suresh"},
            {"Suresh", "Lakshmi"}, {"Lakshmi", "Ramesh"}, {"Ramesh", "Anjali"},
            {"Anjali", "Vinoth"}, {"Vinoth", "Sneha"}, {"Sneha", "Manoj"},
            {"Manoj", "Deepa"}, {"Deepa", "Ravi"}, {"Ravi", "Nandini"},
            {"Nandini", "Vijay"}, {"Vijay", "Kavitha"}, {"Kavitha", "Harish"},
            {"Harish", "Sanjana"}, {"Sanjana", "Gokul"}, {"Gokul", "Keerthi"},
            {"Keerthi", "Arvind"}, {"Arvind", "Divya"}, {"Divya", "Selvam"},
            {"Selvam", "Malar"}, {"Malar", "Surya"}, {"Surya", "Swathi"},
            {"Swathi", "Ganesh"}, {"Ganesh", "Aarthi"}, {"Aarthi", "Arjun"},
            {"Harish", "Sneha"}, {"Sanjana", "Priya"}, {"Gokul", "Malar"},
            {"Keerthi", "Vijay"}, {"Divya", "Ramesh"}, {"Selvam", "Deepa"}
        };

        for (String[] edge : connections) {
            socialNetwork.edgeConnect(edge[0], edge[1]);
        }
    }
}
