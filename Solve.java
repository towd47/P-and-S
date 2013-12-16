import java.util.ArrayList;

/**
 * Write a description of class Solve here.
 * 
 * (0) There are two integers, X and Y, such that 1 < X < Y and X + Y < 100.
 * Mr. S knows S such that S = X + Y.
 * Mr. P knows P such that P = X * Y.
 * Neither S nor P know X or Y.
 * Conversation:
 * (1) P: I dont know X and Y.
 * (2) S: I already knew that.
 * (3) P: Now I know X and Y.
 * (4) S: Now I know X and Y also.
 * (5) What are X and Y?
 * 
 * @author Tom Pescatore (tpescatore11@gmail.com)
 * @version 12/15/13
 */
public class Solve
{
    private ArrayList<XY> xys0;
    private ArrayList<XY> xys1;
    private ArrayList<XY> xysTemp;
    private ArrayList<XY> xys2;
    private ArrayList<XY> xys3;
    private ArrayList<XY> xys4;

    public void Solve() {
        fillXY0(100);
        fillXY1();
        fillXY2();
        fillXY3();
        fillXY4();
    }

    /**
     * (0) There are two integers, X and Y, such that 1 < X < Y and X + Y < 100.
     */
    public void fillXY0(int limit) {
        xys0 = new ArrayList<XY>();
        for (int x = 2; x <= limit/2 - 1; x++) {
            for (int y = x + 1; y <= limit/2; y++) {
                XY a = new XY(x,y);
                assert a.meetsConditions(limit);
                xys0.add(a);
            }
        }
    }

    /**
     * (1) P: I dont know X and Y.
     * X * Y must have Multiple Divisors
     */
    public void fillXY1() {
        xys1 = new ArrayList<XY>();
        for (int i = 0; i < xys0.size(); i++) {
            if (xys0.get(i).hasMultipleDivisors(xys0.get(i).product())){
                xys1.add(xys0.get(i));
            }
        }
    }

    /**
     * (2) S: I already knew that.
     * Find which values add up to a sum such that for each XY that add up to that sum,
     * each XY product also had multiple divisors.
     */
    public void fillXY2() {
        xys2 = new ArrayList<XY>();
        for (int i = 0; i < xys1.size(); i++) {
            int a = 0;
            boolean allMultDivisors = true;
            a = xys1.get(i).sum();
            xysTemp = new ArrayList<XY>();
            for (int j = 2; j < a-1; j++) {
                if (j < a - j) {
                    XY c = new XY(j,a-j);
                    xysTemp.add(c);
                }
            }
            for (int j = 0; j < xysTemp.size(); j++) {
                if (!xysTemp.get(j).hasMultipleDivisors(xysTemp.get(j).product())){
                    allMultDivisors = false;
                }
            }
            if (allMultDivisors) {
                xys2.add(xys1.get(i));
            }
        }
    }

    /**
     * (3) P: Now I know X and Y.
     * Of the pairs that remain, can only be a pair where the product is different from the product
     * of all the other pairs.
     */
    public void fillXY3() {
        xys3 = xys2;
        int xys3Size = xys3.size();
        for (int i = 0; i < xys3.size(); i++) {
            if (i < xys3Size) {
                int product = xys3.get(i).product();
                boolean sameProduct = false;
                for (int j = i + 1; j < xys3.size(); j++) {
                    if (xys3.get(j).product() == product) {
                        xys3.remove(j);
                        sameProduct = true;
                        j = j - 1;
                    }
                }
                if (sameProduct) {
                    xys3.remove(i);
                    i = i - 1;
                    xys3Size = xys3.size();
                }
            }
            else {
                break;
            }
        }
    }

    /**
     * (4) S: Now I know X and Y also.
     * From the pairs above, must be a pair where the sum is different from the sum of all other pairs
     */
    public void fillXY4() {
        xys4 = xys3;
        int xys4Size = xys4.size();
        for (int i = 0; i < xys4.size(); i++) {
            if (i < xys4Size) {
                int sum = xys4.get(i).sum();
                boolean sameSum = false;
                for (int j = i + 1; j < xys4.size(); j++) {
                    if (xys4.get(j).sum() == sum) {
                        xys4.remove(j);
                        sameSum = true;
                        j = j - 1;
                    }
                }
                if (sameSum) {
                    xys4.remove(i);
                    i = i - 1;
                    xys4Size = xys4.size();
                }
            }
            else {
                break;
            }
        }
    }
}
