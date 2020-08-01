
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class PathSearch {
    JFrame frame;

    protected int cell = 50;

    protected final int MSIZE = 600;
    protected int CSIZE = MSIZE / cell;
    protected double genMap = (cell * cell) * 0.5;
    protected boolean solving = false;

    Random r = new Random();

    protected int startx = r.nextInt(cell);
    protected int starty = r.nextInt(cell);
    protected int finishx = r.nextInt(cell);
    protected int finishy = r.nextInt(cell);
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
        map[startx][starty].setStatus(5);
        map[finishx][finishy].setStatus(6);

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
        generate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                generatePuzzle();
            }
        });

        algo = new Algorithm();

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

    class Algorithm 
    {

        public void aStar()
        {
            final ArrayList<Node> priority = new ArrayList<>();
            priority.add(map[getStartX()][getStartY()]);
            while(solving){
                if(priority.size() <= 0){
                    solving = false;
                    break;
                }
                final int pathLen = priority.get(0).getLen() + 1;
                final ArrayList<Node> explored = exploreNearBy(priority.get(0), pathLen);
                if(explored.size() > 0){
                    explored.remove(0);
                    priority.addAll(explored);
                    panel.repaint();
                }
                else{
                    priority.remove(0);
                }
                sortQue(priority);
                
                System.out.println("is start");
            }
            
        }

        private ArrayList<Node> sortQue(ArrayList<Node> p) 
        {
            int c = 0;
            while(c < p.size()){
                int sm = c;
                for(int i = c+1; i < p.size();i++){
                    if(p.get(i).getDist() + p.get(i).getLen() < p.get(sm).getDist() + p.get(sm).getLen())
                        sm = i;
                }
                if(c != sm){
                    Node temp = p.get(c);
                    p.set(c, p.get(sm));
                    p.set(sm, temp);
                }
            }
            return p;
        }

        private ArrayList<Node> exploreNearBy(Node current, int pathLen) {
            final ArrayList<Node> explore = new ArrayList<>();
            for(int a = -1; a <= 1; a++){
                for(int b =-1; b <= 1; b++){
                    int xbound = current.getX() + a;
                    int ybound = current.getY() + b;
                    if(xbound >-1&& xbound <cell && ybound >-1&& ybound <cell){
                        final Node near = map[xbound][ybound];
                        if(near.getStatus() == 1 ){
                            explore(near, current.getX(), current.getY(), pathLen);
                            explore.add(near);
                        }
                    }

                }
            }
            return explore;
        }

        private void explore(Node current, int x, int y, int pathLen) 
        {
            if(current.getStatus() != 5 && current.getStatus() != 6)
                current.setStatus(4);

            current.setLastNode(x, y);
            current.setLen(pathLen);

            if(current.getStatus() ==1)
                backTrack(current.getLastX(), current.getLastY(), pathLen);        
        }

        private void backTrack(int lastX, int lastY, int pathLen) 
        {
            setLength(pathLen);
            while(pathLen > 1){
                final Node current = map[lastX][lastY];
                current.setStatus(3);
                lastX = current.getLastX();
                lastY = current.getLastY();
                pathLen--;
            }
            solving = false;
        }

    }
}