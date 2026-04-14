import java.util.*;

public class GrafoNoDirigido {
    private final List<String> vertices = new ArrayList<>();
    private final Map<String, Integer> indice = new HashMap<>();
    private int[][] matriz = new int[0][0];

    private void redimensionar(int nuevaCapacidad) {
        int n = vertices.size();
        int[][] nueva = new int[nuevaCapacidad][nuevaCapacidad];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matriz[i], 0, nueva[i], 0, n);
        }
        matriz = nueva;
    }

    public boolean agregarVertice(String v) {
        if (indice.containsKey(v)) return false;
        int n = vertices.size();
        if (n >= matriz.length) {
            redimensionar(Math.max(n + 1, matriz.length * 2));
        }
        indice.put(v, n);
        vertices.add(v);
        for (int i = 0; i <= n; i++) {
            matriz[i][n] = 0;
            matriz[n][i] = 0;
        }
        return true;
    }

    public boolean eliminarVertice(String v) {
        Integer idx = indice.remove(v);
        if (idx == null) return false;
        vertices.remove(v);
        int n = vertices.size();
        int[][] nueva = new int[n][n];
        int ni = 0;
        for (int i = 0; i <= n; i++) {
            if (i == idx) continue;
            int nj = 0;
            for (int j = 0; j <= n; j++) {
                if (j == idx) continue;
                nueva[ni][nj] = matriz[i][j];
                nj++;
            }
            ni++;
        }
        matriz = nueva;
        indice.clear();
        for (int i = 0; i < vertices.size(); i++) {
            indice.put(vertices.get(i), i);
        }
        return true;
    }

    public boolean agregarArista(String u, String v) {
        Integer i = indice.get(u);
        Integer j = indice.get(v);
        if (i == null || j == null) return false;
        matriz[i][j] = 1;
        matriz[j][i] = 1;
        return true;
    }

    public boolean eliminarArista(String u, String v) {
        Integer i = indice.get(u);
        Integer j = indice.get(v);
        if (i == null || j == null) return false;
        matriz[i][j] = 0;
        matriz[j][i] = 0;
        return true;
    }

    public List<String> bfs(String inicio) {
        Integer idxIni = indice.get(inicio);
        if (idxIni == null) return new ArrayList<>();
        List<String> resultado = new ArrayList<>();
        boolean[] visitado = new boolean[vertices.size()];
        Queue<Integer> cola = new LinkedList<>();

        visitado[idxIni] = true;
        cola.add(idxIni);

        while (!cola.isEmpty()) {
            int u = cola.poll();
            resultado.add(vertices.get(u));
            for (int v = 0; v < vertices.size(); v++) {
                if (matriz[u][v] == 1 && !visitado[v]) {
                    visitado[v] = true;
                    cola.add(v);
                }
            }
        }
        return resultado;
    }

    public List<String> dfs(String inicio) {
        Integer idxIni = indice.get(inicio);
        if (idxIni == null) return new ArrayList<>();
        List<String> resultado = new ArrayList<>();
        boolean[] visitado = new boolean[vertices.size()];
        dfsRecursivo(idxIni, visitado, resultado);
        return resultado;
    }

    private void dfsRecursivo(int u, boolean[] visitado, List<String> resultado) {
        visitado[u] = true;
        resultado.add(vertices.get(u));
        for (int v = 0; v < vertices.size(); v++) {
            if (matriz[u][v] == 1 && !visitado[v]) {
                dfsRecursivo(v, visitado, resultado);
            }
        }
    }

    public void imprimir() {
        System.out.println("vertices: " + vertices);
        System.out.println("matriz de adyacencia:");
        System.out.print("   ");
        for (String v : vertices) System.out.print(v + " ");
        System.out.println();
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + " ");
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}