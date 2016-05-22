// Generated from C:/Users/tobe/Source/Java/arroba/src/main/antlr4/thosakwe/arroba/antlr\Arroba.g4 by ANTLR 4.5.1
package thosakwe.arroba.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArrobaParser}.
 */
public interface ArrobaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArrobaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(ArrobaParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArrobaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(ArrobaParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArrobaParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(ArrobaParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArrobaParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(ArrobaParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArrobaParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(ArrobaParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArrobaParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(ArrobaParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArrobaParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(ArrobaParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArrobaParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(ArrobaParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArrobaParser#retStmt}.
	 * @param ctx the parse tree
	 */
	void enterRetStmt(ArrobaParser.RetStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArrobaParser#retStmt}.
	 * @param ctx the parse tree
	 */
	void exitRetStmt(ArrobaParser.RetStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringExpr(ArrobaParser.StringExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringExpr(ArrobaParser.StringExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionExpr(ArrobaParser.FunctionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionExpr(ArrobaParser.FunctionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(ArrobaParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(ArrobaParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumExpr(ArrobaParser.NumExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumExpr(ArrobaParser.NumExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LocalExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLocalExpr(ArrobaParser.LocalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LocalExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLocalExpr(ArrobaParser.LocalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpr(ArrobaParser.MemberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpr(ArrobaParser.MemberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NestedExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNestedExpr(ArrobaParser.NestedExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NestedExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNestedExpr(ArrobaParser.NestedExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VectorExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVectorExpr(ArrobaParser.VectorExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VectorExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVectorExpr(ArrobaParser.VectorExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InlineFunctionExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInlineFunctionExpr(ArrobaParser.InlineFunctionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InlineFunctionExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInlineFunctionExpr(ArrobaParser.InlineFunctionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MathExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMathExpr(ArrobaParser.MathExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MathExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMathExpr(ArrobaParser.MathExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrowRightExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrowRightExpr(ArrobaParser.ArrowRightExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrowRightExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrowRightExpr(ArrobaParser.ArrowRightExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InvocationExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInvocationExpr(ArrobaParser.InvocationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InvocationExpr}
	 * labeled alternative in {@link ArrobaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInvocationExpr(ArrobaParser.InvocationExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArrobaParser#paramSpec}.
	 * @param ctx the parse tree
	 */
	void enterParamSpec(ArrobaParser.ParamSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArrobaParser#paramSpec}.
	 * @param ctx the parse tree
	 */
	void exitParamSpec(ArrobaParser.ParamSpecContext ctx);
}