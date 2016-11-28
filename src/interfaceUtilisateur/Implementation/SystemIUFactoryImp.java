package interfaceUtilisateur.Implementation;


public class SystemIUFactoryImp {
	private static InterfaceUtilisateur instance;

	
	public static InterfaceUtilisateur createInstance(){
		if(instance == null)
			instance = new InterfaceUtilisateur();
		return instance;
	}
}
