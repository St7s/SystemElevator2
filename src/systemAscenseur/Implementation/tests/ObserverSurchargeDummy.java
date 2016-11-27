package systemAscenseur.Implementation.tests;

import systemAscenseur.Interface.ObserverSurcharge;

public class ObserverSurchargeDummy implements ObserverSurcharge{

	@Override
	public void notifierSurcharge() {
		System.out.println("notifierSurcharge");
		
	}

}
