package com.banqueMisr.champion.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.banqueMisr.champion.entity.ChampionGroupEntity;

@Repository
public interface ChampionGroupRepo extends JpaRepository<ChampionGroupEntity, Long>{
	@Query(value ="SELECT champion_group.ID as group_id,champion_group.GROUP_NAME,COUNT(participant.ID) as participant_num FROM champion_group \n" + 
			"			LEFT JOIN participant ON (participant.GROUP_id=champion_group.ID) \n" + 
			"			GROUP by champion_group.ID",nativeQuery = true)
	public List<Object[]> getGroupPartitcipantCount();
}	
