import java.text.NumberFormat;

public class Tangent extends Composite {

    public Tangent(Function term) {
        super(term);
    }

    @Override
    public double calculate(double x) {
        return Math.tan(terms().getFirst().calculate(x));
    }

    @Override
    public Function derivative() {
        Function term = terms().getFirst();
        return new Multiplication(Power.of(Cosine.of(term), Const.of(-2)), term.derivative());
    }

    @Override
    public String toPrettyString(NumberFormat nf) {
        return STR."tg(\{terms().getFirst().toPrettyString(nf)})";
    }

    public static Tangent of(Function term){
        return new Tangent(term);
    }

}
