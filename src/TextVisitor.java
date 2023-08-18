import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class TextVisitor extends exprBaseVisitor<BlockE> {
    private final NameSpace ns; // 总命名空间池对象
    private long id; //当前使用的id
    private FuncM funcm = new FuncM();

    public TextVisitor(NameSpace ns) {
        super();
        this.ns = ns;
    }

    public TextVisitor(NameSpace ns, long id) {
        this.ns = ns;
        // 使用传入的命名空间
        this.id = id;
    }

    @Override
    public BlockE visitStat(exprParser.StatContext ctx) {
        // 语句块
        return super.visitStat(ctx);
    }

    @Override
    public BlockE visitArgList(exprParser.ArgListContext ctx) {
        return super.visitArgList(ctx);
    }

    @Override
    public BlockE visitValue(exprParser.ValueContext ctx) {
        BlockE e = visit(ctx.expr(0));
        BlockE res;
        if (!ctx.BR_OPEN().isEmpty() && !ctx.BR_CLOSE().isEmpty()){
            res = visit(ctx.expr(1));
        }else{
            res = e;
        }
        return res;
    }

    @Override
    public BlockE visitVarStat(exprParser.VarStatContext ctx) {
        String head = ctx.VARKEY().getText();
        String name = ctx.NAME().getText();
        String e = ctx.EQUAL().getText();
        BlockE value = visit(ctx.value());

        BlockE res = new BlockE(true);
        res.put("Ftype", "VarStat");
        res.put("op", head);
        res.put("name", name);
        if (value.get("type") == BlockE.KEY.NONE) {
            value.putValue("null");
            res.put("val", value);
        } else {
            res.put("val", value);
        }
        // 变量块存入命名空间
        VariablesNameSpace varNS = (VariablesNameSpace) getNS().getVarNS();
        varNS.setVar(name, res);
        return res;
    }

    @Override
    public BlockE visitTernaryExp(exprParser.TernaryExpContext ctx) {
        BlockE res = new BlockE();
        BlockE left = visit(ctx.andOrExp());
        BlockE mid = new BlockE();
        if (ctx.expr(0) != null) {
            mid = visit(ctx.expr(0));
        } else {
            mid.putType(BlockE.KEY.NONE);
            mid.putValue("");
        }
        BlockE right = new BlockE();
        if (ctx.expr(1) != null) {
            right = visit(ctx.expr(1));
        } else {
            right.putType(BlockE.KEY.NONE);
            right.putValue("");
        }
        // 三元只能判断布尔值，不是布尔值就不做处理
        if ((BlockE.KEY) left.get("type") == BlockE.KEY.BOOL && !left.isNone() && !ctx.QMARK().isEmpty()) {
            if ((String) left.get("value") == "true") {
                res.putType(mid.get("type"));
                res.putValue(mid.get("value"));
            } else if (!ctx.COLON().isEmpty() && right != null) {
                res.putType(right.get("type"));
                res.putValue(right.get("value"));
            } else {
                res.putType(BlockE.KEY.BOOL);
                res.putValue("false");
            }
        } else {
            // 只会写入左侧的值
            res.putType((BlockE.KEY) left.get("type"));
            res.putValue((String) left.get("value"));
        }

        return res;
    }

    @Override
    public BlockE visitAndOrExp(exprParser.AndOrExpContext ctx) {
        BlockE res = new BlockE(false);
        BlockE left = visit(ctx.compExp(0));
        BlockE right = new BlockE(false);
        if (ctx.compExp(1) != null) {
            right = visit(ctx.compExp(1));
        } else {
            right.putType(BlockE.KEY.NONE);
            right.putValue("");
        }

        // 比较 and需要两个 , or 需要一个
        if (!ctx.AND().isEmpty()) {
            if (right != null && left != null) {
                if ((String) left.get("value") == "true" && (String) right.get("value") == "true") {
                    res.putType(BlockE.KEY.BOOL);
                    res.putValue("true");
                } else {
                    res.putType(BlockE.KEY.BOOL);
                    res.putValue("false");
                }
            } else {
                // 只会写入左侧的值
                res.putType((BlockE.KEY) left.get("type"));
                res.putValue((String) left.get("value"));
            }
        } else if (!ctx.OR().isEmpty()) {
            if (right != null && left != null) {
                if ((String) left.get("value") == "true" || (String) right.get("value") == "true") {
                    res.putType(BlockE.KEY.BOOL);
                    res.putValue("true");
                } else {
                    res.putType(BlockE.KEY.BOOL);
                    res.putValue("false");
                }
            } else {
                // 只会写入左侧的值
                res.putType((BlockE.KEY) left.get("type"));
                res.putValue((String) left.get("value"));
            }
        } else if ((BlockE.KEY) left.get("type") == BlockE.KEY.VAR) {
            return super.visitAndOrExp(ctx);
        } else {
            // 只会写入左侧的值
            res.putType((BlockE.KEY) left.get("type"));
            res.putValue((String) left.get("value"));
        }

        return res;
    }


    @Override
    public BlockE visitCompExp(exprParser.CompExpContext ctx) {
        BlockE res = new BlockE(false);
        BlockE left = visit(ctx.addExp(0));
        BlockE right = new BlockE(false);
        if (ctx.addExp(1) != null) {
            right = visit(ctx.addExp(1));
        } else {
            right.putType(BlockE.KEY.NONE);
            right.putValue("");
        }

        // 必须要两个才可以比较，且左右不可为空
        if (right != null && left != null && !left.isNone() && !right.isNone()) {
            // 判断是否有比较的运算符
            // ==
            if (!ctx.DEQUAL().isEmpty()) {
                res.putType(BlockE.KEY.BOOL);
                //类型不同直接为false

                if (left.get("type") == right.get("type")) {
                    if (left.get("value").equals((String) right.get("value"))) {
                        res.putValue("true");
                    } else {
                        res.putValue("false");
                    }
                } else {
                    res.putValue("false");
                }
            } else // !=
                if (!ctx.EXCEQUAL().isEmpty()) {
                    res.putType(BlockE.KEY.BOOL);
                    //类型不同直接为false
                    if (left.get("type") != right.get("type")) {
                        if (left.get("value") != right.get("value")) {
                            res.putValue("true");
                        } else {
                            res.putValue("false");
                        }
                    } else {
                        res.putValue("false");
                    }
                } else {
                    // 那么就是数字比较，强制为数字
                    // >
                    if (!ctx.GRET().isEmpty()) {
                        res.putType(BlockE.KEY.BOOL);
                        if ((BlockE.KEY) left.get("type") == BlockE.KEY.NUM) {
                            if ((Integer) left.get("value") > (Integer) right.get("value")) {
                                res.putValue("true");
                            } else {
                                res.putValue("false");
                            }
                        } else {
                            res.putValue("false");
                        }
                    } else// <
                        if (!ctx.LESST().isEmpty()) {
                            res.putType(BlockE.KEY.BOOL);
                            if ((BlockE.KEY) left.get("type") == BlockE.KEY.NUM) {
                                if ((Integer) left.get("value") < (Integer) right.get("value")) {
                                    res.putValue("true");
                                } else {
                                    res.putValue("false");
                                }
                            } else {
                                res.putValue("false");
                            }
                        } else // >=
                            if (!ctx.GRETEQUAL().isEmpty()) {
                                res.putType(BlockE.KEY.BOOL);
                                if ((BlockE.KEY) left.get("type") == BlockE.KEY.NUM) {
                                    if ((Integer) left.get("value") >= (Integer) right.get("value")) {
                                        res.putValue("true");
                                    } else {
                                        res.putValue("false");
                                    }
                                } else {
                                    res.putValue("false");
                                }
                            } else // <=
                                if (!ctx.LESSTEQUAL().isEmpty()) {
                                    res.putType(BlockE.KEY.BOOL);
                                    if ((BlockE.KEY) left.get("type") == BlockE.KEY.NUM) {
                                        if ((Integer) left.get("value") >= (Integer) right.get("value")) {
                                            res.putValue("true");
                                        } else {
                                            res.putValue("false");
                                        }
                                    } else {
                                        res.putValue("false");
                                    }
                                }
                }

        } else if ((BlockE.KEY) left.get("type") == BlockE.KEY.VAR) {
            return super.visitCompExp(ctx);
        } else {
            // 只会写入左侧的值
            res.putType((BlockE.KEY) left.get("type"));
            res.putValue(new String(String.valueOf(left.get("value"))));
        }
        return res;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public BlockE visitAddExp(exprParser.AddExpContext ctx) {
        BlockE res = new BlockE(false);
        List<exprParser.MultExpContext> multExpContexts = ctx.multExp();
        List<BlockE> expressions = new ArrayList<>();
        // 备用字符串块
        String res1 = "";
        for (exprParser.MultExpContext multExpContext : multExpContexts) {
            BlockE expression = visit(multExpContext);
            expressions.add(expression);
        }

        if (expressions.size() == 1) {
            return expressions.get(0);
        }

        if (!expressions.isEmpty()) {
            BlockE tempRes = expressions.get(0);
            for (int i = 1; i < expressions.size(); i++) {
                BlockE currExp = expressions.get(i);
                System.out.println(currExp);

                if((BlockE.KEY) currExp.get("type") == BlockE.KEY.NONE
                        || (BlockE.KEY) tempRes.get("type")== BlockE.KEY.NONE){
                    // 如果有空类型的表达式，就跳过不处理
                    res.putType(BlockE.KEY.ERR);
                    break;
                }else if ((BlockE.KEY) currExp.get("type") != BlockE.KEY.NUM
                        && (BlockE.KEY) tempRes.get("type")== BlockE.KEY.STRING
                        || (BlockE.KEY) tempRes.get("type") != BlockE.KEY.NUM
                        && (BlockE.KEY) currExp.get("type")== BlockE.KEY.STRING) {
                    // 如果有非数字类型的表达式，且有一个为字符串
                    String value = (String) currExp.get("value");
                        res1 = res1 +this.funcm.CompileString(value);
                    continue;
                }else if ((BlockE.KEY) currExp.get("type") != BlockE.KEY.NUM || (BlockE.KEY) tempRes.get("type") != BlockE.KEY.NUM) {
                    // 如果有非数字类型的表达式，就跳过不处理
                    continue;
                }

                if (ctx.PLUS(i - 1)!=null) {
                    int sum = Integer.parseInt((String) tempRes.get("value")) + Integer.parseInt((String) currExp.get("value"));
                    tempRes.putValue(sum);
                } else if (ctx.MINUS(i - 1)!=null) {
                    int diff = Integer.parseInt((String) tempRes.get("value")) - Integer.parseInt((String) currExp.get("value"));
                    tempRes.putValue(diff);
                }
            }

            if(res1.length()!=0){
                res.putType(BlockE.KEY.STRING);
                res.putValue(this.funcm.CompileString((String) tempRes.get("value"))+this.funcm.CompileString(res1));
            }else if((BlockE.KEY)res.get("type")!= BlockE.KEY.ERR) {
                res.putType(BlockE.KEY.NUM);
                res.putValue(tempRes.get("value"));
            }
        }

        return res;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public BlockE visitMultExp(exprParser.MultExpContext ctx) {
        BlockE res = new BlockE(false);
        List<exprParser.PrimaryExpContext> primaryExpContexts = ctx.primaryExp();
        List<BlockE> expressions = new ArrayList<>();
        for (exprParser.PrimaryExpContext primaryExpContext : primaryExpContexts) {
            BlockE expression = visit(primaryExpContext);
            expressions.add(expression);
        }

        if (expressions.size() == 1) {
            return expressions.get(0);
        }

        if (!expressions.isEmpty()) {
            BlockE tempRes = expressions.get(0);
            for (int i = 1; i < expressions.size(); i++) {
                BlockE currExp = expressions.get(i);

                if ((BlockE.KEY) currExp.get("type") == BlockE.KEY.NONE
                        || (BlockE.KEY) tempRes.get("type") == BlockE.KEY.NONE) {
                // 如果有空类型的表达式，
                    res.putType(BlockE.KEY.ERR);
                    break;
                }
                else if ((BlockE.KEY) currExp.get("type") != BlockE.KEY.NUM && (BlockE.KEY) tempRes.get("type") == BlockE.KEY.STRING || (BlockE.KEY) tempRes.get("type") != BlockE.KEY.NUM && (BlockE.KEY) currExp.get("type") == BlockE.KEY.STRING) {
                    // 如果有非数字类型的表达式，且有一个为字符串
                    continue;
                } else if ((BlockE.KEY) currExp.get("type") != BlockE.KEY.NUM || (BlockE.KEY) tempRes.get("type") != BlockE.KEY.NUM) {
                    // 如果有非数字类型的表达式，就跳过不处理
                    continue;
                }

                if (ctx.DIVIDE(i - 1) != null) {
                    int sum = Integer.parseInt((String) tempRes.get("value")) / Integer.parseInt((String) currExp.get("value"));
                    tempRes.putValue(sum);
                } else if (ctx.TIMES(i - 1) != null) {
                    int diff = Integer.parseInt((String) tempRes.get("value")) * Integer.parseInt((String) currExp.get("value"));
                    tempRes.putValue(diff);
                }
            }
            if((BlockE.KEY)res.get("type")!= BlockE.KEY.ERR){
                res.putType(BlockE.KEY.NUM);
                res.putValue(tempRes.get("value"));
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public BlockE visitPrimaryExp(exprParser.PrimaryExpContext ctx) {
        BlockE res = new BlockE(false);
        if (ctx.NUM() != null) {
            res.putType(BlockE.KEY.NUM);
            res.putValue(ctx.NUM().getText());
        } else if (ctx.STRING() != null) {
            res.putType(BlockE.KEY.STRING);
            res.putValue(this.funcm.CompileString(ctx.STRING().getText()));
        } else if (ctx.NAME() != null) {
            // 执行布尔值解析
            if (ctx.NAME().getText().toLowerCase().equals("true")) {
                res.putType(BlockE.KEY.BOOL);
                res.putValue(ctx.NAME().getText());
            } else if (ctx.NAME().getText().toLowerCase().equals("false")) {
                res.putType(BlockE.KEY.BOOL);
                res.putValue(ctx.NAME().getText());
            } else {
                // 变量
                VariablesNameSpace var_NS = (VariablesNameSpace) getNS().getVarNS();
                BlockE r = (BlockE) var_NS.getVar(ctx.NAME().getText());
                if (r != null) {
                    res.putType((BlockE.KEY) ((BlockE)r.get("val")).get("type"));
                    res.putValue((String) ((BlockE)r.get("val")).get("value"));
                } else {
                    res.putType(BlockE.KEY.NONE);
                    res.putValue("null");
                }
            }
        } else if (ctx.funcCallStat() != null) {
            res.putType(BlockE.KEY.FUNC);
            res.putValue(ctx.funcCallStat().getText());
        }else if (ctx.BR_OPEN()!=null && ctx.BR_CLOSE()!=null){
        }

        return res;
    }


    @Override
    public BlockE visitIfStat(exprParser.IfStatContext ctx) {
        BlockE expr = visit(ctx.expr());
        BlockE res = new BlockE(false);
        if (!((BlockE.KEY) expr.get("type") == BlockE.KEY.BOOL)) {
            // 不是布尔值，强制让其为布尔值
            expr.putType(BlockE.KEY.BOOL);
            expr.putValue("true");
        }
        // 是布尔值，判断其值
        if (expr.get("value").toString().equals("true")) {
            exprParser.BlockContext s =  ctx.block(0);
            new TextVisitor((NameSpace) this.ns, this.id).visit(s);
        } else if (ctx.ELSE()!=null && ctx.LP(1)!=null && ctx.RP(1)!=null){
            exprParser.BlockContext s =  ctx.block(1);
                new TextVisitor((NameSpace) this.ns, this.id).visit(s);
        }
        return res;
    }

    public NameSpace getNS() {
        return this.ns;
    }

    @Override
    public BlockE visitFuncCallStat(exprParser.FuncCallStatContext ctx) {
        String name = ctx.NAME().getText();
        exprParser.ArgListContext arg = ctx.argList();
        if (arg != null) {
            // 定义参数列表块
            BlockE[] arglist = new BlockE[((int) arg.expr().size())];
            for (int i = 0; i < arg.expr().size(); i++) {
                exprParser.ExprContext exprun = (exprParser.ExprContext) arg.expr().get(i);
                BlockE a = visit(exprun);
//                System.out.println(a);
                arglist[i] = a;
            }
            // 运行函数
//            System.out.println(arg.expr());
            BlockE res= Call(name, arglist);
//            System.out.println(Arrays.toString(new List[]{funcm.CompilteArg(arglist)}));
        }
        return new BlockE();
    }

    public BlockE Call(String name, BlockE[] arg){
        BlockE res = new BlockE();
        try {
            res.putType(BlockE.KEY.RES);
            Class<?> targetClass = FuncM.class;
            // 方法名称和参数列表
            String methodName = name;
            Class<?>[] parameterTypes = {BlockE[].class};
            // 获取方法对象
            Method method = targetClass.getDeclaredMethod(methodName, parameterTypes);

            if(method!=null){
                Object obj = targetClass.newInstance();
                Object result = method.invoke(obj, new Object[]{arg});
                if (result!=null){
                    res.putValue(result);
                }else {
                    res.putValue(null);
                }
                return res;
            }else {
                res.putValue(null);
                return res;
            }
        } catch (NoSuchMethodException e){
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}

