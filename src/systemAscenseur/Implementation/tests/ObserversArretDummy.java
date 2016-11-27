package systemAscenseur.Implementation.tests;

import systemAscenseur.Interface.ObserverArret;

public class ObserversArretDummy implements ObserverArret{

	@Override
	public void notifierArret(int niveau) {
		System.out.println("notifierArret : "+niveau);
	}

}
