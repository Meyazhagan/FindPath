import java.util.ArrayList;

class Algorithm 
    {
    PathSearch path;
    public Algorithm(PathSearch path){
        this.path = path;
    }

    public void aStar()
    {
        final ArrayList<Node> priority = new ArrayList<>();
        priority.add(path.map[path.getStartX()][path.getStartY()]);
        while(path.getSolving()){
            if(priority.size() <= 0){
                path.setSolving(false);
                break;
            }
            final int pathLen = priority.get(0).getLen() + 1;
            final ArrayList<Node> explored = exploreNearBy(priority.get(0), pathLen);
            if(explored.size() > 0){
                priority.remove(0);
                priority.addAll(explored);
                path.panel.repaint();
                path.delay();
            }
            else{
                priority.remove(0);
            }
            sortQue(priority);
        }
        
    }

    private ArrayList<Node> sortQue(ArrayList<Node> p) 
    {
        int c = 0;
        while(c < p.size()){
            int sm = c;
            for(int i = c+1; i < p.size();i++){
                if(p.get(i).getEuclidDist(path) + p.get(i).getLen() < p.get(sm).getEuclidDist(path) + p.get(sm).getLen())
                    sm = i;
            }
            if(c != sm){
                Node temp = p.get(c);
                p.set(c, p.get(sm));
                p.set(sm, temp);
            }
            c++;
        }
        return p;
    }

    private ArrayList<Node> exploreNearBy(Node current, int pathLen) {
        final ArrayList<Node> explore = new ArrayList<>();
        for(int a = -1; a <= 1; a++){
            for(int b =-1; b <= 1; b++){
                int xbound = current.getX() + a;
                int ybound = current.getY() + b;
                if((xbound >-1&& xbound <path.cell) && (ybound >-1&& ybound <path.cell)){
                    
                    final Node near = path.map[xbound][ybound];

                    if((near.getLen() == -1 || (near.getLen() > pathLen)) && near.getStatus() != 1 ){
                        explore(near, current.getX(), current.getY(), pathLen);
                        explore.add(near);
                    }
                }

            }
        }
        return explore;
    }

    private void explore(final Node current,final int x,final int y,final int pathLen) 
    {
        if(current.getStatus() != 5 && current.getStatus() != 6)
            current.setStatus(4);

        current.setLastNode(x, y);
        current.setLen(pathLen);

        if(current.getStatus() == 6)
            backTrack(current.getLastX(), current.getLastY(), pathLen);        
    }

    private void backTrack(int lastX, int lastY, int pathLen) 
    {
        path.setLength(pathLen);
        while(pathLen > 0){
            final Node current = path.map[lastX][lastY];
            current.setStatus(2);
            lastX = current.getLastX();
            lastY = current.getLastY();
            pathLen--;
        }
        path.solving = false;
    }
}