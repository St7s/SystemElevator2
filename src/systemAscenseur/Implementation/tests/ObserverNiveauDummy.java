package systemAscenseur.Implementation.tests;

import systemAscenseur.Interface.ObserverNiveau;

public class ObserverNiveauDummy implements ObserverNiveau{

	@Override
	public void notifierNiveau(int niveau) {
		System.out.println("notifierNiveau : " + niveau );
	}

}