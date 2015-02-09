import java.util.HashSet;
import java.util.HashMap;

public class Mesh
{
    HashSet<Edge> edges;
    HashMap<Vertex,HashSet<Edge>> vertex2EdgeMap;
    
    public Mesh() {
        edges = new HashSet<Edge>();
        vertex2EdgeMap = new HashMap<Vertex,HashSet<Edge>>();
    }
    
    public void add(Edge e) {
        for (Vertex v : e.v) {
            HashSet<Edge> temp = vertex2EdgeMap.get(v);
            if (temp == null) {
                temp = new HashSet<Edge>();
                vertex2EdgeMap.put(v,temp);
            }
            temp.add(e);
        }
        edges.add(e);
    }
}
