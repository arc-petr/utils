package pvi.utils.p2y;

import pvi.utils.p2y.exceptions.P2YConverterException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class YamlPrinter {
    private YamlPrinter() {
    }


    public static String toString(TreeNode<ValueObject> node) {
        StringBuilder sb = new StringBuilder();
        node.asYaml(sb);
        return sb.toString();
    }

    public static String toString(File f) {
        PropertiesLoader loader = new PropertiesLoader();
        loader.loadFromFile(f);
        TreeNode<ValueObject> node = loader.getRoot();
        return toString(node);
    }

    public static File toFile(File f) {
        PropertiesLoader loader = new PropertiesLoader();
        loader.loadFromFile(f);
        String newFileName = f.getAbsolutePath();
        if (newFileName.endsWith("properties")) {
            newFileName = newFileName.substring(0, newFileName.length() - 10);
        } else {
            int ndx = newFileName.lastIndexOf('.');
            if (ndx != -1) {
                newFileName = newFileName.substring(0, ndx);
            } else {
                newFileName = newFileName + ".";
            }

        }
        newFileName = newFileName + "yaml";
        File toFile = new File(newFileName);
        try (BufferedWriter br = new BufferedWriter(new FileWriter(toFile))) {
            br.write(toString(f));
        } catch (IOException ex) {
            throw new P2YConverterException(String.format("Can not create file %s", newFileName), ex);
        }
        return toFile;
    }


}
