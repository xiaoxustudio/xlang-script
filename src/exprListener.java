// Generated from F:/IdeaProjects/javademo/expr\expr.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link exprParser}.
 */
public interface exprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link exprParser#model}.
	 * @param ctx the parse tree
	 */
	void enterModel(exprParser.ModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#model}.
	 * @param ctx the parse tree
	 */
	void exitModel(exprParser.ModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(exprParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(exprParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(exprParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(exprParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#varStat}.
	 * @param ctx the parse tree
	 */
	void enterVarStat(exprParser.VarStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#varStat}.
	 * @param ctx the parse tree
	 */
	void exitVarStat(exprParser.VarStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(exprParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(exprParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#funcStat}.
	 * @param ctx the parse tree
	 */
	void enterFuncStat(exprParser.FuncStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#funcStat}.
	 * @param ctx the parse tree
	 */
	void exitFuncStat(exprParser.FuncStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(exprParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(exprParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#funcCallStat}.
	 * @param ctx the parse tree
	 */
	void enterFuncCallStat(exprParser.FuncCallStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#funcCallStat}.
	 * @param ctx the parse tree
	 */
	void exitFuncCallStat(exprParser.FuncCallStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(exprParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(exprParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#ternaryExp}.
	 * @param ctx the parse tree
	 */
	void enterTernaryExp(exprParser.TernaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#ternaryExp}.
	 * @param ctx the parse tree
	 */
	void exitTernaryExp(exprParser.TernaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#andOrExp}.
	 * @param ctx the parse tree
	 */
	void enterAndOrExp(exprParser.AndOrExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#andOrExp}.
	 * @param ctx the parse tree
	 */
	void exitAndOrExp(exprParser.AndOrExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#compExp}.
	 * @param ctx the parse tree
	 */
	void enterCompExp(exprParser.CompExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#compExp}.
	 * @param ctx the parse tree
	 */
	void exitCompExp(exprParser.CompExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#addExp}.
	 * @param ctx the parse tree
	 */
	void enterAddExp(exprParser.AddExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#addExp}.
	 * @param ctx the parse tree
	 */
	void exitAddExp(exprParser.AddExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#multExp}.
	 * @param ctx the parse tree
	 */
	void enterMultExp(exprParser.MultExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#multExp}.
	 * @param ctx the parse tree
	 */
	void exitMultExp(exprParser.MultExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExp(exprParser.PrimaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExp(exprParser.PrimaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(exprParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(exprParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(exprParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(exprParser.WhileStatContext ctx);
}