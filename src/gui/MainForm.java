package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Grzegorz
 * Date: 12.05.14
 * Time: 20:04
 * To change this template use File | Settings | File Templates.
 */
public class MainForm extends JPanel{
    private JTabbedPane mainTabPane = new JTabbedPane();
    private JTextField termInClauseCtrl;
    private JTextField termCountCtrl;
    private JTextField clauseCountCtrl;
    public int getTermInClauseCount()
    {
        return 0;
    }
    public int getTermCount()
    {
        return 0;
    }
    public int getClauseCount()
    {
        return 0;
    }
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    private void insertLabelTextPair(JLabel label,JTextField control,JPanel panel,GridBagConstraints c)
    {
        c.gridx=0;
        panel.add(label,c);
        c.gridx=1;
        panel.add(control,c);
        c.gridy++;
    }
    private JComponent settingsPanel()
    {
        JTabbedPane tabs = new JTabbedPane();
        tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabs.addTab("Generator",settingsPanelGenerator());
        tabs.addTab("Ustawienia wyboru",makeTextPanel("TEST"));
        tabs.addTab("Ustawienia crossover",makeTextPanel("TEST"));
        return tabs;
    }
    private JComponent settingsPanelGenerator()
    {
        JPanel panel = new JPanel(false);
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        /**
         * Ustawienia generatora
         */
        JLabel genLabel1=new JLabel("Generator");
        c.gridx=0;
        c.gridy=0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.5;
        c.weightx = 1;
        panel.add(genLabel1,c);
        c.gridx=0;
        c.gridy=1;
        clauseCountCtrl = new JTextField("128");
        termCountCtrl = new JTextField("128");
        termInClauseCtrl = new JTextField("6");
        insertLabelTextPair(new JLabel("Ilość klauzul"),clauseCountCtrl,panel,c);
        insertLabelTextPair(new JLabel("Ilość termów w puli"),termCountCtrl,panel,c);
        insertLabelTextPair(new JLabel("Ilość termów w klauzuli"),termInClauseCtrl,panel,c);
        return panel;
    }
    public MainForm() {
        super(new GridLayout());
        mainTabPane.addTab("Wprowadzanie danych", makeTextPanel("PLACEHOLDER"));
        mainTabPane.addTab("Ustawienia", settingsPanel());
        add(mainTabPane);
        mainTabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
}
