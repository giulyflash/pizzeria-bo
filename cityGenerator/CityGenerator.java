package cityGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


//generator który pisa³em sobie do testów.
//sza³u nie ma ale zawsze coœ

public class CityGenerator {

	private int minX,maxX;
	private int minY,maxY;
	private int placesCount;
	
	private int minPizzeriaNeighbours;
	private int maxPizzeriaNeighbours;

	private int minNeighbours;
	private int maxNeighbours;
	

	public static void main(String[] args)
	{
		CityGenerator gen = new CityGenerator();
		gen.setMinX(10);
		gen.setMaxX(400);
		gen.setMinY(10);
		gen.setMaxY(400);

		gen.setPlacesCount(50);
		gen.setMinPizzeriaNeighbours(1);
		gen.setMaxPizzeriaNeighbours(5);
		
		gen.setMinNeighbours(1);
		gen.setMaxNeighbours(10);
		
		

		System.out.print(gen.generate());
		
	}
	public String generate()
	{
		List<Place> city = new ArrayList<>();

		//pizzeria ma identyfikator = 0 zawsze i jest mniej wiêej centralnie w mieœcie
		Place pizzeria = new Place(0, new Point((getMaxX() - getMinX()) / 2, (getMaxY() - getMinY()) / 2), new HashSet<Integer>());
		city.add(pizzeria);
		
		//generujemy reszte wierzecho³ków w losowych miejscach
		for (int i = 1; i < getPlacesCount(); i++)
		{
			Place place = new Place(i, new Point(randomInRange(getMinX(), getMaxX()), randomInRange(getMinY(), getMaxY())), new HashSet<Integer>());
			city.add(place);
		}
		
		
		//losujemy ile s¹siadów ma mieæ pizzeria
		int pizzeriaNeighboursCount = randomInRange(getMinPizzeriaNeighbours(), getMaxPizzeriaNeighbours());
	
		//losujemy s¹siadów z wierzcho³ków które dodaliœmy i dodajemy do listy s¹siadów
		for (int i = 0; i < pizzeriaNeighboursCount; i++)
		{
			pizzeria.getNeighbours().add(randomInRange(1, getPlacesCount()));
		}
		
		//dla ka¿dego miejsca w mieœcie
		for(Place place : city)
		{
			//oprócz pizzeria
			if(place.getId()==pizzeria.getId())
				continue;
			
			//wylosuj liczba sasiadow
			int neighbourCount = randomInRange(getMinNeighbours(), getMaxNeighbours());
			
			//dodaj tyle sasiadow co wylosowano
			for (int i = 0; i < neighbourCount; i++)
			{
				//wylosuj sasiada
				int neighbourId = randomInRange(0, getPlacesCount());

				//sprawdz czy wylosowany sasiad nie jest miejscem dla ktorego losujemy
				while (neighbourId == place.getId())
					neighbourId = randomInRange(0, getPlacesCount());

				//dodaj sasiada
				place.getNeighbours().add(neighbourId);
			}
		}
		
		//zapis do pliku
		StringBuilder sb = new StringBuilder();
		
		for(Place place:city)
		{
			sb.append(String.format("%d,%d",place.getPosition().getX(),place.getPosition().getY()));
			
			for(int neighbourId:place.getNeighbours())
				sb.append(String.format(",%d",neighbourId));

			sb.append(System.getProperty("line.separator"));
		}
	
		return sb.toString();
		
	}
		
	public void generateToFile(String destinationFile) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(destinationFile));
		out.write(generate());
		out.close();
	}

	
	public static int randomInRange(int min, int max) {
        return min + (int) (Math.random() * (max - min));
}
	public int getMinX() {
		return minX;
	}
	public void setMinX(int minX) {
		this.minX = minX;
	}
	public int getMaxX() {
		return maxX;
	}
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}
	public int getMinY() {
		return minY;
	}
	public void setMinY(int minY) {
		this.minY = minY;
	}
	public int getMaxY() {
		return maxY;
	}
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	public int getPlacesCount() {
		return placesCount;
	}
	public void setPlacesCount(int placesCount) {
		this.placesCount = placesCount;
	}
	public int getMinPizzeriaNeighbours() {
		return minPizzeriaNeighbours;
	}
	public void setMinPizzeriaNeighbours(int minPizzeriaNeighbours) {
		this.minPizzeriaNeighbours = minPizzeriaNeighbours;
	}
	public int getMaxPizzeriaNeighbours() {
		return maxPizzeriaNeighbours;
	}
	public void setMaxPizzeriaNeighbours(int maxPizzeriaNeighbours) {
		this.maxPizzeriaNeighbours = maxPizzeriaNeighbours;
	}
	public int getMinNeighbours() {
		return minNeighbours;
	}
	public void setMinNeighbours(int minNeighbours) {
		this.minNeighbours = minNeighbours;
	}
	public int getMaxNeighbours() {
		return maxNeighbours;
	}
	public void setMaxNeighbours(int maxNeighbours) {
		this.maxNeighbours = maxNeighbours;
	}
	
}


class Place
{
	private int id;
	private Point position;
	private HashSet<Integer> neighbours;
	
	
	public Place(int id, Point position, HashSet<Integer> neighbours)
	{
		this.setId(id);
		this.setPosition(position);
		this.setNeighbours(neighbours);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Point getPosition() {
		return position;
	}


	public void setPosition(Point position) {
		this.position = position;
	}


	public HashSet<Integer> getNeighbours() {
		return neighbours;
	}


	public void setNeighbours(HashSet<Integer> neighbours) {
		this.neighbours = neighbours;
	}
}


class Point
{
	private int x;
	private int y;
	
	
	public Point(int x,int y)
	{
		this.setX(x);
		this.setY(y);
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
}

