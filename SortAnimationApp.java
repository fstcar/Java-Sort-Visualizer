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

        // Setup Default behavior
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
    
        // Create new JPanel and add populate and action buttons
        controlPanel = new JPanel();
        controlPanel.add(populateButton);
        controlPanel.add(sortSpeedComboBox);
        controlPanel.add(actionButton);
    
    
        // Add two panels to display the sorting
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
            // Interrupt the threads performing sorts


            // Provide the same seed based on the system clock
            long seed = System.currentTimeMillis();

            // Generate the array based on a seed
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
            if (actionButton.getText() == "Sort")
            {
<<<<<<< HEAD
                panel1.createThreads(1);
                panel2.createThreads(1);

                panel1.startSort();
                panel2.startSort();
=======
                int speedToSet = 1;
                if(sortSpeedComboBox.getSelectedIndex() == 0)  speedToSet = 10;
                else if(sortSpeedComboBox.getSelectedIndex() == 1)  speedToSet = 5;
                else if(sortSpeedComboBox.getSelectedIndex() == 2)  speedToSet = 1;
                panel1.setspeed(speedToSet);
                panel2.setspeed(speedToSet);

                panel1.startsort();
                panel2.startsort();
>>>>>>> ceaae1fc8865d5e8fbfd6dcc8a9f57bc18cace71

                // Disable sort button
                actionButton.setEnabled(false);
            }
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

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SortAnimationApp("Sorting Demo");
            }
        });
    }    
}