import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Flight extends DefaultWeightedEdge {

	private String from;
	private String to;
	private String flightNo;
	private String depart;
	private String arrive;
	private String price;
	private int baggage;

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setFlight(String flight) {
		this.flightNo = flight;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public void setArrive(String arrive) {
		this.arrive = arrive;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setBaggage(int baggage) {
		this.baggage = baggage;
	}

	public String getFrom() {
		return from;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public String getDepart() {
		return depart;
	}

	public String getArrive() {
		return arrive;
	}

	public String getTo() {
		return to;
	}

	public String getPrice() {
		return price;
	}

	public int getBaggage() {
		return baggage;
	}

}
