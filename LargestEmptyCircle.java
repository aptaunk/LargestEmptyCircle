import java.util.ArrayList;

public class LargestEmptyCircle
{
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
        Vertex a = new Vertex(e_v2.x-e_v1.x,e_v2.y-e_v1.y);
        Vertex b = new Vertex(p.x-e_v1.x,p.y-e_v1.y);
        return (a.x * b.y) > (a.y * b.x);
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