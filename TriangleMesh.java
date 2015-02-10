import java.util.HashSet;
import java.util.HashMap;

public class TriangleMesh
{
    public HashSet<Triangle> triangles;
    public HashMap<Edge,Triangle[]> edge2TrianglesMap;
    
    public TriangleMesh() {
        triangles = new HashSet<Triangle>();
        edge2TrianglesMap = new HashMap<Edge,Triangle[]>();
    }
    
    public void add(Triangle t) {
        triangles.add(t);
        for (Edge e : t.e) {
            Triangle[] temp = edge2TrianglesMap.get(e);
            if(temp == null) {
                edge2TrianglesMap.put(e,new Triangle[]{t,null});
            } else if (!t.equals(temp[0]) && !t.equals(temp[1])) {
                if (temp[0]==null) {
                    temp[0] = t;
                } else {
                    temp[1] = t;
                }
            }
        }
    }
    
    public void remove(Triangle t) {
        for (Edge e : t.e) {
            Triangle[] temp = edge2TrianglesMap.get(e);
            if (temp!=null) {
                if (t.equals(temp[0])) {
                    temp[0] = null;
                } 
                if (t.equals(temp[1])) {
                    temp[1] = null;
                }
                if (temp[0]==null && temp[1]==null) {
                    edge2TrianglesMap.remove(e);
                }
            }
        }
        triangles.remove(t);
    }
    
    public boolean isSharedEdge(Edge e) {
        Triangle[] temp = edge2TrianglesMap.get(e);
        return temp[0]!=null && temp[1]!=null;
    }
    
    public Triangle[] getNeighbours(Triangle t) {
        Triangle[] neighbour = new Triangle[3];
        for (int i=0; i<3; i++) {
            Triangle[] temp = edge2TrianglesMap.get(t.e[i]);
            neighbour[i] = temp[0].equals(t)?temp[1]:temp[0];
        }
        return neighbour;
    }
}
