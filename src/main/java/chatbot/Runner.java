package chatbot;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helpers.Log;
 
public class Runner {

	public static void main(String[] args) {
		runBot();
	}

	private static void runBot() {
		Scanner	commandScanner	= new Scanner(System.in);

		Log.configureLogger();
		Bot		bot				= new Bot();

		while (bot.isOnline()) {
			String			command	= commandScanner.nextLine();
			
			Log.logToFile("$>"+command);
			final Matcher	m		= Pattern.compile("[\"|«]([\\w ]+)(?:[\"|»]|[:] (.+)[\"|»])").matcher(command);  
/*
 * Варинты паттернов:
 * 1) контроль только одного вида кавычек
 * \"([\\w ]+)(?:\"|[:] (.+)\")
 * 2) контроль нескольких видов кавычек: т.е. фразы в разных видах кавычек - принимаются как команды
 * ["|«]([\w ]+)(?:["|»]|[:] (.+)["|»])
 */
			if (m.find(0)) {
				switch (m.group(1)) {
				case "Start talking":
					bot.unMute();
					break;
				case "Stop talking":
					bot.mute();
					break;

				case "Goodbye":
					bot.getLastPhrase();
					bot.setOffline();
					break;

				case "Use another file":
					bot.setFile(new File(m.group(2)));
					break;

				default:
					Log.info("Комманда не распознана!");
					break;
				}
			} else {
				bot.getRandomPhrase();
			}
			
		}
	}
}
