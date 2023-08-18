public class VariablesNameSpace extends BaseNameSpace {
    protected VariablesNameSpace() {
        super();
    }
    public void setVar(String name, Object value) {
        super.setKey(name,value);
    }
    public BlockE getVar(String name) {
        return  super.getKey(name);
    }
    public boolean containsVar(String name) {
        return super.containsKey(name);
    }
    public void removeVar(String name) {
        super.removeKey(name);
    }
}
