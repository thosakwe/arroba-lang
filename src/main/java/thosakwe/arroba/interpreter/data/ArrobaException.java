package thosakwe.arroba.interpreter.data;

public class ArrobaException extends ArrobaDatum {
    public String message;
    public ArrobaDatum value;
    public String stackTrace = "Stack traces coming soon!";

    public ArrobaException() {

    }


    public ArrobaException(String message) {
        this.message = message;
        addMembers();
    }

    public ArrobaException(ArrobaDatum value) {
        this.value = value;
        addMembers();
    }


    public void addMembers() {
        members.put("msg", new ArrobaPureString(message));
        members.put("stack", new ArrobaPureString(stackTrace));
        members.put("value", value);
    }

    public ArrobaException(Exception exc) {
        message = exc.getMessage();
        stackTrace = "";

        for (StackTraceElement stackTraceElement : exc.getStackTrace()) {
            stackTrace += "\t" + stackTraceElement.toString() + "\n";
        }
        addMembers();
    }


    private String getMessage() {
        if (message != null)
            return message;

        return "An unhandled exception occurred.";
    }

    @Override
    public String toString() {
        String result = "Arroba Exception: " + getMessage() + "\n";
        if (value != null)
            result += "Thrown value: " + value.toString() + "\n";
        if (stackTrace != null)
            result += "Stack Trace:\n" + stackTrace;
        return result;
    }
}
