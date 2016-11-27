package systemControl.Interface;
import systemControl.Implementation.SystemControlFactoryImp;

public class SystemControlFactory {

	public static IU_SC getIU() { 
		return SystemControlFactoryImp.getIU();
	}

	public static SS_SC getSS() { 
		return SystemControlFactoryImp.getSS();
	}
	
	public static ISystemControl create(){
		return SystemControlFactoryImp.create();
	}
}
