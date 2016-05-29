package thosakwe.arroba.interpreter.data;

import org.antlr.v4.runtime.Token;
import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.stdlib.ArrobaMatcher;

import java.util.regex.Pattern;

public class ArrobaLiteralMatcher extends ArrobaMatcher {
    private String flagText = "";

    public ArrobaLiteralMatcher(ArrobaParser.RegexLiteralExprContext ctx) {
        String pattern = ctx.REGEX_LITERAL().getText().replaceAll("(^/|/$)", "");
        int flags = 0;

        if (!ctx.flags.isEmpty()) {
            for (Token flag : ctx.flags) {
                char ch = flag.getText().toCharArray()[0];

                if (ch == 'g')
                    this.multiple = true;
                else if (ch == 'i')
                    flags |= Pattern.CASE_INSENSITIVE;
                else if (ch == 'm')
                    flags |= Pattern.MULTILINE;
                else if (ch == 'u')
                    flags |= Pattern.UNICODE_CASE;
                else if (ch == 'c')
                    flags |= Pattern.COMMENTS;

                flagText += ch;
            }
        }

        this.pattern = Pattern.compile(pattern, flags);
    }

    @Override
    public String toString() {
        return "<Matcher:/" + pattern.pattern() + "/" + flagText + ">";
    }
}
