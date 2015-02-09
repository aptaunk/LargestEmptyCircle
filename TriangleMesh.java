import java.util.ArrayList;
import java.util.HashMap ;

public class TriangleMesh
{
    public ArrayList<Triangle> triangles;
    public HashMap<Edge,Triangle[]> edge2TrianglesMap;
    
    public TriangleMesh() {
        triangles = new ArrayList<Triangle>();
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
}
