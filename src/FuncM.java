import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FuncM {
    private final FuncM instance;

    public FuncM(){
        this.instance=this;
    }
    public BlockE print(BlockE[] te){
        BlockE e = new BlockE();
        List res =  CompilteArg(te);
        for(int i = 0; i < res.size() ; i ++){
            System.out.println(res.get(i));
        }
        return e;
    }
public String CompileString(String value){
    if(value.toString().endsWith("\"")&&value.toString().startsWith("\"")){
        return value.toString().substring(1,value.toString().length()-1);
    }else {
        return value;
    }
}
    /**
     * compilte
     * 将数组块编译成常规数组
     * @param args 块
     * @return {@link Object}
     */
    public List CompilteArg(BlockE[] args){
        List<Object> res = new ArrayList<>(args.length);
        for (int i = 0 ; i<args.length;i++){
            BlockE.KEY type = (BlockE.KEY) args[i].get("type");
            String value = (String) args[i].get("value");
            if(type== BlockE.KEY.NUM){
                res.add(Integer.parseInt(value));
            }else if (type== BlockE.KEY.STRING){
                res.add(CompileString(value));
            }else if (type== BlockE.KEY.BOOL){
                res.add(Boolean.parseBoolean(value));
            } else if (type == BlockE.KEY.VAR){
                res.add(value);
            }else if (type == BlockE.KEY.FUNC){
                res.add(value);
            }
        }
        return res;
    }



}
