package systemControl.Interface;

import systemAscenseur.Implementation.Sens;

public interface IU_SC {
	public void deplacement(int niveau);
	public void appel(int niveau, Sens direction); 
	public int getNombreCommande();
}
