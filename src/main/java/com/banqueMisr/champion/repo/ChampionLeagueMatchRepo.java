package com.banqueMisr.champion.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banqueMisr.champion.entity.ChampionLeagueEntity;
import com.banqueMisr.champion.entity.ChampionLeagueMatchEntity;
import com.banqueMisr.champion.entity.ParticipantEntity;

@Repository
public interface ChampionLeagueMatchRepo extends JpaRepository<ChampionLeagueMatchEntity, Long> {
	Optional<ChampionLeagueMatchEntity> findByIdAndFirstParticipantOrSecondParticipant(Long id,
			ParticipantEntity firstParticipant, ParticipantEntity secondParticipant);
	
	List<ChampionLeagueMatchEntity> findByLeagueMatchEntity(ChampionLeagueEntity championLeagueEntity);
	List<ChampionLeagueMatchEntity> findByMatchWinner(ParticipantEntity matchWinner);
	ChampionLeagueMatchEntity findFirstByOrderById();
	
}
