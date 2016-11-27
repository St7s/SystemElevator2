package systemAscenseur.Interface;

import systemAscenseur.Implementation.Sens;

public interface SystemControl_SA {

	/**
	 * Methode permettant aux SystemControl d'appeler une commande sur
	 * l'ascenseur
	 * @param direction
	 */
	public void commande(Sens direction);
	public int getNiveauMin();
	public int getNiveauMax();
}
