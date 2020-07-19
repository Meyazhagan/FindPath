import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

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

  private void initialize() {
  }

  private void clearMap() {
  }
}