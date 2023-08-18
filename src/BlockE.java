import java.util.HashMap;

public class BlockE extends HashMap {
    private Boolean is_set=false;
    public enum KEY{
        NUM,STRING,BOOL,FUNC,VAR,OBJ,RES,NONE,ID,ERR
    }
    public BlockE(){
            putType(BlockE.KEY.NONE);
            putValue("");
    }
    public BlockE(boolean b){
    }
    public Object putValue(Object value) {
        String key = "value";
        Object res = super.put(key, value);
        return res;
    }
    public Object putType(Object value) {
        String key = "type";
        Object res = super.put(key, value);
        return res;
    }
    public Object getV(Object value){
        if(!((KEY) getOrDefault("type",KEY.NONE)==KEY.NONE)){
            KEY type = (KEY) get("type");
            if (type==KEY.STRING){
                return new String(String.valueOf(get("value")));
            }else if (type== KEY.NUM){
                return (Integer.parseInt((String) get("value")));
            }else if (type == KEY.BOOL){
                if(new String((String) get("value")).toLowerCase() == "true"){
                    return (true);
                }else{
                    return (false);
                }
            }
        }
        return value;
    }
    public boolean isNone(){
        return (KEY) get("type")==KEY.NONE;
    }
    public boolean isEmpty(){
        if ((int) get("value").toString().length()==0){
            return true;
        }else {
            return false;
        }
    }
}
