
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class PathSearch {
    JFrame frame;

    protected int cell = 50;

    protected final int MSIZE = 600;
    protected int CSIZE = MSIZE / cell;
    protected double genMap = (cell * cell) * 0.5;

    Random r = new Random();

    protected int startx = r.nextInt(cell);
    protected int starty = r.nextInt(cell);
    protected int finishx = r.nextInt(cell);
    protected int finishy = r.nextInt(cell);

    protected boolean drawStatus = true;

    protected Node[][] map;

    Map panel;

    JToggleButton draw;
    JToggleButton start;
    JToggleButton path;
    JButton generate;

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
                }
                else
                {
                    start.setText("Start");
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

        panel = new Map(this);
        panel.setBounds(10, 10, 601,601);
        frame.add(panel);
        frame.add(draw);
        frame.add(start);
        frame.add(path);
        frame.add(generate);
        frame.setVisible(true);
    }
    public void create(){
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
            panel.repaint();
        }
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
}