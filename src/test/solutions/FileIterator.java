package test.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

public class FileIterator implements Iterable<String>{
	private BufferedReader r;

    public FileIterator(BufferedReader r) {
        this.r = r;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            @Override
            public boolean hasNext() {
                try {
                    r.mark(1); //mark the position
                    if (r.read() < 0) {  //reads a single char
                        return false;
                    }
                    r.reset();  // reset to the most recent mark
                    return true;
                } catch (IOException e) {
                    return false;
                }
            }

            @Override
            public String next() {
                try {
                    return r.readLine();
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }
}
