package utilisateur;

public interface ObersvableUser {
public void addObserverAppel(ObserverAppel oa);
public void addObserverDeplacement(ObserverDeplacement od);
public void addObserverEntre(ObserverEntre oa);
public void addObserverSortie(ObserverSortie od);
public void appel();
public void deplacement();
public void entre();
public void sortie();
}
