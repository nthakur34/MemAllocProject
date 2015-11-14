package manage;
/**
 * 600.226, Fall 2015
 * Starter code for AVLtree implementation
 */
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * AVL Tree - based on Weiss.
 * @param <T> the base type of data in a node
 *
 */
public class AVLtree<T extends Comparable<? super T>> {

    /**
     * Inner node class.  Do not make this static because you want the T to be
     * the same T as in the BST header.
     */
    public class BNode {

        /** Variable data of type T. */
        protected T data;
        /** Variable left of type BNode. */
        protected BNode left;
        /** Variable right of type BNode. */
        protected BNode right;
        /** Variable height of the node. */
        private int height;

        /**
         * Constructor for BNode.
         * @param val to insert the given node.
         */
        public BNode(T val) {
            this.data = val;
            this.height = 0;
        }

        /**
         * Returns whether node is a leaf or not.
         * @return true is node is leaf, false if not
         */
        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }
        
        /**
         * Returns whether node has two children or not.
         * @return true is node has two children, false if not
         */
        public boolean hasTwoChildren() {
            return (this.left != null && this.right != null);
        }
    }

    /** The root of the tree. */
    private BNode root;
    /** The size of the tree. */
    private int size;


    /**
     * Constructs a Binary Search Tree.
     */
    public AVLtree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Find out how many elements are in the Tree.
     * @return the number
     */
    public int size() {
        return this.size;
    }

    /**
     * See if the Tree is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Get the value of the root.
     * @return value of the root
     */
    public T root() {
        if (this.root == null) {
            return null;
        }
        return this.root.data;
    }

    /**
     * Search for an item in the tree.
     * @param val the item to search for
     * @return true if found, false otherwise
     */
    public boolean contains(T val) {
        return this.contains(val, this.root) != null;
    }

    /**
     * Checks if a tree contains a certain value.
     * @param val the value you're looking for
     * @param curr the root of the tree you're searching
     * @return the node that contains that value
     */
    public BNode contains(T val, BNode curr) {
        if (curr == null || this.isEmpty()) {
            return null;
        }
        if (val.equals(curr.data)) {
            return curr;
        }
        if (val.compareTo(curr.data) < 0) {
            return this.contains(val, curr.left);
        }
        return this.contains(val, curr.right);
    }
    
//    /**
//     * Method to find the smallest node greater than or 
//     * equal to the value
//     * @param val the value we're looking for
//     * @param curr the subtree we're searching through
//     * @return the node that is the smallest node greater than
//     *         or equal to the value
//     */
//    public BNode getBestFit(T val, BNode curr) {
//        if (curr == null || this.isEmpty()) {
//            return null;
//        }
//        if (val.equals(curr.data)) {
//            return curr;
//        } else if (val.compareTo(curr.data) < 0) {
//            // ^if val is less than curr.data, check left child
//            if (curr.left == null) {
//                return curr;
//            } else if (val.compareTo(curr.left.data) < 0
//                    || val.equals(curr.left.data)) {
//                return this.getBestFit(val, curr.left);
//            } else if (val.compareTo(curr.left.data) > 0 && curr.left.right == null) {
//                return curr;
//            } else {
//                return this.getBestFit(val, curr.left);
//            }
//        } else {
//            // ^if val is greater than curr.data, check right child
//            if (curr.right == null) {
//                return curr;
//            } else if (val.compareTo(curr.right.data) == 0) {
//                return this.getBestFit(val, curr.right);
//            } else if (val.compareTo(curr.right.data) < 0) {
//                return this.getBestFit(val, curr.right);
//            } else if (val.compareTo(curr.right.data) > 0){ 
//                return this.getBestFit(val, curr.right);
//            }
//        }
//    }
    
    /**
     * Gets the node of best fit, the smallest node greater than
     * or equal to the value.
     * @param val the value we're looking for
     * @return the node
     */
    public T getBestFit(T val) {
        if (this.root == null || val.compareTo(this.getMaxVal()) > 0) {
            return null;
        }
        return this.findBestFit(val, this.root).data;
    }
    
    /**
     * Helper method to find the smallest node greater than or 
     * equal to the value
     * @param val the value we're looking for
     * @param curr the subtree we're searching through
     * @return the node that is the smallest node greater than
     *         or equal to the value
     */
    private BNode findBestFit(T val, BNode curr) {
//        System.out.println(val.size());
        System.out.println("INSIDE FINDBESTFIT");
        BNode temp;
        if (curr == null || this.isEmpty()) {
            return null;
        }
        if (val.compareTo(curr.data) == 0) {
            System.out.println("THEY WERE EQUAL");
            return curr;
        } else if (val.compareTo(curr.data) < 0) {
            // ^if val is less than curr.data, check left child
            temp = this.findBestFit(val, curr.left);
            if (temp == null && val.compareTo(curr.data) < 0) {
                return curr;
            }
            return temp;
        } else {
            // ^if val is greater than curr.data, check right child
            temp = this.findBestFit(val, curr.right);
            if (temp == null && val.compareTo(curr.data) < 0) {
                return curr;
            }
            return temp;
        }
    }
    
    /**
     * Gets the maximum value in the tree.
     * @return the maximum value in the tree
     */
    public T getMaxVal() {
        if (this.root != null) {
            return this.findMax(this.root).data;
        }
        return null;
    }

    /**
     * Add an item to the Tree.
     * @param val the item to add
     * @return true if added, false if val is null
     */
    public boolean add(T val) {
//        System.out.println("ADDING : " + val);
        if (val != null) {
            this.root = this.insert(val, this.root);
//            System.out.println("after inserting: " + val);
            this.size++;
            //how you can check whether the root is balanced:
            //System.out.println(Math.abs(balanceFactor(this.root)));
//            System.out.println("root.data: " + root.data);
//            System.out.println("root.height: " + this.root.height);
            return true;
        }
        return false;
    }

    /**
     * Helper insert method.
     * @param val the value to insert
     * @param curr the root of the tree
     * @return the node that is inserted
     */
    private BNode insert(T val, BNode curr) {
//        System.out.println("INSIDE INSERT");
//        if (curr != null) {
//            System.out.println("curr.data: " + curr.data);
//            System.out.println("curr.height: " + curr.height);
//        }
        
        BNode temp = curr;
        if (temp == null) { // leaf, make new node
//            System.out.println("adding leaf: " + val);
            return new BNode(val);
        }
        if (val.compareTo(temp.data) < 0) {
//            System.out.println("MOVE LEFT");
            temp.left = this.insert(val, temp.left);
//            System.out.println("temp.data: " + temp.data);
//            System.out.println("temp.left.data: " + temp.left.data);
            temp = this.balance(temp);
//            System.out.println("AFTER BALANCING");
//            System.out.println("temp.data: " + temp.data);
//            System.out.println("root.height: " + this.root.height);

        } else {  // val >= temp
//            System.out.println("MOVE RIGHT");
            temp.right = this.insert(val, temp.right);
            temp = this.balance(temp);
//            System.out.println("AFTER BALANCING");
//            System.out.println("temp.data: " + temp.data);
//            System.out.println("root.data: " + root.data);
//            System.out.println("root.height: " + this.root.height);

        }
        return temp;
    }

    /**
     * Remove an item from the Tree.
     * @param val the item to remove
     * @return true if removed, false if not found
     */
    public boolean remove(T val) {
        if (this.contains(val)) {
            this.root = this.delete(this.root, val);
            this.size--;
            //how you can check whether the root was balanced:
            //System.out.println(Math.abs(balanceFactor(this.root)))
            return true;
        }
        return false;
    }


    /**
     * Helper delete method. - This does the real work - IMPLEMENT!
     * @param value the value to delete
     * @param curr the root of the subtree to look in.
     * @return the new subtree after rebalancing
     */
    private BNode delete(BNode curr, T value) {
//        System.out.println("Deleting: " + value);
//        if (curr != null) {
//            System.out.println("Comparing to: " + curr.data);
//
//        }
        System.out.println("right fucking here");
        BNode temp = null;
        // if value not equal to node value, keep going down
        if (curr.data.compareTo(value) != 0) {
            // if value is less than curr, move to left child
            if (curr.left != null && curr.left.data.compareTo(value) >= 0) {
                System.out.println("rm move to left child");
                curr.left = this.delete(curr.left, value);
                curr = this.balance(curr);
            // if value is greater than curr, move to right child
            } else if (curr.right != null && curr.right.data.compareTo(
                    value) <= 0) {
                System.out.println("rm move to right child");
                curr.right = this.delete(curr.right, value);
                curr = this.balance(curr);
            }
            System.out.println("HEUEHUEHEUHEUEHUE");
        } else {
            System.out.println("FOUND");
            System.out.println("height: " + curr.height);
            //if value is a leaf, remove it
            if (curr.isLeaf()) {
                System.out.println("IS LEAF");
                curr = null;
                return curr;
                //check if no grandchildren
            } else if (curr.height == 1) {
                System.out.println("ONLY HAS CHILDREN");
                // if there's only a left child
                if (curr.right == null) {
                    curr.data = curr.left.data;
                    curr.left = null;
                    return curr;
                // if there exists a right child
                } else {
                    curr.data = curr.right.data;
                    curr.right = null;
                    return curr;
                }
               //if grandchildren
            } else if (curr.height > 1) {
                System.out.println("HAS GRANDCHILDREN");
                System.out.println("the min value: " + this.findMin(curr.right).data);
                curr.data = this.findMin(curr.right).data;
//                temp = curr;
//                temp.data = this.findMin(curr.right).data;
//                temp.right = curr.right;
//                temp.left = curr.left;
//                curr = temp;
                System.out.println("about to delete: " + this.findMin(curr.right).data);
                System.out.println("from subtree: " + curr.right.data);
                curr.right = this.delete(curr.right, this.findMin(curr.right).data);
                curr = this.balance(curr);
                return curr;
            }
            
            return curr;
        }
//        if (curr != null) {
//            System.out.println("---" + curr.data);
//        } else {
//            System.out.println("***");
//        }
        System.out.println("NOWHERE TO BE FUCKING FOUND");
        return curr;
    }


    /**
     * Performs balancing of the nodes if necessary, adjusting heights
     * as necessary.  IMPLEMENT THIS!
     * @param curr the root of the subtree to balance
     * @return the root node of the newly balanced subtree
     */
    private BNode balance(BNode curr) {
        if (curr == null) {
            return curr;
        }
        // if tree is balanced
        if (Math.abs(this.balanceFactor(curr)) <= 1) {
            this.updateHeights(curr);
            return curr;
        }
        if (curr.isLeaf()) {
            this.updateHeights(curr);
            return curr;
        } else if (!curr.hasTwoChildren()) {
            curr = this.oneChildBalance(curr);
        } else {
            curr = this.twoChildBalance(curr);
        }
        
        return curr;
        
    }
    
    /**
     * Helper method to help update heights.
     * @param curr then node you want to update
     */
    private void updateHeights(BNode curr) {
     // updating the heights
        if (curr.isLeaf()) {
            curr.height = 0;
        } else {
            if (curr.hasTwoChildren()) {
//                curr.height = this.max(this.height(k1.left), k2.height) + 1;
                curr.height = this.max(this.height(curr.left), 
                        this.height(curr.right)) + 1;

            } else if (curr.left != null) {
                System.out.println("IN HEREERERERER");
                curr.height = curr.left.height + 1;
            } else if (curr.right != null) {
                curr.height = curr.right.height + 1;
            }
        }
    }
    
    /**
     * Balance the node with one child.
     * @param curr the subtree to be balanced
     * @return the root of the balanced subtree
     */
    private BNode oneChildBalance(BNode curr) {
        System.out.println("INSIDE ONE CHILD BALANCE");
        int factor = this.balanceFactor(curr);
        if (factor > 1) {
            // subtree too long on left
            if (curr.left.hasTwoChildren()) {
                curr = this.rotateWithLeftChild(curr);
            } else if (curr.left.right != null) {
                // left-right too long
                curr = this.doubleWithLeftChild(curr);
            } else {
                // left-left too long
                curr = this.rotateWithLeftChild(curr);
            }
        } else if (factor < -1) {
            // subtree too long on right
            if (curr.right.hasTwoChildren()) {
                curr = this.rotateWithRightChild(curr);
            } else if (curr.right.left != null) {
                // right-left too long
                curr = this.doubleWithRightChild(curr);
            } else {
                System.out.println("RIGHT RIGHT TOO LONG");
                // right-right too long
                curr = this.rotateWithRightChild(curr);
            }
        }
        return curr;
    }
    
    /**
     * Balance the node with two children.
     * @param curr the subtree to be balanced
     * @return the root of the balanced subtree
     */
    private BNode twoChildBalance(BNode curr) {
        System.out.println("INSIDE TWOCHILDBALANCE");
        int factor = this.balanceFactor(curr);
        if (factor > 1) {
            // subtree too long on left
            if (curr.left.left.isLeaf()) {
                // left-right too long
                curr = this.doubleWithLeftChild(curr);
            } else {
                // left-left too long
                curr = this.rotateWithLeftChild(curr);
            }
        } else if (factor < -1) {
            // subtree too long on right
            if (curr.right.right.isLeaf()) {
                // right-left too long
                curr = this.doubleWithRightChild(curr);
            } else {
                System.out.println("RIGHT RIGHT TOO LONG");
                // right-right too long
                curr = this.rotateWithRightChild(curr);
            }
        }
        return curr;
    }
    
//    private BNode getToLowest(BNode curr) {
//        int factor = this.balanceFactor(curr);
//        if (factor > 1) {
//            // subtree is too heavy on left
//            if (this.balanceFactor(curr.left) > 1
//                    || this.balanceFactor(curr.left) < 1) {
//                this.getToLowest(curr.left);
//            }
//        } else if (factor < -1) {
//            // subtree is too heavy on right
//            if (this.balanceFactor(curr.right) > 1
//                    || this.balanceFactor(curr.right) < 1) {
//                this.getToLowest(curr.right);
//            }
//        }
//        return curr;
//    }

    /**
     * Checks balance of nodes.
     * @param b node to check balance at
     * @return integer that is balance factor
     */
    private int balanceFactor(BNode b) {
        if (b == null) {
            return -1;
        }

        if (b.isLeaf()) {
            return 0;
        }

        return this.height(b.left) - this.height(b.right);
    }

    /**
     * Search from curr (as root of subtree) and find minimum value.
     * @param curr the root of the tree
     * @return the min
     */
    private BNode findMin(BNode curr) {
        BNode temp = curr;
        if (temp == null) {
            return temp;
        }
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }
    
    /**
     * Search from curr (as root of subtree) and find maximum value.
     * @param curr the root of the tree
     * @return the max
     */
    private BNode findMax(BNode curr) {
        BNode temp = curr;
        if (temp == null) {
            return temp;
        }
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    /**
     * Return the height of node t, or -1, if null.
     * @param t the node to find the height of
     * @return int height to be returned
     */
    private int height(BNode t) {
        if (t == null) {
            return -1;
        }
        return t.height;
    }


    /**
     * Return maximum of lhs and rhs.
     * @param lhs height of lhs
     * @param rhs height of rhs
     * @return the int that's larger
     */
    private static int max(int lhs, int rhs) {
        if (lhs > rhs) {
            return lhs;
        }
        return rhs;
    }

    /**
     * Rotate binary tree node with left child.
     * Update heights, then return new root.
     * @param k2 node to rotate
     * @return updated node
     */
    private BNode rotateWithLeftChild(BNode k2) {
        System.out.println("INSIDE ROTATEWITHLEFTCHILD");
        if (k2 == null) {
            return null;
        }
        BNode k1 = k2.left;
        if (k1 != null) {
            k2.left = k1.right;
            k1.right = k2;
            k2.height = this.max(this.height(k2.left),
                this.height(k2.right)) + 1;
            k1.height = this.max(this.height(k1.left), k2.height) + 1;
        }
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * Update heights, then return new root.
     * @param k1 node to rotate
     * @return updated node
     */
    private BNode rotateWithRightChild(BNode k1) {
        System.out.println("INSIDE ROTATEWITHRIGHTCHILD");
        if (k1 == null) {
            return null;
        }
        BNode k2 = k1.right;
        System.out.println("to be the root: " + k2.data);
        if (k2 != null) {
            k1.right = k2.left;
            k2.left = k1;
            k1.height = max(this.height(k1.left), this.height(k1.right)) + 1;
            k2.height = max(this.height(k2.right), k1.height) + 1;
        }
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * Update heights, then return new root.
     * @param k3 node to rotate
     * @return update node
     */
    private BNode doubleWithLeftChild(BNode k3) {
        System.out.println("INSIDE DOUBLEWITHLEFTCHILD");
        if (k3 != null) {
            k3.left = this.rotateWithRightChild(k3.left);
            return this.rotateWithLeftChild(k3);
        }
        return k3;
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * Update heights, then return new root.
     * @param k1 node to rotate
     * @return updated node
     */
    private BNode doubleWithRightChild(BNode k1) {
        if (k1 != null) {
            k1.right = this.rotateWithLeftChild(k1.right);
            return this.rotateWithRightChild(k1);
        }
        return k1;
    }

    /**
     * String representation of the Tree with elements in order.
     * @return a string containing the Tree contents in the format "[1, 5, 6]".
     */
    public String toString() {
        return this.inOrder().toString();
    }

    /**
     * Inorder traversal.
     * @return a Collection of the Tree elements in order
     */
    public Iterable<T> inOrder() {
        return this.inOrder(this.root);
    }

    /**
     * Preorder traversal.
     * @return a Collection of the Tree elements in preorder
     */
    public Iterable<T> preOrder() {
        return this.preOrder(this.root);
    }

    /**
     * Postorder traversal.
     * @return a Collection of the Tree elements in postorder
     */
    public Iterable<T> postOrder() {
        return this.postOrder(this.root);
    }

    /**
     * Generates an in-order list of items.
     * @param curr the root of the tree
     * @return collection of items in order
     */
    private Collection<T> inOrder(BNode curr) {
        LinkedList<T> iter = new LinkedList<T>();
        if (curr == null) {
            return iter;
        }
        iter.addAll(this.inOrder(curr.left));
        iter.addLast(curr.data);
        iter.addAll(this.inOrder(curr.right));
        return iter;
    }

    /**
     * Generates a pre-order list of items.
     * @param curr the root of the tree
     * @return collection of items in preorder
     */
    private Collection<T> preOrder(BNode curr) {
        LinkedList<T> iter = new LinkedList<T>();
        if (curr == null) {
            return iter;
        }
        iter.addLast(curr.data);
        iter.addAll(this.preOrder(curr.left));
        iter.addAll(this.preOrder(curr.right));
        return iter;
    }

    /**
     * Generates a post-order list of items.
     * @param curr the root of the tree
     * @return collection of items in postorder
     */
    private Collection<T> postOrder(BNode curr) {
        LinkedList<T> iter = new LinkedList<T>();
        if (curr == null) {
            return iter;
        }
        iter.addAll(this.postOrder(curr.left));
        iter.addAll(this.postOrder(curr.right));
        iter.addLast(curr.data);
        return iter;
    }
    
  /*  public Iterable<BNode> inOrderNodes() {
        return this.inOrderNodes(this.root);
    }
    
    private Collection<BNode> inOrderNodes(BNode curr) {
        LinkedList<BNode> iter = new LinkedList<BNode>();
        if (curr.data == null) {
            return iter;
        }
        iter.addAll(this.inOrderNodes(curr.left));
        iter.addLast(curr);
        iter.addAll(this.inOrderNodes(curr.right));
        return iter;
    } */
}
