import java.util.*;

class SocialNetwork {
    private Map<String, List<String>> connections;

    public SocialNetwork() {
        this.connections = new HashMap<>();
    }

    public void makeNode(String name) {
        connections.putIfAbsent(name, new ArrayList<>());
    }

    public void edgeConnect(String name1, String name2) {
        if (!connections.containsKey(name1) || !connections.containsKey(name2)) {
            System.out.println("One or both users not found.");
            return;
        }
        connections.get(name1).add(name2);
        connections.get(name2).add(name1);
        System.out.println(name1 + " is now connected to " + name2);
    }

    public void deleteEdge(String name1, String name2) {
        if (!connections.containsKey(name1) || !connections.containsKey(name2)) {
            System.out.println("One or both users not found.");
            return;
        }
        connections.get(name1).remove(name2);
        connections.get(name2).remove(name1);
        System.out.println("Edge removed between " + name1 + " and " + name2);
    }

    public void deleteNode(String name) {
        if (!connections.containsKey(name)) {
            System.out.println("User not found: " + name);
            return;
        }
        for (String connection : new ArrayList<>(connections.get(name))) {
            connections.get(connection).remove(name);
        }
        connections.remove(name);
        System.out.println(name + " has been removed.");
    }

    public List<String> getConnections(String user) {
        return connections.getOrDefault(user, new ArrayList<>());
    }

    public List<String> getAllNodes() {
        return new ArrayList<>(connections.keySet());
    }

    public String toGraphviz() {
        StringBuilder sb = new StringBuilder();
        sb.append("graph G {\n");
        for (String node : connections.keySet()) {
            for (String neighbor : connections.get(node)) {
                if (node.compareTo(neighbor) < 0) { // Prevent duplicate edges
                    sb.append("  ").append(node).append(" -- ").append(neighbor).append(";\n");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public List<String> findPath(String start, String end) {
        if (!connections.containsKey(start) || !connections.containsKey(end)) {
            return Collections.emptyList();
        }
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        boolean found = dfs(start, end, visited, path);
        return found ? path : Collections.emptyList();
    }

    private boolean dfs(String current, String end, Set<String> visited, List<String> path) {
        visited.add(current);
        path.add(current);

        if (current.equals(end)) {
            return true;
        }

        for (String neighbor : connections.get(current)) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, end, visited, path)) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}
