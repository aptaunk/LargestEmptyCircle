public class Triangle
{
    public Vertex[] v;
    public Edge[] e;
    public Vertex circumcenter;
    public double circumcircleRadiusSquared;
    
    public Triangle (Vertex v1, Vertex v2, Vertex v3) {
        v = new Vertex[3];
        v[0] = v1;
        if (isCCW()) {
            v[1] = v2;
            v[2] = v3;
        } else {
            v[1] = v3;
            v[2] = v2;
        }
        if (v[0].compareTo(v[1])>0 || v[0].compareTo(v[2])>0) {
            if (v[1].compareTo(v[2]) < 0) {
                rotateCW();
            } else {
                rotateCCW();
            }
        }
        e = new Edge[3];
        e[0] = new Edge(v[1],v[2]);
        e[1] = new Edge(v[0],v[2]);
        e[2] = new Edge(v[0],v[1]);
        circumcenter = getCircumcenter();
        circumcircleRadiusSquared = getCircumcircleRadiusSquared();
    }
    
    private boolean isCCW() {
        double ax = v[1].x-v[0].x;
        double ay = v[1].y-v[0].y;
        double bx = v[3].x-v[0].x;
        double by = v[3].y-v[0].y;
        return (ax * by) > (ay * bx);
    }
    
    private void rotateCW() {
        Vertex temp = v[0];
        v[0] = v[1];
        v[1] = v[2];
        v[2] = temp;
    }
    
    private void rotateCCW() {
        Vertex temp = v[0];
        v[0] = v[2];
        v[2] = v[1];
        v[1] = temp;
    }
    
    public boolean insideCircumcircle(Vertex p) {
        return (p.x-circumcenter.x)*(p.x-circumcenter.x)+(p.y-circumcenter.y)*(p.y-circumcenter.y) < circumcircleRadiusSquared;
    }
    
    private Vertex getCircumcenter() {
        double d = 2*(v[0].x*(v[1].y-v[2].y)+v[1].x*(v[2].y-v[0].y)+v[2].x*(v[0].y-v[1].y));
        double ux = ((v[0].x*v[0].x+v[0].y*v[0].y)*(v[1].y-v[2].y)+(v[1].x*v[1].x+v[1].y*v[1].y)*(v[2].y-v[0].y)+(v[2].x*v[2].x+v[2].y*v[2].y)*(v[0].y-v[1].y))/d;
        double uy = ((v[0].x*v[0].x+v[0].y*v[0].y)*(v[2].x-v[1].x)+(v[1].x*v[1].x+v[1].y*v[1].y)*(v[0].x-v[2].x)+(v[2].x*v[2].x+v[2].y*v[2].y)*(v[1].x-v[0].x))/d;
        return new Vertex(ux,uy);
    }
    
    private double getCircumcircleRadiusSquared() {
        return (circumcenter.x-v[0].x)*(circumcenter.x-v[0].x)+(circumcenter.y-v[0].y)*(circumcenter.y-v[0].y);
    }
    
    @Override
    public String toString() {
        return "["+v[0].toString()+v[1].toString()+v[2].toString()+"]";
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Triangle)) return false;
        Triangle t = (Triangle) o;
        return t.toString().equals(this.toString());
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
