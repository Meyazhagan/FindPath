import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Map extends PathSearching implements MouseListener, MouseMotionListener {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  JPanel pane;
  public Map(){
      pane = new JPanel();
			pane.addMouseListener(this);
			pane.addMouseMotionListener(this);
		}
		
		public void paintComponent(Graphics g) {	//REPAINT
			paintComponent(g);
			for(int x = 0; x < cells; x++) {	//PAINT EACH NODE IN THE GRID
				for(int y = 0; y < cells; y++) {
					switch(map[x][y].getType()) {
						case 0:
							g.setColor(Color.GREEN);
							break;
						case 1:
							g.setColor(Color.RED);
							break;
						case 2:
							g.setColor(Color.BLACK);
							break;
						case 3:
							g.setColor(Color.WHITE);
							break;
						case 4:
							g.setColor(Color.CYAN);
							break;
						case 5:
							g.setColor(Color.YELLOW);
							break;
					}
					g.fillRect(x*CSIZE,y*CSIZE,CSIZE,CSIZE);
					g.setColor(Color.BLACK);
					g.drawRect(x*CSIZE,y*CSIZE,CSIZE,CSIZE);
					//DEBUG STUFF
					/*
					if(curAlg == 1)
						g.drawString(map[x][y].getHops()+"/"+map[x][y].getEuclidDist(), (x*CSIZE)+(CSIZE/2)-10, (y*CSIZE)+(CSIZE/2));
					else 
						g.drawString(""+map[x][y].getHops(), (x*CSIZE)+(CSIZE/2), (y*CSIZE)+(CSIZE/2));
					*/
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			try {
				int x = e.getX()/CSIZE;	
				int y = e.getY()/CSIZE;
				Node current = map[x][y];
				if((tool == 2 || tool == 3) && (current.getType() != 0 && current.getType() != 1))
					current.setType(tool);
				Update();
			} catch(Exception z) {}
		}

		@Override
		public void mouseMoved(MouseEvent e) {}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			resetMap();	//RESET THE MAP WHENEVER CLICKED
			try {
				int x = e.getX()/CSIZE;	//GET THE X AND Y OF THE MOUSE CLICK IN RELATION TO THE SIZE OF THE GRID
				int y = e.getY()/CSIZE;
				Node current = map[x][y];
				switch(tool ) {
					case 0: {	//START NODE
						if(current.getType()!=2) {	//IF NOT WALL
							if(startx > -1 && starty > -1) {	//IF START EXISTS SET IT TO EMPTY
								map[startx][starty].setType(3);
								map[startx][starty].setPathL(-1);
							}
							current.setPathL(0);
							startx = x;	//SET THE START X AND Y
							starty = y;
							current.setType(0);	//SET THE NODE CLICKED TO BE START
						}
						break;
					}
					case 1: {//FINISH NODE
						if(current.getType()!=2) {	//IF NOT WALL
							if(finishx > -1 && finishy > -1)	//IF FINISH EXISTS SET IT TO EMPTY
								map[finishx][finishy].setType(3);
							finishx = x;	//SET THE FINISH X AND Y
							finishy = y;
							current.setType(1);	//SET THE NODE CLICKED TO BE FINISH
						}
						break;
					}
					default:
						if(current.getType() != 0 && current.getType() != 1)
							current.setType(tool);
						break;
				}
				Update();
			} catch(Exception z) {}	//EXCEPTION HANDLER
		}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
