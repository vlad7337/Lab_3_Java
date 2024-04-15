import java.text.NumberFormat;

public class Sign extends Composite {

    public Sign(Function term) {
        super(term);
    }

    @Override
    public double calculate(double x) {
        double termCalculateResult = terms().getFirst().calculate(x);
        if (termCalculateResult > 0) {
            return 1;
        }
        if (termCalculateResult < 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public Function derivative() {
        return Const.ZERO;
    }

    @Override
    public String toPrettyString(NumberFormat nf) {
        return STR."|\{terms().getFirst().toPrettyString(nf)}|";
    }

    public static Sign of(Function term){
        return new Sign(term);
    }

}
