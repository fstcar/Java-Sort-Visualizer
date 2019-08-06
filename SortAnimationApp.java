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