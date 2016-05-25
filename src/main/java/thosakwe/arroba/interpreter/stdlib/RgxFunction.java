package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RgxFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        ArrobaDatum pattern = args.get(0);

        if (pattern != null && pattern instanceof ArrobaString) {
            ArrobaMatcher result = new ArrobaMatcher(Pattern.compile(pattern.toString()));

            for (int i = 1; i < args.size(); i++) {
                ArrobaDatum datum = args.get(i);

                if (datum instanceof ArrobaString && datum.toString().equals("g")) {
                    result.multiple = true;
                }
            }

            return result;
        }

        System.err.println("rgx expects argument 1 to be a string");
        return super.invoke(args);
    }

    @Override
    public String toString() {
        return "<Native Function> rgx(pattern)";
    }
}

class ArrobaMatcher extends ArrobaFunction {
    private Pattern pattern;
    Boolean multiple;

    ArrobaMatcher(Pattern pattern) {
        this.pattern = pattern;
        this.multiple = false;
    }

    ArrobaMatcher(Pattern pattern, Boolean multiple) {
        this.pattern = pattern;
        this.multiple = multiple;
    }

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        ArrobaDatum subject = args.get(0);

        if (subject != null && subject instanceof ArrobaString) {
            Matcher matcher = pattern.matcher(subject.toString());

            if (!multiple) {
                if (matcher.find()) {
                    return new ArrobaMatch(matcher);
                }

                System.err.println("Given string (" + subject.toString() + ") does not match pattern (" + pattern.pattern() + ")");
                return null;
            } else {
                List<ArrobaMatch> matches = new ArrayList<>();

                while (matcher.find()) {
                    matches.add(new ArrobaMatch(matcher));
                }

                return new ArrobaArray(matches);
            }
        }

        System.err.println("Matcher expects argument 1 to be a string");
        return null;
    }

    @Override
    public String toString() {
        return "<Matcher:/" + pattern.pattern() + "/>";
    }
}

class ArrobaMatch extends ArrobaArray {
    private Matcher matcher;

    ArrobaMatch(Matcher matcher) {
        this.matcher = matcher;
        for (int i = 0; i < matcher.groupCount(); i++) {
            items.add(new ArrobaPureString(matcher.group(i)));
        }

        members.put("group", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                ArrobaDatum target = args.get(0);

                if (target != null && target instanceof ArrobaString) {
                    return new ArrobaPureString(matcher.group(target.toString()));
                } else if (target != null && target instanceof ArrobaNumber) {
                    return new ArrobaPureString(matcher.group(((ArrobaNumber) target).value.intValue()));
                }

                System.err.println("match.group expects argument 1 to be a string or number");
                return null;
            }

            @Override
            public String toString() {
                return "<Native Function> match.group(name)";
            }
        });
    }

    @Override
    public String toString() {
        return "<Match:" + matcher.groupCount() + ">";
    }
}