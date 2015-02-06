import java.util.*;

public class ConvexHull
{
    public static ArrayList<Vertex> getConvexHull(Vertex[] s) {
        return jarvis(s);
    }
    
    private static ArrayList<Vertex> jarvis(Vertex[] s) {
        ArrayList<Vertex> p = new ArrayList<Vertex>();
        Vertex pointOnHull = leftMostVertex(s);
        int i=0;
        Vertex endpoint;
        do {
            p.add(pointOnHull);
            endpoint = s[0];
            for (int j = 1; j<=s.length; j++) {
                if ((endpoint.equals(pointOnHull)) || (isLeftOfEdge(s[j],p.get(i),endpoint))) {
                    endpoint = s[j];
                }
                i++;
                pointOnHull = endpoint;
            }
        } while (endpoint != p.get(0));
        return p;
    }
    private static boolean isLeftOfEdge(Vertex p, Vertex e_v1, Vertex e_v2) {
        double ax = e_v2.x-e_v1.x;
        double ay = e_v2.y-e_v1.y;
        double bx = p.x-e_v1.x;
        double by = p.y-e_v1.y;
        return (ax * by) > (ay * bx);
    }
    private static Vertex leftMostVertex(Vertex[] s) {
        Vertex min = s[0];
        for (int i=0; i<s.length; i++) {
            if (s[i].compareTo(min)<0) {
                min = s[i];
            }
        }
        return min;
    }
}
