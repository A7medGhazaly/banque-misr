package com.banqueMisr.champion.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "champion_league")
public class ChampionLeagueEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column(name = "start_at")
	private Date startAt;
	@Column(name = "ends_at")
	private Date endsAt;
	@OneToOne
	@JoinColumn(name = "winner", referencedColumnName = "id")
	private ParticipantEntity winner;
	@OneToMany(mappedBy = "leagueMatchEntity",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ChampionLeagueMatchEntity> matches;
	@OneToMany(mappedBy = "leagueMatchEntity",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ChampionLeagueMatchEntity> closedmatches;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartAt() {
		return startAt;
	}
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}
	public Date getEndsAt() {
		return endsAt;
	}
	public void setEndsAt(Date endsAt) {
		this.endsAt = endsAt;
	}
	//public ParticipantEntity getWinner() {
	//	return winner;
	//}
	//public void setWinner(ParticipantEntity winner) {
//		this.winner = winner;
//	}
	public List<ChampionLeagueMatchEntity> getMatches() {
		return matches;
	}
	public void setMatches(List<ChampionLeagueMatchEntity> matches) {
		this.matches = matches;
	}
	public List<ChampionLeagueMatchEntity> getClosedmatches() {
		return closedmatches;
	}
	public void setClosedmatches(List<ChampionLeagueMatchEntity> closedmatches) {
		this.closedmatches = closedmatches;
	}
	public ParticipantEntity getWinner() {
		return winner;
	}
	public void setWinner(ParticipantEntity winner) {
		this.winner = winner;
	}

}
