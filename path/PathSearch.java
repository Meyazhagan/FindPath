
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;

public class PathSearch {
    JFrame frame;

    protected int cell = 20;

    protected final int MSIZE = 600;
    protected int CSIZE = MSIZE / cell;
    protected double genMap = (cell * cell) * 0.5;
    protected boolean solving = false;

    Random r = new Random();

    protected int startx;
    protected int starty;
    protected int finishx;
    protected int finishy;
    protected int length;

    protected boolean drawStatus = true;

    protected Node[][] map;

    Map panel;
    Algorithm algo;

    JToggleButton draw;
    JToggleButton start;
    JToggleButton path;
    JButton generate;

    JButton clear;
    JButton cellPlus;
    JButton cellMinus;
    JLabel cellSize;
    JLabel pathL;
    JLabel compL;

    public PathSearch() {
        init();
    }

    public void init() {
        frame = new JFrame();
        frame.setSize(640, 700);
        frame.setResizable(false);
        frame.setTitle("Path Finding - A* Searching Algorithms");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        create();
        createPoints();

        draw =  new JToggleButton("Draw");
        draw.setBounds(20, 620, 100, 30);
        draw.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(draw.isSelected())
                {
                    draw.setText("Erase");
                    drawStatus = false;
                }
                else
                {
                    draw.setText("Draw");
                    drawStatus = true;
                }
            } 
        });

        start = new JToggleButton("Start");
        start.setBounds(160, 620, 100, 30);
        start.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(start.isSelected())
                {
                    start.setText("Stop");
                    solving = true;
                }
                else
                {
                    start.setText("Start");
                    solving = false;
                    clear();
                }
            } 
        });

        path = new JToggleButton("Path");
        path.setBounds(310, 620, 100, 30);
        path.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(path.isSelected())
                {
                    path.setText("Visualize");
                }
                else
                {
                    path.setText("Path");
                }
            } 
        });

        generate = new JButton("Generate Map");
        generate.setBounds(450, 620, 150, 30);
        generate.setBorder(null);
        generate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                generatePuzzle();
            }
        });

        algo = new Algorithm(this);

        panel = new Map(this);
        panel.setBounds(10, 10, 601,601);
        frame.add(panel);
        frame.add(draw);
        frame.add(start);
        frame.add(path);
        frame.add(generate);
        frame.setVisible(true);
        
        startSearching();
    }
    
    private void startSearching() 
    {
        if(solving)
            algo.aStar();
        waitTill();
    } 

    public void waitTill() {
		int i = 0;
		while(!solving) 
		{
			i++;
			if(i > 500)
				i = 0;
			try 
			{
				Thread.sleep(1);
			} catch(Exception e) {}
		}
		startSearching();
	}

    public void create() {
        map = new Node[cell][cell];
        for(int i =0; i < cell; i++){
            for(int j = 0; j < cell ; j++){
                map[i][j] = new Node(i, j , 0);
            }
        }
    }
    public void clear(){
        for(int i =0; i < cell; i++){
            for(int j = 0; j < cell ; j++){
                map[i][j].setStatus(0);
                map[i][j].setLen(-1);
            }
        }
        createPoints();
    }

    public void createPoints(){
        startx = r.nextInt(cell);
        starty = r.nextInt(cell);
        finishx = r.nextInt(cell);
        finishy = r.nextInt(cell);
     
        map[startx][starty].setStatus(5);
        map[finishx][finishy].setStatus(6);
    }

    public void generatePuzzle(){
        for(int i =0; i < genMap; i++){
            int x  = r.nextInt(cell);
            int y = r.nextInt(cell);
            if(!panel.isIntersect(x, y, startx, starty) && 
               !panel.isIntersect(x, y, finishx, finishy) &&
               !(map[x][y].getStatus() == 1))
            {
                map[x][y].setStatus(1);
            }
            update();
        }
    }

    public void update(){
        panel.repaint();
    }

    public static void main(String[] args) 
    {
        new PathSearch();
    }

	public void setFinishY(int y) {
        this.finishy = y;
	}

	public void setFinishX(int x) {
        this.finishx = x;
	}

	public int getFinishy() {
		return finishy;
	}

	public int getFinishx() {
		return finishx;
	}

    public void setStartX(int x){
        this.startx = x;
    }

    public void setStartY(int y){
        this.starty = y;
    }

    public int getStartX(){
        return startx;
    }

    public int getStartY(){
        return starty;
    }

    public boolean getSolving(){
        return solving;
    }

    public void setSolving(boolean solving){
        this.solving = solving;
    }

    public void setLength(int length){
        this.length = length;
    }

    public void delay() 
	{
		try {
			Thread.sleep(100);
		} catch(Exception e) {}
	}
}