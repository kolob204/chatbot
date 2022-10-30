package chatbot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ThreadLocalRandom;

import helpers.Log;

public class Bot {
 
	private boolean	online;
	private boolean	muted;

	private File	currentFile;
	private int		currentFileLinesCount;

	public Bot() {
		setOnline();
		muted = false;

		setFile(new File("answers.txt"));
		getFirstPhrase();
	}

	/** Извлечение приветственной фразы из первой строки текстового файла (набора фраз) */
	public void getFirstPhrase() {
		try {
			Log.info("Bot: " + Files.readAllLines(currentFile.toPath()).get(0));
			System.out.print("$>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Извлечение случайной фразы из текстового файла  (набора фраз) */
	public void getRandomPhrase() {
		if (!isMuted()) {
			try {
				Log.info("Bot: " + Files.readAllLines(currentFile.toPath())
						.get(ThreadLocalRandom.current().nextInt(1, currentFileLinesCount)));
				System.out.print("$>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** Извлечение прощальной фразы из последней строки текстового файла  (набора фраз) */
	public void getLastPhrase() {
		if (!isMuted()) {
			try {
				Log.info("Bot: " + Files.readAllLines(currentFile.toPath()).get(currentFileLinesCount - 1));
				System.out.print("$>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** Выбор текстового файла  с набором фраз
	 *  Можно указать название файла, находящегося в той же дирректории, что и проект
	 *  Или полный путь до файла в файловой системе 
	*/
	public void setFile(File file) {
		this.currentFile = file;
		initFileContent(file);
	}

	/**
	 * Предварительный анализ подключенного файла на количество доступных в нём строк 
	 */
	private void initFileContent(File file) {
		try {
			currentFileLinesCount = Files.readAllLines(file.toPath()).size();
			Log.info("Файл " + file.getAbsolutePath() + " подключен успешно!");
		} catch (IOException e) {
			Log.error("Файла с таким именем " + file.toPath() + " не существует!");
		}
	}
 
	/**
	 * Три метода по управлению Флаг активности бота (online)
	 * setOnline()
	 * setOffline()
	 * isOnline()
	 * 
	 * Флаг используется в цикле для работы с ботом до тех пор, пока статус isOnline=true 
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