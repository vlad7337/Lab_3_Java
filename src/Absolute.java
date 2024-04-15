import java.text.NumberFormat;

public class Absolute extends Composite {

    public Absolute(Function term) {
        super(term);
    }

    @Override
    public double calculate(double x) {
        return Math.abs(terms().getFirst().calculate(x));
    }

    @Override
    public Function derivative() {
        Function term = terms().getFirst();
        return new Multiplication(Sign.of(term), term.derivative());
    }

    @Override
    public String toPrettyString(NumberFormat nf) {
        return STR."|\{terms().getFirst().toPrettyString(nf)}|";
    }

    public static Absolute of(Function term){
        return new Absolute(term);
    }

}
