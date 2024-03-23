public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }

    public String preOrder() {
        return preOrder(this);
    }

    private String preOrder(TreeNode curr) {
        if (curr == null) {
            return null;
        } else {
            return preOrder(curr.left) + " " + curr.val + " " + preOrder(curr.right);
        }
    }
}