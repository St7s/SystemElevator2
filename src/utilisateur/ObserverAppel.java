package utilisateur;

import systemAscenseur.Implementation.Sens;

public interface ObserverAppel {
 public void appel(int niveau, Sens sens);
}
