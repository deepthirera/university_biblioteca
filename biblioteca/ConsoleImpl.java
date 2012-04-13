package biblioteca;

import java.io.*;

public class ConsoleImpl implements IConsole {

    final private PrintStream _out;
    BufferedReader _in;

    public ConsoleImpl() {
        this(System.out);
    }

    public ConsoleImpl(PrintStream out) {
        _out = out;
    }

    @Override
    public void writeLine(String line) {
        _out.println(line);
    }


}
