package playground;

public class DummyService {
    private DummyCalculator dummyCalculator = new DummyCalculator();

    public int dummySum(int x, int y) {
        return dummyCalculator.plus(x,y);
    }

    public int dummySumLogic(int a, int b, int c, int d) {
        int aPlusB = dummyCalculator.plus(a,b);
        int cPlusD = dummyCalculator.plus(c,d);
        return aPlusB + cPlusD;
    }
}
