import java.util.Iterator;
import java.util.Scanner;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class directFlights {

	/*******************************************************
	 * Part A: Representing Direct Flights
	 *******************************************************/

	public static SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> createGraph() {
		SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> g = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		String edinburgh = "Edinburgh";
		String heathrow = "Heathrow";
		String amsterdam = "Amsterdam";
		String boston = "Boston";
		String chicago = "Chicago";
		String montreal = "Montreal";
		String toronto = "Toronto";
		String shanghai = "Shanghai";
		String newdelhi = "New Delhi";
		String hongKong = "Hong Kong";

		g.addVertex(edinburgh);
		g.addVertex(heathrow);
		g.addVertex(amsterdam);
		g.addVertex(boston);
		g.addVertex(chicago);
		g.addVertex(montreal);
		g.addVertex(toronto);
		g.addVertex(shanghai);
		g.addVertex(newdelhi);
		g.addVertex(hongKong);

		DefaultWeightedEdge e1 = g.addEdge(edinburgh, heathrow);
		DefaultWeightedEdge e11 = g.addEdge(heathrow, edinburgh);
		DefaultWeightedEdge e2 = g.addEdge(heathrow, amsterdam);
		DefaultWeightedEdge e21 = g.addEdge(amsterdam, heathrow);
		DefaultWeightedEdge e3 = g.addEdge(heathrow, boston);
		DefaultWeightedEdge e31 = g.addEdge(boston, heathrow);
		DefaultWeightedEdge e4 = g.addEdge(boston, chicago);
		DefaultWeightedEdge e41 = g.addEdge(chicago, boston);
		DefaultWeightedEdge e5 = g.addEdge(boston, montreal);
		DefaultWeightedEdge e51 = g.addEdge(montreal, boston);
		DefaultWeightedEdge e6 = g.addEdge(montreal, toronto);
		DefaultWeightedEdge e61 = g.addEdge(toronto, montreal);
		DefaultWeightedEdge e7 = g.addEdge(edinburgh, chicago);
		DefaultWeightedEdge e71 = g.addEdge(chicago, edinburgh);
		DefaultWeightedEdge e8 = g.addEdge(newdelhi, shanghai);
		DefaultWeightedEdge e81 = g.addEdge(shanghai, newdelhi);
		DefaultWeightedEdge e9 = g.addEdge(shanghai, hongKong);
		DefaultWeightedEdge e91 = g.addEdge(hongKong, shanghai);

		g.setEdgeWeight(e1, 110);
		g.setEdgeWeight(e2, 100);
		g.setEdgeWeight(e3, 230);
		g.setEdgeWeight(e4, 150);
		g.setEdgeWeight(e5, 100);
		g.setEdgeWeight(e6, 90);
		g.setEdgeWeight(e7, 560);
		g.setEdgeWeight(e8, 430);
		g.setEdgeWeight(e9, 230);
		g.setEdgeWeight(e11, 110);
		g.setEdgeWeight(e21, 100);
		g.setEdgeWeight(e31, 230);
		g.setEdgeWeight(e41, 150);
		g.setEdgeWeight(e51, 100);
		g.setEdgeWeight(e61, 90);
		g.setEdgeWeight(e71, 560);
		g.setEdgeWeight(e81, 430);
		g.setEdgeWeight(e91, 230);

		System.out.println(g.getEdgeWeight(e1));
		System.out.println(g.getEdgeWeight(e2));
		System.out.println(g.getEdgeWeight(e3));
		System.out.println(g.getEdgeWeight(e4));
		System.out.println(g.getEdgeWeight(e5));
		System.out.println(g.getEdgeWeight(e6));
		System.out.println(g.getEdgeWeight(e7));
		System.out.println(g.getEdgeWeight(e8));
		System.out.println(g.getEdgeWeight(e9));

		System.out.println(g);

		return g;
	}

	/*******************************************************
	 * Part B: Least Cost Connections
	 *******************************************************/

	public static void shortestPath() {
		Scanner s = new Scanner(System.in);
		SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> g = createGraph();
		System.out.println("The following airports are used: ");
		System.out.println("");
		Iterator<String> it = g.vertexSet().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out
				.println("--------------------------------------------------");
		System.out.println("Please enter the start airport: ");
		String from = s.next();
		System.out
				.println("--------------------------------------------------");
		System.out.println("Please enter the destination airport: ");
		String to = s.next();

		try {
			System.out
					.println("--------------------------------------------------");
			System.out.println("Shortest (i.e. cheapest ) path :");
			DijkstraShortestPath dsp = new DijkstraShortestPath(g, from, to);
			String route = dsp.getPath().toString();
			route = route.replace("[", "");
			route = route.replace("]", "");
			route = route.replace("(", "");
			route = route.replace(")", "");
			route = route.replace(":", "to");

			String[] finalRoute = route.split(",");
			int j = 1;
			for (int i = 0; i < finalRoute.length; i++) {
				System.out.println(j + ". " + finalRoute[i]);
				j++;
			}
			System.out
					.println("Cost of shortest path: $" + dsp.getPathLength());
			System.out
					.println("--------------------------------------------------");
		} catch (Exception e) {
			System.out
					.println("--------------------------------------------------");
			System.out.println("No Flight in this route");
			System.out
					.println("--------------------------------------------------");
		}
	}

	public static void main(String[] args) {
		createGraph();
		shortestPath();

	}
}