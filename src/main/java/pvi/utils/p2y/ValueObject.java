package pvi.utils.p2y;

import java.util.List;

public class ValueObject<T> {

    private T data;
    private String comments;

    public T getData() {
        return data;
    }

    public String getComments() {
        return comments;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public String asYaml(String key) {
        StringBuilder sb = new StringBuilder();
        sb.append(key).append(":");
        if (data != null) {
            if (data instanceof Number) {
                sb.append(' ').append(data);
            } else if (data instanceof List) {
                ((List) data).stream().forEach(e -> sb.append("\n  - ").append(e));
            } else if (data instanceof String || data instanceof Boolean) {
                String sD = String.valueOf(data);
                if (sD.endsWith("\"") && sD.startsWith("\"")) {
                    sb.append(' ').append(data);
                } else {
                    sb.append(" \"").append(data).append('"');
                }
            }
        }
        return sb.toString();
    }
}
