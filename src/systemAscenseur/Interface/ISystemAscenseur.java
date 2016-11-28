package systemAscenseur.Interface;

import sequenceur.Interface.Event;
import utilisateur.ObserverEntree;
import utilisateur.ObserverSortie;

public interface ISystemAscenseur extends Observable, SystemControl_SA, Event, ObserverEntree, ObserverSortie{

}
