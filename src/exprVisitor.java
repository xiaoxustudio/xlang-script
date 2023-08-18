// Generated from F:/IdeaProjects/javademo/expr\expr.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link exprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface exprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link exprParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(exprParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(exprParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(exprParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#varStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarStat(exprParser.VarStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(exprParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#funcStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncStat(exprParser.FuncStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(exprParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#funcCallStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallStat(exprParser.FuncCallStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(exprParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#ternaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExp(exprParser.TernaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#andOrExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOrExp(exprParser.AndOrExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#compExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExp(exprParser.CompExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#addExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExp(exprParser.AddExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#multExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExp(exprParser.MultExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(exprParser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#ifStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(exprParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#whileStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(exprParser.WhileStatContext ctx);
}