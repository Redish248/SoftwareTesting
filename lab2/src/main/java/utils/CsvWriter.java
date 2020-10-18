package utils;

import functions.Cosine;
import functions.Sinus;
import functions.SystemModule;
import functions.Tangens;

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
        write(sinus, 0, -Math.PI/8, 9);
        write(cosine, 0, -Math.PI/8, 9);
        write(tangens, 0, -Math.PI/8, 9);
    }
}
