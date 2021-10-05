package com.banqueMisr.champion.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="closed_round")
public class ChampionClosedRound {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FIRST_PARTICIPANT", referencedColumnName = "id")
	private ParticipantEntity firstParticipant;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SECOND_PARTICIPANT", referencedColumnName = "id")
	private ParticipantEntity secondParticipant;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WINNER", referencedColumnName = "id")
	private ParticipantEntity matchWinner;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CHAMPION_LEAGUE_ID", referencedColumnName = "id")
	private ChampionLeagueEntity leagueMatchEntity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ParticipantEntity getFirstParticipant() {
		return firstParticipant;
	}
	public void setFirstParticipant(ParticipantEntity firstParticipant) {
		this.firstParticipant = firstParticipant;
	}
	public ParticipantEntity getSecondParticipant() {
		return secondParticipant;
	}
	public void setSecondParticipant(ParticipantEntity secondParticipant) {
		this.secondParticipant = secondParticipant;
	}
	public ParticipantEntity getMatchWinner() {
		return matchWinner;
	}
	public void setMatchWinner(ParticipantEntity matchWinner) {
		this.matchWinner = matchWinner;
	}
	public ChampionLeagueEntity getLeagueMatchEntity() {
		return leagueMatchEntity;
	}
	public void setLeagueMatchEntity(ChampionLeagueEntity leagueMatchEntity) {
		this.leagueMatchEntity = leagueMatchEntity;
	}
	
}
