package thosakwe.arroba.interpreter.data;

public class ArrobaPureString extends ArrobaString {
    public ArrobaPureString(String pure) {
        super(pure, null, null);
    }

    @Override
    public String resolveValue() {
        return text;
    }
}
