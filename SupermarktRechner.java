import java.util.Scanner;
import java.util.Arrays;

public class SupermarktRechner {

	// Erstellen eines Arrays mit Lebensmitteln

	String[] lebensmittel = { "Apfel", "Birne", "Brot", "Wasser", "Fleisch", "Fisch", "Tomate", "Paprika", "Reis",
			"Pasta" };

	// Erstellen eines Arrays mit Preisen für Lebensmittel

	float[] preise = { 1.20f, 1.10f, 1.80f, 0.90f, 6.90f, 5.90f, 3.99f, 1.30f, 1.50f, 2.00f };

	// Erstellen eines Arrays mit der Menge der Lebensmittel

	int[] lebensmittelMenge = { 10, 20, 12, 30, 5, 7, 20, 5, 12, 15 };

	// Erstellen eines leeren Arrays für Kundeneinkäufe

	String[] warenkorb = new String[10];

	// Erstellen eines leeren Arrays für Artikel, die unter einem bestimmten Preis
	// liegen

	String[] angebote = new String[10];

	// Erstellen eines Scanner Objektes

	Scanner eingabe = new Scanner(System.in);

	// Erstellen von Variablen;

	String benutzereingabe = " ";
	String ausgang = "Exit";
	String einkauf = " ";
	String fertig = "Complete";
	boolean isEqual = false;
	boolean istGleich = false;
	boolean artikelVorhanden = false;
	String kundenArtikel = " ";
	float gesamtpreis = 0;
	float einzelpreis = 0;
	float artikelpreis = 0;
	float durchschnittspreis = 0;
	float wunschpreis = 0;
	final float RABATT_GROSS = 0.80f;
	final float RABATT_KLEIN = 0.90f;
	int menge = 0;
	int gesamtmenge = 0;

	// Erstellen einer Methode zum Suchen von Artikeln

	public boolean artikelSuchen(String kundenArtikel) {

		int index = 0;

		for (int i = 0; i < lebensmittel.length; i++) {

			if (kundenArtikel.equalsIgnoreCase(lebensmittel[i])) {

				warenkorb[index] = kundenArtikel;
				index++;

				// Holen des Artikelpreises des zugehörigen Artikels

				einzelpreis = preise[i];

				// Holen der Gesamtmenge des zugehörigen Artikels

				gesamtmenge = lebensmittelMenge[i];

				return true;
			}
		}
		return false;
	}

	// Erstellen einer Methode zur Berechnung des Durchschnittspreises der Artikel

	public float durchschnittspreisBerechnen() {

		float summe = 0;

		for (float preis : preise) {

			summe += preis;

		}

		durchschnittspreis = summe / preise.length;

		return durchschnittspreis;

	}

	// Erstellen einer Methode, um Artikel, die unter einem bestimmten Preis liegen,
	// rauszufiltern

	public void artikelRausfiltern(float wunschpreis) {

		// Die Liste der Angebote leeren
		Arrays.fill(angebote, null);

		int index = 0;

		for (int i = 0; i < preise.length; i++) {

			if (preise[i] < wunschpreis) {

				angebote[index] = lebensmittel[i];

				index++;

			}

		}

		for (index = 0; index < angebote.length; index++) {

			if (angebote[index] == null) {

				break;
			}

			System.out.print(angebote[index] + "\t ");

		}
		System.out.print("\n");
	}

	// Erstellen einer Methode, um Rabatte anzuwenden

	public float rabatteAnwenden(float gesamtpreis) {

		if (gesamtpreis > 50) {

			gesamtpreis *= RABATT_GROSS;

		} else if (gesamtpreis > 20) {

			gesamtpreis *= RABATT_KLEIN;

		} else {
			gesamtpreis *= 1.00;
		}
		return gesamtpreis;

	}

	public static void main(String[] args) {

		// Erstellen eines Objektes der Klasse SupermarktRechner

		SupermarktRechner r = new SupermarktRechner();

		do {

			System.out.print("Herzlich willkommen in unserem Supermarkt.\n "
					+ "Wenn Sie einkaufen wollen, geben Sie Einkaufen ein:   \n "
					+ "Wenn Sie den Supermarkt verlassen wollen, geben Sie Exit ein. \n");

			r.benutzereingabe = r.eingabe.nextLine();

			if (r.benutzereingabe.equalsIgnoreCase(r.ausgang)) {

				r.isEqual = true;

			} else {
				do {

					System.out.print("Wenn Sie weiter einkaufen moechten, geben Sie weiter ein: \n"
							+ "Wenn Sie alle Artikel ausgewaehlt haben, geben Sie Complete ein.  \n");

					r.einkauf = r.eingabe.nextLine();

					if (r.einkauf.equalsIgnoreCase(r.fertig)) {

						r.istGleich = true;

					} else {

						System.out.print("Der Durchschnittspreis aller unserer Artikel betraegt "
								+ r.durchschnittspreisBerechnen() + " Euro. \n");

						System.out.print("Artikel, die unter 1.50 Euro kosten: \n");

						r.artikelRausfiltern(1.50f);
						// Aufforderung des Kunden, Artikel einzugeben

						while (r.artikelVorhanden == false) {

							System.out.print("Bitte geben Sie einen Artikelnamen ein: \n");

							r.kundenArtikel = r.eingabe.nextLine();

							r.artikelVorhanden = r.artikelSuchen(r.kundenArtikel);

						}

						r.artikelVorhanden = false;

						// Aufforderung des Kunden, die Menge des Artikels einzugeben

						System.out.print("Bitte geben Sie die Menge des gewaehlten Artikels ein: \n");
						try {
							r.menge = r.eingabe.nextInt();
							while (r.menge > r.gesamtmenge) {

								System.out.print("Es sind nur noch " + r.gesamtmenge + " vorhanden. \n"
										+ " Bitte geben Sie eine kleinere Menge ein: \n");

								r.menge = r.eingabe.nextInt();
							}

						} catch (Exception e) {
							System.out.print("Bitte geben Sie nur ganze Zahlen ein.");
							r.eingabe.nextLine();

						}

						r.gesamtmenge -= r.menge;

						r.eingabe.nextLine();

						// Berechnen des Artikelpreises

						r.artikelpreis = r.einzelpreis * r.menge;

						// Berechnen des Gesamtpreises

						r.gesamtpreis += r.artikelpreis;
					}
				}

				while (r.istGleich == false);

				// Ausgabe des Gesamtpreises
				System.out.print("Der Gesamtpreis betraegt " + r.rabatteAnwenden(r.gesamtpreis) + " Euro. \n");

				r.gesamtpreis = 0;

				r.istGleich = false;
			}

		} while (r.isEqual == false);

		System.out.print("Vielen Dank fuer Ihren Einkauf! \n");

		r.eingabe.close();

	}

}
