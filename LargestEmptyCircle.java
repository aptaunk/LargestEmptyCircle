import java.util.ArrayList;

public class LargestEmptyCircle
{
    public static void main (String[] args) {
        Vertex sv1 = new Vertex(-0.01,-0.01);
        Vertex sv2 = new Vertex(-0.01,1.02);
        Vertex sv3 = new Vertex(1.02,-0.01);
        Triangle superTriangle = new Triangle(sv1,sv2,sv3);
        
        ArrayList<Vertex> s = new ArrayList<Vertex>();
        s.add(new Vertex(0,0));
        s.add(new Vertex(0,1));
        s.add(new Vertex(1.01,0));
       getLargestEmptyCircle(superTriangle,s);
    }
    
    public static void getLargestEmptyCircle(Triangle superTriangle, ArrayList<Vertex> s) {
        TriangleMesh triangulation = DelaunayTriangulation.bowyerWatson(superTriangle,s);
        ArrayList<Vertex> convexHull = ConvexHull.jarvis(s);
        ArrayList<Triangle> candidates = new ArrayList<Triangle>();
        for (Triangle t : triangulation.triangles) {
            if (insideConvexHull(t.circumcenter,convexHull)) {
                candidates.add(t);
            }
        }
        Triangle max = candidates.get(0);
        for (Triangle t : candidates) {
            if (t.circumcircleRadiusSquared > max.circumcircleRadiusSquared) {
                max = t;
            }
        }
        
        System.out.println(max.circumcenter.toString());
        System.out.println(max.circumcircleRadiusSquared);
    }
    
    private static boolean insideConvexHull(Vertex v, ArrayList<Vertex> convexHull) {
        for (int i=1; i<convexHull.size(); i++) {
            if (!isLeftOfEdge(v,convexHull.get(i-1),convexHull.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isLeftOfEdge(Vertex p, Vertex e_v1, Vertex e_v2) {
        double ax = e_v2.x-e_v1.x;
        double ay = e_v2.y-e_v1.y;
        double bx = p.x-e_v1.x;
        double by = p.y-e_v1.y;
        return (ax * by) > (ay * bx);
    }
}
