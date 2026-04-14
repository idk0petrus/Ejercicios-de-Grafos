public class Main {
    public static void main(String[] args) {
        GrafoDirigido grafo = new GrafoDirigido();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");
        grafo.agregarVertice("F");

        grafo.agregarArista("A", "B", 20);
        grafo.agregarArista("A", "D", 50);
        grafo.agregarArista("B", "C", 10);
        grafo.agregarArista("C", "A", 25);
        grafo.agregarArista("C", "F", 45);
        grafo.agregarArista("D", "C", 30);
        grafo.agregarArista("D", "E", 10);
        grafo.agregarArista("E", "F", 5);
        grafo.agregarArista("F", "A", 30);
        grafo.agregarArista("F", "D", 25);

        System.out.println("=== Gafo Dirigido Con Pesos ===");
        grafo.imprimir();

        System.out.println("\n--- Recorridos desde A ---");
        System.out.println("BFS: " + grafo.bfs("A"));
        System.out.println("DFS: " + grafo.dfs("A"));

        System.out.println("\n--- Agregar vertice G y arista C->G (15) ---");
        grafo.agregarVertice("G");
        grafo.agregarArista("C", "G", 15);
        grafo.imprimir();

        System.out.println("\n--- Actualizar peso A->B a 99 ---");
        grafo.actualizarPeso("A", "B", 99);
        grafo.imprimir();

        System.out.println("\n--- Eliminar arista F->D y vertice E ---");
        grafo.eliminarArista("F", "D");
        grafo.eliminarVertice("E");
        grafo.imprimir();

        System.out.println("\n Recorridos finales desde A:");
        System.out.println("BFS: " + grafo.bfs("A"));
        System.out.println("DFS: " + grafo.dfs("A"));
    }
}