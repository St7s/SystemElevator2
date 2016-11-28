package interfaceUtilisateur.Interface;

import systemAscenseur.Implementation.Sens;

public interface UserInterface {
	
	public void deplacement(int niveau);

	public void monterAscenseur();

	public void emettreAppel(Sens deplacement, int pos);
}
