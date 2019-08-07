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
            // Call appropriate sort method utilizing the combo box to sort in asc. order
            // Call repaint() everytime there's a swap
            // After each pass through an outer loop, sleep the thread for 100 miliseconds
        }
        // Override the paintComponent() method
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
}