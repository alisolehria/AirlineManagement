import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class SolehriaAirlines {

	private static HashMap<String, Integer> vouchers = new HashMap<String, Integer>();

	/*******************************************************
	 * Part C: Additional Flight Information
	 *******************************************************/

	/* creates graph, calls createFlightRoutes method to create the routes */
	public static SimpleDirectedWeightedGraph<String, Flight> createGraph(
			boolean b) {
		SimpleDirectedWeightedGraph<String, Flight> g = new SimpleDirectedWeightedGraph<String, Flight>(
				Flight.class);

		String edinburgh = "Edinburgh";
		String heathrow = "Heathrow";
		String amsterdam = "Amsterdam";
		String boston = "Boston";
		String chicago = "Chicago";
		String montreal = "Montreal";
		String toronto = "Toronto";
		String shanghai = "Shanghai";
		String newdelhi = "NewDelhi";
		String hongKong = "HongKong";

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

		createFlightRoutes(g, edinburgh, heathrow, "SA001", "SA002", "22:00",
				"00:00", "05:00", "07:00", "110", 30, 30, b);
		createFlightRoutes(g, heathrow, amsterdam, "SA003", "SA004", "03:30",
				"08:00", "08:00", "11:00", "100", 30, 30, b);
		createFlightRoutes(g, heathrow, boston, "SA005", "SA006", "04:00",
				"08:00", "06:00", "10:00", "230", 30, 30, b);
		createFlightRoutes(g, boston, chicago, "SA007", "SA008", "05:00",
				"07:00", "08:00", "10:00", "150", 30, 30, b);
		createFlightRoutes(g, boston, montreal, "SA009", "SA010", "06:00",
				"09:00", "10:00", "13:00", "100", 30, 30, b);
		createFlightRoutes(g, montreal, toronto, "SA011", "SA012", "15:00",
				"18:00", "22:00", "01:00", "90", 30, 30, b);
		createFlightRoutes(g, edinburgh, chicago, "SA013", "SA014", "04:00",
				"16:00", "08:00", "20:00", "560", 30, 30, b);
		createFlightRoutes(g, newdelhi, shanghai, "SA015", "SA016", "12:00",
				"18:00", "18:00", "00:00", "430", 30, 30, b);
		createFlightRoutes(g, shanghai, hongKong, "SA017", "SA018", "22:00",
				"02:00", "19:00", "23:00", "230", 30, 30, b);

		return g;

	}

	/* Create flights from and to both airports, adds flight information */
	public static void createFlightRoutes(
			SimpleDirectedWeightedGraph<String, Flight> g, String location1,
			String location2, String flightNumber1, String flightNumber2,
			String depart1, String arrive1, String depart2, String arrive2,
			String price, int baggage1, int baggage2, boolean b) {

		Flight temp;
		temp = g.addEdge(location1, location2);
		temp.setFlight(flightNumber1);
		temp.setFrom(location1);
		temp.setTo(location2);
		temp.setDepart(depart1);
		temp.setArrive(arrive1);
		temp.setPrice(price);
		temp.setBaggage(baggage1);
		if (b) {
			g.setEdgeWeight(temp, Double.parseDouble(price));
		} else {
			g.setEdgeWeight(temp, 0);
		}

		temp = g.addEdge(location2, location1);
		temp.setFlight(flightNumber2);
		temp.setFrom(location2);
		temp.setTo(location1);
		temp.setDepart(depart2);
		temp.setArrive(arrive2);
		temp.setPrice(price);
		temp.setBaggage(baggage2);
		if (b) {
			g.setEdgeWeight(temp, Double.parseDouble(price));
		} else {
			g.setEdgeWeight(temp, 0);
		}

	}

	/*******************************************************
	 * Part D: Itinerary
	 *******************************************************/

	private static void booking() throws Exception {
		SimpleDirectedWeightedGraph<String, Flight> g = createGraph(true);
		boolean inputc = false;
		String from = null;
		String to = null;
		Scanner s = new Scanner(System.in);
		int price = 0;
		String time = "00:00";

		int flight = 1;
		String luggage = null;

		System.out
				.println("WELCOME TO SOLEHRIA AIRLINES ONLINE BOOKING SYSTEM");
		System.out
				.println("--------------------------------------------------");
		System.out.println("The following airports are used: ");
		System.out.println("");

		Iterator<String> it = g.vertexSet().iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println("");
		System.out
				.println("--------------------------------------------------");

		while (!inputc) {
			System.out
					.println("Please enter the city you want to travel from: ");
			from = s.next();

			if (!g.vertexSet().contains(from)) {
				System.out
						.println("Wrong city name entered! Please enter the name of the city you want to travel from again.");
				System.out.println();
				System.out
						.println("--------------------------------------------------");

			} else {
				inputc = true;
			}
		}

		inputc = false;

		while (!inputc) {
			System.out
					.println("--------------------------------------------------");
			System.out.println("Please enter the city you want to travel to: ");
			to = s.next();
			if (!g.vertexSet().contains(to)) {
				System.out
						.println("Wrong city name entered! Please enter the name of the city you want to travel to again.");
				System.out.println();

			} else {
				inputc = true;
			}
		}

		System.out
				.println("--------------------------------------------------");
		System.out
				.println("You want shortest route or chepest route press, Press s for shortest route or any other key for cheapest route: ");

		String cheap = s.next();

		if (cheap.equals("s")) {
			g = createGraph(false);
		}

		List<Flight> firstRoute = null;

		try {
			firstRoute = DijkstraShortestPath.findPathBetween(g, from, to);
		} catch (Exception e) {
			System.out.println("Sorry we do not fly to this route.");
			System.exit(0);
		}

		inputc = false;

		while (!inputc) {

			System.out
					.println("--------------------------------------------------");
			System.out
					.println("Do you want to add extra luggage? Press a,b,c,or d");
			System.out.println("a. No");
			System.out.println("b. 10kg for 20AED");
			System.out.println("c. 20kg for 40AED");
			System.out.println("d. 30kg for 60AED");

			luggage = s.next();

			if (!(luggage.equals("a") || luggage.equals("b")
					|| luggage.equals("c") || luggage.equals("d"))) {
				System.out.println("Please press a,b,c or d");
				Thread.sleep(500);
				System.out
						.println("--------------------------------------------------");
			} else {
				inputc = true;
			}
		}

		System.out
				.println("--------------------------------------------------");
		System.out.println("Itinerary for " + from + " to " + to);

		boolean first = true;
		String prev = "00:00";
		ArrayList<String> times = new ArrayList<String>();
		for (Object o : firstRoute.toArray()) {

			Flight f = (Flight) o;
			System.out
					.println("--------------------------------------------------");
			System.out.println("Leg: " + flight);

			System.out.println();

			times.add(f.getDepart());
			times.add(f.getArrive());

			if (!first) {
				if (!compare(prev, f.getDepart())) {
					System.out
							.println("The next flight is in less than an hour or after 5 hours of the arrival time of the previous flight. Press y to continue or any other key to cancel.");
					String check = s.next();
					if (!check.equals("y")) {
						System.exit(100);
					}
				}
			} else {
				first = false;

			}

			int pcheck = luggageCheck(f, luggage, "b", "c", "d", price);
			prev = f.getArrive();
			flight = flight + 1;
			price = pcheck + Integer.parseInt(f.getPrice());

			String diff = diff(f.getArrive(), f.getDepart());

			time = add(time, diff);

			System.out.println("");
			System.out.println("Leg Time: " + diff);

			System.out
					.println("-----------------------------------------------");
		}

		System.out
				.println("Do You Want to redeem a voucher? If yes enter your voucher code, if you dont have a voucher code please press n.");
		String voucher = s.next();
		int fprice = voucherCheck(voucher, price);
		System.out.println("Total Cost: AED " + fprice);
		System.out.println("Total time in air: " + time);
		System.out.println("Total time: " + addc(times) + " hrs");

	}

	private static void printRoute(Flight f) {
		System.out.println("Flight no.: " + f.getFlightNo());
		System.out.println("From: " + f.getFrom());
		System.out.println("Baggage Allowed: " + f.getBaggage() + " kg");
		System.out.println("Depart: " + f.getDepart() + " hrs");
		System.out.println("To: " + f.getTo());
		System.out.println("Arrive: " + f.getArrive());
		System.out.println("Ticket Price: AED " + f.getPrice());

	}

	/*******************************************************
	 * Part E: Itinerary Duration
	 *******************************************************/

	private static String add(String a, String b) throws Exception {

		int a1 = Integer.parseInt(a.substring(0, 2));
		int a2 = Integer.parseInt(b.substring(0, 2));
		int hrs = a1 + a2;
		int b1 = Integer.parseInt(a.substring(3, 5));
		int b2 = Integer.parseInt(b.substring(3, 5));
		int mins = b1 + b2;
		if (mins >= 60) {
			hrs = hrs + 1;
			mins = b2 - (60 - b1);
		}
		String hrsc = String.format("%02d", hrs);
		String minsc = String.format("%02d", mins);

		String result = hrsc + ":" + minsc;

		return result;

	}

	private static String diff(String arrive, String depart) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = format.parse(depart);
		Date date2 = format.parse(arrive);
		long difference = date2.getTime() - date1.getTime();
		long minutes = difference / (60 * 1000) % 60;

		long hours = difference / (60 * 60 * 1000) % 24;
		String convert = hours + ":" + minutes;
		Date date3 = format.parse(convert);
		String result = format.format(date3);
		return result;

	}

	/*******************************************************
	 * Part F: Alternative Extensions
	 *******************************************************/

	/* Voucher Extension to reduce total price if voucher present */

	private static int voucherCheck(String scan, int price) {
		vouchers.put("n", 0);
		vouchers.put("20off", 20);
		vouchers.put("40off", 40);
		vouchers.put("100off", 100);
		if (vouchers.containsKey(scan)) {
			price = price - vouchers.get(scan);
			return price;
		} else {
			System.out.println("Wrong voucher code.");
			return price;
		}
	}

	/* Allow more luggage by increasing total price. */

	private static int luggageCheck(Flight f, String luggage, String a,
			String b, String c, int price) {
		if (luggage.equals(a)) {
			price = price + 20;
			f.setBaggage(50);
			printRoute(f);
			System.out.println("Extra Luggage for: AED 20");
			return price;
		} else if (luggage.equals(b)) {
			price = price + 40;
			f.setBaggage(70);
			printRoute(f);
			System.out.println("Extra Luggage for: AED 40");
			return price;
		} else if (luggage.equals(c)) {
			price = price + 60;
			f.setBaggage(90);
			printRoute(f);
			System.out.println("Extra Luggage for: AED 60");
			return price;
		} else {
			printRoute(f);
			return price;
		}
	}

	/*
	 * Check if next flight is in less than hour or more than five hours of the
	 * arrival time of the previous flight.
	 */
	private static boolean compare(String a, String b) {

		int a1 = Integer.parseInt(a.substring(0, 2) + a.substring(3, 5));
		/** conversion to int */
		int b1 = Integer.parseInt(b.substring(0, 2) + b.substring(3, 5));

		int c = b1 - a1;
		if (c >= 100 && c <= 500) {
			return true;
		}
		return false;

	}

	/* Add total time: time in air+ changeover time */
	private static int addc(ArrayList<String> a) throws Exception {

		if (a.size() == 2) {
			String s = diff(a.get(1), a.get(0));
			int i = Integer.parseInt(s.substring(0, 2) + s.substring(3, 5));
			return i;
		}

		String start = a.get(0);
		String end = a.get(a.size() - 1);

		int j = 0;

		a.remove(0);
		a.remove(a.size() - 1);

		int s = Integer.parseInt(start.substring(0, 2) + start.substring(3, 5));
		int e = Integer.parseInt(end.substring(0, 2) + end.substring(3, 5));
		int first = Integer.parseInt(a.get(0).substring(0, 2)
				+ a.get(0).substring(3, 5));

		for (int i = 0; i < a.size(); i = i + 2) {

			int arrive = Integer.parseInt(a.get(i).substring(0, 2)
					+ a.get(i).substring(3, 5));

			int depart = Integer.parseInt(a.get(i + 1).substring(0, 2)
					+ a.get(i + 1).substring(3, 5));

			if (arrive > depart) {

				j = j + 1;

			}

		}
		if (j > 1) {
			if (s > e) {
				j = j - 1;
			}
			if (s > first) {
				j = j + 1;
			}
		}
		a.add(0, start);
		a.add(a.size(), end);
		String x = diff(a.get(a.size() - 1), a.get(0));

		int y = 2400 * j;

		int z = Integer.parseInt(x.substring(0, 2) + x.substring(3, 5));

		int result = z + y;
		return result;

	}

	public static void main(String[] args) throws Exception {
		booking();

	}

}
