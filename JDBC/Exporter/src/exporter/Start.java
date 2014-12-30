package exporter;

public class Start {
 /**
  * -h ... Hostname des DBMS. Standard: localhost
  *	-u ... Benutzername. Standard: Benutzername des im Betriebssystem angemeldeten Benutzers
  *	-p ... Passwort. Alternativ kann ein Passwortprompt angezeigt werden. Standard: keins
  *	-d ... Name der Datenbank
  *	-s ... Feld, nach dem sortiert werden soll (nur eines möglich, Standard: keines)
  *	-r ... Sortierrichtung. Standard: ASC
  *	-w ... eine Bedingung in SQL-Syntax, die um Filtern der Tabelle verwendet wird. Standard: keine
  *	-t ... Trennzeichen, dass für die Ausgabe verwendet werden soll. Standard: ; 
  *	-f ... Kommagetrennte Liste (ohne Leerzeichen) der Felder, die im Ergebnis enthalten sein sollen. * soll akzeptiert werden (Pflicht)
  *	-o ... Name der Ausgabedatei. Standard: keine -> Ausgabe auf der Konsole
  *	-T ... Tabellenname (Pflicht)
  * @param args
  */
	public static void main(String[] args) {
		Connection con = new Connection();
		String befehl = con.createSelect(null, "DESC", null, "*", "element");
		System.out.println(befehl);
		String result = con.sendSelectCommand("192.168.1.22", "insy4", "blabla", "testinsy", befehl,";",null);
		System.out.println(result);
		
		
	}

}
