import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Multiplication extends Composite {
    public Multiplication() {
        super();
    }

    public Multiplication(Function... terms) {
        super(terms);
    }

    public Multiplication(ArrayList<Function> terms) {
        super(terms);
    }

    @Override
    public double calculate(double x) {
        double result = 1.0;
        for (Function function : terms()) {
            result *= function.calculate(x);
        }
        return result;
    }

    @Override
    public Function derivative() {
        int size = terms().size();

        final ArrayList<Function> derivativeTerms = new ArrayList<>(size);
        for (Function function : terms()) {
            derivativeTerms.add(function.derivative());
        }

        final ArrayList<Function> multiplicationTerms = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            final ArrayList<Function> multiplicationTerms2 = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                if (j == i) {
                    multiplicationTerms2.add(derivativeTerms.get(j));
                } else {
                    multiplicationTerms2.add(terms().get(j));
                }
            }
            multiplicationTerms.add(new Multiplication(multiplicationTerms2));
        }

        return new Sum(multiplicationTerms);
    }

    @Override
    public String toPrettyString(NumberFormat nf) {
        final StringJoiner joiner = new StringJoiner("*");
        for (Function function : terms()) {
            joiner.add(function.toPrettyString(nf));
        }
        return String.format("(%s)", joiner.toString());
    }

    public static Multiplication of(Function... terms) {
        return new Multiplication(terms);
    }
}