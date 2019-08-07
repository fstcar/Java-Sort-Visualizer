import javax.swing.JFrame;

public class SortAnimationApp extends JFrame
{
    private static final long serialVersionUID = 1L;
    private SortPanel panel1;
    private SortPanel panel2;

    public SortAnimationApp(String title) 
    {
        super(title);
        // Add Two SortPanel objects to the app layout
        // Populate button
        // JComboBox for speed of sorting (Slow, med, fast)
        // Sort/Pause/Resume button
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