import java.awt.event.*;

import javax.swing.JPanel;

import java.awt.*;

public class Map extends JPanel implements MouseListener, MouseMotionListener
{
    private static final long serialVersionUID = 1L;

    JPanel pane;
    private PathSearch path;
    private int mSize;
    private int cSize; 
    private int cell;
    private boolean start = false;
    private boolean finish = false;

    protected int startx;
    protected int starty;
    protected int finishx;
    protected int finishy;


    public Map(PathSearch path){
        this.path = path;
        this.mSize = path.MSIZE;
        this.cSize = path.CSIZE;
        this.cell = path.cell;
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) 
    {
		for(int x = 0; x < cell; x++) 
		{
			for(int y = 0; y < cell; y++) 
			{
                switch(path.map[x][y].getStatus())
                {
                    case 0: g.setColor(Color.WHITE);
                        break;
                    case 1: g.setColor(Color.BLACK);
                        break;
                    case 2: g.setColor(Color.BLUE);
                        break;
                    case 3: g.setColor(Color.GREEN);
                        break;
                    case 4: g.setColor(Color.RED);
                        break;
                    case 5: g.setColor(Color.YELLOW);
                        break;
                    case 6: g.setColor(Color.MAGENTA);
                        break;
                }
                g.fillRect(x*cSize, y*cSize, cSize,cSize);
                g.setColor(Color.BLACK);
                g.drawRect(x*cSize, y*cSize, cSize,cSize);
            }	
		}
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
     
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        int x = e.getX()/cSize;
        int y = e.getY()/cSize;
        int sx = path.getStartX();
        int sy = path.getStartY();
        int fx = path.getFinishx();
        int fy = path.getFinishy();
        if(sx ==x && sy ==y){
            start = true;
        } 
        if(fx==x && fy==y){
            finish = true;
        }   
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int sx = path.getStartX();
        int sy = path.getStartY();
        int fx = path.getFinishx();
        int fy = path.getFinishy();
        int x = e.getX()/cSize;
        int y = e.getY()/cSize;

        if(e.getX()< 600 && e.getY() < 600 && e.getX() > 0 && e.getY() > 0)
        {
            if(start && !(x==fx && y==fy))
            {
                path.map[sx][sy].setStatus(0);
                path.map[x][y].setStatus(5);
                path.setStartX(x); 
                path.setStartY(y);
            }
            if(finish && !(x==sx && y==sy))
            {
                path.map[fx][fy].setStatus(0);
                path.map[x][y].setStatus(6);
                path.setFinishX(x); 
                path.setFinishY(y);
            }
        }
        else{
            if(start)
                path.map[sx][sy].setStatus(5);
            
            if(finish)
                path.map[fx][fy].setStatus(6);
        }
        start = false;
        finish = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}