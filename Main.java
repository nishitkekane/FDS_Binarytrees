import java.util.*;

public class Main {
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("Root");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Add child node");
            System.out.println("2. Display tree");
            System.out.println("3. Find siblings of a node");
            System.out.println("4. List leaves of the tree");
            System.out.println("5. List internal nodes of the tree");
            System.out.println("6. List path for a given node");
            System.out.println("7. Depth of a node");
            System.out.println("8. Height of the tree");
            System.out.println("9. Subtree rooted at a given node");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter parent node data: ");
                    String parentData = scanner.next();
                    System.out.print("Enter child node data: ");
                    String childData = scanner.next();
                    TreeNode<String> parentNode = findNode(tree.getRoot(), parentData);
                    if (parentNode != null) {
                        tree.addChild(parentNode, childData);
                        System.out.println("Child node added.");
                    } else {
                        System.out.println("Parent node not found.");
                    }
                    break;

                case 2:
                    displayTree(tree.getRoot(), 0);
                    break;

                case 3:
                    System.out.print("Enter node data: ");
                    String nodeData = scanner.next();
                    TreeNode<String> node = findNode(tree.getRoot(), nodeData);
                    if (node != null) {
                        List<TreeNode<String>> siblings = tree.getSiblings(node);
                        System.out.println("Siblings of the node:");
                        for (TreeNode<String> sibling : siblings) {
                            System.out.println(sibling.data);
                        }
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;

                case 4:
                    List<TreeNode<String>> leaves = tree.getLeaves(tree.getRoot());
                    System.out.println("Leaves of the tree:");
                    for (TreeNode<String> leaf : leaves) {
                        System.out.println(leaf.data);
                    }
                    break;

                case 5:
                    List<TreeNode<String>> internalNodes = tree.getInternalNodes(tree.getRoot());
                    System.out.println("Internal nodes of the tree:");
                    for (TreeNode<String> internalNode : internalNodes) {
                        System.out.println(internalNode.data);
                    }
                    break;

                case 6:
                    System.out.print("Enter node data: ");
                    nodeData = scanner.next();
                    node = findNode(tree.getRoot(), nodeData);
                    if (node != null) {
                        List<TreeNode<String>> path = tree.getPath(node);
                        System.out.println("Path for the given node:");
                        for (TreeNode<String> pathNode : path) {
                            System.out.println(pathNode.data);
                        }
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;

                case 7:
                    System.out.print("Enter node data: ");
                    nodeData = scanner.next();
                    node = findNode(tree.getRoot(), nodeData);
                    if (node != null) {
                        int depth = tree.getDepth(node);
                        System.out.println("Depth of the node: " + depth);
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;

                case 8:
                    int height = tree.getHeight();
                    System.out.println("Height of the tree: " + height);
                    break;

                case 9:
                    System.out.print("Enter node data: ");
                    nodeData = scanner.next();
                    node = findNode(tree.getRoot(), nodeData);
                    if (node != null) {
                        Tree<String> subtree = tree.getSubtree(node);
                        System.out.println("Subtree rooted at the given node:");
                        displayTree(subtree.getRoot(), 0);
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;

                case 0:
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static TreeNode<String> findNode(TreeNode<String> current, String targetData) {
        if (current.data.equals(targetData)) {
            return current;
        }
        for (TreeNode<String> child : current.children) {
            TreeNode<String> found = findNode(child, targetData);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private static void displayTree(TreeNode<String> node, int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        System.out.println(indent.toString() + node.data);
        for (TreeNode<String> child : node.children) {
            displayTree(child, level + 1);
        }
    }
}