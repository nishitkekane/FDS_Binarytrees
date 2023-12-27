import java.util.*;

class Tree<T> {
    private TreeNode<T> root;

    public Tree(T rootData) {
        root = new TreeNode<>(rootData);
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void addChild(TreeNode<T> parent, T childData) {
        TreeNode<T> child = new TreeNode<>(childData);
        parent.children.add(child);
    }

    public List<TreeNode<T>> getSiblings(TreeNode<T> node) {
        if (node == root) {
            return null;
        }

        TreeNode<T> parent = findParent(root, node);
        if (parent != null) {
            return parent.children;
        }

        return null;
    }

    public List<TreeNode<T>> getLeaves(TreeNode<T> node) {
        List<TreeNode<T>> leaves = new ArrayList<>();
        findLeaves(node, leaves);
        return leaves;
    }

    public List<TreeNode<T>> getInternalNodes(TreeNode<T> node) {
        List<TreeNode<T>> internalNodes = new ArrayList<>();
        findInternalNodes(node, internalNodes);
        return internalNodes;
    }

    public List<TreeNode<T>> getPath(TreeNode<T> node) {
        List<TreeNode<T>> path = new ArrayList<>();
        findPath(root, node, path);
        return path;
    }

    public int getDepth(TreeNode<T> node) {
        return findDepth(root, node, 0);
    }

    public int getHeight() {
        return findHeight(root);
    }

    public Tree<T> getSubtree(TreeNode<T> node) {
        TreeNode<T> subtreeRoot = findNode(root, node);
        if (subtreeRoot != null) {
            Tree<T> subtree = new Tree<>(subtreeRoot.data);
            copySubtree(subtreeRoot, subtree.getRoot());
            return subtree;
        }
        return null;
    }

    private TreeNode<T> findNode(TreeNode<T> current, TreeNode<T> target) {
        if (current == target) {
            return current;
        }
        for (TreeNode<T> child : current.children) {
            TreeNode<T> found = findNode(child, target);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private TreeNode<T> findParent(TreeNode<T> current, TreeNode<T> target) {
        for (TreeNode<T> child : current.children) {
            if (child == target) {
                return current;
            }
            TreeNode<T> parent = findParent(child, target);
            if (parent != null) {
                return parent;
            }
        }
        return null;
    }

    private void findLeaves(TreeNode<T> current, List<TreeNode<T>> leaves) {
        if (current.children.isEmpty()) {
            leaves.add(current);
        } else {
            for (TreeNode<T> child : current.children) {
                findLeaves(child, leaves);
            }
        }
    }

    private void findInternalNodes(TreeNode<T> current, List<TreeNode<T>> internalNodes) {
        if (!current.children.isEmpty()) {
            internalNodes.add(current);
            for (TreeNode<T> child : current.children) {
                findInternalNodes(child, internalNodes);
            }
        }
    }

    private void findPath(TreeNode<T> current, TreeNode<T> target, List<TreeNode<T>> path) {
        if (current == target) {
            path.add(current);
            return;
        }

        for (TreeNode<T> child : current.children) {
            if (findNode(child, target) != null) {
                path.add(current);
                findPath(child, target, path);
            }
        }
    }

    private int findDepth(TreeNode<T> current, TreeNode<T> target, int depth) {
        if (current == target) {
            return depth;
        }
        for (TreeNode<T> child : current.children) {
            int result = findDepth(child, target, depth + 1);
            if (result >= 0) {
                return result;
            }
        }
        return -1;
    }

    private int findHeight(TreeNode<T> current) {
        if (current.children.isEmpty()) {
            return 0;
        } else {
            int maxHeight = 0;
            for (TreeNode<T> child : current.children) {
                int childHeight = findHeight(child);
                maxHeight = Math.max(maxHeight, childHeight);
            }
            return maxHeight + 1;
        }
    }

    private void copySubtree(TreeNode<T> current, TreeNode<T> copy) {
        for (TreeNode<T> child : current.children) {
            TreeNode<T> childCopy = new TreeNode<>(child.data);
            copy.children.add(childCopy);
            copySubtree(child, childCopy);
        }
    }
}

