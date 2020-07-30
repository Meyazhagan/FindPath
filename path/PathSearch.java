
import javax.swing.JFrame;

public class PathSearch 
{
    JFrame frame;

    protected int startx = -1;
    protected int starty = -1;
    protected int finishx = -1;
    protected int finishy = -1;

    protected int cell = 20;

    public PathSearch()
    {
        init();        
    }

    public void init()
    {
        frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) 
    {
        new PathSearch();
    }

}