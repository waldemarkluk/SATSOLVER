import logic.reader.CNF;
import logic.reader.Reader;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 16.03.14
 *         Time: 10:47
 */
public class Main {
    public static void main(String [ ] args)
    {
        CNF rd=new CNF();
        try {
            rd.read("model1.txt");
            System.out.println(rd);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
