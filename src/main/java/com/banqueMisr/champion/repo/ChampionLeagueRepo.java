package com.banqueMisr.champion.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banqueMisr.champion.entity.ChampionLeagueEntity;
@Repository
public interface ChampionLeagueRepo extends JpaRepository<ChampionLeagueEntity,Long>{
	ChampionLeagueEntity findFirstByOrderByIdDesc();
}
