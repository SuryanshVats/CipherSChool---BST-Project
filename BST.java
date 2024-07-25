public class BinarySearchTree {

    // Node class representing each node in the tree
    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Root of the BST
    Node root;

    // Constructor
    BinarySearchTree() {
        root = null;
    }

    // Insert a new key
    void insert(int key) {
        root = insertRec(root, key);
    }

    // A recursive function to insert a new key in the BST
    Node insertRec(Node root, int key) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Otherwise, recur down the tree
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        // Return the (unchanged) node pointer
        return root;
    }

    // Search a key in the BST
    boolean search(int key) {
        return searchRec(root, key);
    }

    // A recursive function to search a key in the BST
    boolean searchRec(Node root, int key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.key == key)
            return root != null;

        // Key is greater than root's key
        if (root.key < key)
            return searchRec(root.right, key);

        // Key is smaller than root's key
        return searchRec(root.left, key);
    }

    // Delete a key
    void delete(int key) {
        root = deleteRec(root, key);
    }

    // A recursive function to delete a key from the BST
    Node deleteRec(Node root, int key) {
        // Base Case: if the tree is empty
        if (root == null)
            return root;

        // Otherwise, recur down the tree
        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);

        // If the key is the same as root's key, then this is the node to be deleted
        else {
            // Node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    // A utility function to find the smallest node in the tree
    int minValue(Node root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    // Inorder traversal
    void inorder() {
        inorderRec(root);
    }

    // A utility function to do inorder traversal of BST
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Preorder traversal
    void preorder() {
        preorderRec(root);
    }

    // A utility function to do preorder traversal of BST
    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Postorder traversal
    void postorder() {
        postorderRec(root);
    }

    // A utility function to do postorder traversal of BST
    void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal:");
        tree.inorder();

        System.out.println("\n\nPreorder traversal:");
        tree.preorder();

        System.out.println("\n\nPostorder traversal:");
        tree.postorder();

        System.out.println("\n\nDelete 20");
        tree.delete(20);
        System.out.println("Inorder traversal:");
        tree.inorder();

        System.out.println("\n\nDelete 30");
        tree.delete(30);
        System.out.println("Inorder traversal:");
        tree.inorder();

        System.out.println("\n\nDelete 50");
        tree.delete(50);
        System.out.println("Inorder traversal:");
        tree.inorder();

        System.out.println("\n\nSearch for 40:");
        System.out.println(tree.search(40));

        System.out.println("Search for 100:");
        System.out.println(tree.search(100));
    }
}
