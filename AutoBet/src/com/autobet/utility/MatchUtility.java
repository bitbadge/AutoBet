package com.autobet.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import com.autobet.pojo.Match;

public class MatchUtility {

	public static void printAllMatchesToFile() {
		try {
			Date d = new Date();
			File file = new File("MATCHDATA" + d.getTime() + ".txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 1; i < 7050; i++) {
				Match match = null;
				for (int j = 0; j < 5; j++) {
					match = MatchUtility.populateMatch("http://csgo.guru/match/" + i);
					if (match != null && match.getLoungeUrl() != null) {
						bw.write(match.toStringDelimited() + "\n");
						break;
					}
				}
				System.out.println(match);
			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Set<String> getUpcomingMatchUrls() {

		try {
			Set<String> urls = new TreeSet<String>();

			Document doc = Jsoup.connect("http://csgo.guru/scores").get();
			Elements links = doc.select("a[href]");

			for (Element e : links) {
				if (e.attr("href").contains("http://csgo.guru/match/"))
					urls.add(e.attr("href"));
			}

			return urls;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Match populateMatch(String matchUrl) {
		Match match = new Match();

		try {
			// pull guru match page html
			Document guruMatchPage = Jsoup.connect(matchUrl).userAgent("Mozilla").get();

			// setGuruUrl
			match.setGuruUrl(matchUrl);

			// setPrediction
			String holdPrediction = Jsoup.clean(guruMatchPage.select("div.match-prediction").text(), Whitelist.basic());
			match.setPrediction(holdPrediction);

			// setRisk and setBet
			Elements holdBetRiskElement = guruMatchPage.select("div.odds-text");
			if (!holdBetRiskElement.text().contains("Prediction in progress")) {
				String holdRiskText = holdBetRiskElement.select("span").first().text();
				String holdBetText = holdBetRiskElement.select("span").last().text();
				match.setRisk(holdRiskText);
				match.setBetSize(holdBetText);
			}

			// setRealOdds
			Elements realOddsText = guruMatchPage.select("span.newbetboxRW");
			match.setRealOdds(Double.parseDouble(realOddsText.text().substring(0, 2)) / 100);

			// setLoungeUrl
			Element guruMatchUrl = guruMatchPage.select("a[href*=csgolounge.com/match]").first();
			if (guruMatchUrl != null) {
				match.setLoungeUrl(guruMatchUrl.attr("href"));
			}

			if (match.getLoungeUrl() != null) {
				// pull csgo lounge match page
				Document loungeMatchPage = Jsoup.connect(match.getLoungeUrl()).userAgent("Mozilla").get();

				// setTeams
				Elements loungeOddsText = loungeMatchPage.select("a[onclick*=selectTeam]");
				String holdTeam1Text = loungeOddsText.select("b").first().text();
				String holdTeam2Text = loungeOddsText.select("b").last().text();
				if (holdTeam1Text.contains("win")) {
					holdTeam1Text = holdTeam1Text.replace(" (win)", "");
					match.setWinner(holdTeam1Text);
				} else if (holdTeam2Text.contains("win")) {
					holdTeam2Text = holdTeam2Text.replace(" (win)", "");
					match.setWinner(holdTeam2Text);
				} else {
					match.setWinner(null);
				}
				match.setTeam1(holdTeam1Text);
				match.setTeam2(holdTeam2Text);

				// setLoungeOdds
				String holdLoungeOddsText = loungeOddsText.select("i").first().text();
				holdLoungeOddsText = holdLoungeOddsText.substring(0, holdLoungeOddsText.indexOf("%"));
				match.setLoungeOdds(Double.parseDouble(holdLoungeOddsText) / 100);

				// setDifference
				match.setDifference(match.getRealOdds() - match.getLoungeOdds());
			}

		} catch (SocketTimeoutException e) {
			return null;
		} catch (HttpStatusException e) {
			if (e.getStatusCode() != 404) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return match;
	}

}
