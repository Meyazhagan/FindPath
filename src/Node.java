public class Node {
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
    return dToEnd;
  }

}