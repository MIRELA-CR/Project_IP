import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Main extends JFrame {

    public Main() {
        CurrentMCount currentMCount = new CurrentMCount();
        MovesData movesData = new MovesData();
        movesData.registerObs(currentMCount);
        movesData.dataChanged();
        movesData.dataChanged();
        this.initComponents();
    }

    public static void main(String[] aArgs) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main start = new Main();
                Dimension d = new Dimension(480, 450);
                start.setMinimumSize(d);
                start.setMaximumSize(d);
                start.setPreferredSize(d);
                start.setLocationRelativeTo((Component)null);
                start.setResizable(false);
                start.setTitle("Ball Sort");
                start.setVisible(true);
            }
        });
    }

    private void initComponents() {
        this.setDefaultCloseOperation(3);
        JPanel ballSortPanel = new JPanel();
        final ScreenPanel screenPanel = new ScreenPanel();

        this.addMouseListener(screenPanel);
        Dimension tubesPanelDim = new Dimension(480, 250);
        screenPanel.setOpaque(true);
        screenPanel.setMaximumSize(tubesPanelDim);
        screenPanel.setMinimumSize(tubesPanelDim);
        screenPanel.setPreferredSize(tubesPanelDim);
        screenPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        screenPanel.setBackground(new Color(150, 190, 120));

        JLabel tNumContainersLbl = new JLabel("Number of Containers");
        Integer[] tNumberTubes = new Integer[]{3, 4, 5, 6, 7};
        final JComboBox<Integer> tComboBoxNumContainers = new JComboBox(tNumberTubes);
        final JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent aEvt) {
                tComboBoxNumContainers.setEnabled(false);
                startButton.setEnabled(false);
                int tNumTubes = (Integer)tComboBoxNumContainers.getSelectedItem();
                screenPanel.addTubes(tNumTubes);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        screenPanel.validate();
                    }
                });
            }
        });

        JButton tResetButton = new JButton("Reset");
        tResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent aEvt) {
                tComboBoxNumContainers.setEnabled(true);
                startButton.setEnabled(true);
                screenPanel.removeAll();
                screenPanel.resetPanel();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        screenPanel.validate();
                    }
                });
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        screenPanel.repaint();
                    }
                });
            }
        });


        GroupLayout ballSortPanelLayout = new GroupLayout(ballSortPanel);
        ballSortPanel.setLayout(ballSortPanelLayout);
        ballSortPanelLayout.setHorizontalGroup(ballSortPanelLayout.createParallelGroup(Alignment.CENTER).addComponent(screenPanel).addGroup(ballSortPanelLayout.createSequentialGroup().addGroup(ballSortPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(ballSortPanelLayout.createSequentialGroup().addGap(160, 160, 160).addComponent(tNumContainersLbl)).addGroup(ballSortPanelLayout.createSequentialGroup().addGap(215, 215, 215).addComponent(tComboBoxNumContainers, -2, -1, -2)).addGroup(ballSortPanelLayout.createSequentialGroup().addGap(198, 198, 198).addComponent(startButton)).addGroup(ballSortPanelLayout.createSequentialGroup().addGap(195, 195, 195).addComponent(tResetButton))).addContainerGap(132, 32767)));
        ballSortPanelLayout.setVerticalGroup(
                ballSortPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(screenPanel)
                        .addGroup(Alignment.TRAILING, ballSortPanelLayout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(tNumContainersLbl)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(tComboBoxNumContainers, -2, -1, -2)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(startButton)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(tResetButton)
                                .addGap(23, 23, 23)));

        GroupLayout tLayout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(tLayout);
        tLayout.setHorizontalGroup(tLayout.createParallelGroup(Alignment.LEADING).addComponent(ballSortPanel, -2, -2, 32767));
        tLayout.setVerticalGroup(tLayout.createParallelGroup(Alignment.LEADING).addComponent(ballSortPanel, -2, -2, 32767));
    }


}
