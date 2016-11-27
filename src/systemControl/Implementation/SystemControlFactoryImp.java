package systemControl.Implementation;

import systemControl.Interface.ISystemControl;
import systemControl.Interface.IU_SC;
import systemControl.Interface.SS_SC;

public class SystemControlFactoryImp {
	


	public static IU_SC getIU() {
		return SystemControl.getInstance();
	}

	public static ISystemControl create() {
		return SystemControl.getInstance();
	}

	public static SS_SC getSS() {
		return SystemControl.getInstance();
	}
}
