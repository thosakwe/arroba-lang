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


    public ArrobaDatum visitExpr(ArrobaParser.ExprContext expr) {
        if (expr instanceof ArrobaParser.NestedExprContext) {
            return visitExpr(((ArrobaParser.NestedExprContext) expr).expr());
        } else if (expr instanceof ArrobaParser.IdExprContext) {
            return value(expr.getText());
        } else if (expr instanceof ArrobaParser.ArrowRightExprContext) {
            ArrobaParser.ArrowRightExprContext ctx = (ArrobaParser.ArrowRightExprContext) expr;
            ArrobaParser.ExprContext rightExpr = ctx.expr(1);
            ArrobaDatum left = visitExpr(ctx.expr(0));
            ArrobaDatum right = visitExpr(rightExpr);

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
            ArrobaDatum target = visitExpr(((ArrobaParser.InvocationExprContext) expr).target);
            if (target instanceof ArrobaFunction) {
                createChildScope();
                List<ArrobaParser.ExprContext> exprs = ((ArrobaParser.InvocationExprContext) expr).expr();
                List<ArrobaDatum> args = new ArrayList<ArrobaDatum>();
                exprs.remove(0);

                for (ArrobaParser.ExprContext exprContext : exprs) {
                    args.add(visitExpr(exprContext));
                }

                ArrobaDatum result = ((ArrobaFunction) target).invoke(args);
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
        ArrobaDatum leftValue = visitExpr(leftExpr);
        ArrobaDatum rightValue = visitExpr(rightExpr);

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
    public ArrobaDatum visitAssignStmt(ArrobaParser.AssignStmtContext ctx) {
        ArrobaParser.ExprContext left = ctx.expr(0);
        ArrobaParser.ExprContext right = ctx.expr(1);
        ArrobaDatum result = null;

        if (left instanceof ArrobaParser.LocalExprContext) {
            value(((ArrobaParser.LocalExprContext) left).ID().getText(), visitExpr(right), true);
        } else {
            ArrobaDatum target = visitExpr(left);

            if (target instanceof ArrobaFunction) {
                // This is a call
                createChildScope();
                List<ArrobaDatum> args = new ArrayList<>();
                args.add(visitExpr(right));
                result = ((ArrobaFunction) target).invoke(args);
                exitLastScope();
            } else if (left instanceof ArrobaParser.IdExprContext) {
                result = visitExpr(right);
                value(left.getText(), result, false);
                //System.out.println("Set " + left.getText() + " to (" + value(left.getText()) + ")");
            }
        }

        return result;
    }

    @Override
    public ArrobaDatum visitExprStmt(ArrobaParser.ExprStmtContext ctx) {
        return visitExpr(ctx.expr());
    }

    @Override
    public ArrobaDatum visitFunctionExpr(ArrobaParser.FunctionExprContext ctx) {
        System.out.println("Ok");
        //super.enterFunctionExpr(ctx);
        return super.visitFunctionExpr(ctx);
    }
}
