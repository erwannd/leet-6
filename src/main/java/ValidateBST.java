import java.util.HashSet;

public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, new HashSet<>(), new HashSet<>());
    }

    private boolean isValidBST(TreeNode root, HashSet<Integer> leftDescendantOf, HashSet<Integer> rightDescendantOf) {
        if (root == null) {
            return true;
        }

        if (root.left != null && root.left.val >= root.val) {
            return false;
        }

        if (root.right != null && root.right.val <= root.val) {
            return false;
        }

        if (validLeftDescendant(root.val, leftDescendantOf) && validRightDescendant(root.val, rightDescendantOf)) {
            HashSet<Integer> updatedLeftDescendantOf = new HashSet<>(leftDescendantOf);
            updatedLeftDescendantOf.add(root.val);

            HashSet<Integer> updatedRightDescendantOf = new HashSet<>(rightDescendantOf);
            updatedRightDescendantOf.add(root.val);
            boolean isLeftSubtreeValid = isValidBST(root.left, updatedLeftDescendantOf, rightDescendantOf);
            boolean isRightSubtreeValid = isValidBST(root.right, leftDescendantOf, updatedRightDescendantOf);
            return isLeftSubtreeValid && isRightSubtreeValid;
        }
        return false;
    }

    private boolean validLeftDescendant(int val, HashSet<Integer> leftDescendantOf) {
        for (int parentVal : leftDescendantOf) {
            if (val >= parentVal) {
                return false;
            }
        }
        return true;
    }

    private boolean validRightDescendant(int val, HashSet<Integer> rightDescendantOf) {
        for (int parentVal : rightDescendantOf) {
            if (val <= parentVal) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generate a tree that looks like:
     *      2
     *    /  \
     *   1    3
     * Expected outcome: valid (true)
     */
    public static TreeNode generateTree() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        return root;
    }

    /**
     * Generate a tree that looks like:
     *      5
     *    /  \
     *   1    4
     *       / \
     *      3   6
     * Expected outcome: valid
     */
    public static TreeNode generateTree2() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        return root;
    }

    /**
     * Generate a tree that looks like:
     *      2
     *    /  \
     *   2    2
     * Expected outcome: invalid (false)
     */
    public static TreeNode generateTree3() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        return root;
    }

    /**
     * Generate a tree that looks like:
     *      1
     *    /
     *   1
     * Expected outcome: invalid
     */
    public static TreeNode generateTree4() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        return root;
    }

    /**
     * Generate a tree that looks like:
     *      5
     *    /  \
     *   4    6
     *       / \
     *      3   7
     * Expected outcome: invalid
     */
    public static TreeNode generateTree5() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(7);
        return root;
    }

    /**
     * Generate a tree that looks like:
     *              3
     *            /  \
     *           1    5
     *         / \   / \
     *        0   2 4   6
     * Expected outcome: valid
     */
    public static TreeNode generateTree6() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(6);
        return root;
    }

    public static void main(String[] args) {
        ValidateBST bst = new ValidateBST();
        TreeNode tree = generateTree6();
        boolean isValid = bst.isValidBST(tree);
        System.out.println(isValid);

//        HashSet<Integer> leftChildOf = new HashSet<>();
//        leftChildOf.add(6);
//        HashSet<Integer> rightChildOf = new HashSet<>();
//        rightChildOf.add(5);
//        boolean validDescendant = bst.validRightDescendant(3, rightChildOf) && bst.validLeftDescendant(3, leftChildOf);
//        System.out.println(validDescendant);
    }
}
