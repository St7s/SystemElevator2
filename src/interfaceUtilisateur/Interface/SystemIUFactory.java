package interfaceUtilisateur.Interface;

import interfaceUtilisateur.Implementation.SystemIUFactoryImp;

public class SystemIUFactory {
	public static IintefaceUtilisateur createInstance(){
		return SystemIUFactoryImp.createInstance();
	}

	
}
