package com.banqueMisr.champion.entity;

import javax.persistence.Column;
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
@Table(name = "participant")
public class ParticipantEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "ENGLISH_NAME", updatable = false)
	private String englishName;
	@Column(name = "EMAIL", updatable = false)
	private String email;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID")
	private ChampionGroupEntity championGroup;
	 @OneToOne(mappedBy = "winner")
	 private ChampionLeagueEntity championLeagueEntity;
	@OneToOne(mappedBy = "firstParticipant")
	private ChampionLeagueMatchEntity firstParticipant;
	@OneToOne(mappedBy = "secondParticipant")
	private ChampionLeagueMatchEntity secondParticipant;
	@OneToOne(mappedBy = "matchWinner")
	private ChampionLeagueMatchEntity matchWinner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ChampionGroupEntity getChampionGroup() {
		return championGroup;
	}

	public void setChampionGroup(ChampionGroupEntity championGroup) {
		this.championGroup = championGroup;
	}
	public ChampionLeagueEntity getChampionLeagueEntity() {
	 return championLeagueEntity;
	}
	public void setChampionLeagueEntity(ChampionLeagueEntity championLeagueEntity) {
		this.championLeagueEntity = championLeagueEntity;
	}

	public ChampionLeagueMatchEntity getFirstParticipant() {
		return firstParticipant;
	}

	public void setFirstParticipant(ChampionLeagueMatchEntity firstParticipant) {
		this.firstParticipant = firstParticipant;
	}

	public ChampionLeagueMatchEntity getSecondParticipant() {
		return secondParticipant;
	}

	public void setSecondParticipant(ChampionLeagueMatchEntity secondParticipant) {
		this.secondParticipant = secondParticipant;
	}

	public ChampionLeagueMatchEntity getMatchWinner() {
		return matchWinner;
	}

	public void setMatchWinner(ChampionLeagueMatchEntity matchWinner) {
		this.matchWinner = matchWinner;
	}

}
