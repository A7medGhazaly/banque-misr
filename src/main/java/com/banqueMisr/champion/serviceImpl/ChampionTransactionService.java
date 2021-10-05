package com.banqueMisr.champion.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.banqueMisr.champion.entity.ChampionClosedRound;
import com.banqueMisr.champion.entity.ChampionLeagueMatchEntity;
import com.banqueMisr.champion.exception.BusinessException;
import com.banqueMisr.champion.repo.ChampionClosedRoundRepo;
import com.banqueMisr.champion.repo.ChampionLeagueMatchRepo;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class,DataAccessException.class,BusinessException.class})
public class ChampionTransactionService {

	@Autowired
	ChampionLeagueMatchRepo championLeagueMatchRepo;

	@Autowired
	ChampionClosedRoundRepo closedRoundRepo;
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class,DataAccessException.class,BusinessException.class})
	public void closeRound() throws BusinessException {
		List<ChampionLeagueMatchEntity> championLeagueMatchEntities = championLeagueMatchRepo.findByMatchWinner(null);
		if (championLeagueMatchEntities != null && championLeagueMatchEntities.size() != 0) {
			throw new BusinessException("Cannot close exist Round before submit result of every match in round");
		}
		List<ChampionLeagueMatchEntity> firstRoundMatches = championLeagueMatchRepo.findAll();
		if(firstRoundMatches==null || firstRoundMatches.size()==0) {
			throw new BusinessException("there is no round to close");
		}
		List<ChampionClosedRound> closed = firstRoundMatches.stream().map(row -> {
			ChampionClosedRound championClosedRound = new ChampionClosedRound();
			BeanUtils.copyProperties(row, championClosedRound);
			return championClosedRound;
		}).collect(Collectors.toList());
		closedRoundRepo.saveAll(closed);
		championLeagueMatchRepo.deleteAll();
	}
}
