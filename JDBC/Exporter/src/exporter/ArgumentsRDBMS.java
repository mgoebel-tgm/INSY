package exporter;


import java.util.Map;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.internal.ArgumentParserImpl;
import net.sourceforge.argparse4j.impl.Arguments;

public class ArgumentsRDBMS {
	public static void main(String[] args) {
		ArgumentParser parser = new ArgumentParserImpl("Connect to RDBMS:",false)
		.defaultHelp(true)    
		.description("Checks arguments for connecting to a RDBMS");

		parser.addArgument("-r")
		.choices("ASC", "DESC").setDefault("ASC")
		.help("Sortdirection");
		parser.addArgument("-h")
		.setDefault("localhost")
		.help("hostname");
		parser.addArgument("-u")
		.setDefault("root")
		.help("username");
		parser.addArgument("-p")
		.setDefault("")
		.help("password");
		parser.addArgument("-d").required(true)
		.help("datenbank");
		parser.addArgument("-s")
		.setDefault("")
		.help("field to sort by");
		parser.addArgument("-w")
		.setDefault("")
		.help("the where-clausel in SQL-Syntax");
		parser.addArgument("-t")
		.setDefault(";")
		.help("delimeter for fields");
		parser.addArgument("-f").required(true)
		.help("selected fields");
		parser.addArgument("-o")
		.setDefault("")
		.help("Output file");
		parser.addArgument("-T").required(true)
		.help("table name");

		parser.addArgument("--help")
		.action(Arguments.help())
		.setDefault(Arguments.SUPPRESS);
		Map<String,Object> arguments = null;
		try {
			Namespace res = parser.parseArgs(args);
			arguments =  res.getAttrs();
		} catch (ArgumentParserException e) {
			parser.handleError(e);
			System.exit(1);
		}
		Connection con = new Connection();
		String command = con.createSelect(arguments.get("s").toString(), arguments.get("r").toString(), arguments.get("w").toString(), arguments.get("f").toString(), arguments.get("T").toString());
		System.out.println(command);
		con.sendSelectCommand(arguments.get("h").toString(), arguments.get("u").toString(), 
				arguments.get("p").toString(), arguments.get("p").toString(), command, 
				arguments.get("t").toString(), arguments.get("o").toString());
	}
}