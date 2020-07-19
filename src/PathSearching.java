import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class PathSearching { // FRAME
	JFrame frame;
	// GENERAL VARIABLES
	protected int cells = 20;
	protected int delay = 30;
	protected double dense = .5;
	protected double density = (cells * cells) * .5;
	protected int startx = -1;
	protected int starty = -1;
	protected int finishx = -1;
	protected int finishy = -1;
	protected int tool = 0;
	protected int checks = 0;
	protected int length = 0;
	protected int curAlg = 0;
	protected int WIDTH = 850;
	protected final int HEIGHT = 650;
	protected final int MSIZE = 600;
	protected int CSIZE = MSIZE / cells;
	// UTIL ARRAYS
	protected String[] algorithms = { "Dijkstra", "A*" };
	protected String[] tools = { "Start", "Finish", "Wall", "Eraser" };
	// BOOLEANS
	protected boolean solving = false;
	// UTIL
	Node[][] map;
	Algorithms Alg = new Algorithms();
	Random r = new Random();
	// SLIDERS
	JSlider size = new JSlider(1, 5, 2);
	JSlider speed = new JSlider(0, 500, delay);
	JSlider obstacles = new JSlider(1, 100, 50);
	// LABELS
	JLabel algL = new JLabel("Algorithms");
	JLabel toolL = new JLabel("Toolbox");
	JLabel sizeL = new JLabel("Size:");
	JLabel cellsL = new JLabel(cells + "x" + cells);
	JLabel delayL = new JLabel("Delay:");
	JLabel msL = new JLabel(delay + "ms");
	JLabel obstacleL = new JLabel("Dens:");
	JLabel densityL = new JLabel(obstacles.getValue() + "%");
	JLabel checkL = new JLabel("Checks: " + checks);
	JLabel lengthL = new JLabel("Path Length: " + length);
	// BUTTONS
	JButton searchB = new JButton("Start Search");
	JButton resetB = new JButton("Reset");
	JButton genMapB = new JButton("Generate Map");
	JButton clearMapB = new JButton("Clear Map");
	JButton creditB = new JButton("Credit");
	// DROP DOWN
	JComboBox algorithmsBx = new JComboBox(algorithms);
	JComboBox toolBx = new JComboBox(tools);
	// PANELS
	JPanel toolP = new JPanel();
	// CANVAS
	Map canvas;
	// BORDER
	Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

	public static void main(String[] args) { // MAIN METHOD
		new PathSearching();
	}

	public PathSearching() { // CONSTRUCTOR
		clearMap();
		initialize();
	}

  public void generateMap() { // GENERATE MAP
		clearMap(); // CREATE CLEAR MAP TO START
		for (int i = 0; i < density; i++) {
			Node current;
			do {
				int x = r.nextInt(cells);
				int y = r.nextInt(cells);
				current = map[x][y]; // FIND A RANDOM NODE IN THE GRID
			} while (current.getType() == 2); // IF IT IS ALREADY A WALL, FIND A NEW ONE
			current.setType(2); // SET NODE TO BE A WALL
		}
	}

	public void clearMap() { // CLEAR MAP
		finishx = -1; // RESET THE START AND FINISH
		finishy = -1;
		startx = -1;
		starty = -1;
		map = new Node[cells][cells]; // CREATE NEW MAP OF NODES
		for (int x = 0; x < cells; x++) {
			for (int y = 0; y < cells; y++) {
				map[x][y] = new Node(3, x, y); // SET ALL NODES TO EMPTY
			}
		}
		reset(); // RESET SOME VARIABLES
	}

	public void resetMap() { // RESET MAP
		for (int x = 0; x < cells; x++) {
			for (int y = 0; y < cells; y++) {
				Node current = map[x][y];
				if (current.getType() == 4 || current.getType() == 5) // CHECK TO SEE IF CURRENT NODE IS EITHER CHECKED OR FINAL
																															// PATH
					map[x][y] = new Node(3, x, y); // RESET IT TO AN EMPTY NODE
			}
		}
		if (startx > -1 && starty > -1) { // RESET THE START AND FINISH
			map[startx][starty] = new Node(0, startx, starty);
			map[startx][starty].setPathL(0);
		}
		if (finishx > -1 && finishy > -1)
			map[finishx][finishy] = new Node(1, finishx, finishy);
		reset(); // RESET SOME VARIABLES
	}

	private void initialize() { // INITIALIZE THE GUI ELEMENTS
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("Path Finding");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		toolP.setBorder(BorderFactory.createTitledBorder(loweredetched, "Controls"));
		int space = 25;
		int buff = 45;

		toolP.setLayout(null);
		toolP.setBounds(10, 10, 210, 600);

		searchB.setBounds(40, space, 120, 25);
		toolP.add(searchB);
		space += buff;

		resetB.setBounds(40, space, 120, 25);
		toolP.add(resetB);
		space += buff;

		genMapB.setBounds(40, space, 120, 25);
		toolP.add(genMapB);
		space += buff;

		clearMapB.setBounds(40, space, 120, 25);
		toolP.add(clearMapB);
		space += 40;

		algL.setBounds(40, space, 120, 25);
		toolP.add(algL);
		space += 25;

		algorithmsBx.setBounds(40, space, 120, 25);
		toolP.add(algorithmsBx);
		space += 40;

		toolL.setBounds(40, space, 120, 25);
		toolP.add(toolL);
		space += 25;

		toolBx.setBounds(40, space, 120, 25);
		toolP.add(toolBx);
		space += buff;

		sizeL.setBounds(15, space, 40, 25);
		toolP.add(sizeL);
		size.setMajorTickSpacing(10);
		size.setBounds(50, space, 100, 25);
		toolP.add(size);
		cellsL.setBounds(160, space, 40, 25);
		toolP.add(cellsL);
		space += buff;

		delayL.setBounds(15, space, 50, 25);
		toolP.add(delayL);
		speed.setMajorTickSpacing(5);
		speed.setBounds(50, space, 100, 25);
		toolP.add(speed);
		msL.setBounds(160, space, 40, 25);
		toolP.add(msL);
		space += buff;

		obstacleL.setBounds(15, space, 100, 25);
		toolP.add(obstacleL);
		obstacles.setMajorTickSpacing(5);
		obstacles.setBounds(50, space, 100, 25);
		toolP.add(obstacles);
		densityL.setBounds(160, space, 100, 25);
		toolP.add(densityL);
		space += buff;

		checkL.setBounds(15, space, 100, 25);
		toolP.add(checkL);
		space += buff;

		lengthL.setBounds(15, space, 100, 25);
		toolP.add(lengthL);
		space += buff;

		creditB.setBounds(40, space, 120, 25);
		toolP.add(creditB);

		frame.getContentPane().add(toolP);

		canvas = new Map();
		canvas.pane.setBounds(230, 10, MSIZE + 1, MSIZE + 1);
		frame.getContentPane().add(canvas.pane);

		searchB.addActionListener(new ActionListener() { // ACTION LISTENERS
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				if ((startx > -1 && starty > -1) && (finishx > -1 && finishy > -1))
					solving = true;
			}
		});
		resetB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetMap();
				Update();
			}
		});
		genMapB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generateMap();
				Update();
			}
		});
		clearMapB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearMap();
				Update();
			}
		});
		algorithmsBx.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				curAlg = algorithmsBx.getSelectedIndex();
				Update();
			}
		});
		toolBx.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tool = toolBx.getSelectedIndex();
			}
		});
		size.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				cells = size.getValue() * 10;
				clearMap();
				reset();
				Update();
			}
		});
		speed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				delay = speed.getValue();
				Update();
			}
		});
		obstacles.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				dense = (double) obstacles.getValue() / 100;
				Update();
			}
		});
		creditB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "	                         Pathfinding\n"
												   + "             Copyright (c) 2017-2018\n"
												   + "                         Greer Viau\n"
												   + "          Build Date:  March 28, 2018   ", "Credit", JOptionPane.PLAIN_MESSAGE, new ImageIcon(""));
			}
		});
		
		startSearch();	//START STATE
	}
	
	public void startSearch() {	//START STATE
		if(solving) {
			switch(curAlg) {
				case 0:
					Alg.Dijkstra(this);
					break;
				case 1:
					Alg.AStar(this);
					break;
			}
		}
		pause();	//PAUSE STATE
	}
	
	public void pause() {	//PAUSE STATE
		int i = 0;
		while(!solving) {
			i++;
			if(i > 500)
				i = 0;
			try {
				Thread.sleep(1);
			} catch(Exception e) {}
		}
		startSearch();	//START STATE
	}
	
	public void Update() {	//UPDATE ELEMENTS OF THE GUI
		density = (cells*cells)*dense;
		CSIZE = MSIZE/cells;
		canvas.pane.repaint();
		cellsL.setText(cells+"x"+cells);
		msL.setText(delay+"ms");
		lengthL.setText("Path Length: "+length);
		densityL.setText(obstacles.getValue()+"%");
		checkL.setText("Checks: "+checks);
	}
	
	public void reset() {	//RESET METHOD
		solving = false;
		length = 0;
		checks = 0;
	}
	
	public void delay() {	//DELAY METHOD
		try {
			Thread.sleep(1000);
		} catch(Exception e) {}
	}
}