package chatbot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import helpers.Log;

public class Bot {

	private boolean			online;
	private boolean			muted;
 	
	private List<String>	linesFromFile;

	public Bot() {
		setOnline();
		muted = false;

		setFile(new File("answers.txt"));
		getFirstPhrase();
	}

	/**
	 * Извлечение приветственной фразы из первой строки текстового файла (набора
	 * фраз)
	 */
	public void getFirstPhrase() {
		Log.info("Bot: " + linesFromFile.get(0));
		System.out.print("$>");
	}

	/** Извлечение случайной фразы из текстового файла (набора фраз) */
	public void getRandomPhrase() {
		if (!isMuted()) {
			Log.info("Bot: " + linesFromFile
					.get(ThreadLocalRandom.current().nextInt(1, linesFromFile.size())));
			System.out.print("$>");
		}
	}

	/**
	 * Извлечение прощальной фразы из последней строки текстового файла (набора
	 * фраз)
	 */
	public void getLastPhrase() {
		Log.info("Bot: " + linesFromFile.get(linesFromFile.size()-1));
		System.out.print("$>");
	}

	/**
	 * Выбор текстового файла с набором фраз
	 * Можно указать название файла, находящегося в той же дирректории, что и проект
	 * Или полный путь до файла в файловой системе
	 */
	public void setFile(File file) {
		initFileContent(file);
	}

	/**
	 * 
	 */
	private void initFileContent(File file) {
		try {
			linesFromFile = Files.readAllLines(file.toPath());
			Log.info("Файл " + file.getAbsolutePath() + " подключен успешно!");
		} catch (IOException e) {
			Log.error("Файла с таким именем " + file.toPath() + " не существует!");
		}
		clearEmptyStrings();
	} 
	
	private void clearEmptyStrings() {
		linesFromFile = linesFromFile.stream()
		.filter(x -> !x.equals(""))
		.collect(Collectors.toList());
	}

	/**
	 * Три метода по управлению Флаг активности бота (online)
	 * setOnline()
	 * setOffline()
	 * isOnline()
	 * 
	 * Флаг используется в цикле для работы с ботом до тех пор, пока статус
	 * isOnline=true
	 */
	public void setOnline() {
		this.online = true;
	}

	public void setOffline() {
		this.online = false;
	}

	public boolean isOnline() {
		return online;
	}

	/**
	 * Три метода по управлению "режимом молчания" бота
	 * mute()
	 * unMute()
	 * isMuted()
	 */
	public void mute() {
		muted = true;
	}

	public void unMute() {
		muted = false;
	}

	public boolean isMuted() {
		return muted;
	}

}