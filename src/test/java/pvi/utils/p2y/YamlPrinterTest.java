package pvi.utils.p2y;

import jdk.jfr.events.FileReadEvent;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pvi.utils.p2y.exceptions.P2YConverterException;

import java.io.*;

import static org.junit.Assert.*;

public class YamlPrinterTest {
    final static Logger logger = Logger.getLogger(PropertiesLoader.class);

    Reader sample1;

    @Before
    public void setup() throws IOException {
        sample1 = null;
    }

    @After
    public void tearDown() throws IOException {
        if (sample1 != null) {
            sample1.close();
            sample1 = null;
        }

    }

    @Test
    public void testToStringFromNode_springboot_withlogging() throws IOException {
        String useCaseName = "testprops_springboot_withlogging";

        runUseCaseFromNode(useCaseName);
    }


    @Test
    public void testToStringFromNode_case1() throws IOException {
        String useCaseName = "testprops1";

        runUseCaseFromNode(useCaseName);
    }

    @Test
    public void testToStringFromNode_flat_key() throws IOException {
        String useCaseName = "testprops_flat_key";

        runUseCaseFromNode(useCaseName);
    }

    @Test
    public void testToStringFromNode_one_level() throws IOException {
        String useCaseName = "testprops_one_level";

        runUseCaseFromNode(useCaseName);
    }

    @Test
    public void testToStringFromNode_sample() throws IOException {
        String useCaseName = "testprops_sample";

        runUseCaseFromNode(useCaseName);
    }

    @Test
    public void testToStringFromNode_list() throws IOException {
        String useCaseName = "testprops_list";

        runUseCaseFromNode(useCaseName);
    }


    @Test
    public void testToStringFromFile_sample() throws IOException {
        String useCaseName = "testprops_sample";
        runUseCaseFromFile(useCaseName);

    }

    @Test
    public void testToStringFromFile_case() throws IOException {
        String useCaseName = "testprops1";
        runUseCaseFromFile(useCaseName);

    }

    @Test
    public void testToStringFromFile_flat_key() throws IOException {
        String useCaseName = "testprops_flat_key";
        runUseCaseFromFile(useCaseName);

    }

    @Test
    public void testToStringFromFile_list() throws IOException {
        String useCaseName = "testprops_list";
        runUseCaseFromFile(useCaseName);

    }

    @Test
    public void testToStringFromFileToFile_sample() throws IOException {
        String useCaseName = "testprops_sample";
        runUseCaseFromFileToFile(useCaseName);

    }

    @Test
    public void testToStringFromFileToFile_case() throws IOException {
        String useCaseName = "testprops1";
        runUseCaseFromFileToFile(useCaseName);

    }

    @Test
    public void testToStringFromFileToFile_flat_key() throws IOException {
        String useCaseName = "testprops_flat_key";
        runUseCaseFromFileToFile(useCaseName);

    }

    @Test
    public void testToStringFromFileToFile_list() throws IOException {
        String useCaseName = "testprops_list";
        runUseCaseFromFileToFile(useCaseName);

    }


    @Test
    public void testToStringFromFile_one_level() throws IOException {
        String useCaseName = "testprops_one_level";
        runUseCaseFromFile(useCaseName);
    }

    @Test
    public void toFile_one_level() throws IOException {
        String useCaseName = "testprops_one_level";
        runUseCaseFromFileToFile(useCaseName);
    }

    @Test
    public void toFile_noneStandardExtension() throws IOException {
        String useCaseName = "testprops_one_level_noext";

        File prop = getFileFromClassLoader(useCaseName, "");
        File yaml = getYamlFileFromClassLoader("yaml_testprops_one_level");
        sample1 = new FileReader(yaml);
        File yamlFile = YamlPrinter.toFile(prop);
        checkResults(yamlFile);
    }

    @Test(expected=P2YConverterException.class)
    public void testToStringFromNode_invalid() throws IOException {
        String useCaseName = "testprops_invalid";

        File prop = getPropertiesFileFromClassLoader(useCaseName);
        YamlPrinter.toString(prop);
    }


    private void runUseCaseFromFileToFile(String useCaseName) throws IOException {
        File prop = getFiles(useCaseName);
        File yamlFile = YamlPrinter.toFile(prop);
        checkResults(yamlFile);
    }

    private void runUseCaseFromFile(String useCaseName) throws IOException {
        File prop = getFiles(useCaseName);
        String newYaml = YamlPrinter.toString(prop);
        checkResults(newYaml);
    }


    private void runUseCaseFromNode(String useCaseName) throws IOException {
        File prop = getFiles(useCaseName);

        PropertiesLoader pl = new PropertiesLoader();
        pl.loadFromFile(prop);
        String newYaml = YamlPrinter.toString(pl.getRoot());
        checkResults(newYaml);
    }

    private void checkResults(String newYaml) throws IOException {
        logger.debug(newYaml);
        assertEquals(IOUtils.toString(sample1), newYaml);
    }

    private void checkResults(File yamlFile) throws IOException {
        logger.debug(yamlFile);
        try (BufferedReader br = new BufferedReader(new FileReader(yamlFile))) {
            String newYaml = IOUtils.toString(br);
            logger.debug(newYaml);
            assertEquals(IOUtils.toString(sample1), newYaml);

        }
    }

    private File getFiles(String useCaseName) throws FileNotFoundException {
        File prop = getPropertiesFileFromClassLoader(useCaseName);
        File yaml = getYamlFileFromClassLoader("yaml_" + useCaseName);
        sample1 = new FileReader(yaml);
        return prop;
    }


    private File getYamlFileFromClassLoader(String name) {
        return getFileFromClassLoader(name, "yaml");
    }

    private File getPropertiesFileFromClassLoader(String name) {
        return getFileFromClassLoader(name, "properties");
    }

    private File getFileFromClassLoader(String name, String extension) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(name + (extension != null && !extension.isEmpty() ? "." + extension : "")).getFile());
    }


}