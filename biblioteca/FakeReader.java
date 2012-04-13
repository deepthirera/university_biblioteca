package biblioteca;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;

public class FakeReader extends BufferedReader implements IReader {
    private ArrayList<String> mockUserInput;

    public FakeReader(Reader in, int sz) {
        super(in, sz);
        mockUserInput = new ArrayList<String>();
    }

    public void setUserInput(String input) {
        mockUserInput.add(input);
    }

    @Override
    public String readLine() {
        return mockUserInput.remove(mockUserInput.size() - 1);
    }
}