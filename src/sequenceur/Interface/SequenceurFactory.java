package sequenceur.Interface;

import sequenceur.Implementation.SequenceurImp;

public class SequenceurFactory {
	public static ISequencer create (long temps_execution, long temps_debut, float coefficient_temps)
	{
		return SequenceurImp.create(temps_execution, temps_debut, coefficient_temps);
	}
}
