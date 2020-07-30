
import java.awt.*;
import java.util.Random;

import javax.swing.*;

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
    JPanel tools;
    JLabel cellL;
    JLabel cellS;
    JSlider cellSlider;
    JLabel drawL;
    JToggleButton draw;
    JLabel chooseL;
    JComboBox choose;

    public PathSearch() {
        init();
    }

    public void init() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(900, 660);
        // frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        create();
        map[startx][starty].setStatus(5);
        map[finishx][finishy].setStatus(6);
        
        tools = new JPanel();
        tools.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tools.setBounds(625, 10, 350, 601);

        // cellL = new JLabel("cell ");
        // cellL.setBounds(10, space, 100, 20);
        // tools.add(cellL);
        // space += buffer;

        // cellSlider = new JSlider();
        // cellSlider.setBounds(10, space, 50, 20);
        // cellSlider.addChangeListener(new ChangeListener(){

		// 	@Override
		// 	public void stateChanged(ChangeEvent e) {
				
		// 	}
        // });
        // tools.add(cellSlider);

        panel = new Map(this);
        panel.setBounds(10, 10, 601,601);
        frame.add(panel);
        frame.add(tools);
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