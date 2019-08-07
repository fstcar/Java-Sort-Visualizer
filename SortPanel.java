import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class SortPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private SortAnimationPanel sortAnimationPanel;
    private int[] valuesToSort;

    public SortPanel()
    {
        // Manage layout for the controls and animation panel
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

    private class SortAnimationPanel extends JPanel implements Runnable
    {
        private static final long serialVersionUID = 1L;

        SortAnimationPanel(String title)
        {
            super(title);

            // Get dimmensions of panel and clear it

            if (valuesToSort != null)
            {
                // Draw lines representing values
            }
            
        }        

        // Override the paintComponent() method
    }
}