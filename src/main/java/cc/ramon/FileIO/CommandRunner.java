package cc.ramon.FileIO;

import java.io.IOException;

public class CommandRunner {
    public static Process runCommand(String command) {
        try {
            return Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            return null;
        }
    }
}
