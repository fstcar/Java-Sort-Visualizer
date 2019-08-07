import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.event.ActionListener;

public class SortAnimationApp extends JFrame
{
    private static final long serialVersionUID = 1L;
    private SortPanel panel1;
    private SortPanel panel2;
    private final JButton populateButton;
    private final JComboBox sortSpeedComboBox;
    private final JButton actionButton;

    public SortAnimationApp(String title) 
    {
        super(title);

        // Setup Layout and Constraints
        layout = new GridBagLayout();
        setLayout(layout);      
        constraints = new GridBagConstraints();

        // Create GUI Components
        populateButton = new JButton("Populate");
        sortSpeedComboBox = new JComboBox<String>(new String[] {"Slow", "Medium", "Fast"});
        actionButton = new JButton("Sort");

        // Add components
        addComponent(populateButton, 3, 2, 1, 1);
        addComponent(sortSpeedComboBox, 4, 2, 1, 1);
        addComponent(actionButton, 5, 2, 1, 1);
            // Add Two SortPanel objects to the app layout

        // Instantiate Handlers
        HandlePopulateButton handlePopulateButton = new HandlePopulateButton();
        HandleSortButton handleSortButton = new HandleSortButton();

        // Add listeners to the relevant objects
        populateButton.addActionListener(handlePopulateButton);
        actionButton.addActionListener(handleSortButton);

    }

    // Handle events from buttons
    private class HandlePopulateButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Create an array list with size equal to the panel width
            ArrayList<Integer> randomizedArray = new ArrayList<Integer>(SortAnimationPanel.this.getWidth());

            // Instantiate randomizer
            Random rand = new Random();

            // Populate the array with numbers 0 - height of the panel
            for (Integer thisInt : randomizedArray) 
            {
                int randomizedInt = rand.nextInt() % SortAnimationPanel.this.getHeight();
                randomizedArray.add(randomizedInt);
            }

            // Convert to an integer array
            valuesToSort = randomizedArray.toArray();
            // Call repaint() to display the same array values
            // Disable populate button
            // Enable sort button
        }
    }

    private class HandleSortButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Disable sort button
            // Create a new Thread object from the runnable SortAnimationPanel
            // Call the Thread object's start() method to start the sort for both panels
        }
    }

    // Method to set constraints on 
    private void addComponent(Component component,
    int row, int column, int width, int height)
    {
       constraints.gridx = column; 
       constraints.gridy = row;
       constraints.gridwidth = width;
       constraints.gridheight = height;
       layout.setConstraints(component, constraints); // Set constraints
       add(component); // Add component
    }

    // Note to max: I'm not yet positive how we start our main based on the document's instructions, but I think we need it in here
    public static void main()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SortAnimationApp();
            }
        });
    }    
}