import java.text.NumberFormat;

/* f(x) = x ^ n */
public class Power extends Composite {

    public Power(Function base, Function exponent) {
        super(base, exponent);
    }

    @Override
    public double calculate(double x) {
        Function base = terms().get(0);
        Function exponent = terms().get(1);
        return Math.pow(base.calculate(x), exponent.calculate(x));
    }

    @Override
    public Function derivative() {
        Function base = terms().get(0);
        Function exponent = terms().get(1);
        return Multiplication.of(exponent, Power.of(base, Sum.of(exponent, Const.NEGATIVE_ONE)), base.derivative());
    }

    @Override
    public String toPrettyString(NumberFormat nf) {
        Function base = terms().get(0);
        Function exponent = terms().get(1);
        return STR."(\{base.toPrettyString(nf)})^(\{exponent.toPrettyString(nf)})";
    }

    public static Power of(Function base, Function exponent) {
        return new Power(base, exponent);
    }
}
