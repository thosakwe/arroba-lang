package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.*;

import java.util.List;

public class SocketFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (args.size() >= 2 && args.get(0) instanceof ArrobaString && args.get(1) instanceof ArrobaNumber) {
            String host = args.get(0).toString();
            int port = ((ArrobaNumber) args.get(1)).value.intValue();

            Boolean udp = false;

            if (args.size() >= 3 && args.get(2) instanceof ArrobaNumber) {
                udp = args.get(2).equals(ArrobaNumber.True());
            }

            if (udp)
                return new ArrobaException("Arroba does not support UDP sockets yet.");

            try {
                return new ArrobaSocket(host, port);
            } catch (Exception exc) {
                return new ArrobaException(exc);
            }
        }

        return new ArrobaException("Socket expects argument 1 to be a string and argument 2 to be a number.");
    }

    @Override
    public String toString() {
        return "<Native Function> Socket(host:string, port:number)";
    }
}
