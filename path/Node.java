
public class Node 
{
    int x;
    int y;
    int status; // defines cells status like empty, wall, start, finish, path.
    int lastx; 
    int lasty;
    int len; // total number of nodes in path
    int dist; // distance between nodes to end

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

    public int getDist()
    {
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