
import java.awt.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.*;

public class PathSearch {
    JFrame frame;

    protected int cell = 40;

    protected final int MSIZE = 600;
    protected int CSIZE = MSIZE / cell;

    Random r = new Random();

    protected int startx = r.nextInt(cell);
    protected int starty = r.nextInt(cell);
    protected int finishx = r.nextInt(cell);
    protected int finishy = r.nextInt(cell);

    private int space = 20;
    private int buffer = 40;

    protected Node[][] map;

    Map panel;

    public PathSearch() {
        init();
    }

    public void init() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(650, 660);
        // frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        create();
        map[startx][starty].setStatus(5);
        map[finishx][finishy].setStatus(6);

        panel = new Map(this);
        panel.setBounds(10, 10, 601,601);
        frame.add(panel);
    }
    public void create(){
        map = new Node[cell][cell];
        for(int i =0; i < cell; i++){
            for(int j = 0; j < cell ; j++){
                map[i][j] = new Node(i, j , 0);
            }
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