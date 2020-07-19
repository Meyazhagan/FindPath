public class Node extends PathSearching{
  int x;
  int y;
  int type;
  int lastx;
  int lasty;
  int pathL;
  double dToEnd;

  public Node(int type, int x, int y){
    this.type = type;
    this.x = x;
    this.y = y;
    pathL = -1;
  }

  public double getEuclidDIst(){
    int xdis = Math.abs(x - finishX);
    int ydis = Math.abs(y - finishy);
    dToEnd = Math.sqrt((xdis*xdis) + (ydis*ydis));
    return dToEnd;
  }

  public int getX() {return x;}		//GET METHODS
  public int getY() {return y;}
  public int getLastx() {return lastx;}
  public int getLasty() {return lasty;}
  public int getType() {return type;}
  public int getPathL() {return pathL;}
  
  public void settype(int type) {this.type = type;}		//SET METHODS
  public void setLastNode(int x, int y) {lastx = x; lasty = y;}
  public void setPathL(int pathL) {this.pathL = pathL;}

}