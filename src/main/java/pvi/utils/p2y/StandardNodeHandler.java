package pvi.utils.p2y;

public class StandardNodeHandler implements NodeHandler {
    public ValueObject getVo() {
        return vo;
    }

    public String getKey() {
        return key;
    }

    private ValueObject<?> vo;

    protected void setKey(String key) {
        this.key = key;
    }

    private String key;

    public StandardNodeHandler(String key, ValueObject vo) {
        this.vo = vo;
        this.key = key;
    }

    @Override
    public void addTo(TreeNode<ValueObject> root) {
        root.addNode(key, vo);
    }
}
