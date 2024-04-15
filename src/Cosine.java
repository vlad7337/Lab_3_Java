import java.text.NumberFormat;

public class Cosine extends Composite {

    public Cosine(Function term) {
        super(term);
    }

    @Override
    public double calculate(double x) {
        return Math.cos(terms().getFirst().calculate(x));
    }

    @Override
    public Function derivative() {
        Function term = terms().getFirst();
        return new Multiplication(Const.NEGATIVE_ONE, Sine.of(term), term.derivative());
    }

    @Override
    public String toPrettyString(NumberFormat nf) {
        return STR."cos(\{terms().getFirst().toPrettyString(nf)})";
    }

    public static Cosine of(Function term){
        return new Cosine(term);
    }

}