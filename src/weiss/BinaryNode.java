package weiss;

/** Binary node class with recursive routines to compute size and height.
 * @author Rosan Prins(10964711) & Laura Keemink(10912797)*/
public class BinaryNode<AnyType>
{
    private AnyType element;
    private Path path;
    private BinaryNode<AnyType> left;
    private BinaryNode<AnyType> right;

    public BinaryNode()
    {
        this(null, null, null, null);
    }

    public BinaryNode(AnyType theElement) {
        this(theElement, new Path(), null, null);
    }
    
    public BinaryNode(AnyType theElement, Path thePath, BinaryNode<AnyType> lt,
            BinaryNode<AnyType> rt)
    {
        element = theElement;
        path = thePath;
        left = lt;
        right = rt;
    }

    /**
     * Return the size of the binary tree rooted at t.
     */
    public static <AnyType> int size(BinaryNode<AnyType> t)
    {
        if (t == null)
            return 0;
        else
            return 1 + size(t.left) + size(t.right);
    }

    /**
     * Return the height of the binary tree rooted at t.
     */
    public static <AnyType> int height(BinaryNode<AnyType> t)
    {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    /** Traverse tree rooted at current node using preorder traversal. */
    public void traverse(Visitor<AnyType> visitor)
    {
        traverse(visitor, 0);
    }
    
    /**
     * Loopt over de boom heen.
     * @param visitor de visitor
     * @param level de level
     */
    private void traverse(Visitor<AnyType> visitor, int level)
    {
        // TODO Opdracht 3d
            visitor.visit(element, level);
            level++;
            if(right != null)
            {
                right.traverse(visitor,level);
            }
            if(left != null)
            {
                left.traverse(visitor,level);
            }
        //
    }
    
    /**
     * Return a reference to a node that is the root of a duplicate of the
     * binary tree rooted at the current node.
     */
    public BinaryNode<AnyType> duplicate()
    {
        BinaryNode<AnyType> root = new BinaryNode<>(element, path, null, null);

        if (left != null) // If there's a left subtree
            root.left = left.duplicate(); // Duplicate; attach
        if (right != null) // If there's a right subtree
            root.right = right.duplicate(); // Duplicate; attach
        return root; // Return resulting tree
    }

    public AnyType getElement()
    {
        return element;
    }

    public BinaryNode<AnyType> getLeft()
    {
        return left;
    }

    public BinaryNode<AnyType> getRight()
    {
        return right;
    }

    public void setElement(AnyType x)
    {
        element = x;
    }

    void setLeft(BinaryNode<AnyType> t)
    {
        left = t;
    }

    void setRight(BinaryNode<AnyType> t)
    {
        right = t;
    }
    
    /** Inserts a child to this node as its left child. Returns the child node. */
    public BinaryNode<AnyType> insertLeft(AnyType newChild)
    {        
        Path childPath = path.leftOf();
        BinaryNode<AnyType> childNode = new BinaryNode<>(newChild, childPath, null, null);
        setLeft(childNode);
        return childNode;
    }
    
    /** Inserts a child to this node as its right child. Returns the child node. */    
    public BinaryNode<AnyType> insertRight(AnyType newChild)
    {                
        Path childPath = path.rightOf();
        BinaryNode<AnyType> childNode = new BinaryNode<>(newChild, childPath, null, null);
        setRight(childNode);
        return childNode;
    }

    public BinaryNode<AnyType> find(AnyType toFind)
    {
        if (element.equals(toFind)) return this;

        BinaryNode<AnyType> subtree = getLeft();
        BinaryNode<AnyType> found;
        if (subtree != null)
        {
            found = subtree.find(toFind);
            if (found != null) return found;
        }

        subtree = getRight();
        if (subtree != null)
        {
            found = subtree.find(toFind);
            return found;
        }
        return null;
    }
       
    public Path getPath()
    {
        return path;
    }
}
