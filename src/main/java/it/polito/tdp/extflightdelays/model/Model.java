package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private ExtFlightDelaysDAO dao;
	private List<Airport> allAirports;
	private Map<Integer, Airport> idMap;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
		this.allAirports = dao.loadAllAirports();
		idMap = new HashMap<>();
		
	}
	
	public void creaGrafo(int distance) {
		for(Airport a : this.allAirports) {
			this.idMap.put(a.getId(), a);
		}
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, this.allAirports);
		
		List<Rotta> edges = this.dao.loadAllEdges(idMap, distance);
		
		for(Rotta r : edges) {
			Airport origin = r.getOrigin();
			Airport destination = r.getDestination();
			int distanza = r.getDistanza();
			
			if(grafo.vertexSet().contains(origin) && grafo.vertexSet().contains(destination)) {
				this.grafo.addEdge(origin, destination);
				this.grafo.setEdgeWeight(origin, destination, distanza);
				
			}
		}
		
		System.out.println("Ci sono " + grafo.vertexSet().size() + " vertici.");
		System.out.println("Ci sono " + grafo.edgeSet().size() + " archi.");
		
	}

	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	

}
