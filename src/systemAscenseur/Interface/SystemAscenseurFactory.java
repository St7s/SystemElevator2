package systemAscenseur.Interface;
import systemAscenseur.Implementation.SystemeAscenseurFactoryEmp;

public class SystemAscenseurFactory {

	/**
	 * 
	 * @return
	 */
	public static IU_SA getIU_SA() {
		return SystemeAscenseurFactoryEmp.getIU_SA();
	}

	/**
	 * @param vitesseMoteur
	 * @param niveauMax
	 * @param niveauMin
	 * @param poidsMax 
	 */
	public static ISystemAscenseur create(float vitesseMoteur, int niveauMin, int niveauMax, float distanceNiveaux, int poidsMax) {
		return SystemeAscenseurFactoryEmp.create(vitesseMoteur, niveauMin, niveauMax, distanceNiveaux, poidsMax);
	}

}
