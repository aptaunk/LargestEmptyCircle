import java.util.ArrayList;

public class DelaunayTriangulation
{
    public static TriangleMesh bowyerWatson(Triangle superTraingle, ArrayList<Vertex> s) {
        TriangleMesh triangulation = new TriangleMesh();
        triangulation.add(superTraingle);
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
                    if (temp[0]==null || temp[1]==null) {
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
            if (
                t.v[0].equals(superTraingle.v[0]) || t.v[0].equals(superTraingle.v[1]) || t.v[0].equals(superTraingle.v[2]) ||
                t.v[1].equals(superTraingle.v[1]) || t.v[1].equals(superTraingle.v[2]) ||
                t.v[2].equals(superTraingle.v[2]) 
            ) {
                triangulation.remove(t);
            }
        }
        return triangulation;
    }
}
