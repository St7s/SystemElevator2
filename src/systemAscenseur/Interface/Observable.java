package systemAscenseur.Interface;

public interface Observable {
	/**
	 * Ajouter des observeurs pour les 3 cas suivants
	 *  - l'arret de l'ascenseur
	 *  - le changement de niveau de l'ascenseur
	 *  - la surcharge de l'ascenseur
	 * @param obj
	 */
	public void addObserverArret(ObserverArret obj);
	public void addObserverNiveau(ObserverNiveau obj);
	public void addObserverSurcharge(ObserverSurcharge obj);
	
	/**
	 * Notifier tout les observeur du niveau de la position actuelles
	 */
	public void notifyAllNiveau();
	/**
	 * Notifier tout les observeur d'arret de la position actuelle ou l'ascenseur s'arrete
	 */
	public void notifyAllArret();
	/**
	 * Notifier tout les observeur de surcharge de la presence d'une surcharge dans l'ascenseur
	 */
	public void notifyAllSurcharge();
}
