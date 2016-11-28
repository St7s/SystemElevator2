package interfaceUtilisateur.Implementation;


public class SystemIUFactoryImp {
	private static InterfaceUtilisateur insatance;

	public static InterfaceUtilisateur getInsatance() {
		return insatance;
	}

	public static void setInsatance(InterfaceUtilisateur insatance) {
		SystemIUFactoryImp.insatance = insatance;
	}
	
	public static void createInstance(){
		if(insatance == null)
			insatance = new InterfaceUtilisateur();
	}
}
