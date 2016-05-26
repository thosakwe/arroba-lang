package thosakwe.arroba.interpreter.data;

public class ArrobaException extends ArrobaDatum {
    public String message;
    public ArrobaDatum value;
    public String stackTrace = "Stack traces coming soon!";

    public ArrobaException() {

    }

    public void addMembers() {
        members.put("msg", new ArrobaPureString(message));
        members.put("stack", new ArrobaPureString(stackTrace));
        members.put("value", value);
    }

    public ArrobaException(Exception exc) {
        message = exc.getMessage();
        addMembers();
    }

    @Override
    public String toString() {
        String result = "Arroba Exception: " + message + "\n";
        if (value != null)
            result += "Thrown value: " + value.toString() + "\n";
        result += "Stack Trace:\n" + stackTrace;
        return result;
    }
}
