package interfaceUtilisateur.Implementation;

import interfaceUtilisateur.Interface.ObservableIU;
import interfaceUtilisateur.Interface.SystemControl_IU;
import interfaceUtilisateur.Interface.UserInterface;
import systemAscenseur.Interface.ObserverArret;
import systemAscenseur.Interface.ObserverNiveau;
import systemAscenseur.Interface.ObserverSurcharge;
import utilisateur.ObserverAppel;

public interface IintefaceUtilisateur extends SystemControl_IU, ObserverNiveau, UserInterface, ObserverAppel, ObservableIU,ObserverArret,ObserverSurcharge{

}
