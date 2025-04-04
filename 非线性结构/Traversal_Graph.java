package Nonlinear;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Traversal_Graph<E> {
    List<Vertex> graphBFS(GraphAdjList graph, Vertex startVet) {
        List<Vertex> res = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        visited.add(startVet);
        Queue<Vertex> que = new LinkedList<>();
        que.offer(startVet);
        while (!que.isEmpty()) {
            Vertex vet = que.poll();
            res.add(vet);
            if (graph.adjList.get(vet) != null) {
                for (Vertex adjVet : (List<Vertex<E>>)graph.adjList.get(vet)) {
                    if (visited.contains(adjVet))
                        continue;
                    que.offer(adjVet);
                    visited.add(adjVet);
                }
            }
        }
        return res;
    }

    void dfs(GraphAdjList graph, Set<Vertex> visited, List<Vertex> res, Vertex vet) {
        res.add(vet);
        visited.add(vet);
        for (Vertex adjVet : (List<Vertex<E>>)graph.adjList.get(vet)) {
            if (visited.contains(adjVet))
                continue;
            dfs(graph, visited, res, adjVet);
        }
    }

    List<Vertex> graphDFS(GraphAdjList graph, Vertex startVet) {
        List<Vertex> res = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        dfs(graph, visited, res, startVet);
        return res;
    }
}
