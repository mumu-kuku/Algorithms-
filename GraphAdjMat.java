package Nonlinear;

import java.util.ArrayList;
import java.util.List;

// 手写基于邻接矩阵实现的无向图
public class GraphAdjMat <E> {
    List<E> vertices;
    List<List<Integer>> adjMat;

    public GraphAdjMat(E[] vertices, int[][] edges) {
        this.vertices = new ArrayList<>();
        this.adjMat = new ArrayList<>();

        for (E val : vertices) {
            addVertex(val);
        }
        for (int[] e : edges) {
            addEdge(e[0], e[1]);
        }
    }

    public int size() {
        return vertices.size();
    }

    public void addVertex(E val) {
        int n = size();
        List<Integer> newRow = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            newRow.add(0);
        }
        adjMat.add(newRow);
        for (List<Integer> row : adjMat) {
            row.add(0);
        }
    }

    public boolean removeVertex(int index) {
        if (index >= size() || index < 0)
            return false;
        vertices.remove(index);
        adjMat.remove(index);
        for (List<Integer> row : adjMat) {
            row.remove(index);
        }
        return true;
    }

    public boolean addEdge(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j)
            return false;
        adjMat.get(i).set(j, 1);
        adjMat.get(j).set(i, 1);
        return true;
    }

    public boolean removeEdge(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j)
            return false;
        adjMat.get(i).set(j, 0);
        adjMat.get(j).set(i, 0);
        return true;
    }

    public void print() {
        System.out.print(" 顶点列表 = ");
        System.out.println(vertices);
        System.out.print(" 邻接矩阵 = ");
        System.out.println(adjMat);
    }
}
