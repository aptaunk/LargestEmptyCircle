public class Edge
{
    public Vertex[] v;
    
    public Edge(Vertex v1, Vertex v2) {
        v = new Vertex[2];
        if (v1.compareTo(v2) < 0) {
            v[0] = v1;
            v[1] = v2;
        } else {
            v[0] = v2;
            v[1] = v1;
        }
    }
    
    @Override
    public String toString() {
        return "["+v[0].toString()+v[1].toString()+"]";
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Edge)) return false;
        Edge e = (Edge) o;
        return e.toString().equals(this.toString());
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
