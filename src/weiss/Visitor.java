package weiss;

/**
 * Interface met visit() method.
 * @author Rosan Prins(10964711) & Laura Keemink(10912797)
 * @param <AnyType> elk type
 */
public interface Visitor<AnyType>
{
    void visit(AnyType element, int level);
}
