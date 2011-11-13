package model.pizzeria;

/**
 * Klasa przedstawiaj�ca dostawc�.
 * @author DoomThrower
 *
 */
public class DeliveryBoy {
	/**
	 * Trasa, na kt�rej znajduje si� obecnie dostawca (Je�li null, to dostawca znajduje si� w pizzerii).
	 */
	private Route _currentRoute;
	/**
	 * (UNUSED) Informacja o �rodku lokomocji (na wypadek bajer�w w GUI).
	 */
	private TransportType _transportType;
	/**
	 * Nazwa dostawcy (na wypadek podawania wynik�w w konsoli - "Franek dostarczy� pizz� do w�z�a 7")
	 */
	private String _name;
	/**
	 * Pojemno�� dostawcy - na chwil� obecn� sta�a
	 */
	private int _loadCapacity;
	
	/**
	 * Konstruktor
	 * @param currentRoute
	 * @param transportType
	 * @param name
	 * @param loadCapacity
	 */
	public DeliveryBoy(Route currentRoute, TransportType transportType, String name, int loadCapacity) {
		_currentRoute = currentRoute;
		_transportType = transportType;
		_name = name;
		_loadCapacity = loadCapacity;
	}
	
	public DeliveryBoy(String name, int loadCapacity) {
		this(null, TransportType.SCOOTER, name, loadCapacity);
	}
	
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		_name = name;
	}
	
	public TransportType getTransportType() {
		return _transportType;
	}
	public void setTransportType(TransportType transportType) {
		_transportType = transportType;
	}
	
	public Route getCurrentRoute() {
		return _currentRoute;
	}
	public void setCurrentRoute(Route currentRoute) {
		_currentRoute = currentRoute;
	}
	
	public int getLoadCapacity() {
		return _loadCapacity;
	}
	public void setLoadCapacity(int loadCapacity) {
		_loadCapacity = loadCapacity;
	}
	
}
