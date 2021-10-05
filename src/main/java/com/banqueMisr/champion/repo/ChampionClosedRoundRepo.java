package com.banqueMisr.champion.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banqueMisr.champion.entity.ChampionClosedRound;

@Repository
public interface ChampionClosedRoundRepo extends JpaRepository<ChampionClosedRound, Long> {

}
