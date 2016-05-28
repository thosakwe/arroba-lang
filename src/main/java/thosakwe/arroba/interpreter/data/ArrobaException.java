package thosakwe.arroba.interpreter.data;

public class ArrobaException extends ArrobaDatum {
    public String message;
    public ArrobaDatum value;
    public String stackTrace = "Stack traces coming soon!";

    public ArrobaException() {

    }

<<<<<<< HEAD
    public ArrobaException(String message) {
        this.message = message;
        addMembers();
    }

    public ArrobaException(ArrobaDatum value) {
        this.value = value;
        addMembers();
    }

=======
>>>>>>> 1ee8b1c0a3f6e1d6b40644fab3f9a010059b406f
    public void addMembers() {
        members.put("msg", new ArrobaPureString(message));
        members.put("stack", new ArrobaPureString(stackTrace));
        members.put("value", value);
    }

    public ArrobaException(Exception exc) {
        message = exc.getMessage();
        addMembers();
    }

<<<<<<< HEAD
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
=======
    @Override
    public String toString() {
        String result = "Arroba Exception: " + message + "\n";
        if (value != null)
            result += "Thrown value: " + value.toString() + "\n";
>>>>>>> 1ee8b1c0a3f6e1d6b40644fab3f9a010059b406f
        result += "Stack Trace:\n" + stackTrace;
        return result;
    }
}
