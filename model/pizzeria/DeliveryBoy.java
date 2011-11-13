package model.pizzeria;

/**
 * Klasa przedstawiaj¹ca dostawcê.
 * @author DoomThrower
 *
 */
public class DeliveryBoy {
	/**
	 * Trasa, na której znajduje siê obecnie dostawca (Jeœli null, to dostawca znajduje siê w pizzerii).
	 */
	private Route _currentRoute;
	/**
	 * (UNUSED) Informacja o œrodku lokomocji (na wypadek bajerów w GUI).
	 */
	private TransportType _transportType;
	/**
	 * Nazwa dostawcy (na wypadek podawania wyników w konsoli - "Franek dostarczy³ pizzê do wêz³a 7")
	 */
	private String _name;
	/**
	 * Pojemnoœæ dostawcy - na chwilê obecn¹ sta³a
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
