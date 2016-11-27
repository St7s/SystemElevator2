package interfaceUtilisateur.Interface;

public interface ObservableIU {
public void addObserverSurcharge(ObserverSurchargeIU os);
public void addObserverArret(ObserverArretIU oa);
public void notifyObserverSurcharge();
public void notifyObserverArret(int niveau);
}
