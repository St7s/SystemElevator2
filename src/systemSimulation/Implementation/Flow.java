package systemSimulation.Implementation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import interfaceUtilisateur.Interface.ObserverArretIU;
import interfaceUtilisateur.Interface.ObserverSurchargeIU;
import sequenceur.Interface.Event;
import utilisateur.ObserverAppel;
import utilisateur.ObserverDeplacement;

 class Flow implements ObserverArretIU,ObserverSurchargeIU, Event {
	/**
	 * 
	 */
	private ObserverAppel oa;
	/**
	 * 
	 */
	private ObserverDeplacement od;
	/**
	 * instance_Flow de flow, singleton
	 */
	private static Flow instance_Flow;
	public ObserverAppel getObserverAppel() {
		return oa;
	}

	public void setObserverAppel(ObserverAppel oa) {
		this.oa = oa;
	}

	public ObserverDeplacement getObserverDeplacement() {
		return od;
	}

	public void setObserverDeplacement(ObserverDeplacement od) {
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
				tempsAttente =+ (user.getTempsEntre() - user.getTempsAppel());
			}
			tempsAttente = tempsAttente / usersHappy.size();
			System.out.println("temps d'attente moyen pour un appel : "+tempsAttente);
			return tempsAttente;
		}
		
	 }

	/**
	 * calcule du temps moyen d'attente pour qu'un deplacement soit satisfait
	 */
	public long temps_deplacement() { 
		long tempsDeplacement = 0;
		if (usersHappy.size() == 0) {
			System.out.println("aucun utilisateur satisfait, ou aucune demande de deplacement");
			return 0;
		}
		else {
			for (UserSimulation user : usersHappy) {
				tempsDeplacement =+ (user.getTempsSortie() - user.getTempsEntre());
			}
			tempsDeplacement = tempsDeplacement / usersHappy.size();
			System.out.println("temps d'attente moyen pour un deplacement : "+tempsDeplacement);
			return tempsDeplacement;
		}
	}
	public void addUser(UserSimulation user)
	{
		user.addObserverAppel(oa);
		user.addObserverDeplacement(od);
		users.add(user);
	}
	/**
	 * Singleton
	 * @param nom_fichier
	 * @throws IOException
	 */
	public static void creatFlow() throws IOException {
		if (instance_Flow == null) {
			instance_Flow = new Flow();
		}
	}
	public static Flow getInstance() {
		return instance_Flow;
	}
	private Flow()
	{
		
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
		users = new ArrayList<UserSimulation>();
		while(ligne != null) {
			ligne2 = ligne.split(" ");
			this.addUser(new UserSimulation(ligne2[0], Integer.parseInt(ligne2[1]) , Integer.parseInt(ligne2[2]))); 
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

		UserSimulation usr1 = users.get(0);
		if (usr1 != null) {
			usr1.setTempsAppel(t);
			usr1.appel();
			usersAppel.add(usr1);
			users.remove(0);			
		}
		else {
			System.out.println("aucun utilisateur dans l'ascenseur ");
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void arret(int niveau) {
		
		// a changer
		long t = Configurator.seq.SimulationTime();
		//
		for (UserSimulation in : usersInAscenseur) {
			if (in.niveau_final == niveau) {
				in.inLift = false;
				in.setTempsSortie(t);
				usersHappy.add(in);
				usersInAscenseur.remove(in);
				// Recuperer le temps courrant qui correspand au temps ou le deplacement est satisfait
				// On appele l'inteface utilisateur pour faire sortir l'utilisateur
				in.sortie();
			}			
		}
		int i = 0;
		//
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
					usersAppel.remove(out);
				}
					
			}
		 i++;
		}
		
	surchage = false;
	}


}
