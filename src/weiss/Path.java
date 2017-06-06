package weiss;

/** Immutable class that represents a path in a binary tree, consisting of a sequence of lefts and rights 
 * @author Rosan Prins(10964711) & Laura Keemink(10912797) */
public class Path 
{
    private int code;
    
    /** Constructs a path to the root */
    public Path()
    {
        code = 1;
    }
    
    Path(int code)
    {
        this.code = code;
    }
    
    /** Returns a path that has a left added to this path */
    public Path leftOf()
    {
        return new Path(code * 2 + 1);
    }
    
    /** Returns a path that has a right added to this path */
    public Path rightOf()
    {
        return new Path(code * 2);
    }
    
    /** True if the path is empty: neither lefts nor rights */
    public boolean isEmpty()
    {
        return code == 1;
    }
    
    /** Returns the path equal to this path with the last step undone */
    public Path back()
    {
        return new Path(code / 2); 
    }
    
    /** True if this path ends with a right */
    public boolean endsRight()
    {
        return code % 2 == 0;
    }        
    
    /** Returns the integer code corresponding to this path */
    public int getCode()
    {
        return code;
    }
    
    /** Returns the binary string corresponding to this path, where 0 is right and 1 is left */
    public String getBinary()
    {
        return Integer.toBinaryString(code);
    }
    
    /** Returns the path length */
    public int size()
    {
        return (int) (Math.log(code) / Math.log(2));
    }
    
    @Override
    public String toString()
    {
        return "Path[" + code + "]";
    }       
}
