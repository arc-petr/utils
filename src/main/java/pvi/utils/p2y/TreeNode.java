package pvi.utils.p2y;

import pvi.utils.p2y.exceptions.P2YConverterException;

import java.util.HashMap;
import java.util.Map;

public class TreeNode<T extends ValueObject> {
    public static final String ROOT_NAME = "<ROOT>";

    public TreeNode(String nodeName) {
        this.nodeName = nodeName;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode addNode(String key, T data) {
        return addNode(key, data, false);
    }

    public TreeNode addNode(String key, T data, boolean flat) {
        if(key == null || key.isEmpty()){
            throw new P2YConverterException(String.format("Invalid key ends with %s", key));
        }
        int ind = key.indexOf('.');
        if (flat || ind < 0) {
            return makeTreeNode(key, data);
        } else if (ind == 0) {
            throw new P2YConverterException(String.format("Invalid key ends with %s", key));
        } else {
            String newKey = key.substring(0, ind);
            TreeNode<T> node = ensureTreeNode(newKey);
            return node.addNode(key.substring(ind + 1), data);
        }
    }

    private TreeNode makeTreeNode(String key, T data) {
        TreeNode<T> node = ensureTreeNode(key);
        if (node.getData() == null) {
            node.setData(data);
        }
        return node;
    }

    public void asYaml(StringBuilder sb) {
        asYaml(sb, false, "");
    }

    public void asYaml(StringBuilder sb, boolean flatKey, String parentPrefix) {
        boolean isRoot = nodeName.equals(ROOT_NAME);
        if (!isRoot) {
            if (data != null) {
                if (data.getComments() != null) {
                    sb.append(data.getComments().replaceAll("!", "#")).append("\n");
                }
                sb.append(data.asYaml(realNodeName(parentPrefix, flatKey)));
                flatKey = true;
            } else {
                sb.append(realNodeName(parentPrefix, flatKey)).append(":");
            }
        }
        if (branches != null && branches.size() > 0 && sb.length() > 0) {
            sb.append("\n");
        }
        StringBuilder csb = new StringBuilder();

        String prefix = flatKey ? realNodeName(parentPrefix, flatKey && !isRoot) : "";
        for (Map.Entry<String, TreeNode<T>> e : branches.entrySet()) {
            TreeNode node = e.getValue();
            if (csb.length() > 0) {
                csb.append("\n");
            }
            node.asYaml(csb, flatKey, prefix);
        }
        String s = csb.toString();
        if (!isRoot && !s.isEmpty() && !flatKey) {
            s = s.replaceAll("^", "  ").replaceAll("\\n", "\n  ");
        }
        sb.append(s);

        if (isRoot) {
            StringBuilder tmp = new StringBuilder(sb.toString().replaceAll("\n(\\s*)#(.*)", "\n#$2"));
            sb.delete(0, sb.length()).append(tmp.toString());
        }
    }

    private String realNodeName(String prefix, boolean flat) {
        return flat && prefix != null && !prefix.isEmpty() ? String.format("%s.%s", prefix, nodeName) : nodeName;
    }

    private TreeNode<T> ensureTreeNode(String key) {
        TreeNode<T> node = branches.get(key);
        if (node == null) {
            node = new TreeNode<>(key);
            branches.put(key, node);
        }
        return node;
    }

    private String nodeName;
    private T data;
    private Map<String, TreeNode<T>> branches = new HashMap<>();


}
