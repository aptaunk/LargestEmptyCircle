import java.util.ArrayList;

public class DelaunayTriangulation
{
    public static TriangleMesh bowyerWatson(Triangle superTriangle, ArrayList<Vertex> s) {
        TriangleMesh triangulation = new TriangleMesh();
        triangulation.add(superTriangle);
        for (Vertex v : s) {
            ArrayList<Triangle> badTriangles = new ArrayList<Triangle>();
            for (Triangle t : triangulation.triangles) {
                if (t.insideCircumcircle(v)) {
                    badTriangles.add(t);
                }
            }
            ArrayList<Edge> polygon = new ArrayList<Edge>();
            for (Triangle t : badTriangles) {
                for (Edge e : t.e) {
                    Triangle[] temp = triangulation.edge2TrianglesMap.get(e);
                    if (!triangulation.isSharedEdge(e)) {
                        polygon.add(e);
                    }
                }
            }
            for (Triangle t : badTriangles) {
                triangulation.remove(t);
            }
            for (Edge e : polygon) {
                Triangle newTri = new Triangle(v,e.v[0],e.v[1]);
                triangulation.add(newTri);
            }
        }
        for (Triangle t : triangulation.triangles) {
            if (shareVertices(t,superTriangle)) {
                triangulation.remove(t);
            }
        }
        return triangulation;
    }
    
    private static boolean shareVertices(Triangle t1, Triangle t2) {
        return 
            t1.v[0].equals(t2.v[0]) || t1.v[0].equals(t2.v[1]) || t1.v[0].equals(t2.v[2]) ||
            t1.v[1].equals(t2.v[0]) || t1.v[1].equals(t2.v[1]) || t1.v[1].equals(t2.v[2]) ||
            t1.v[2].equals(t2.v[0]) || t1.v[2].equals(t2.v[1]) || t1.v[2].equals(t2.v[2]);
    }
}
