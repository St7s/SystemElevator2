package systemAscenseur.Implementation.tests;

import java.util.ArrayList;

import systemAscenseur.Implementation.Sens;
import systemAscenseur.Interface.ISystemAscenseur;
import systemAscenseur.Interface.ObserverArret;
import systemAscenseur.Interface.ObserverNiveau;
import systemAscenseur.Interface.ObserverSurcharge;

public class SimulationTrigger {

	public static void main(String[] args) throws InterruptedException {
		
		/**Configuration du SystemAscenseur**/
		float vitesseMoteur = 1;
		int niveauMin = 0;
		int niveauMax = 10;
		float distanceNiveaux = 3;
		
		ArrayList<Sens> test = new ArrayList<Sens>();
		test.add(Sens.UP);
		test.add(Sens.UP);
		test.add(Sens.UP);
		
		test.add(Sens.UP);
		test.add(Sens.UP);
		test.add(Sens.UP);
		
		//test.add(null);
		
		test.add(Sens.DOWN);
		test.add(Sens.DOWN);
		test.add(Sens.DOWN);
		
		
		
		
		//On cree le systemeAscenseur
		ISystemAscenseur sa = systemAscenseur.Interface.SystemAscenseurFactory.create(vitesseMoteur, niveauMin, niveauMax, distanceNiveaux);
		
		ObserverArret ObserverArret = new ObserversArretDummy();
		sa.addObserverArret(ObserverArret);
		
		ObserverNiveau ObserverNiveau = new ObserverNiveauDummy();
		sa.addObserverNiveau(ObserverNiveau);
		
		ObserverSurcharge ObserverSurcharge = new ObserverSurchargeDummy();
		sa.addObserverSurcharge(ObserverSurcharge);
		

		for (int i = 0; i < test.size(); i++) {
			sa.commande(test.get(i));
			Thread.sleep(1000);
		}

	}

}
