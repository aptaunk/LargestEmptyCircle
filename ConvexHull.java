import java.util.ArrayList;

public class ConvexHull
{
    public static ArrayList<Vertex> jarvis(ArrayList<Vertex> s) {
        ArrayList<Vertex> p = new ArrayList<Vertex>();
        Vertex pointOnHull = leftMostVertex(s);
        int i=0;
        Vertex endpoint;
        do {
            p.add(pointOnHull);
            endpoint = s.get(0);
            for (int j = 1; j<s.size(); j++) {
                if ((endpoint.equals(pointOnHull)) || (isLeftOfEdge(s.get(j),p.get(i),endpoint))) {
                    endpoint = s.get(j);
                }
                i++;
                pointOnHull = endpoint;
            }
        } while (!endpoint.equals(p.get(0)));
        return p;
    }
    private static boolean isLeftOfEdge(Vertex p, Vertex e_v1, Vertex e_v2) {
        double ax = e_v2.x-e_v1.x;
        double ay = e_v2.y-e_v1.y;
        double bx = p.x-e_v1.x;
        double by = p.y-e_v1.y;
        return (ax * by) > (ay * bx);
    }
    private static Vertex leftMostVertex(ArrayList<Vertex> s) {
        Vertex min = s.get(0);
        for (int i=0; i<s.size(); i++) {
            if (s.get(i).compareTo(min)<0) {
                min = s.get(i);
            }
        }
        return min;
    }
}
