package pvi.utils.p2y;

public class SpringBootLoggingHandler extends StandardNodeHandler {

    public SpringBootLoggingHandler(String key, ValueObject vo) {
        super(key,vo);
    }

    @Override
    public void addTo(TreeNode<ValueObject> root) {
        if(getKey().equals("logging.level.*")){
            setKey("logging.level.ROOT");
        }
        TreeNode n = root.addNode("logging.level",null);
        n.addNode(getKey().substring("logging.level.".length()),getVo(),true);
    }
}
