package utils;

import functions.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    public static void write(SystemModule systemModule, double startX, double step, int amount) throws IOException {
        String filename = String.format("csvResults/%sResult.csv", systemModule.getModuleName());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, false));
        bufferedWriter.write(String.format("x; %s(x)\n", systemModule.getModuleName()));
        double x;
        for (int i = 0; i < amount; i++) {
            x = startX + i * step;
            bufferedWriter.write(String.format("%1.7f; %1.7f\n", x, systemModule.calc(x)));
        }
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {
        Sinus sinus = new Sinus();
        Cosine cosine = new Cosine(sinus);
        Tangens tangens = new Tangens(sinus, cosine);
        LnFunction lnFunction = new LnFunction();
        Log2 log2 = new Log2(lnFunction);
        Log5 log5 = new Log5(lnFunction);
        Function function = new Function(sinus, tangens, lnFunction, log5, log2);
        write(sinus, 0, -Math.PI/8, 17);
        write(cosine, 0, -Math.PI/16, 33);
        write(tangens, 0, -Math.PI/16, 17);
        write(lnFunction,1E-4, 0.5, 15);
        write(log2,1E-4, 0.5, 15);
        write(log5,1E-4, 0.5, 15);
        write(function, -10, 0.5, 41);
    }
}
