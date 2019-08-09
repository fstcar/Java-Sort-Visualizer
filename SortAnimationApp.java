/************************************************************
 *                                                          *
 *  CSCI 470-2/502-2       Assignment 5        Summer 2019  *
 *                   (or In-Class Exercise n)               *
 *                                                          *
 *  Class Name:  SortAnimationApp                           *
 *                                                          *
 *  Programmer:  Robert Oury                                *
 *               Brett Carney                               *
 *                                                          *
 *  Purpose:  App class that contains the main method, the  *
 *            control panel objects, Sort Panels, and button*
 *            event handling                                *
 *                                                          *
 ************************************************************/
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
    private SortPanel panel1;                       // A panel that contains sort algo. combo box and painted values
    private SortPanel panel2;                       // A panel that contains sort algo. combo box and painted values
    private JPanel controlPanel;                    // Holds Populate Btn. Sort Speed Combo box and Action Button
    private final JButton populateButton;           // Populates the panels
    private final JComboBox sortSpeedComboBox;      // Combo box that has speed options
    private final JButton actionButton;             // Sort / Pause / Resume buttons
    private final GridBagLayout layout;             // layout of this frame
    private final GridBagConstraints constraints;   // layout's constraints

    /************************************************************
    *                                                           *
    *  Method Name:  SortAnimationApp                           *
    *                                                           *
    *  Purpose:  Constructor for our default layout             *
    *                                                           *
    ************************************************************/
    public SortAnimationApp(String title) 
    {
        // Set title
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

        // Set to false until populate is pressed
        actionButton.setEnabled(false);
    
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

    /************************************************************
    *                                                           *
    *  Handler Name:  HandlePopulateButton                      *
    *                                                           *
    *  Purpose:  Terminates the current sort, creates a new     *
    *            integer array for all panels, and displays the *
    *            new array to the panels                        *
    *                                                           *
    ************************************************************/
    private class HandlePopulateButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Interrupt the threads performing sorts
            panel1.terminateSort();
            panel2.terminateSort();


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

    /************************************************************
    *                                                           *
    *  Handler Name:  HandleActionButton                        *
    *                                                           *
    *  Purpose:  Sets the selected speed, creates threads based *
    *            on selected speed/algorithm, and starts the    *
    *            sorting processes                              *
    *                                                           *
    ************************************************************/
    private class HandleActionButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Assign getSelectedSpeed so we aren't calling multiple times
            String getSelectedSpeed = sortSpeedComboBox.getSelectedItem().toString();

            // Always do this so user can change speed
            if (getSelectedSpeed == "Slow")
            {
                panel1.setSortSpeed(100);
                panel2.setSortSpeed(100);
            }
            else if (getSelectedSpeed == "Medium")
            {
                panel1.setSortSpeed(50);
                panel2.setSortSpeed(50);
            }
            else if (getSelectedSpeed == "Fast")
            {
                panel1.setSortSpeed(5);
                panel2.setSortSpeed(5);
            }

            // Logic depending on what button is pressed
            if (actionButton.getText() == "Sort")
            {
                // Heap and Selection don't benefit from our multithreading so we give them one thread
                if (panel1.getAlgorithm() == "Heap" || panel1.getAlgorithm() == "Selection" || panel1.getAlgorithm() == "Pancake")
                {
                    panel1.createThreads(1);                    
                }
                else
                {
                    // Create threads based on selected speed
                    if (getSelectedSpeed == "Slow")
                    {
                        panel1.createThreads(1);
                    }
                    else if (getSelectedSpeed == "Medium")
                    {
                        panel1.createThreads(10);
                    }
                    else if (getSelectedSpeed == "Fast")
                    {
                        panel1.createThreads(20);
                    }
                }

                // Heap and Selection don't benefit from our multithreading so we give them one thread
                if (panel2.getAlgorithm() == "Heap" || panel2.getAlgorithm() == "Selection" || panel1.getAlgorithm() == "Pancake")
                {
                    panel2.createThreads(1);
                }
                else
                {
                    // Create threads based on selected speed
                    if (getSelectedSpeed == "Slow")
                    {
                        panel2.createThreads(1);
                    }
                    else if (getSelectedSpeed == "Medium")
                    {
                        panel2.createThreads(10);
                    }
                    else if (getSelectedSpeed == "Fast")
                    {
                        panel2.createThreads(20);
                    }
                }

                // Start sorting process
                panel1.startSort();
                panel2.startSort();

                // Enable Pause button
                actionButton.setText("Pause");
            }        
            else if (actionButton.getText() == "Pause")
            {
                // Stop our sorting
                panel1.stopSort();
                panel2.stopSort();
                
                // Enable Resume button
                actionButton.setText("Resume");
            }
            else if (actionButton.getText() == "Resume")
            {
                // Resume our sorting
                panel1.resumeSort();
                panel2.resumeSort();

                // Enable Pause button
                actionButton.setText("Pause");
            }
        }
    }

    /************************************************************
    *                                                           *
    *  Method Name:  addComponent                               *
    *                                                           *
    *  Purpose:  Adds a component to the specified position     *
    *                                                           *
    ************************************************************/
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