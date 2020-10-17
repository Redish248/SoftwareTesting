package functions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Log2 {

    LnFunction lnFunction;

    public double log2(double x) {
        return lnFunction.ln(x)/lnFunction.ln(2);
    }

}
