package pvi.utils;

import org.apache.log4j.Logger;
import pvi.utils.p2y.YamlPrinter;

import java.io.File;

public class P2Y {
    private static final Logger logger = Logger.getLogger(P2Y.class);

    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            handleErrorInput("Not enough arguments.\n");
        }
        File file = new File(args[0]);

        if (file.exists()) {
            YamlPrinter.toFile(file);
        } else {
            handleErrorInput(String.format("Can't find file %s %n", file.getAbsolutePath()));
        }
    }

    private static void handleErrorInput(String msg) {
        logger.info(String.format("%s Converter from properties to YAML. Creates YAML file with the same name replacing extension to 'yaml'.%n Usage: %n%n   java pvi.utils.P2Y <existing properties file>", msg));
        System.exit(1);
    }
}