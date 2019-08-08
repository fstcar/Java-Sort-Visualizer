import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.lang.Math;

public class SortPanel extends JPanel 
{
    private static final long serialVersionUID = 1L;
    private final JComboBox algorithmComboBox;
    private SortAnimationPanel sortAnimationPanel;
    private Border blackline = BorderFactory.createLineBorder(Color.black);
    private Border thicc = BorderFactory.createLineBorder(Color.lightGray, 5);
    private int[] valuesToSort;
    private GridBagConstraints constraints; // layout's constraints
    private GridBagLayout layout; // layout of this frame
    private ArrayList<Thread> threads;

    public SortPanel() 
    {
        threads = new ArrayList<Thread>();

        // Setup Layout and Constraints
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();

        setBackground(Color.lightGray);
        setBorder(thicc);

        sortAnimationPanel = new SortAnimationPanel();

        // Create GUI Components
        algorithmComboBox = new JComboBox<String>(new String[] { "Selection", "Bubble", "Heap" });

        addComponent(sortAnimationPanel, 0, 0, 1, 1, 1, 1);
        addComponent(algorithmComboBox, 1, 0, 1, 1, 0, 0);

        // Manage layout for the controls and animation panel
    }

    public void generateArray(long seed) 
    {
        // Create an array list with size equal to the panel width
        // SortAnimationPanel.this.getHeight()
        valuesToSort = new int[getWidth()];

        // Instantiate randomizer
        Random rand = new Random();

        rand.setSeed(seed);

        // Populate the array with numbers 0 - height of the panel
        for (int i = 0; i < valuesToSort.length; i++) 
        {
            int randomizedInt = Math.abs(rand.nextInt()) % getHeight();
            valuesToSort[i] = randomizedInt;
        }

        rand.setSeed(1);
    }

    //creating the sorting threads
    public void createThreads(int numberOfThreads) 
    {
        // Create a new Thread object from the runnable SortAnimationPanel
        for(int i = 0; i < numberOfThreads; i++)
        {
            threads.add(new Thread(sortAnimationPanel));
        }         
    }

    public void startSort()
    {
        // Call the Thread object's start method to start the sort for both panels
        for (Thread thread : threads) 
        {            
            thread.start();    
        }
    }

    public void stopSort()
    {
        for (Thread thread : threads) 
        {            
            thread.interrupt();    
        }
    }

    public void bubbleSort()
    {
        try 
        {
            boolean swapped = true;
            int j = 0;
            int tmp;
            while (swapped) 
            {
                swapped = false;
                j++;
                for (int i = 0; i < valuesToSort.length - j; i++) 
                {
                    if (valuesToSort[i] > valuesToSort[i + 1]) 
                    {
                        tmp = valuesToSort[i];
                        valuesToSort[i] = valuesToSort[i + 1];
                        valuesToSort[i + 1] = tmp;
                        swapped = true;
                        repaint();
                        Thread.sleep(1);
                    }
                }
            }
        } 
        catch (InterruptedException e) 
        {
            // Displays that the thread was interupted.
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    }

    public void heapSort() 
    { 
        try
        {
            int n = valuesToSort.length; 
  
            // Build heap (rearrange array) 
            for (int i = n / 2 - 1; i >= 0; i--) 
                heapify(n, i); 
            
            // One by one extract an element from heap 
            for (int i=n-1; i>=0; i--) 
            { 
                // Move current root to end 
                int temp = valuesToSort[0]; 
                valuesToSort[0] = valuesToSort[i]; 
                valuesToSort[i] = temp; 
            
                // call max heapify on the reduced heap 
                heapify(i, 0); 
                repaint();
            } 
        }
        catch (InterruptedException e) 
        {
            // Displays that the thread was interupted.
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    } 
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && valuesToSort[l] > valuesToSort[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && valuesToSort[r] > valuesToSort[largest]) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
            int swap = valuesToSort[i]; 
            valuesToSort[i] = valuesToSort[largest]; 
            valuesToSort[largest] = swap; 
            repaint();
            Thread.sleep(1);
  
            // Recursively heapify the affected sub-tree 
            heapify(n, largest); 
        } 
    } 

    private class SortAnimationPanel extends JPanel implements Runnable 
    {
        private static final long serialVersionUID = 1L;

        SortAnimationPanel() 
        {
            repaint();
        }

        public void paintComponent(Graphics g) 
        {
            setBackground(Color.white);
            if (valuesToSort != null) 
            {
                // Draw lines representing values
                super.paintComponent(g);
                g.setColor(Color.blue);
                for (int i = 0; i < valuesToSort.length; i++) 
                {
                    g.fillRect(i, getHeight() - valuesToSort[i], 1, valuesToSort[i]);
                }
            }
        }

        public void run() 
        {
            //Testing Basic Sorting
            heapSort();
            // Call appropriate sort method utilizing the combo box to sort in asc. order
            // Call repaint() everytime there's a swap
            // After each pass through an outer loop, sleep the thread for 100 miliseconds
        }
        // Override the paintComponent() method
    }

    // method to set constraints on
    private void addComponent(Component component, int row, int column, int width, int height, int weightx,
            int weighty) {
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
}