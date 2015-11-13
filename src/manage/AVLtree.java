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

    /**
     * Add an item to the Tree.
     * @param val the item to add
     * @return true if added, false if val is null
     */
    public boolean add(T val) {
        if (val != null) {
            this.root = this.insert(val, this.root);
            this.size++;
            //how you can check whether the root is balanced:
            //System.out.println(Math.abs(balanceFactor(this.root)));
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
        BNode temp = curr;
        if (temp == null) { // leaf, make new node
            return new BNode(val);
        }
        if (val.compareTo(temp.data) < 0) {
            temp.left = this.insert(val, temp.left);
            temp = this.balance(temp);

        } else {  // val >= temp
            temp.right = this.insert(val, temp.right);
            temp = this.balance(temp);

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
        BNode temp = null;
        //BNode toRemove = curr;
        // if value not equal to node value, keep going down
        if (curr.data.compareTo(value) != 0) {
            // if value is less than curr, move to left child
            if (curr.left != null && curr.left.data.compareTo(value) >= 0) {
                curr.left = this.delete(curr.left, value);
                curr = this.balance(curr);
            // if value is greater than curr, move to right child
            } else if (curr.right != null && curr.right.data.compareTo(
                    value) <= 0) {
                curr.right = this.delete(curr.right, value);
                curr = this.balance(curr);
            }
//            else {
//                return null; //could not find node
//            }
        } else {
            //if value is a leaf, remove it
            if (curr.isLeaf()) {
                curr = null;
                return curr;
                //check if no grandchildren
            } else if (curr.height == 1) {
                // if there's only a left child
                if (curr.right == null) {
                    curr = curr.left;
                    curr.left = null;
                    return curr;
                // if there exists a right child
                } else {
                    curr = curr.right;
                    curr.right = null;
                    return curr;
                }
               //if grandchildren
            } else if (curr.height > 1) {
                temp = this.findMin(curr.right);
                curr = temp;
                this.delete(curr.right, temp.data);
                return curr;
            }
            return curr;
        }
        if (curr != null) {
            System.out.println("---" + curr.data);
        } else {
            System.out.println("***");
        }
        
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
        if (curr.isLeaf()) {
            return curr;
        } else if (!curr.hasTwoChildren()) {
            this.oneChildBalance(curr);
        } else {
            this.twoChildBalance(curr);
        }
        return curr;
    }
    
    /**
     * Balance the node with one child.
     * @param curr the subtree to be balanced
     * @return the root of the balanced subtree
     */
    private BNode oneChildBalance(BNode curr) {
        int factor = this.balanceFactor(curr);
        if (factor > 1) {
            // subtree too long on left
            if (curr.left.right != null) {
                // left-right too long
                this.doubleWithLeftChild(curr);
            } else {
                // left-left too long
                this.rotateWithLeftChild(curr);
            }
        } else if (factor < -1) {
            // subtree too long on right
            if (curr.right.left != null) {
                // right-left too long
                this.doubleWithRightChild(curr);
            } else {
                // right-right too long
                this.rotateWithRightChild(curr);
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
        int factor = this.balanceFactor(curr);
        if (factor > 1) {
            // subtree too long on left
            if (curr.left.left.isLeaf()) {
                // left-right too long
                this.doubleWithLeftChild(curr);
            } else {
                // left-left too long
                this.rotateWithLeftChild(curr);
            }
        } else if (factor < -1) {
            // subtree too long on right
            if (curr.right.right.isLeaf()) {
                // right-left too long
                this.doubleWithRightChild(curr);
            } else {
                // right-right too long
                this.rotateWithRightChild(curr);
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
        if (k1 == null) {
            return null;
        }
        BNode k2 = k1.right;
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
