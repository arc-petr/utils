package pvi.utils.p2y;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.log4j.Logger;
import pvi.utils.p2y.exceptions.P2YConverterException;

import java.io.File;
import java.util.Iterator;

public class PropertiesLoader {

    private  static final Logger logger = Logger.getLogger(PropertiesLoader.class);

    public Configuration getConfig() {
        return config;
    }

    public TreeNode<ValueObject> getRoot() {
        return root;
    }

    private Configuration config;
    private TreeNode<ValueObject> root;

    public void loadFromFile(File f) {
        Configurations configs = new Configurations();
        try {
            config = configs.properties(f);
        } catch (ConfigurationException ex) {
            logger.error(ex.getLocalizedMessage(),ex);
            throw new P2YConverterException(String.format("Error loading properties from file %s",f),ex);
        }

        Iterator<String> keys = config.getKeys();
        root = new TreeNode<>(TreeNode.ROOT_NAME);
        while (keys.hasNext()) {
            String key = keys.next();
            ValueObject vo =ValueObjectFactory.buildObject(key, (PropertiesConfiguration) config);
            NodeHandler nh = NodeHandlerFactory.create(key, vo);
            nh.addTo(root);
        }
    }
}
