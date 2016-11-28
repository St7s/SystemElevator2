package interfaceUtilisateur.Interface;

import interfaceUtilisateur.Implementation.IintefaceUtilisateur;
import interfaceUtilisateur.Implementation.SystemIUFactoryImp;

public class SystemIUFactory {
	public static void createInstance(){
		SystemIUFactoryImp.createInstance();
	}

	public static IintefaceUtilisateur getInstance() {
		return SystemIUFactoryImp.getInsatance();
	}
}
