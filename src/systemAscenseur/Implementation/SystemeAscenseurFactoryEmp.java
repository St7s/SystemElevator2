package systemAscenseur.Implementation;

import systemAscenseur.Interface.ISystemAscenseur;
import systemAscenseur.Interface.IU_SA;


public class SystemeAscenseurFactoryEmp {
	/**
	 * 
	 * @return
	 */
	public static IU_SA getIU_SA(){
		
		return null;
	}

	
	/**
	 * 
	 * @param vitesseMoteur
	 * @param niveauMax
	 * @param niveauMin
	 * @param poidsMax 
	 * @param sc 
	 * @param ui 
	 */
	public static ISystemAscenseur create(float vitesseMoteur, int niveauMin, int niveauMax, float distanceNiveaux, int poidsMax) {
		Cabine cabine = new Cabine();
		Moteur moteur = new Moteur(vitesseMoteur, cabine);
		CapteurNiveau capteurNiveau = new CapteurNiveau();
		SystemAscenseur sys = new SystemAscenseur(niveauMin, niveauMax, distanceNiveaux, moteur, capteurNiveau, poidsMax);
		return sys;
	}
}