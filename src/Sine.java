import java.text.NumberFormat;

public class Sine extends Composite {

    public Sine(Function term) {
        super(term);
    }

    @Override
    public double calculate(double x) {
        return Math.sin(terms().getFirst().calculate(x));
    }

    @Override
    public Function derivative() {
        Function term = terms().getFirst();
        return Multiplication.of(Cosine.of(term), term.derivative());
    }

    @Override
    public String toPrettyString(NumberFormat nf) {
        return STR."sin(\{terms().getFirst().toPrettyString(nf)})";
    }

    public static Sine of(Function term) {
        return new Sine(term);
    }

}
