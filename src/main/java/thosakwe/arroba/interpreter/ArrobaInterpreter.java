package thosakwe.arroba.interpreter;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.data.*;
import thosakwe.arroba.interpreter.stdlib.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * TODO: Both types of arrow expressions should be expressions,
 * and use a common method(left, right). Allow for assignments.
 * Will be cool.
 */
public class ArrobaInterpreter extends Scoped {
    public Scanner scanner = new Scanner(System.in);

    public ArrobaInterpreter(String[] args) {
        super();
        globalScope.symbols.put("print", new PrintFunction());
        globalScope.symbols.put("input", new InputFunction(this));
        globalScope.symbols.put("for", new ForFunction());
        globalScope.symbols.put("all", new AllFunction());
        globalScope.symbols.put("any", new AnyFunction());
        globalScope.symbols.put("File", new FileFunction());
        globalScope.symbols.put("rgx", new RgxFunction());
        globalScope.symbols.put("cat", new CatFunction());

        List<ArrobaDatum> arguments = new ArrayList<>();

        for (String arg : args) {
            arguments.add(new ArrobaPureString(arg));
        }

        globalScope.symbols.put("argc", new ArrobaNumber(args.length * 1.0));
        globalScope.symbols.put("argv", new ArrobaArray(arguments));
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
        } else if (expr instanceof ArrobaParser.MemberExprContext) {
            ArrobaParser.MemberExprContext ctx = (ArrobaParser.MemberExprContext) expr;
            ArrobaDatum parent = visitExpr(ctx.expr());

            if (parent != null) {
                return parent.resolve(ctx.ID().getText());
            } else {
                System.err.println("Invalid expression: " + ctx.expr().getText());
            }
        } else if (expr instanceof ArrobaParser.StringExprContext) {
            ArrobaParser.StringExprContext ctx = (ArrobaParser.StringExprContext) expr;
            String text = ctx.getText().replaceAll("(^\")|(\"$)", "");
            return new ArrobaString(text, ctx, this);
        } else if (expr instanceof ArrobaParser.ConstBoolExprContext) {
            return new ArrobaNumber(((ArrobaParser.ConstBoolExprContext) expr).FALSE() == null ? 1.0 : 0.0);
        } else if (expr instanceof ArrobaParser.FunctionExprContext) {
            return new ArrobaFullFunction((ArrobaParser.FunctionExprContext) expr, this);
        } else if (expr instanceof ArrobaParser.InlineFunctionExprContext) {
            return new ArrobaInlineFunction((ArrobaParser.InlineFunctionExprContext) expr, this);
        } else if (expr instanceof ArrobaParser.MathExprContext) {
            return resolveMathExpr((ArrobaParser.MathExprContext) expr);
        } else if (expr instanceof ArrobaParser.NumExprContext) {
            return new ArrobaNumber((ArrobaParser.NumExprContext) expr);
        } else if (expr instanceof ArrobaParser.ArrayExprContext) {
            return new ArrobaArray(((ArrobaParser.ArrayExprContext) expr).expr(), this);
        } else if (expr instanceof ArrobaParser.IndexExprContext) {
            ArrobaParser.IndexExprContext ctx = (ArrobaParser.IndexExprContext) expr;
            ArrobaDatum target = visitExpr(ctx.target);

            if (target instanceof ArrobaArray) {
                ArrobaDatum index = visitExpr(ctx.index);
                return ((ArrobaArray) target).resolveIndex(index);
            } else {
                System.err.println("Given expression is not an array: " + ctx.target.getText());
                return null;
            }
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

    @Override
    public ArrobaDatum visitIfStmt(ArrobaParser.IfStmtContext ctx) {
        return super.visitIfStmt(ctx);
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
        } else if (left instanceof ArrobaParser.MemberExprContext) {
            ArrobaParser.MemberExprContext expr = (ArrobaParser.MemberExprContext) left;
            ArrobaDatum target = visitExpr(expr.expr());

            if (target != null) {
                target.setMember(expr.ID().getText(), visitExpr(right));
            } else {
                System.err.println("Invalid expression: " + expr.getText());
            }
        } else {
            ArrobaDatum target = visitExpr(left);

            /*if (target instanceof ArrobaFunction) {
                // This is a call
                createChildScope();
                List<ArrobaDatum> args = new ArrayList<>();
                args.add(visitExpr(right));
                result = ((ArrobaFunction) target).invoke(args);
                exitLastScope();
            } else*/
            if (left instanceof ArrobaParser.IdExprContext) {
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
