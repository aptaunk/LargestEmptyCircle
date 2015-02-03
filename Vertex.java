public class Vertex implements Comparable<Vertex> {
  public double x;
  public double y;
  
  public Vertex(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  @Override
  public int compareTo(Vertex v) {
    return
      this.x > v.x ? 1 : (this.x < v.x ? -1 : (this.y > v.y ? 1 : (this.y < v.y ? -1 : 0)));
  }
}
