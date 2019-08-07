import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.lang.Math;


public class SortAnimationApp extends JFrame
{

    private static final long serialVersionUID = 1L;
    private SortPanel panel1;
    private SortPanel panel2;
    private JPanel controlPanel;
    private final JButton populateButton;
    private final JComboBox sortSpeedComboBox;
    private final JButton actionButton;
    private final GridBagLayout layout; // layout of this frame
    private final GridBagConstraints constraints; // layout's constraints

    public SortAnimationApp(String title) 
    {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600); 
        setVisible(true); 

        // Setup Layout and Constraints
        layout = new GridBagLayout();
        setLayout(layout);      
        constraints = new GridBagConstraints();

        // Create GUI Components
        populateButton = new JButton("Populate");
        sortSpeedComboBox = new JComboBox<String>(new String[] {"Slow", "Medium", "Fast"});
        actionButton = new JButton("Sort");
    
        controlPanel = new JPanel();
        controlPanel.add(populateButton);
        controlPanel.add(actionButton);
    
    
        panel1 = new SortPanel();
        panel2 = new SortPanel();
        addComponent(panel1, 0, 0, 1, 1, 1, 1);
        addComponent(panel2, 0, 1, 1, 1, 1, 1);
        addComponent(controlPanel,1 ,0 ,2 ,1 , 0, 0);
    

        // Instantiate Handlers
        HandlePopulateButton handlePopulateButton = new HandlePopulateButton();
        HandleActionButton handleActionButton = new HandleActionButton();

        // Add listeners to the relevant objects
        populateButton.addActionListener(handlePopulateButton);
        actionButton.addActionListener(handleActionButton);

    }

    // Handle events from buttons
    private class HandlePopulateButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            long seed = System.currentTimeMillis();
            panel1.generateArray(seed);
            panel2.generateArray(seed);
            // Call repaint() to display the same array values
            panel1.repaint();
            panel2.repaint();
            // Disable populate button
            //populateButton.setEnabled(false);

            // Enable action button as sort
            actionButton.setEnabled(true);
            actionButton.setText("Sort");
        }
    }

    private class HandleActionButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Disable sort button
            //actionButton.setEnabled(false);
            panel1.startsort();
            panel2.startsort();
            // Create a new Thread object from the runnable SortAnimationPanel
            // Call the Thread object's start() method to start the sort for both panels
        }
    }

    // method to set constraints on 
    private void addComponent(Component component,
    int row, int column, int width, int height, double weightx, double weighty)
 {
    constraints.gridx = column; 
    constraints.gridy = row;
    constraints.gridwidth = width;
    constraints.gridheight = height;
    constraints.weightx = weightx;
    constraints.weighty = weighty;
    constraints.fill = GridBagConstraints.BOTH;
    layout.setConstraints(component, constraints); // set constraints
    add(component); // add component
 }

    // Note to max: I'm not yet positive how we start our main based on the document's instructions, but I think we need it in here
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SortAnimationApp("Testing");
            }
        });
    }    
}