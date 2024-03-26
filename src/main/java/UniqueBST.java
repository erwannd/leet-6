import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
/**
 * PROMPT:
 * Given an integer n, return all the structurally unique BST's,
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 * For n=3, result:
 *  1       1           2          3            3
 *   \       \         / \        /            /
 *    3       2       1   3      1            2
 *   /         \                  \          /
 *  2           3                  2        1
 */
public class UniqueBST {
    /**
     * Checks if two trees are the same.
     */
    private boolean isSimilarTree(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 != null && n2 != null) {
            if (n1.val == n2.val) {
                return isSimilarTree(n1.left, n2.left) && isSimilarTree(n1.right, n2.right);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private TreeNode insertNode(TreeNode current, int val) {
        if (current == null) {
            return new TreeNode(val);
        }
        if (current.val <= val) {
            current.right = insertNode(current.right, val);
        } else {
            current.left = insertNode(current.left, val);
        }
        return current;
    }

    /**
     * Given a tree, return an independent copy of it.
     */
    private TreeNode cloneSubtree(TreeNode node) {
        if (node == null) return null;
        TreeNode clone = new TreeNode(node.val);
        clone.left = cloneSubtree(node.left);
        clone.right = cloneSubtree(node.right);
        return clone;
    }

    /**
     * Main driver method
     */
    public List<TreeNode> generateTrees(int max) {
        List<TreeNode> myList = new LinkedList<>();
        generateSubtrees(null, max, new HashSet<>(), myList);
        return myList;
    }

    /**
     * Helper method to generate subtrees
     */
    private void generateSubtrees(TreeNode node, int max, HashSet<Integer> usedValues, List<TreeNode> myList) {
        for (int i = 1; i <= max; i++) {
            if (!usedValues.contains(i)) {
                TreeNode newNode = cloneSubtree(node);
                newNode = insertNode(newNode, i);
                HashSet<Integer> updatedValues = new HashSet<>(usedValues);
                updatedValues.add(i);
                generateSubtrees(newNode, max, updatedValues, myList);

                if (updatedValues.size() != max) {
                    continue;
                }

                // check if this subtree is already included
                boolean isDuplicate = false;
                for (TreeNode n : myList) {
                    if (isSimilarTree(n, newNode)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    myList.add(newNode);
                }
            }
        }
    }


    public static void main(String[] args) {
        UniqueBST bst = new UniqueBST();

        List<TreeNode> myList = bst.generateTrees(3);
        int index = 0;
        for (TreeNode node : myList) {
            System.out.printf("result %d: %s\n" , index++, node.preOrder());
        }
    }

    /*
     * Given a list of integers i.e. [1, 2, 3]
     * Build a subtree with 1 as root:
     *  --> Build subtree with 2 as root
     *          --> Build subtree with 3 as root
     * --> Build subtree with 3 as root
     *          --> Build subtree with 2 as root
     *
     * Build a subtree with 2 as root:
     *  --> Build subtree with 3 as root
     *          --> Build subtree with 1 as root
     *  --> Build subtree with 1 as root
     *          --> Build subtree with 3 as root
     *
     * Build a subtree with 3 as root:
     *  --> Build subtree with 1 as root
     *          --> Build subtree with 2 as root
     *  --> Build subtree with 2 as root
     *          --> Build subtree with 1 as root
     */
}
