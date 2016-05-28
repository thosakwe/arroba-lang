package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaNumber;

public class ArrobaTaskResult extends ArrobaDatum {
    private Boolean success;

    public ArrobaTaskResult(Boolean success) {
        this.success = success;

        members.put("success", new ArrobaNumber(success ? 1.0 : 0.0));
    }

    @Override
    public String toString() {
        return "<Task Result: " + (success ? "Success" : "Failure") + ">";
    }
}
