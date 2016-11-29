package interfaceUtilisateur.Interface;

import systemAscenseur.Interface.ObserverArret;
import systemAscenseur.Interface.ObserverNiveau;
import systemAscenseur.Interface.ObserverSurcharge;
import systemControl.Interface.IU_SC;
import utilisateur.ObserverAppelUser;
import utilisateur.ObserverDeplacementUser;

public interface IintefaceUtilisateur extends  ObserverNiveau, UserInterface, ObserverAppelUser, ObservableIU,ObserverArret,ObserverSurcharge,ObserverDeplacementUser{
	public void link(IU_SC cn);
}
