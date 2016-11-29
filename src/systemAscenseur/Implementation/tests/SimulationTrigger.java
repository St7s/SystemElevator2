package systemAscenseur.Implementation.tests;

import java.util.ArrayList;

import systemAscenseur.Implementation.Sens;
import systemAscenseur.Interface.ISystemAscenseur;
import systemAscenseur.Interface.ObserverArret;
import systemAscenseur.Interface.ObserverNiveau;
import systemAscenseur.Interface.ObserverSurcharge;

public class SimulationTrigger {

	public static void main(String[] args, int poidsMax) throws InterruptedException {
		
		/**Configuration du SystemAscenseur**/
		float vitesseMoteur = (float) 1.1;
		int niveauMin = 0;
		int niveauMax = 10;
		float distanceNiveaux = 3;
		
		ArrayList<Sens> listeCommandes = new ArrayList<Sens>();
		listeCommandes.add(Sens.UP);
		listeCommandes.add(Sens.UP);
		listeCommandes.add(Sens.UP);
		listeCommandes.add(Sens.UP);
		
		listeCommandes.add(null);
		listeCommandes.add(null);
		listeCommandes.add(null);
		
		listeCommandes.add(Sens.UP);
		listeCommandes.add(Sens.UP);
		listeCommandes.add(Sens.UP);
		
		listeCommandes.add(Sens.DOWN);
		listeCommandes.add(Sens.DOWN);
		listeCommandes.add(Sens.DOWN);
				
		listeCommandes.add(Sens.UP);
		listeCommandes.add(Sens.UP);
		listeCommandes.add(Sens.UP);
		
		//On cree le systemeAscenseur
		ISystemAscenseur sa = systemAscenseur.Interface.SystemAscenseurFactory.create(vitesseMoteur, niveauMin, niveauMax, distanceNiveaux, poidsMax);
		
		ObserverArret ObserverArret = new ObserversArretDummy();
		sa.addObserverArret(ObserverArret);
		
		ObserverNiveau ObserverNiveau = new ObserverNiveauDummy();
		sa.addObserverNiveau(ObserverNiveau);
		
		ObserverSurcharge ObserverSurcharge = new ObserverSurchargeDummy();
		sa.addObserverSurcharge(ObserverSurcharge);
		
		long t = 0;
		for (int i = 0; i < listeCommandes.size(); i++) {
			
			sa.trigger(t);
			sa.commande(listeCommandes.get(i));
			//Thread.sleep(1000);
			t+=1000;
		}

	}

}
