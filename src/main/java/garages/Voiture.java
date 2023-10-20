package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		boolean dansgarage = false;
		for (Stationnement a : myStationnements) {
			if (a.estEnCours()) {
				dansgarage = true;
			}
		}
		if (dansgarage) {
			throw new IllegalArgumentException("La voiture est deja dans un garage");
		}
		// Et si la voiture est déjà dans un garage ?

		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if (!myStationnements.isEmpty()) {
			int dernierGarage = myStationnements.size() -1;
			Stationnement dernierStationnement = myStationnements.get(dernierGarage);
			if (dernierStationnement.estEnCours()) {
				dernierStationnement.terminer();
			}
			else {
				throw new Exception("La voiture est déjà sortie");
			}
		}
		else {
		throw new Exception ("La voiture n'a pas de stationnement");	
		}
		
		// TODO: Implémenter cette méthode

		// Trouver le dernier stationnement de la voiture
		// Terminer ce stationnement
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		// TODO: Implémenter cette méthode
		HashSet<Garage> garagesVisites = new HashSet<Garage>();
		for (Stationnement s : myStationnements) {
			garagesVisites.add(s.getGarage());
		}
		return garagesVisites ;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		boolean reponse = true;
		if (myStationnements.isEmpty()) {
			reponse = false ;
		}
		else {
			Stationnement lastStat = myStationnements.get(myStationnements.size()-1);
			if(!lastStat.estEnCours()) {
				reponse = false;
			}
		}
		return reponse;
		// TODO: Implémenter cette méthode

		//throw new UnsupportedOperationException("Pas encore implémenté");
		// Vrai si le dernier stationnement est en cours
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * dates d'entrée / sortie dans ce garage
	 * <br>
	 * Exemple :
	 * 
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		Set<Garage> listgarage = this.garagesVisites();
		String imp = "";
		for (Garage a: listgarage) {
			imp = imp + a + '\n';
			for (Stationnement b : myStationnements) {
				if(b.getGarage() ==a) {
					imp = imp + '\t' + b + '\n' ;
				}
			}
		}
	// TODO: Implémenter cette méthode
	// Utiliser les méthodes toString() de Garage et Stationnement
		out.println(imp);
	}

}
