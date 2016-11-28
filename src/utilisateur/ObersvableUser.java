package utilisateur;

public interface ObersvableUser {
public void addObserverAppel(ObserverAppelUser oa);
public void addObserverDeplacement(ObserverDeplacementUser od);
public void addObserverEntre(ObserverEntree oa);
public void addObserverSortie(ObserverSortie od);
public void appel();
public void deplacement();
public void entre();
public void sortie();
}
