import logic.SAT_Solver;
import logic.reader.CNF;
import satsolver.FitnessChart;

import java.awt.*;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 19.03.14
 *         Time: 17:36
 */

public class Sandbox {

    public static void main(String [ ] args)
    {
        //MainForm mf=new MainForm();

        new Thread() {
            @Override
            public void run() {
                FitnessChart demo = new FitnessChart("Comparison", "Which operating system are you using?");
                demo.pack();
                demo.setVisible(true);

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                //TreeSet<BitSet> population=new TreeSet<BitSet>();
                CNF rd=new CNF();
                try {
                    rd.read("model4.txt");
                    System.out.println(rd);
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                //LogicHelper.generateProblem();

                SAT_Solver st=new SAT_Solver();
                st.initDebugPopulation(200, "debugPop1.txt");
                //st.initPopulation(200);
                st.solve();
            }
        }.start();

    }
}
