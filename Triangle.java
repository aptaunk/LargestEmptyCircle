public class Triangle
{
    public Vertex[] v;
    
    public Triangle (Vertex v1, Vertex v2, Vertex v3) {
        v = new Vertex[3];
        v[0] = v1;
        v[1] = v2;
        v[2] = v3;
        if (!isCCW()) {
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
    }
    
    private boolean isCCW() {
        Vertex a = new Vertex(v[1].x-v[0].x,v[1].y-v[0].y);
        Vertex b = new Vertex(v[3].x-v[0].x,v[3].y-v[0].y);
        return (a.x * b.y) > (a.y * b.x);
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
        double[][] m = new double[3][3];
        m[0][0] = v[0].x - p.x;
        m[0][1] = v[0].y - p.y;
        m[0][2] = (v[0].x*v[0].x-p.x*p.x)-(v[0].y*v[0].y-p.y*p.y);
        m[1][0] = v[1].x - p.x;
        m[1][1] = v[1].y - p.y;
        m[1][2] = (v[1].x*v[1].x-p.x*p.x)-(v[1].y*v[1].y-p.y*p.y);
        m[2][0] = v[2].x - p.x;
        m[2][1] = v[2].y - p.y;
        m[2][2] = (v[2].x*v[2].x-p.x*p.x)-(v[2].y*v[2].y-p.y*p.y);
        return
            m[0][0]*(m[1][1]*m[2][2]-m[1][2]*m[2][1])
            - m[0][1]*(m[1][0]*m[2][2]-m[1][2]*m[2][0])
            + m[0][2]*(m[1][0]*m[2][1]-m[1][1]*m[2][0]) > 0;
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
