package pvi.utils.p2y;


public class NodeHandlerFactory {
    private NodeHandlerFactory(){}
    public static NodeHandler create(String key, ValueObject vo) {
        if(key.startsWith("logging.level.")){
            return new SpringBootLoggingHandler(key,vo);
        }
        return new StandardNodeHandler(key,vo);
    }
}
