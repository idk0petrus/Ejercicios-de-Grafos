public class Main {
    public static void main(String[] args) {
        GrafoNoDirigido grafo = new GrafoNoDirigido();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");
        grafo.agregarVertice("F");
        grafo.agregarVertice("G");

        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("A", "D");
        grafo.agregarArista("B", "E");
        grafo.agregarArista("B", "C");
        grafo.agregarArista("B", "G");
        grafo.agregarArista("C", "G");
        grafo.agregarArista("C", "F");
        grafo.agregarArista("C", "D");
        grafo.agregarArista("D", "G");
        grafo.agregarArista("E", "F");
        grafo.agregarArista("E", "G");
        grafo.agregarArista("F", "G");

        System.out.println("=== Grafo no dirigido ===");
        grafo.imprimir();

        System.out.println("\n--- Recorridos desde A ---");
        System.out.println("BFS: " + grafo.bfs("A"));
        System.out.println("DFS: " + grafo.dfs("A"));

        System.out.println("\n--- Agregar vertice H y arista C-H ---");
        grafo.agregarVertice("H");
        grafo.agregarArista("C", "H");
        grafo.imprimir();

        System.out.println("\n--- Eliminar arista A-D y vértice E ---");
        grafo.eliminarArista("A", "D");
        grafo.eliminarVertice("E");
        grafo.imprimir();

        System.out.println("\nRecorridos finales desde A:");
        System.out.println("BFS: " + grafo.bfs("A"));
        System.out.println("DFS: " + grafo.dfs("A"));
    }
}