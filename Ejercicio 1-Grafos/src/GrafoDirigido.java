import java.util.*;

public class GrafoDirigido {
    private static class Arista {
        String destino;
        int peso;
        Arista(String destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
        public String toString() {
            return destino + "(" + peso + ")";
        }
    }

    private final Map<String, LinkedList<Arista>> adj = new LinkedHashMap<>();
    private final List<String> vertices = new ArrayList<>();

    public boolean agregarVertice(String v) {
        if (adj.containsKey(v)) return false;
        adj.put(v, new LinkedList<>());
        vertices.add(v);
        Collections.sort(vertices);
        return true;
    }

    public boolean eliminarVertice(String v) {
        if (!adj.containsKey(v)) return false;
        for (String origen : adj.keySet()) {
            adj.get(origen).removeIf(a -> a.destino.equals(v));
        }
        adj.remove(v);
        vertices.remove(v);
        return true;
    }

    public boolean agregarArista(String origen, String destino, int peso) {
        if (!adj.containsKey(origen) || !adj.containsKey(destino)) return false;
        for (Arista a : adj.get(origen)) {
            if (a.destino.equals(destino)) return false;
        }
        adj.get(origen).add(new Arista(destino, peso));
        adj.get(origen).sort(Comparator.comparing(a -> a.destino));
        return true;
    }

    public boolean eliminarArista(String origen, String destino) {
        if (!adj.containsKey(origen)) return false;
        return adj.get(origen).removeIf(a -> a.destino.equals(destino));
    }

    public boolean actualizarPeso(String origen, String destino, int nuevoPeso) {
        if (!adj.containsKey(origen)) return false;
        for (Arista a : adj.get(origen)) {
            if (a.destino.equals(destino)) {
                a.peso = nuevoPeso;
                return true;
            }
        }
        return false;
    }

    public List<String> bfs(String inicio) {
        if (!adj.containsKey(inicio)) return new ArrayList<>();
        List<String> resultado = new ArrayList<>();
        Set<String> visitado = new HashSet<>();
        Queue<String> cola = new LinkedList<>();

        visitado.add(inicio);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            String u = cola.poll();
            resultado.add(u);
            for (Arista a : adj.get(u)) {
                String v = a.destino;
                if (!visitado.contains(v)) {
                    visitado.add(v);
                    cola.add(v);
                }
            }
        }
        return resultado;
    }

    public List<String> dfs(String inicio) {
        if (!adj.containsKey(inicio)) return new ArrayList<>();
        List<String> resultado = new ArrayList<>();
        Set<String> visitado = new HashSet<>();
        dfsRecursivo(inicio, visitado, resultado);
        return resultado;
    }

    private void dfsRecursivo(String u, Set<String> visitado, List<String> resultado) {
        visitado.add(u);
        resultado.add(u);
        for (Arista a : adj.get(u)) {
            String v = a.destino;
            if (!visitado.contains(v)) {
                dfsRecursivo(v, visitado, resultado);
            }
        }
    }

    public void imprimir() {
        System.out.println("=== Lista de Adyacencia ===");
        for (String v : vertices) {
            System.out.print(v + " -> ");
            System.out.println(adj.get(v));
        }
    }
}
