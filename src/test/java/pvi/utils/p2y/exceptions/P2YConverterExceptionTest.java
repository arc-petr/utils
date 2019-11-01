package pvi.utils.p2y.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class P2YConverterExceptionTest {
    @Test
    public void testNoThrowable(){
        String testMessage= "Test message";
        try{
            throw new P2YConverterException(testMessage);
        } catch(P2YConverterException ex){
            assertEquals(testMessage,ex.getLocalizedMessage());
        }
    }
    @Test
    public void testWithThrowable(){
        String testMessage= "Test message";
        String testMessage2 ="Test message2";

        Throwable t = new RuntimeException(testMessage);
        try{
            throw new P2YConverterException(testMessage2,t);
        } catch(P2YConverterException ex){
            assertEquals(testMessage2,ex.getLocalizedMessage());
            assertEquals(testMessage,ex.getCause().getLocalizedMessage());
        }
    }


}
