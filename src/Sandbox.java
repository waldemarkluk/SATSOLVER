import gui.MainForm;
import logic.SAT_Solver;
import logic.reader.CNF;
import satsolver.FitnessChart;
import utils.LogicHelper;

import javax.swing.*;
import java.awt.*;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 19.03.14
 *         Time: 17:36
 */

public class Sandbox {
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SATSOLVER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 400));
        //Add content to the window.
        frame.add(new MainForm());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String [ ] args)
    {
        //LogicHelper.generateProblem();
        //System.exit(0);
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
        /*System.exit(0);


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
        }.start();       */

    }
}
