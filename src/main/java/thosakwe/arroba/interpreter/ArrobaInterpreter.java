package thosakwe.arroba.interpreter;


import org.antlr.v4.runtime.tree.TerminalNode;


import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.data.*;
import thosakwe.arroba.interpreter.stdlib.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrobaInterpreter extends Scoped {
    public Scanner scanner = new Scanner(System.in);

    public ArrobaInterpreter(String[] args) {
        super();
        try {
            globalScope.symbols.put("import", new ImportFunction(this));
        } catch (Exception e) {
            System.err.println("Could not find include dir. Standard imports will not work.");
        }
        globalScope.symbols.put("eval", new EvalFunction(this));
        globalScope.symbols.put("print", new PrintFunction());
        globalScope.symbols.put("printErr", new PrintErrFunction());
        globalScope.symbols.put("input", new InputFunction(this));
        globalScope.symbols.put("for", new ForFunction());
        globalScope.symbols.put("all", new AllFunction());
        globalScope.symbols.put("any", new AnyFunction());
        globalScope.symbols.put("File", new FileFunction());
        globalScope.symbols.put("Directory", new DirectoryFunction());
        globalScope.symbols.put("Socket", new SocketFunction());
        globalScope.symbols.put("Task", new TaskFunction());
        globalScope.symbols.put("Process", new ProcessTask());
        globalScope.symbols.put("rgx", new RgxFunction());
        globalScope.symbols.put("cat", new CatFunction());
        globalScope.symbols.put("Exception", new ExceptionFunction());
        globalScope.symbols.put("Event", new EventFunction());
        globalScope.symbols.put("quit", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty()) {
                    ArrobaDatum status = args.get(0);
                    ArrobaNumber result = (status instanceof ArrobaNumber) ? (ArrobaNumber) status : ArrobaNumber.False();
                    System.exit(result.value.intValue());
                    return null;
                }
                System.exit(0);
                return null;
            }

            @Override
            public String toString() {
                return "<Native Function> quit()";
            }
        });

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
                return new ArrobaException("Invalid expression: " + ctx.expr().getText());
            }
        } else if (expr instanceof ArrobaParser.RawStringExprContext) {
            ArrobaParser.RawStringExprContext ctx = (ArrobaParser.RawStringExprContext) expr;
            String text = ctx.getText().replaceAll("(^r\")|(\"$)", "");
            return new ArrobaPureString(text);
        } else if (expr instanceof ArrobaParser.StringExprContext) {
            ArrobaParser.StringExprContext ctx = (ArrobaParser.StringExprContext) expr;
            String text = ctx.getText().replaceAll("(^\")|(\"$)", "");
            return new ArrobaString(text, ctx, this);
        } else if (expr instanceof ArrobaParser.ConstBoolExprContext) {
            return new ArrobaNumber(((ArrobaParser.ConstBoolExprContext) expr).FALSE() == null ? 1.0 : 0.0);
        } else if (expr instanceof ArrobaParser.BoolExprContext) {
            return visitBoolExpr((ArrobaParser.BoolExprContext) expr);
        } else if (expr instanceof ArrobaParser.NegationExprContext) {
            ArrobaDatum resolved = visitExpr(((ArrobaParser.NegationExprContext) expr).expr());
            Boolean truth = (resolved == null) ? false : resolved.toBool();

            return new ArrobaNumber(truth ? 1.0 : 0.0);
        } else if (expr instanceof ArrobaParser.FunctionExprContext) {
            ArrobaFunction result = new ArrobaFullFunction((ArrobaParser.FunctionExprContext) expr, this);
            result.hoist(expr, this);

            if(((ArrobaParser.FunctionExprContext) expr).ID() != null) {
                value(((ArrobaParser.FunctionExprContext) expr).ID().getText(), result);
            }

            return result;
        } else if (expr instanceof ArrobaParser.InlineFunctionExprContext) {
            ArrobaFunction result = new ArrobaInlineFunction((ArrobaParser.InlineFunctionExprContext) expr, this);
            result.hoist(expr, this, true);
            if(((ArrobaParser.InlineFunctionExprContext) expr).ID() != null) {
                value(((ArrobaParser.InlineFunctionExprContext) expr).ID().getText(), result);
            }

            return result;
        } else if (expr instanceof ArrobaParser.MathExprContext) {
            return resolveMathExpr((ArrobaParser.MathExprContext) expr);
        } else if (expr instanceof ArrobaParser.NumExprContext) {
            return new ArrobaNumber((ArrobaParser.NumExprContext) expr);
        } else if (expr instanceof ArrobaParser.ArrayExprContext) {
            return new ArrobaArray(((ArrobaParser.ArrayExprContext) expr).expr(), this);
        } else if (expr instanceof ArrobaParser.AwaitExprContext) {
            List<ArrobaParser.ExprContext> exprs = ((ArrobaParser.AwaitExprContext) expr).expr();
            ArrobaDatum target = visitExpr(((ArrobaParser.AwaitExprContext) expr).target);

            if (target instanceof ArrobaFunction) {
                List<ArrobaDatum> args = new ArrayList<>();

                for (int i = 1; i < exprs.size(); i++) {
                    args.add(visitExpr(exprs.get(i)));
                }

                target = ((ArrobaFunction) target).invoke(args);
            }

            if (target instanceof ArrobaTask) {
                List<ArrobaDatum> args = new ArrayList<>();

                for (int i = 1; i < exprs.size(); i++) {
                    args.add(visitExpr(exprs.get(i)));
                }

                ArrobaFunction yielder = (ArrobaFunction) target.members.get("yield");
                return yielder.invoke(args);
            }

            String message = "You can only await tasks.";

            if (target == null)
                return new ArrobaException(message + " Null values cannot be awaited!");
            else return new ArrobaException(message + " Instead, you tried to await this: " + target.toString());
        } else if (expr instanceof ArrobaParser.IndexExprContext) {
            ArrobaParser.IndexExprContext ctx = (ArrobaParser.IndexExprContext) expr;
            ArrobaDatum target = visitExpr(ctx.target);

            if (target instanceof ArrobaArray) {
                ArrobaDatum index = visitExpr(ctx.index);
                return ((ArrobaArray) target).resolveIndex(index);
            } else {
                return new ArrobaException("Given expression is not an array: " + ctx.target.getText());
            }
        } else if (expr instanceof ArrobaParser.RegexLiteralExprContext) {
            return new ArrobaLiteralMatcher((ArrobaParser.RegexLiteralExprContext) expr);
        } else if (expr instanceof ArrobaParser.InvocationExprContext) {
            ArrobaDatum target = visitExpr(((ArrobaParser.InvocationExprContext) expr).target);
            ArrobaFunction run = null;

            if (target instanceof ArrobaFunction) {
                run = (ArrobaFunction) target;
            } else {
                ArrobaDatum callee = target.resolve("call");

                if (callee instanceof ArrobaFunction) {
                    run = (ArrobaFunction) callee;
                }
            }

            if (run != null) {
                createChildScope();
                List<ArrobaParser.ExprContext> exprs = ((ArrobaParser.InvocationExprContext) expr).expr();
                List<ArrobaDatum> args = new ArrayList<>();
                exprs.remove(0);

                for (ArrobaParser.ExprContext exprContext : exprs) {
                    args.add(visitExpr(exprContext));
                }

                ArrobaDatum result = run.invoke(args);
                exitLastScope();
                return result;
            } else {
                return new ArrobaException(((ArrobaParser.InvocationExprContext) expr).target.getText() + " is not a function");
            }
        }

        return null;
    }

    @Override
    public ArrobaDatum visitIfStmt(ArrobaParser.IfStmtContext ctx) {
        ArrobaDatum ifIsTrue = visitExpr(ctx.ifBlock().expr());

        ArrobaDatum result = null;
        if (ifIsTrue != null) {
            if (ifIsTrue.toBool()) {
                for (ArrobaParser.StmtContext stmt : ctx.ifBlock().stmt()) {
                    result = visitStmt(stmt);
                    if (stmt.retStmt() != null)
                        break;
                }
            } else if (!ctx.elifBlock().isEmpty()) {
                for (ArrobaParser.ElifBlockContext eli : ctx.elifBlock()) {
                    ArrobaDatum condition = visitExpr(eli.expr());

                    if (condition != null) {
                        if (condition.toBool()) {
                            for (ArrobaParser.StmtContext stmt : eli.stmt()) {
                                result = visitStmt(stmt);
                                if (stmt.retStmt() != null)
                                    break;
                            }
                        }
                    }
                }
            } else if (ctx.elseBlock() != null) {
                for (ArrobaParser.StmtContext stmt : ctx.elseBlock().stmt()) {
                    result = visitStmt(stmt);
                    if (stmt.retStmt() != null)
                        break;
                }
            }
        }

        return result;

    }

    private ArrobaDatum resolveMathExpr(ArrobaParser.MathExprContext ctx) {
        ArrobaParser.ExprContext leftExpr = ctx.expr(0);
        ArrobaParser.ExprContext rightExpr = ctx.expr(1);
        ArrobaDatum leftValue = visitExpr(leftExpr);
        ArrobaDatum rightValue = visitExpr(rightExpr);

        ArrobaNumber left = (leftValue instanceof ArrobaNumber) ? (ArrobaNumber) leftValue : ArrobaNumber.False();
        ArrobaNumber right = (rightValue instanceof ArrobaNumber) ? (ArrobaNumber) rightValue : ArrobaNumber.False();

        /*System.out.println("left: " + left);
        System.out.println("right: " + right);*/

        if (ctx.PLUS() != null) {
            if (leftValue instanceof ArrobaArray) {
                ((ArrobaArray) leftValue).items.add(rightValue);
                return leftValue;
            } else if (leftValue instanceof ArrobaString || rightValue instanceof ArrobaString) {
                return new ArrobaPureString(leftValue.toString() + rightValue.toString());
            } else return ArrobaNumber.From(left.value + right.value);
        } else if (ctx.MINUS() != null) {
            if (leftValue instanceof ArrobaArray) {
                ((ArrobaArray) leftValue).items.remove(rightValue);
                return leftValue;
            }

            return ArrobaNumber.From(left.value - right.value);
        } else if (ctx.TIMES() != null)
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
            result = visitExpr(right);
            return value(((ArrobaParser.LocalExprContext) left).ID().getText(), result, true);
        } else if (left instanceof ArrobaParser.MemberExprContext) {
            ArrobaParser.MemberExprContext expr = (ArrobaParser.MemberExprContext) left;
            ArrobaDatum target = visitExpr(expr.expr());

            if (target != null) {
                target.setMember(expr.ID().getText(), visitExpr(right));
                return target.resolve(expr.ID().getText());
            } else {
                return new ArrobaException("Invalid expression: " + expr.getText());
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
    public ArrobaDatum visitBoolExpr(ArrobaParser.BoolExprContext ctx) {
        ArrobaDatum leftExpr = visitExpr(ctx.left);
        leftExpr = (leftExpr == null) ? ArrobaNumber.False() : leftExpr;
        ArrobaDatum rightExpr = visitExpr(ctx.right);
        rightExpr = (rightExpr == null) ? ArrobaNumber.False() : rightExpr;
        Boolean result = false;
        ArrobaParser.BooleanOperatorContext operator = ctx.booleanOperator();

        if (operator.IS() != null || operator.NOT() != null) {
            Boolean equality = leftExpr.equals(rightExpr);
            if (operator.IS() != null) {
                result = equality;
            } else {
                result = !equality;
            }
        } else {
            ArrobaNumber left = (leftExpr instanceof ArrobaNumber) ? (ArrobaNumber) leftExpr : ArrobaNumber.False();
            ArrobaNumber right = (rightExpr instanceof ArrobaNumber) ? (ArrobaNumber) rightExpr : ArrobaNumber.False();

            if (operator.AND() != null) {
                result = left.toBool() && right.toBool();
            } else if (operator.OR() != null) {
                result = left.toBool() || right.toBool();
            } else if (operator.LT() != null) {
                result = left.value < right.value;
            } else if (operator.LTE() != null) {
                result = left.value <= right.value;
            } else if (operator.GT() != null) {
                result = left.value > right.value;
            } else if (operator.GTE() != null) {
                result = left.value >= right.value;
            }

        }

        return new ArrobaNumber(result ? 1.0 : 0.0);
    }

    @Override
    public ArrobaDatum visitCompilationUnit(ArrobaParser.CompilationUnitContext ctx) {
        if (ctx == null)
            return null;

        ArrobaDatum result = null;

        for (ArrobaParser.StmtContext stmt : ctx.stmt()) {
            result = visitStmt(stmt);

            if (stmt.retStmt() != null)
                break;
        }

        return result;
    }

    @Override
    public ArrobaDatum visitExprStmt(ArrobaParser.ExprStmtContext ctx) {
        return visitExpr(ctx.expr());
    }

    @Override
    public ArrobaDatum visitStmt(ArrobaParser.StmtContext ctx) {
        if (ctx.ifStmt() != null) {
            return visitIfStmt(ctx.ifStmt());
        } else if (ctx.exprStmt() != null) {
            return visitExprStmt(ctx.exprStmt());
        } else if (ctx.retStmt() != null) {
            return visitExpr(ctx.retStmt().expr());

        } else if (ctx.throwStmt() != null) {
            return visitThrowStmt(ctx.throwStmt());
        } else if (ctx.tryStmt() != null) {
            return visitTryStmt(ctx.tryStmt());


        } else if (ctx.whileStmt() != null) {
            return visitWhileStmt(ctx.whileStmt());
        }

        return super.visitStmt(ctx);
    }

    @Override

    public ArrobaDatum visitThrowStmt(ArrobaParser.ThrowStmtContext ctx) {
        ArrobaDatum toThrow = visitExpr(ctx.expr());

        if (toThrow == null) {
            return new ArrobaException("Cannot throw a null value: " + ctx.expr().getText());
        }

        if (toThrow instanceof ArrobaException)
            return toThrow;

        else return new ArrobaException(toThrow);
    }

    @Override
    public ArrobaDatum visitTryStmt(ArrobaParser.TryStmtContext ctx) {
        ArrobaDatum result = null;

        createChildScope();
        for (ArrobaParser.StmtContext stmt : ctx.toTry) {
            result = visitStmt(stmt);

            if (stmt.retStmt() != null || stmt.throwStmt() != null || result instanceof ArrobaException) {
                break;
            }
        }
        exitLastScope();

        if (result instanceof ArrobaException) {
            createChildScope();
            TerminalNode id = ctx.ID();

            if (id != null) {
                value(id.getText(), result);
            }

            for (ArrobaParser.StmtContext stmt : ctx.toCatch) {
                result = visitStmt(stmt);
                if (stmt.retStmt() != null || stmt.throwStmt() != null) {
                    break;
                }
            }
            exitLastScope();
        }
        return result;
    }

    @Override


    public ArrobaDatum visitWhileStmt(ArrobaParser.WhileStmtContext ctx) {
        ArrobaParser.ExprContext toEvaluate = ctx.expr();
        ArrobaDatum condition = visitExpr(toEvaluate);
        ArrobaDatum result = null;

        while (condition != null && condition.toBool()) {
            for (ArrobaParser.StmtContext stmt : ctx.stmt()) {
                if (stmt.breakStmt() != null) {
                    break;
                } else if (stmt.retStmt() != null) {
                    return visitRetStmt(stmt.retStmt());
                }

                result = visitStmt(stmt);
            }

            condition = visitExpr(toEvaluate);
        }

        return result;
    }
}
