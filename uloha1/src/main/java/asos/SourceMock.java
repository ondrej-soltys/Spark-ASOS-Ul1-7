package asos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SourceMock implements ISource{
    
    private final  BufferedReader reader;
    
    SourceMock() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader("src/main/resources/data1.txt"));
    }

    @Override
    public String nextMessage() {
        try {
            return reader.readLine();
        } catch (IOException ex) {
            return null;
        }
    }
    
}
