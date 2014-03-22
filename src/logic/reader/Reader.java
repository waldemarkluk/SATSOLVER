package logic.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 16.03.14
 *         Time: 10:47
 */
public interface Reader {
    public void read(String file) throws Exception;

}
