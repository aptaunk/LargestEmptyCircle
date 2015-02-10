public class VoronoiDiagram
{
    public static Mesh dualizeDelauneyTriangulation(TriangleMesh triangulation) {
        Mesh voronoiDiagram = new Mesh();
        for (Triangle t : triangulation.triangles) {
            for (Triangle neighbour : triangulation.getNeighbours(t)){
                if (neighbour != null) {
                    voronoiDiagram.add(new Edge(t.circumcenter,neighbour.circumcenter));
                }
            }
        }
        return voronoiDiagram;
    }
}
