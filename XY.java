
/**
 * This class makes pairs of X and Y.
 * 
 * @author Tom Pescatore (tpescatore11@gmail.com) 
 * @version 12/15/13
 */
public class XY
{
    // instance variables
    private final int x;
    private final int y;
    private int numDivisors;

    /**
     * Constructor for objects of class XY
     */
    public XY(int a, int b)
    {
        x = a;
        y = b;
    }
    
    public int sum()
    {
        return x + y;
    }
    
    public int product()
    {
        return x * y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public boolean meetsConditions(int limit)
    {
        return (x > 1 && y > x && x + y < limit);
    }
    
    public boolean hasMultipleDivisors(int x)
    {
        numDivisors = 0;
        for(int i = 2; i <= x/2; i++) {
            if(x % i == 0) {
                numDivisors++;
            }
        }
        return (numDivisors > 3);
    }
}
