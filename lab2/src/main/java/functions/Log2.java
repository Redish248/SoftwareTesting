package functions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Log2 implements SystemModule {

    LnFunction lnFunction;

    public double calc(double x) {
        return lnFunction.calc(x)/lnFunction.calc(2);
    }

    @Override
    public String getModuleName() {
        return "Log2";
    }
}
