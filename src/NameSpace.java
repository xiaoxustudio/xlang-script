import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 名称空间
 *
 * @author 17835
 * @date 2023/08/17
 */
public class NameSpace<K, V> extends HashMap<K, V> {
    private Map<Object,Object> ChildNameSpaceList; // 总命名空间池
    private VariablesNameSpace variablesNameSpace; // 变量命名空间池
    private long id = 0;
    private NameSpace parent ;
    public NameSpace(){
        this.ChildNameSpaceList=new HashMap();
        this.variablesNameSpace = new VariablesNameSpace();

    }

    /**
     * 创建块
     * 返回子命名空间id
     * @return long
     */
    public long create_chunk(){
        long Id = System.currentTimeMillis();
        NameSpace chunk = new NameSpace();
        // 初始化子命名空间
        //存变量
        Map var_ns = new HashMap();
        //存函数
        Map func_ns = new HashMap();
        //存其他
        Map other_ns = new HashMap();

        chunk.id = Id;
        chunk.parent = this;
        //保存到总池
        this.ChildNameSpaceList.put(Id,chunk);
        return Id;
    }



    @Override
    public Object put(Object key, Object value) {
        return this.ChildNameSpaceList.put(key,value);
    }

    @Override
    public V get(Object key) {
        return (V) this.ChildNameSpaceList.get(key);
    }
    /**
     * getNS
     *  获取命名空间
     * @param id id
     * @return {@link Object}
     */
    public Object get(long id){
        return this.ChildNameSpaceList.get(id);
    }
    public Object getVarNS(){
        return this.variablesNameSpace;
    }
    public Object getFuncNS(){
        return get("func_ns");
    }
    public Object getOtherNS(){
        return get("other_ns");
    }
    public Map getNS(){
        return this.ChildNameSpaceList;
    }
    public BlockE getChildList(){
        BlockE res = new BlockE(false);
        for (Map.Entry<Object, Object> entry : ChildNameSpaceList.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            res.put("key",key);
            if (value instanceof NameSpace){
                NameSpace ns = (NameSpace) value;
                res.put("value",ns);
            }else {
                res.put("value",value);
            }
        }
        return res;
    }

}
