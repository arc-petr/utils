package pvi.utils.p2y.pvi.utils;

import pvi.utils.p2y.YamlPrinter;

import java.io.File;

public class P2Y {
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            handleErrorInput();
        }
        File file = new File(args[0]);

        if (file.exists()) {
            YamlPrinter.toFile(file);
        } else {
            handleErrorInput();
        }
    }

    private static void handleErrorInput() {
        System.out.println("Converter from properties to YAML. Creates YAML file with the same name replacing extension to 'yaml'.\nUsage:\n\n   java pvi.utils.P2Y <existing properties file>");
        System.exit(1);
    }
}