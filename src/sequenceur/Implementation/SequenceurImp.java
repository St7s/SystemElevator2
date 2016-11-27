package sequenceur.Implementation;


public class SequenceurImp {
	static Sequencer  instance = null;
public static Sequencer create (long temps_execution, long temps_debut, float coefficient_temps)
{
	if(instance == null)
		instance = new Sequencer(temps_execution,temps_debut, coefficient_temps);
	return instance;
}
}
