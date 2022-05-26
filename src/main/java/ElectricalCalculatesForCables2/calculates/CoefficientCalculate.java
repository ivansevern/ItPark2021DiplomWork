package ElectricalCalculatesForCables2.calculates;

public class CoefficientCalculate implements CoefficientGeneral {
    double cGeneral, c1, c2, c3;

    @Override
    public void multC() {
        cGeneral = c1 * c2 * c3;
        String format = String.format("%.2f", cGeneral);
    }
}