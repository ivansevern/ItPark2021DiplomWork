package ElectricalCalculatesForCables2.calculates;

public class IConverter {
    public static void main(String[] args) {


//        double i380 = 0;
        int sp = 2500;
        double u = 380;
        double cosf = 1;

        double i380 = sp / (Math.sqrt(3) * u * cosf) * 1000;
        String format = String.format("%.2f", i380);
        System.out.println(format);
    }
}
