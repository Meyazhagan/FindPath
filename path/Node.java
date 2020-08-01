
public class Node 
{
    int x;
    int y;
    int status; // defines cells status like 0 - empty, 1 - wall, 2- path, 3- currentpath, 4 - finished,5- start, 6 - finish
    int lastx; 
    int lasty;
    int len; // total number of nodes in path
    double dist; // distance between nodes to end

    public Node(int x, int y, int status){
        this.x = x;
        this.y = y;
        this.len = -1;
        this.status = status;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getLastX()
    {
        return lastx;
    }

    public int getLastY()
    {
        return lasty;
    }

    public int getStatus()
    {
        return status;
    }

    public int getLen()
    {
        return len;
    }

    public double getEuclidDist(PathSearch p)
    {
        int xdis = Math.abs(x - p.getFinishx());
        int ydis = Math.abs(y - p.getFinishy());
        dist = Math.sqrt((xdis*xdis) + (ydis*ydis));
        return dist;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void setLen(int len){
        this.len = len;
    }

    public void setLastNode(int lastx, int lasty){
        this.lastx = lastx;
        this.lasty = lasty;
    }
}