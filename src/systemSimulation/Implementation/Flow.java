package systemSimulation.Implementation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import interfaceUtilisateur.Interface.ObserverArretIU;
import interfaceUtilisateur.Interface.ObserverSurchargeIU;
import sequenceur.Interface.Event;
import utilisateur.ObserverAppelUser;
import utilisateur.ObserverDeplacementUser;

class Flow implements ObserverArretIU,ObserverSurchargeIU, Event {
	/**
	 * 
	 */
	private ObserverAppelUser oa;
	/**
	 * 
	 */
	private ObserverDeplacementUser od;
	/**
	 * instance_Flow de flow, singleton
	 */
	private static Flow instance_Flow;
	public ObserverAppelUser getObserverAppel() {
		return oa;
	}

	public void setObserverAppel(ObserverAppelUser oa) {
		this.oa = oa;
	}

	public ObserverDeplacementUser getObserverDeplacement() {
		return od;
	}

	public void setObserverDeplacement(ObserverDeplacementUser od) {
		this.od = od;
	}

	/**
	 * liste des utilisateurs utiliser pour la simulation
	 */
	public ArrayList<UserSimulation> users;
	/**
	 * listes des utilisateurs ayant fait un appel
	 */
	public ArrayList<UserSimulation> usersAppel;
	/**
	 * surcharge de la cabine ou pas
	 */
	public boolean surchage = false;
	/**
	 * 
	 */
	public ArrayList<UserSimulation> usersInAscenseur;
	/**
	 * 
	 */
	public ArrayList<UserSimulation> usersHappy;
	/**
	 * calcule du temps moyen d'attente pour qu'un appel soit satisfait
	 */
	public long temps_attente() { 
		long tempsAttente = 0;
		if (usersHappy.size() == 0) {
			System.out.println("aucun utilisateur satisfait, ou aucun appel");
			return 0;
		}
		else {
			for (UserSimulation user : usersHappy) {
				tempsAttente += (user.getTempsEntre() - user.getTempsAppel());
			}
			tempsAttente = tempsAttente / usersHappy.size();
			System.out.println("temps d'attente moyen pour un appel : "+tempsAttente);
			return tempsAttente;
		}

	}

	/**
	 * calcule du temps moyen d'attente pour qu'un deplacement soit satisfait
	 */
	public long temps_deplacement() 
	{ 
		long tempsDeplacement = 0;
		if (usersHappy.size() == 0) {
			System.out.println("aucun utilisateur satisfait, ou aucune demande de deplacement");
			return 0;
		}
		else {
			for (UserSimulation user : usersHappy) {
				tempsDeplacement += (user.getTempsSortie() - user.getTempsEntre());
			}
			tempsDeplacement = tempsDeplacement / usersHappy.size();
			System.out.println("temps d'attente moyen pour un deplacement : "+tempsDeplacement);
			return tempsDeplacement;
		}
	}
	public void tempDeplacementUser(){
		System.out.println("============================================================================");
		System.out.println("================== Temps deplacement des utilisateurs ======================");
		System.out.println("============================================================================");
		for (UserSimulation user : usersHappy) {
			System.out.println("Nom : "+user.nom+" Temps Sortie : "+ (user.getTempsSortie()- user.getTempsEntre()) + " Temps Attentes "+(user.getTempsEntre() - user.getTempsAppel()));
		}
		System.out.println("============================================================================");

	}
	public void addUser(UserSimulation user)
	{
		users.add(user);
	}

	public void addObserveurAppel(ObserverAppelUser observerAppel)
	{
		this.setObserverAppel(observerAppel);
		for (UserSimulation userSimulation : users) 
			userSimulation.addObserverAppel(observerAppel);
	}

	public void addObserveurDeplacement(ObserverDeplacementUser observateurDeplacement)
	{
		this.setObserverDeplacement(observateurDeplacement);
		for (UserSimulation userSimulation : users)
			userSimulation.addObserverDeplacement(observateurDeplacement);
	}

	/**
	 * Singleton
	 * @param nom_fichier
	 * @throws IOException
	 */
	public static Flow creatFlow() throws IOException {
		if (instance_Flow == null) {
			instance_Flow = new Flow();
		}
		return instance_Flow;
	}
	private Flow()
	{
		users = new ArrayList<UserSimulation>();
		usersAppel = new ArrayList<UserSimulation>();
		usersInAscenseur = new ArrayList<UserSimulation>();

		usersHappy = new  ArrayList<UserSimulation>();
	}

	/**
	 * 
	 * @param nom_fichier "nom niveau_initial niveau_fianl"
	 * @throws IOException 
	 */
	public void addFichier(String nom_fichier) throws IOException { 
		BufferedReader entree  = new BufferedReader(new FileReader(nom_fichier));
		// premier ligne indique l'ordre des infos
		String ligne = entree.readLine();
		ligne = entree.readLine();
		String[] ligne2;

		while(ligne != null) {
			ligne2 = ligne.split(" ");
			this.addUser(new UserSimulation(ligne2[0], Integer.parseInt(ligne2[1]) , Integer.parseInt(ligne2[2]))); 
			ligne = entree.readLine();
		}
		entree.close();
	}

	/**
	 * 
	 */
	@Override
	public void surcharge() { 
		surchage = true;
	}
	/**
	 * 
	 */
	@Override
	public void trigger(long t) {

		if (users.size() != 0) {
			UserSimulation usr1 = users.get(0);
			usr1.setTempsAppel(t);
			usr1.appel();
			usersAppel.add(usr1);
			users.remove(usr1);			
		}
		/*else {
			System.out.println("aucun utilisateur dans l'ascenseur ");
		}*/
	}

	/**
	 * 
	 */
	@Override
	public void arret(int niveau) {
		ArrayList<UserSimulation> supphap = new ArrayList<UserSimulation>();
		// a changer
		long t = Configurator.getSeq().SimulationTime();
		//
		for (UserSimulation in : usersInAscenseur) {
			if (in.niveau_final == niveau) {
				in.inLift = false;
				in.setTempsSortie(t);
				usersHappy.add(in);
				supphap.add(in);
				// Recuperer le temps courrant qui correspand au temps ou le deplacement est satisfait
				// On appele l'inteface utilisateur pour faire sortir l'utilisateur
				in.sortie();
			}			
		}
		for (UserSimulation out : supphap)
			usersInAscenseur.remove(out);
		int i = 0;
		//
		ArrayList<UserSimulation> supp = new ArrayList<UserSimulation>();
	
		for (UserSimulation out : usersAppel) {
			if (out.niveau_initial == niveau) {
				// Appel IU pour faire entre l'utilisateur
				out.entre();
				// verification sur la surcharge
				if(surchage) {
					for( ; i<usersAppel.size();i++)
						if (usersAppel.get(i).niveau_initial == niveau)
							usersAppel.get(i).appel();
					break;
				}
				else {
					out.inLift = true;
					out.setTempsEntre(t);
					out.deplacement();
					usersInAscenseur.add(out);
					supp.add(out);
				}

			}
			i++;
		}
		for (UserSimulation out : supp)
			usersAppel.remove(out);
		surchage = false;
	}


}
