import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class SortPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private final JComboBox algorithmComboBox;
    private SortAnimationPanel sortAnimationPanel;
    private int[] valuesToSort;

    public SortPanel()
    {
        // Create GUI Components
        algorithmComboBox = new JComboBox<String>(new String[] {"Selection", "Bubble", "Heap"});

        // Instantiate our handlers
        HandlePopulateButton handlePopulateButton = new HandlePopulateButton();
        HandleSortButton handleSortButton = new HandleSortButton();

        // Add listeners to the relevant objects
        algorithmComboBox.add
        // Manage layout for the controls and animation panel
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

        public void run()
        {
            // Call appropriate sort method (Prob use switch for this) to sort in asc. order
            // Call repaint() everytime there's a swap
            // After each pass through an outer loop, sleep the thread for 100 miliseconds
        }
        // Override the paintComponent() method
    }
}