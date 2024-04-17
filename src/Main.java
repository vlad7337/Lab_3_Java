import java.text.NumberFormat;

// My Lab_3 edited

public class Main {
    public static void main(String[] args) {
        final NumberFormat nf = NumberFormat.getInstance();
        final double a = 0.1;
        final double b = -0.5;
        final double x0 = 1.65;

        // f₁(x)  =  cos(ln|tg(ax)|) - ln²|bx-a|
        // f₁'(x) = -sin(ln|tg(ax)|) / |tg(ax)| * sign(tg(ax)) / cos²(ax) * a
        //          - 2 * ln|bx-a| / |bx-a| * sign(bx-a) * b

        final Function expression1 =
            Sum.of(
                Cosine.of(
                    NaturalLogarithm.of(
                        Absolute.of(
                            Tangent.of(
                                Linear.of(a)
                            )
                        )
                    )
                ),
                Multiplication.of(
                    Const.NEGATIVE_ONE,
                    Power.of(
                        NaturalLogarithm.of(
                            Absolute.of(
                                Sum.of(
                                    Linear.of(b),
                                    Const.of(-a)
                                )
                            )
                        ),
                        Const.of(2)
                    )
                )
            );
        System.out.format("f1 (x) = %s", expression1.toPrettyString(nf)).println();
        System.out.format("f1'(x) = %s", expression1.derivative().toPrettyString(nf)).println();
        System.out.format("f1 (%.2f) = %f", x0, expression1.calculate(x0)).println();
        System.out.format("f1'(%.2f) = %f", x0, expression1.derivative().calculate(x0)).println();

        // f₂(x)  = xsh²|a∛(cos(bx))|
        // f'₂(x) =  sh²|a∛(cos(bx))|
        //         + x * 2 * sh|a∛(cos(bx))| * ch|a∛(cos(bx))| * sign(a∛(cos(bx))
        //         * a * (1/3) * (cos(bx))^(-2/3) * (-sin(bx)) * b
        final Function expression2 =
            Multiplication.of(
                Linear.X,
                Power.of(
                    HyperbolicSine.of(
                        Absolute.of(
                            Multiplication.of(
                                Const.of(a),
                                Power.of(
                                    Cosine.of(
                                        Linear.of(b)
                                    ),
                                    Const.of(1.0 / 3.0)
                                )
                            )
                        )
                    ),
                    Const.of(2)
                )
            );
        System.out.format("f2(x) = %s", expression2.toPrettyString(nf)).println();
        System.out.format("f2'(x) = %s", expression2.derivative().toPrettyString(nf)).println();
        System.out.format("f2(%.2f) = %f", x0, expression2.calculate(x0)).println();
        System.out.format("f2'(%.2f) = %f", x0, expression2.derivative().calculate(x0)).println();
    }
}

