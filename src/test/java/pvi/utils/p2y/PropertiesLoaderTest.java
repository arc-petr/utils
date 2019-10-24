package pvi.utils.p2y;

import org.junit.Test;
import pvi.utils.p2y.exceptions.P2YConverterException;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PropertiesLoaderTest {

    @Test
    public void testLoadFileWithComments(){
        PropertiesLoader pl = new PropertiesLoader();
        File f = new File("testprops1.properties");

        pl.loadFromFile(f);

        assertEquals("false",pl.getConfig().getProperty("test.property"));
    }

    @Test(expected = P2YConverterException.class)
    public void testLoadNoneExistingFile(){
        PropertiesLoader pl = new PropertiesLoader();
        File f = new File("testprops_unknown.properties");

        pl.loadFromFile(f);

    }

}
