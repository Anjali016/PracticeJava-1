package data.structures.binarytrees;

import utilities.TreeNode;

class TreeExamples {

    /**
     * 1.
     * Problem: Tree traversals.
     */
    void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    /**
     * 2.
     * Problem: Size of a tree
     */
    int size(TreeNode node) {
        if (node == null) return 0;
        else return 1 + size(node.left) + size(node.right);
    }

    /**
     * 3.
     * Problem: Determine if Two Trees are Identical
     */
    boolean isIdentical(TreeNode root1, TreeNode root2) {
        return root1 == null && root2 == null ||
                root1 != null && root2 != null && root1.data == root2.data &&
                isIdentical(root1.left,root2.left) &&
                isIdentical(root1.right,root2.right);
    }

     /**
     * 4.
     * Problem: Maximum Depth or Height of a Tree
     */
    int height(TreeNode node) {
        if (node == null) return 0;
        else return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * 5.
     * Problem: Delete a tree.
     *
     * Solution: Do a post order traversal and make the node as null.
     */

     /**
     * 6.
     * Problem: Convert a Binary Tree into its Mirror Tree
     */
    void mirror(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            mirror(root.left);
            mirror(root.right);
        }
    }

    /**
     * 7.
     * Problem: If you are given two traversal sequences, can you construct the binary tree?
     *
     * Solution: It depends on what traversals are given. If one of the traversal methods is Inorder then
     * the tree can be constructed, otherwise not.
     */

    /**
     * 8.
     * Problem: Print out all of its root-to-leaf paths one per line.
     */
    void rootToLeafPath(String path, TreeNode root) {
        if (root != null) {
            if (isLeaf(root)) System.out.println(path + root.data);
            else {
                rootToLeafPath(path + root.data + "->", root.left);
                rootToLeafPath(path + root.data + "->", root.right);
            }
        }
    }

    /**
     * 9. Lowest Common Ancestor in a Binary Tree.
     */
    TreeNode LCA(TreeNode root, int k1, int k2) {
        if (root == null || root.data == k1 || root.data == k2) return root;
        else {
            TreeNode left = LCA(root.left,k1,k2);
            TreeNode right = LCA(root.right,k1,k2);
            if (left != null && right != null) return root;
            else return left != null ? left : right;
        }
    }

    /**
     * 10.
     * Problem: The Great Tree-List Recursion Problem.
     */
    TreeNode head = null, prev =null;
    void treeToList(TreeNode root) {
        if (root != null) {
            treeToList(root.left);
            if (head == null) head = root;
            else {
                prev.right = root;
                root.left = prev;
            }
            prev = root;
            treeToList(root.right);
        }
    }

    /**
     * 11. Problem:
     * Level Order Tree Traversal
     */
    void levelOrder(TreeNode root) {
        int height = height(root);
        for(int i = 1; i <= height; i++) {
            level(root,i);
            System.out.println("");
        }
    }

    private void level(TreeNode root, int level) {
        if (root != null) {
            if (level <= 1) System.out.print(root.data + " ");
            else {
                level(root.left, level - 1);
                level(root.right, level - 1);
            }
        }
    }

    /**
     * 12.
     * Problem: Count leaf nodes in a binary tree
     */
    int countLeaves(TreeNode root) {
        if (root == null) return 0;
        else if (isLeaf(root)) return 1;
        else return countLeaves(root.right) + countLeaves(root.left);
    }

    private boolean isLeaf(TreeNode node) { return node.left == null && node.right == null; }

    /**
     * 13.
     * Problem: Level order traversal in spiral form
     */
    void levelOrderSpiral(TreeNode root) {
        int height = height(root);
        for(int i = 1; i <= height; i++) {
            levelSpiral(root, i, i % 2 == 0);
            System.out.println("");
        }
    }

    private void levelSpiral(TreeNode root, int level, boolean odd) {
        if (root != null) {
            if (level < 2) System.out.print(root.data + " ");
            else if (odd){
                level(root.right, level - 1);
                level(root.left, level - 1);
            }
            else {
                level(root.left, level - 1);
                level(root.right, level - 1);
            }
        }
    }

    /**
     * 14.
     * Problem: Check for Children Sum Property in a Binary Tree.
     */
    boolean hasChildSum(TreeNode root) {
        return root == null || isLeaf(root) ||
                root.data == getChildSum(root) &&
                        hasChildSum(root.left) &&
                        hasChildSum(root.right);
    }

    int getChildSum(TreeNode node) {
        int sum = 0;
        if (node.left != null) sum += node.left.data;
        if (node.right != null) sum += node.right.data;
        return sum;
    }

    /**
     * 15.
     * Problem: Convert an arbitrary Binary Tree to a tree that holds Children Sum Property
     */
    TreeNode convertToChildSum(TreeNode root) {
        if (root == null || isLeaf(root)) return root;
        else {
            convertToChildSum(root.left);
            convertToChildSum(root.right);
            root.data = getChildSum(root);
            return root;
        }
    }

    /**
     * 16.
     * Problem: Diameter of a Binary Tree
     */
    int diameter(TreeNode root) {
        if (root == null) return 0;
        else {
            return Math.max(height(root.left) + 1 + height(root.right),
                    Math.max(diameter(root.left), diameter(root.right)));
        }
    }

    /**
     * 17.
     * Problem: How to determine if a binary tree is height-balanced?
     */
    boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            return Math.abs(leftHeight - rightHeight) <=1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    /**
     * Short circuited solution
     */
    boolean isBalancedS(TreeNode root) {
        if (root == null) return true;
        else {
            if (!isBalancedS(root.left)) return false;
            else if (!isBalancedS(root.right)) return false;
            else {
                int leftHeight = height(root.left);
                int rightHeight = height(root.right);
                return Math.abs(leftHeight - rightHeight) <=1;
            }
        }
    }

    /**
     * Optimized solution
     */
    boolean isBal = true;
    int isBalancedOpt(TreeNode root) {
        if (root == null) return 0;
        else {
            int leftHeight = isBalancedOpt(root.left);
            int rightHeight = isBalancedOpt(root.right);
            int difference = Math.abs(leftHeight - rightHeight);
            if (difference > 1) isBal = false;
            return 1 + Math.max(leftHeight,rightHeight);
        }
    }

    /**
     * 18.
     * Problem: Inorder Tree Traversal without Recursion
     *
     * Solution:
     * 1) Create an empty stack S.
     * 2) Initialize current node as root
     * 3) Push the current node to S and set current = current->left until current is NULL
     * 4) If current is NULL and stack is not empty then
     *      a) Pop the top item from stack.
     *      b) Print the popped item, set current = popped_item->right
     *      c) Go to step 3.
     * 5) If current is NULL and stack is empty then we are done.
     */

    /**
     * 19.
     * Problem: Inorder Tree Traversal without recursion and without stack!
     *
     * Solution: Use Morris Traversal.
     * 1. Initialize current as root
     * 2. While current is not NULL
     * If current does not have left child
     *      a) Print current’s data
     *      b) Go to the right, i.e., current = current->right
     * Else
     *      a) Make current as right child of the rightmost node in current's left subtree
     *      b) Go to this left child, i.e., current = current->left
     */

    /**
     * 20.
     * Problem: Root to leaf path sum equal to a given number
     */
    boolean existsPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        else if (isLeaf(root)) return sum == root.data;
        else return existsPathSum(root.left, sum - root.data) || existsPathSum(root.right, sum - root.data);
    }

    /**
     * 69. Check if all leaves are at same level.
     */

   /**
     * 112. Expression Tree.
     * Expression tree is a binary tree in which each internal node corresponds to operator and each leaf node
     * corresponds to operand.
     *
     * Solution:
     * If  t is not null then
     *      If t.value is operand then
     *          Return  t.value
     *      A = solve(t.left)
     *      B = solve(t.right)
     *
     *      calculate applies operator 't.value' on A and B, and returns value.
     *      Return calculate(A, B, t.value)
     */

    /**
     * 114. Change a Binary Tree so that every node stores sum of all nodes in left subtree.
     */
    int updateTree(TreeNode root)
    {
        if (root == null) return 0;
        if (isLeaf(root)) return root.data;

        int leftSum  = updateTree(root.left);
        int rightSum = updateTree(root.right);

        root.data += leftSum;

        return root.data + rightSum;
    }

    /**
     * 115. Iterative Search for a key ‘x’ in Binary Tree
     *
     * Solution: Use any traversal.
     */

    /**
     * 116. Find maximum (or minimum) in Binary Tree.
     */
    int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        else return Math.max(Math.max(root.data,findMax(root.left)),findMax(root.right));
    }

}
