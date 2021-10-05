package com.banqueMisr.champion.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="champion_group")
public class ChampionGroupEntity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	@Column(name="GROUP_NAME")
	private String groupName;
	@OneToMany(mappedBy = "championGroup",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ParticipantEntity> participants;
	
	public ChampionGroupEntity(Long id, String groupName) {
		super();
		this.id = id;
		this.groupName = groupName;
	}
	
	public ChampionGroupEntity() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
