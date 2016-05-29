package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaArray;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaException;
import thosakwe.arroba.interpreter.data.ArrobaString;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrobaMatcher extends ArrobaFunction {
    public Pattern pattern;
    public Boolean multiple;

    public ArrobaMatcher() {

    }

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

                return new ArrobaException("Given string (" + subject.toString() + ") does not match pattern (" + pattern.pattern() + ")");
            } else {
                List<ArrobaMatch> matches = new ArrayList<>();

                while (matcher.find()) {
                    matches.add(new ArrobaMatch(matcher));
                }

                return new ArrobaArray(matches);
            }
        }

        return new ArrobaException("Matcher expects argument 1 to be a string");
    }

    @Override
    public String toString() {
        return "<Matcher:/" + pattern.pattern() + "/>";
    }
}
