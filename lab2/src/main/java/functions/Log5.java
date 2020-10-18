package functions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Log5 implements SystemModule{

    LnFunction lnFunction;

    public double calc(double x) {
        return lnFunction.calc(x)/lnFunction.calc(5);
    }

    @Override
    public String getModuleName() {
        return "Log5";
    }
}
