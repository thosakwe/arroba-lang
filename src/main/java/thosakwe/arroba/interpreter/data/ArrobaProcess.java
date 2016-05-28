package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaException;
import thosakwe.arroba.interpreter.data.ArrobaNumber;
import thosakwe.arroba.interpreter.data.ArrobaPureString;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArrobaProcess extends ArrobaDatum {
    private final String processName;

    public ArrobaProcess(Process process, String processName, int code) {
        this.processName = processName;

        members.put("code", new ArrobaNumber(code));

        try {
            String stdout = "";
            String line;
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                stdout += line + "\n";
            }

            stdout = stdout.substring(0, stdout.length() -1);

            input.close();
            members.put("stdout", new ArrobaPureString(stdout));
        } catch (Exception exc) {
            members.put("stdout", new ArrobaPureString(""));
        }

        try {
            String stderr = "";
            String line;
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(process.getErrorStream()));
            while ((line = input.readLine()) != null) {
                stderr += line + "\n";
            }

            stderr = stderr.substring(0, stderr.length() -1);

            input.close();
            members.put("stderr", new ArrobaPureString(stderr));
        } catch (Exception exc) {
            members.put("stderr", new ArrobaPureString(""));
        }

        //members.put("kill", kill());
        //members.put("read", read());
        //members.put("write", write());
    }

    @Override
    public String toString() {
        return "<Process:" + processName + ">";
    }
}
