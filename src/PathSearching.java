import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class PathSearching { // FRAME
	JFrame frame;
	// GENERAL VARIABLES
	private int cells = 20;
	private int delay = 30;
	private double dense = .5;
	private double density = (cells * cells) * .5;
	private int startx = -1;
	private int starty = -1;
	private int finishx = -1;
	private int finishy = -1;
	private int tool = 0;
	private int checks = 0;
	private int length = 0;
	private int curAlg = 0;
	private int WIDTH = 850;
	private final int HEIGHT = 650;
	private final int MSIZE = 600;
	private int CSIZE = MSIZE / cells;
	// UTIL ARRAYS
	private String[] algorithms = { "Dijkstra", "A*" };
	private String[] tools = { "Start", "Finish", "Wall", "Eraser" };
	// BOOLEANS
	private boolean solving = false;
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

  private void initialize() {
  }

  private void clearMap() {
  }
}