// Generated from C:/Users/tobe/Source/Java/arroba/src/main/antlr4/thosakwe/arroba/antlr\Arroba.g4 by ANTLR 4.5.1
package thosakwe.arroba.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ArrobaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ArrobaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ArrobaParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(ArrobaParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArrobaParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(ArrobaParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArrobaParser#assignStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(ArrobaParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArrobaParser#exprStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(ArrobaParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArrobaParser#retStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetStmt(ArrobaParser.RetStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpr(ArrobaParser.StringExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionExpr(ArrobaParser.FunctionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(ArrobaParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumExpr(ArrobaParser.NumExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LocalExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalExpr(ArrobaParser.LocalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExpr(ArrobaParser.MemberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NestedExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedExpr(ArrobaParser.NestedExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VectorExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVectorExpr(ArrobaParser.VectorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InlineFunctionExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineFunctionExpr(ArrobaParser.InlineFunctionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MathExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathExpr(ArrobaParser.MathExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrowRightExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrowRightExpr(ArrobaParser.ArrowRightExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InvocationExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvocationExpr(ArrobaParser.InvocationExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArrobaParser#paramSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamSpec(ArrobaParser.ParamSpecContext ctx);
}