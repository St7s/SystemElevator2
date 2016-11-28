package systemControl.Implementation;

import systemControl.Interface.ISystemControl;

import java.util.ArrayList;
import systemAscenseur.Implementation.Sens;
import systemAscenseur.Interface.SystemControl_SA;

class SystemControl implements ISystemControl{

	private static SystemControl instance = null;
	private static SystemControl_SA systemAscenseur;

	private ArrayList<Commande> commandes = new ArrayList<Commande>();
	private Commande last_commande = null;
	private int niveauActuel;


	private SystemControl(){}

	/**
	 * Getter of commande
	 */
	public static SystemControl getInstance()
	{
		if(instance == null)
			instance = new SystemControl();
		return instance;
	}

	@Override
	public void link(SystemControl_SA systemAscenseur){
		if(SystemControl.systemAscenseur == null)
			SystemControl.systemAscenseur = systemAscenseur;
	}

	public ArrayList<Commande> getCommande() {
		return commandes; 
	}
	/**
	 * Setter of commande
	 */
	public void setCommande(ArrayList<Commande> commande) { 
		this.commandes = commande; 
	}
	/**
	 * 
	 * @param command 
	 */
	public void Depiler() {
		ArrayList<Commande> aRetirer = new ArrayList<Commande>();
		//On parcours toutes les commandes en memoire
		for(Commande uneCommande : commandes){
			if(uneCommande.niveau == niveauActuel){
				if(last_commande == null || last_commande.sens==uneCommande.sens||last_commande.niveau>uneCommande.niveau)
					last_commande = uneCommande;
				aRetirer.add(uneCommande);
			}
		}
		//On retire la liste des commandes traitees
		for (Commande commande : aRetirer)
			commandes.remove(commandes.get(commandes.indexOf(commande)));
	}

	/**
	 * Algorithme principal de mise en place des scores
	 */
	public Commande algoWithSens()
	{
		if(this.commandes.size() == 0)
			return null;
		if (this.commandes.size() == 1)						// si le tableau n'a qu'une case
			return commandes.get(0);								// aller a  la premia¨re case

		Commande test = new Commande();
		if(this.last_commande==null)
		{
			last_commande = new Commande(systemAscenseur.getNiveauMin(), Sens.UP);
			test = this.nextUp();
		}
		else
		{
			if (this.last_commande.getSens() == Sens.UP)	// si la dernia¨re action etait de monter
			{
				if (this.findUp() == true)						// si on trouve une personne au dessus qui veut monter
					test = this.nextUp();				// aller a  la personne au-dessus la plus proche qui veut monter

				else											// sinon
					test = this.nextOtherSens();							// aller a  la personne la plus haute qui veut descendre
			}

			if (this.last_commande.getSens() == Sens.DOWN)	// si la dernia¨re action etait de descendre
			{
				if (this.findDown() == true)					// si on trouve une personne au dessous qui veut descendre
					test = this.nextDown();			// aller a  la personne au-dessous la plus proche qui veut descendre

				else											// sinon
					test = this.nextOtherSens();							// aller a  la personne la plus basse qui veut monter
			}
		}
		return test;
	}


	/* ---------------------- Fonctions booleennes de recherche -----------------------------*/

	/**
	 * Si on trouve une commande avec un etage superieur et qui veut monter
	 */
	public boolean findUp()
	{	
		for(Commande element: this.commandes)
		{
			if (element.niveau > this.last_commande.niveau && element.sens == Sens.UP)
				// si on trouve une commande avec un etage sup et Sens vers le haut
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Si on trouve une commande avec un etage inferieur et qui veut descendre
	 */
	public boolean findDown()
	{	
		for(Commande element: this.commandes)
		{
			if (element.niveau < this.last_commande.niveau && element.sens == Sens.DOWN)
				// si on trouve une commande avec un etage sinf et Sens vers le bas
			{
				return true;
			}
		}
		return false;
	}


	/* ----------------------------Fonction de recherche de commande suivante ---------------------*/
	//TODO modifier TOUT
	/**
	 * Retourne la commande avec niveau juste superieur et sens vers le haut
	 * @return Commande
	 */
	public Commande nextUp()
	{
		Commande next = new Commande(systemAscenseur.getNiveauMax()+1,Sens.DOWN);

		for(Commande uneCommande: this.commandes)
		{
			// si on trouve une commande avec un etage sup a  last_element et inf a  next et Sens vers le haut
			if ((uneCommande.niveau >= this.last_commande.niveau) && (uneCommande.niveau < next.niveau) && (uneCommande.sens == Sens.UP))
			{
				next.copy(uneCommande);
			}
			else if((uneCommande.niveau <= this.last_commande.niveau) && (uneCommande.niveau < next.niveau) && (uneCommande.sens == Sens.UP) && (last_commande.sens == Sens.DOWN))
			{
				next.copy(uneCommande);
			}
		}
		return next;
	}

	/**
	 * Retourne la commande avec niveau juste superieur et sens vers le haut
	 * @return Commande
	 */
	public Commande nextDown()
	{
		Commande next = new Commande(systemAscenseur.getNiveauMin()-1,Sens.UP);

		for(Commande uneCommande: this.commandes)
		{
			// si on trouve une commande avec un etage inf a  last_element et sup a  next et Sens vers le bas
			if ((uneCommande.niveau <= this.last_commande.niveau) && (uneCommande.niveau > next.niveau) && (uneCommande.sens == Sens.DOWN))
			{
				next.copy(uneCommande);
			}
			else if((uneCommande.niveau >= this.last_commande.niveau) && (uneCommande.niveau > next.niveau) && (uneCommande.sens == Sens.DOWN) && (last_commande.sens == Sens.UP))
			{
				next.copy(uneCommande);
			}
		}
		return next;
	}

	/**
	 * 
	 */
	public Commande nextOtherSens()
	{
		if (this.last_commande.sens == Sens.UP)	// chercher la commande en dessous allant vers le bas
			return nextDown();
		else
			return nextUp();
	}

	/**
	 * 
	 */
	public void MAJDeplacement() {
	}
	/**
	 * 
	 * @param niveau 
	 */
	public void notifierNiveau(int niveau) {
		niveauActuel = niveau;
		Commande temp = algoWithSens();
		if(temp == null)
		{
			System.out.println("Pas de commande");
			systemAscenseur.commande(null);
		}
		else if(temp.niveau==niveau)
		{
			systemAscenseur.commande(null);
			Depiler();
		}
		else if(temp.niveau<niveau)
		{
			systemAscenseur.commande(Sens.DOWN);
		}
		else
		{
			systemAscenseur.commande(Sens.UP);
		}

	}
	/**
	 * 
	 * @param niveau 
	 */
	public void deplacement(int niveau) {
		Sens direction;
		if(niveauActuel-niveau<0)
		{
			direction=Sens.UP;
		}
		else
		{
			direction=Sens.DOWN;
		}
		Commande comm = new Commande(niveau,direction);
		commandes.add(comm);
		//MAJDeplacement();
	}
	/**
	 * 
	 * @param direction 
	 * @param niveau 
	 */
	public void appel(int niveau, Sens direction) {
		Commande comm = new Commande(niveau, direction);
		commandes.add(comm);
		//MAJDeplacement();
	}
	public int getNombreCommande() {
		return commandes.size();
	}

}
