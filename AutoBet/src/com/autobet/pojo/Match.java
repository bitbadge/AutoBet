package com.autobet.pojo;

import com.autobet.constants.AutoBetConstants;

public class Match {

	private String guruUrl;
	private String loungeUrl;
	private String team1;
	private String team2;
	private String winner;

	private double realOdds;
	private double loungeOdds;
	private double difference;
	private String risk;
	private String betSize;
	private String prediction;

	@Override
	public String toString() {
		return "Match [guruUrl=" + guruUrl + ", loungeUrl=" + loungeUrl + ", team1=" + team1 + ", team2=" + team2
				+ ", winner=" + winner + ", realOdds=" + realOdds + ", loungeOdds=" + loungeOdds + ", difference="
				+ difference + ", risk=" + risk + ", betSize=" + betSize + ", prediction=" + prediction + "]";
	}

	public String toStringDelimited() {
		return guruUrl + AutoBetConstants.delimiter + loungeUrl + AutoBetConstants.delimiter + team1
				+ AutoBetConstants.delimiter + team2 + AutoBetConstants.delimiter + winner + AutoBetConstants.delimiter
				+ realOdds + AutoBetConstants.delimiter + loungeOdds + AutoBetConstants.delimiter + difference
				+ AutoBetConstants.delimiter + risk + AutoBetConstants.delimiter + betSize + AutoBetConstants.delimiter
				+ prediction;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getBetSize() {
		return betSize;
	}

	public void setBetSize(String betSize) {
		this.betSize = betSize;
	}

	public String getGuruUrl() {
		return guruUrl;
	}

	public void setGuruUrl(String guruUrl) {
		this.guruUrl = guruUrl;
	}

	public String getLoungeUrl() {
		return loungeUrl;
	}

	public void setLoungeUrl(String loungeUrl) {
		this.loungeUrl = loungeUrl;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public double getRealOdds() {
		return realOdds;
	}

	public void setRealOdds(double realOdds) {
		this.realOdds = realOdds;
	}

	public double getLoungeOdds() {
		return loungeOdds;
	}

	public void setLoungeOdds(double loungeOdds) {
		this.loungeOdds = loungeOdds;
	}

	public double getDifference() {
		return difference;
	}

	public void setDifference(double difference) {
		this.difference = difference;
	}

	public String getPrediction() {
		return prediction;
	}

	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}

}
