package systemAscenseur.Interface;

public interface Observable {
	public void addObserverArret(ObserverArret obj);
	public void addObserverNiveau(ObserverNiveau obj);
	public void addObserverSurcharge(ObserverSurcharge obj);
}
