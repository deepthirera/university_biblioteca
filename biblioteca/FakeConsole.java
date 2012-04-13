package biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class FakeConsole extends ArrayList<String> implements IConsole {
    @Override
    public void writeLine(String line) {
        add(line);
    }

    public String lastLine() {
        return get(size() - 1);
    }
    public String writeda(int n) {
        return get(size() - n);
    }

}
