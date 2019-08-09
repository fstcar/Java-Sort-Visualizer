/************************************************************
 *                                                          *
 *  CSCI 470-2/502-2       Assignment 5        Summer 2019  *
 *                   (or In-Class Exercise n)               *
 *                                                          *
 *  Class Name:  SortPanel                                  *
 *                                                          *
 *  Programmer:  Robert Oury                                *
 *               Brett Carney                               *
 *                                                          *
 *  Purpose:  Handles algorithm selection, animation panel  *
 *            postion, random array, sort speed, and        *
 *            algorithm logic.                              *
 *                                                          *
 ************************************************************/
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.lang.Math;

public class SortPanel extends JPanel 
{
    private static final long serialVersionUID = 1L;
    private final JComboBox algorithmComboBox;                                  // Lists algorithm choices
    private SortAnimationPanel sortAnimationPanel;                              // Represents the animation panel to be painted
    private Border thicc = BorderFactory.createLineBorder(Color.lightGray, 5);  // Border
    private int[] valuesToSort;                                                 // Integer array of the randomized values
    private GridBagConstraints constraints;                                     // layout's constraints
    private GridBagLayout layout;                                               // layout of this frame
    private ArrayList<Thread> threads;                                          // Array list of threads used in multithreading
    private int sortSpeed;                                                      // Represents sleep time

    /************************************************************
    *                                                           *
    *  Method Name:  SortPanel                                  *
    *                                                           *
    *  Purpose:  Constructor for our default layout             *
    *                                                           *
    ************************************************************/
    public SortPanel() 
    {
        // Instantiate our thread list
        threads = new ArrayList<Thread>(); 
        
        // Provide a default sleep time
        sortSpeed = 20;

        // Setup Layout and Constraints
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();

        // Set background and border
        setBackground(Color.lightGray);
        setBorder(thicc);

        // Instantiate the Animation panel
        sortAnimationPanel = new SortAnimationPanel();

        // Create GUI Components
        algorithmComboBox = new JComboBox<String>(new String[] { "Selection", "Bubble", "Heap", "Cocktail", "Pancake" });

        // Add our components
        addComponent(sortAnimationPanel, 0, 0, 1, 1, 1, 1);
        addComponent(algorithmComboBox, 1, 0, 1, 1, 0, 0);
    }

    /************************************************************
    *                                                           *
    *  Method Name:  getAlgorithm                               *
    *                                                           *
    *  Purpose:  Gets the selected algorithm as a String        *
    *                                                           *
    ************************************************************/
    public String getAlgorithm()
    {
        return algorithmComboBox.getSelectedItem().toString();
    }

    /************************************************************
    *                                                           *
    *  Method Name:  setSortSpeed                               *
    *                                                           *
    *  Purpose:  Sets the private member for sort speed         *
    *                                                           *
    ************************************************************/
    public void setSortSpeed(int sortSpeed)
    {
        this.sortSpeed = sortSpeed;
    }

    /************************************************************
    *                                                           *
    *  Method Name:  generateArray                              *
    *                                                           *
    *  Purpose:  Creates an integer array based on the seed     *
    *                                                           *
    ************************************************************/
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

    /************************************************************
    *                                                           *
    *  Method Name:  createThreads                              *
    *                                                           *
    *  Purpose:  Adds numberOfThreads new threads to the threads*
    *            array list                                     *
    *                                                           *
    ************************************************************/
    public void createThreads(int numberOfThreads) 
    {
        // Creates threads based on requested amount
        for(int i = 0; i < numberOfThreads; i++)
        {
            threads.add(new Thread(sortAnimationPanel));
        }         
    }

    /************************************************************
    *                                                           *
    *  Method Name:  startSort                                  *
    *                                                           *
    *  Purpose:  Starts all threads in the threads ArrayList    *
    *                                                           *
    ************************************************************/
    public void startSort()
    {
        // Call each Thread object's start method to start the sort for both panels
        for (Thread thread : threads) 
        {            
            thread.start();    
        }
    }

    /************************************************************
    *                                                           *
    *  Method Name:  stopSort                                   *
    *                                                           *
    *  Purpose:  Suspends all threads in the threads ArrayList  *
    *                                                           *
    ************************************************************/
    public void stopSort()
    {
        // Suspend each thread until resume is called
        for (Thread thread : threads) 
        {
            thread.suspend();
        }
    }

    /************************************************************
    *                                                           *
    *  Method Name:  resumeSort                                 *
    *                                                           *
    *  Purpose:  Resumes all threads in the threads ArrayList   *
    *                                                           *
    ************************************************************/
    public void resumeSort()
    {
        // Resume each thread
        for (Thread thread : threads) 
        {
            thread.resume();
        }
    }

    /************************************************************
    *                                                           *
    *  Method Name:  terminateSort                              *
    *                                                           *
    *  Purpose:  Interrupts all threads in the threads ArrayList*
    *                                                           *
    ************************************************************/
    public void terminateSort()
    {
        // Interrupt each thread
        for (Thread thread : threads) 
        {
            thread.interrupt();
        }

        // Cleanup the threads object
        threads.clear();
    }
  
    /************************************************************
    *                                                           *
    *  Method Name:  pancakeSort                                *
    *                                                           *
    *  Purpose:  Performs the pancake sort algorithm            *
    *            on valuesToSort                                *
    *                                                           *
    ************************************************************/
    private void pancakeSort() 
    { 
        try
        {
            // Start from the complete 
            // array and one by one  
            // reduce current size  
            // by one 
            for (int curr_size = valuesToSort.length; curr_size > 1; --curr_size) 
            { 
                // Find index of the  
                // maximum element in  
                // arr[0..curr_size-1] 
                int mi = findMax(curr_size); 
            
                // Move the maximum 
                // element to end of  
                // current array if 
                // it's not already 
                // at the end 
                if (mi != curr_size-1) 
                { 
                    // To move at the end, 
                    // first move maximum  
                    // number to beginning  
                    flip(mi); 
                
                    // Now move the maximum  
                    // number to end by  
                    // reversing current array 
                    flip(curr_size-1); 
                } 
                repaint();
                Thread.sleep(sortSpeed/2);
            } 
        }
        catch (InterruptedException e)
        {
            // Displays that the thread was interupted.
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    }

    /************************************************************
    *                                                           *
    *  Method Name:  flip                                       *
    *                                                           *
    *  Purpose:  Helper method to pancakeSort                   *
    *                                                           *
    ************************************************************/
    private void flip(int i) 
    { 
        try
        {
            int temp, start = 0; 
            while (start < i) 
            { 
                temp = valuesToSort[start]; 
                valuesToSort[start] = valuesToSort[i]; 
                valuesToSort[i] = temp; 
                start++; 
                i--; 
                repaint();
                Thread.sleep(sortSpeed/3);
            } 
        }
        catch (InterruptedException e) 
        {
            // Displays that the thread was interupted.
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    } 
  
    /************************************************************
    *                                                           *
    *  Method Name:  findMax                                    *
    *                                                           *
    *  Purpose:  Helper method to pancakeSort                   *
    *                                                           *
    ************************************************************/
    private int findMax(int n) 
    { 
        int mi, i; 
        for (mi = 0, i = 0; i < n; ++i) 
            if (valuesToSort[i] > valuesToSort[mi]) 
                    mi = i; 
        return mi; 
    } 

    /************************************************************
    *                                                           *
    *  Method Name:  selectionSort                              *
    *                                                           *
    *  Purpose:  Performs the selection sort algorithm          *
    *            on valuesToSort                                *
    *                                                           *
    ************************************************************/
    private void selectionSort() 
    { 
        try
        {
            int n = valuesToSort.length; 
  
            // One by one move boundary of unsorted subarray 
            for (int i = 0; i < n-1; i++) 
            { 
                // Find the minimum element in unsorted array 
                int min_idx = i; 
                for (int j = i+1; j < n; j++) 
                {
                    if (valuesToSort[j] < valuesToSort[min_idx])
                    {
                       min_idx = j;                    
                    }
                }
                // Swap the found minimum element with the first 
                // element 
                int temp = valuesToSort[min_idx]; 
                valuesToSort[min_idx] = valuesToSort[i]; 
                valuesToSort[i] = temp; 
                repaint();
                Thread.sleep(sortSpeed*10);
            } 
        }
        catch (InterruptedException e) 
        {
            // Displays that the thread was interupted.
            System.out.println(Thread.currentThread().getName() + " interrupted");
        } 

    }

    /************************************************************
    *                                                           *
    *  Method Name:  bubbleSort                                 *
    *                                                           *
    *  Purpose:  Performs the bubble sort algorithm             *
    *            on valuesToSort                                *
    *                                                           *
    ************************************************************/
    private void bubbleSort()
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
                        Thread.sleep(sortSpeed);
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

    /************************************************************
    *                                                           *
    *  Method Name:  cocktailSort                               *
    *                                                           *
    *  Purpose:  Performs the cocktail algorithm on valuesToSort*
    *                                                           *
    ************************************************************/
    private void cocktailSort() 
    { 
        try
        {
            boolean swapped = true; 
            int start = 0; 
            int end = valuesToSort.length; 
            
            while (swapped == true) 
            { 
                // reset the swapped flag on entering the 
                // loop, because it might be true from a 
                // previous iteration. 
                swapped = false; 
            
                // loop from bottom to top same as 
                // the bubble sort 
                for (int i = start; i < end - 1; ++i) 
                { 
                    if (valuesToSort[i] > valuesToSort[i + 1]) 
                    { 
                        int temp = valuesToSort[i]; 
                        valuesToSort[i] = valuesToSort[i + 1]; 
                        valuesToSort[i + 1] = temp; 
                        swapped = true; 
                    } 
                    Thread.sleep(sortSpeed/5);
                    repaint();
                } 
            
                // if nothing moved, then array is sorted. 
                if (swapped == false) 
                    break; 
            
                // otherwise, reset the swapped flag so that it 
                // can be used in the next stage 
                swapped = false; 
            
                // move the end point back by one, because 
                // item at the end is in its rightful spot 
                end = end - 1; 
            
                // from top to bottom, doing the 
                // same comparison as in the previous stage 
                for (int i = end - 1; i >= start; i--) 
                { 
                    if (valuesToSort[i] > valuesToSort[i + 1]) 
                    { 
                        int temp = valuesToSort[i]; 
                        valuesToSort[i] = valuesToSort[i + 1]; 
                        valuesToSort[i + 1] = temp; 
                        swapped = true; 
                    } 
                    Thread.sleep(sortSpeed/5);
                    repaint();
                } 
            
                // increase the starting point, because 
                // the last stage would have moved the next 
                // smallest number to its rightful spot. 
                start = start + 1; 
            } 
        }
        catch (InterruptedException e)
        {
            // Displays that the thread was interupted.
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    }

    /************************************************************
    *                                                           *
    *  Method Name:  heapSort                                   *
    *                                                           *
    *  Purpose:  Performs the heapsort algorithm on valuesToSort*
    *                                                           *
    ************************************************************/
    private void heapSort() 
    { 
        try
        {
            int n = valuesToSort.length; 
  
            // Build heap (rearrange array) 
            for (int i = n / 2 - 1; i >= 0; i--) 
            {
                Thread.sleep(sortSpeed*5);
                repaint();
                heapify(n, i); 
            }
            // One by one extract an element from heap 
            for (int i=n-1; i>=0; i--) 
            { 
                // Move current root to end 
                int temp = valuesToSort[0]; 
                valuesToSort[0] = valuesToSort[i]; 
                valuesToSort[i] = temp; 
            
                // call max heapify on the reduced heap 
                heapify(i, 0); 
                Thread.sleep(sortSpeed*10);
                repaint();
            } 
        }
        catch (InterruptedException e) 
        {
            // Displays that the thread was interupted.
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    } 
  
    /************************************************************
    *                                                           *
    *  Method Name:  heapify                                    *
    *                                                           *
    *  Purpose:  Helper method for heapSort                     *
    *                                                           *
    ************************************************************/
    private void heapify(int n, int i) 
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
  
            // Recursively heapify the affected sub-tree 
            heapify(n, largest); 
        } 
    } 

    /************************************************************
    *                                                          *
    *  CSCI 470-2/502-2       Assignment 5        Summer 2019  *
    *                   (or In-Class Exercise n)               *
    *                                                          *
    *  Class Name:  SortAnimationPanel                         *
    *                                                          *
    *  Programmer:  Robert Oury                                *
    *               Brett Carney                               *
    *                                                          *
    *  Purpose:  Inner runnable class to paint all algorithm   *
    *            updates to the array to the animation panel   *
    *                                                          *
    ************************************************************/
    private class SortAnimationPanel extends JPanel implements Runnable 
    {
        private static final long serialVersionUID = 1L;

        /************************************************************
        *                                                           *
        *  Method Name:  SortAnimationPanel                         *
        *                                                           *
        *  Purpose:  Call repaint upon instantiation                *
        *                                                           *
        ************************************************************/
        SortAnimationPanel() 
        {
            repaint();
        }

        /************************************************************
        *                                                           *
        *  Method Name:  paintComponent                             *
        *                                                           *
        *  Purpose:  Set background to white and paint a blue rect. *
        *            based on the value                             *
        *                                                           *
        ************************************************************/
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

        /************************************************************
        *                                                           *
        *  Method Name:  run                                        *
        *                                                           *
        *  Purpose:  Runs the selected sort algorithm               *
        *                                                           *
        ************************************************************/
        public void run() 
        {
            // Call appropriate sort method utilizing the combo box to sort in asc. order
            if(algorithmComboBox.getSelectedItem() == "Heap")
                heapSort();
            else if (algorithmComboBox.getSelectedItem() == "Selection")
                selectionSort();
            else if (algorithmComboBox.getSelectedItem() == "Bubble")
                bubbleSort();
            else if (algorithmComboBox.getSelectedItem() == "Cocktail")
                cocktailSort();
            else if (algorithmComboBox.getSelectedItem() == "Pancake")
                pancakeSort();
        }
    }

    /************************************************************
    *                                                           *
    *  Method Name:  addComponent                               *
    *                                                           *
    *  Purpose:  Adds a component to the specified position     *
    *                                                           *
    ************************************************************/
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