import java.text.NumberFormat;

public class NaturalLogarithm extends Composite {

    public NaturalLogarithm(Function term) {
        super(term);
    }

    @Override
    public double calculate(double x) {
        return Math.log(terms().getFirst().calculate(x));
    }

    @Override
    public Function derivative() {
        Function term = terms().getFirst();
        return new Multiplication(Power.of(term, Const.NEGATIVE_ONE), term.derivative());
    }

    @Override
    public String toPrettyString(NumberFormat nf) {
        return STR."ln(\{terms().getFirst().toPrettyString(nf)})";
    }

    public static NaturalLogarithm of(Function term){
        return new NaturalLogarithm(term);
    }

}
