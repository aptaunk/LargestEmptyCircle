public class Vertex implements Comparable<Vertex> {
    public double x;
    public double y;
    
    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int compareTo(Vertex v) {
        return this.x > v.x ? 1 : (this.x < v.x ? -1 : (this.y > v.y ? 1 : (this.y < v.y ? -1 : 0)));
    }
    
    @Override
    public String toString() {
        return "("+this.x+","+this.y+")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex v = (Vertex) o;
        return v.x == this.x && v.y == this.y;
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
