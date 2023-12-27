import java.util.*;

class TreeNode<T> {
    T data;
    List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}