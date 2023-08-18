import java.util.HashMap;
import java.util.Map;

/**
 * 基础名称空间
 *
 * @author 17835
 * @date 2023/08/18
 */
public class BaseNameSpace {
    private final Map<String, Object> list;

    protected BaseNameSpace() {
        list = new HashMap<>();
    }

    protected void setKey(String name, Object value) {
        list.put(name, value);
    }

    protected BlockE getKey(String name) {
        return (BlockE) list.get(name);
    }

    protected boolean containsKey(String name) {
        return list.containsKey(name);
    }

    protected void removeKey(String name) {
        list.remove(name);
    }

}
