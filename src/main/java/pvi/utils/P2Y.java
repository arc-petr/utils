package pvi.utils;

import pvi.utils.p2y.YamlPrinter;

import java.io.File;

public class P2Y {
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            handleErrorInput("Not enough arguments\n.");
        }
        File file = new File(args[0]);

        if (file.exists()) {
            YamlPrinter.toFile(file);
        } else {
            handleErrorInput(String.format("Can't find file %s\n", file.getAbsolutePath()));
        }
    }

    private static void handleErrorInput(String msg) {
        System.out.println(String.format("%s Converter from properties to YAML. Creates YAML file with the same name replacing extension to 'yaml'.\nUsage:\n\n   java pvi.utils.P2Y <existing properties file>", msg));
        System.exit(1);
    }
}