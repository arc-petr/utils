package pvi.utils.p2y;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;

import java.util.Arrays;

public class ValueObjectFactory {
    private ValueObjectFactory(){}

    public static ValueObject buildObject(String key, PropertiesConfiguration config){

        Object d = config.getProperty(key);
        String sD = String.valueOf(d);

        int ind = sD.indexOf(',');
        boolean isArray = false;
        while (ind >=0){
            if(ind > 0){
                if(sD.charAt(ind-1) != '\\') {
                    isArray = true;
                    break;
                }
            }
            ind = sD.indexOf(',',ind);
        }

        ValueObject result = new ValueObject();
        if(isArray){
            result.setData(Arrays.asList(sD.split(",")));
        } else {
            result.setData(asObject(sD));
        }

        PropertiesConfigurationLayout dataLayout = config.getLayout();

        result.setComments(dataLayout.getComment(key));


        return result;
    }

    public static Object asObject(String val) {
        if(val == null){
            return null;
        }
        if (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false")) {
            return Boolean.valueOf(val);
        } else {
            try {
                return Integer.parseInt(val);
            } catch (NumberFormatException e) {
            }
            try {
                return Long.parseLong(val);
            } catch (NumberFormatException e) {
            }
            try {
                return Double.parseDouble(val);
            } catch (NumberFormatException e) {
            }
            return val;
        }
    }
}
