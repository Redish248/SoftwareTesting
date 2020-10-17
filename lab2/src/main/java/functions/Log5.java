package functions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Log5 {

    LnFunction lnFunction;

    public double log5(double x) {
        return lnFunction.ln(x)/lnFunction.ln(5);
    }

}
