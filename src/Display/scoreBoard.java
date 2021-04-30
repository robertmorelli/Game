package Display;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to print out the score board for the top 5 scores.
 * 
 * @author Kody Berry
 * @author Robert Morelli
 *
 */
public class scoreBoard {

	public void endGame(int finalScore) throws IOException {
		try {
			WriteNamesAndScoreToFile();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

	public List<String> retrieveScores() throws IOException, URISyntaxException {
		BufferedReader reader = new BufferedReader(new FileReader("score.txt"));

		String scoreLine = reader.readLine();
		List<String> scores = new ArrayList<>();
		if (scoreLine != null) {
			String[] tempScore = scoreLine.split(", ");
			scores = new ArrayList<>(Arrays.asList(tempScore));
		}
		ScoreBoardDisplay.leaderScores = new int[scores.size()];
		int index = 0;
		for (String score : scores) {
			ScoreBoardDisplay.leaderScores[index++] = Integer.parseInt(score);
		}
		reader.close();
		return null;
	}

	public List<String> retrieveNames() throws IOException, URISyntaxException {
		BufferedReader reader = new BufferedReader(new FileReader("name.txt"));
		String namesLine = reader.readLine();
		List<String> names = new ArrayList<>();
		if (namesLine != null) {
			String[] tempScore = namesLine.split(", ");
			names = new ArrayList<>(Arrays.asList(tempScore));
		}
		ScoreBoardDisplay.leaderNames = new String[names.size()];
		int index = 0;
		for (String name : names) {
			ScoreBoardDisplay.leaderNames[index++] = name;
		}
		reader.close();
		return names;
	}

	@SuppressWarnings("unused")
	private void addNewScore(int finalScore, List<String> scores) {
		boolean foundSpotForNewScore = false;
		int i = 0;
		while (!foundSpotForNewScore && i < scores.size()) {
			if (finalScore <= Integer.parseInt(scores.get(i))) {
				foundSpotForNewScore = true;
			}
			i++;
		}
		scores.add(i, String.valueOf(finalScore));
	}

	@SuppressWarnings("unused")
	private void writeScores(List<String> scores) throws IOException {
		String outputScores = scores.toString();
		outputScores = outputScores.replace("[", "");
		outputScores = outputScores.replace("]", "");
	}

	public void WriteNamesAndScoreToFile() throws IOException, URISyntaxException {

		FileWriter scorefile = new FileWriter("score.txt");

		FileWriter namefile = new FileWriter("name.txt");

		namefile.write(String.join(", ", Arrays.asList(ScoreBoardDisplay.leaderNames)));

		List<String> scores = new ArrayList<>(ScoreBoardDisplay.leaderScores.length);
		for (int i : ScoreBoardDisplay.leaderScores) {
			scores.add(i + "");
		}
		scorefile.write(String.join(", ", scores));
		scorefile.write(", " + Game.mainCharacter.score);
		String dific = Game.difficulty == difficulties.easy ? "easy" : "";
		dific = Game.difficulty == difficulties.peaceful ? "peaceful" : dific;
		dific = Game.difficulty == difficulties.impossible ? "impossible" : dific;
		namefile.write(", " + ScoreBoardDisplay.myName + "(" + dific + ")");
		namefile.close();
		scorefile.close();

	}
	
	public void ReadNamesAndScoreToFile() throws IOException, URISyntaxException {
		BufferedReader NameReader = new BufferedReader(new FileReader("name.txt"));
		String namesLine = NameReader.readLine();
		List<String> names = new ArrayList<>();
		if (namesLine != null) {
			String[] tempNames = namesLine.split(", ");
			names = new ArrayList<>(Arrays.asList(tempNames));
		}
		ScoreBoardDisplay.leaderNames = new String[names.size()];
		int NameIndex = 0;
		for (String name : names) {
			ScoreBoardDisplay.leaderNames[NameIndex++] = name;
		}
		NameReader.close();
		
		BufferedReader ScoreReader = new BufferedReader(new FileReader("score.txt"));
		String scoreLine = ScoreReader.readLine();
		List<String> scores = new ArrayList<>();
		if (scoreLine != null) {
			String[] tempScore = scoreLine.split(", ");
			scores = new ArrayList<>(Arrays.asList(tempScore));
		}
		ScoreBoardDisplay.leaderScores = new int[scores.size()];
		int ScoreIndex = 0;
		for (String score : scores) {
			ScoreBoardDisplay.leaderScores[ScoreIndex++] = Integer.parseInt(score);
		}
		ScoreReader.close();
		
	}
	
	

	@SuppressWarnings("unused")
	private void showLeaderBoard(List<String> scores) {
		System.out.println("TOP 5 LEADERBOARD");
		int i = 0;
		while (i < 5 && i < scores.size()) {
			System.out.println(scores.get(i));
			i++;
		}
	}

}