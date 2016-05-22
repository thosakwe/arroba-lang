package thosakwe.arroba.interpreter;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaNumber;
import thosakwe.arroba.interpreter.data.ArrobaString;
import thosakwe.arroba.interpreter.stdlib.ForFunction;
import thosakwe.arroba.interpreter.stdlib.PrintFunction;

import java.util.ArrayList;
import java.util.List;

/*
 * TODO: Both types of arrow expressions should be expressions,
 * and use a common method(left, right). Allow for assignments.
 * Will be cool.
 */
public class ArrobaInterpreter extends Scoped {
    public ArrobaInterpreter() {
        super();
        globalScope.symbols.put("print", new PrintFunction());
        globalScope.symbols.put("for", new ForFunction());
    }

    public ArrobaDatum resolveExpr(ArrobaParser.ExprContext expr) {
        if (expr instanceof ArrobaParser.NestedExprContext) {
            return resolveExpr(((ArrobaParser.NestedExprContext) expr).expr());
        } else if (expr instanceof ArrobaParser.IdExprContext) {
            return value(expr.getText());
        } else if (expr instanceof ArrobaParser.ArrowRightExprContext) {
            ArrobaParser.ArrowRightExprContext ctx = (ArrobaParser.ArrowRightExprContext) expr;
            ArrobaParser.ExprContext rightExpr = ctx.expr(1);
            ArrobaDatum left = resolveExpr(ctx.expr(0));
            ArrobaDatum right = resolveExpr(rightExpr);

            if (right instanceof ArrobaFunction) {
                List<ArrobaDatum> args = new ArrayList<>();
                args.add(left);

                return ((ArrobaFunction) right).invoke(args);
            } else if (rightExpr instanceof ArrobaParser.IdExprContext) {
                return value(((ArrobaParser.IdExprContext) rightExpr).ID().getText(), left, false);
            } else if (rightExpr instanceof ArrobaParser.LocalExprContext) {
                return value(((ArrobaParser.LocalExprContext) rightExpr).ID().getText(), left, true);
            }
        } else if (expr instanceof ArrobaParser.StringExprContext) {
            ArrobaParser.StringExprContext ctx = (ArrobaParser.StringExprContext) expr;
            String text = ctx.getText().replaceAll("(^\")|(\"$)", "");
            return new ArrobaString(text, ctx, this);
        } else if (expr instanceof ArrobaParser.FunctionExprContext) {
            return new ArrobaFullFunction((ArrobaParser.FunctionExprContext) expr, this);
        } else if (expr instanceof ArrobaParser.InlineFunctionExprContext) {
            return new ArrobaInlineFunction((ArrobaParser.InlineFunctionExprContext) expr, this);
        } else if (expr instanceof ArrobaParser.MathExprContext) {
            return resolveMathExpr((ArrobaParser.MathExprContext) expr);
        } else if (expr instanceof ArrobaParser.NumExprContext) {
            return new ArrobaNumber((ArrobaParser.NumExprContext) expr);
        } else if (expr instanceof ArrobaParser.InvocationExprContext) {
            ArrobaDatum target = resolveExpr(((ArrobaParser.InvocationExprContext) expr).target);
            if (target instanceof ArrobaFunction) {
                createChildScope();
                List<ArrobaParser.ExprContext> exprs = ((ArrobaParser.InvocationExprContext) expr).expr();
                List<ArrobaDatum> args = new ArrayList<ArrobaDatum>();
                exprs.remove(0);

                for (ArrobaParser.ExprContext exprContext : exprs) {
                    args.add(resolveExpr(exprContext));
                }

                System.out.println("before invocationexpr");
                ArrobaDatum result = ((ArrobaFunction) target).invoke(args);
                System.out.println("after invocationexpr");
                exitLastScope();
                return result;
            } else {
                System.err.println(((ArrobaParser.InvocationExprContext) expr).target.getText() + " is not a function");
            }
        }

        return null;
    }

    private ArrobaNumber resolveMathExpr(ArrobaParser.MathExprContext ctx) {
        ArrobaParser.ExprContext leftExpr = ctx.expr(0);
        ArrobaParser.ExprContext rightExpr = ctx.expr(1);
        ArrobaDatum leftValue = resolveExpr(leftExpr);
        ArrobaDatum rightValue = resolveExpr(rightExpr);

        ArrobaNumber left = (leftValue instanceof ArrobaNumber) ? (ArrobaNumber) leftValue : ArrobaNumber.Zero();
        ArrobaNumber right = (rightValue instanceof ArrobaNumber) ? (ArrobaNumber) rightValue : ArrobaNumber.Zero();

        /*System.out.println("left: " + left);
        System.out.println("right: " + right);*/

        if (ctx.PLUS() != null)
            return ArrobaNumber.From(left.value + right.value);
        else if (ctx.MINUS() != null)
            return ArrobaNumber.From(left.value - right.value);
        else if (ctx.TIMES() != null)
            return ArrobaNumber.From(left.value * right.value);
        else if (ctx.DIVIDE() != null)
            return ArrobaNumber.From(left.value / right.value);
        else if (ctx.CARET() != null)
            return ArrobaNumber.From(Math.pow(left.value, right.value));
        else if (ctx.MODULO() != null)
            return ArrobaNumber.From(left.value % right.value);

        return null;
    }

    @Override
    public void enterAssignStmt(ArrobaParser.AssignStmtContext ctx) {
        ArrobaParser.ExprContext left = ctx.expr(0);
        ArrobaParser.ExprContext right = ctx.expr(1);

        if (left instanceof ArrobaParser.LocalExprContext) {
            value(((ArrobaParser.LocalExprContext) left).ID().getText(), resolveExpr(right), true);
        } else {
            ArrobaDatum target = resolveExpr(left);

            if (target instanceof ArrobaFunction) {
                // This is a call
                createChildScope();
                List<ArrobaDatum> args = new ArrayList<>();
                args.add(resolveExpr(right));
                ((ArrobaFunction) target).invoke(args);
                exitLastScope();
            } else if (left instanceof ArrobaParser.IdExprContext) {
                value(left.getText(), resolveExpr(right), false);
                //System.out.println("Set " + left.getText() + " to (" + value(left.getText()) + ")");
            }
        }

        super.enterAssignStmt(ctx);
    }

    @Override
    public void enterExprStmt(ArrobaParser.ExprStmtContext ctx) {
        resolveExpr(ctx.expr());
        super.enterExprStmt(ctx);
    }

    @Override
    public void enterFunctionExpr(ArrobaParser.FunctionExprContext ctx) {
        System.out.println("Ok");
        //super.enterFunctionExpr(ctx);
    }
}
