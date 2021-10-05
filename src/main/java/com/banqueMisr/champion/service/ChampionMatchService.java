package com.banqueMisr.champion.service;

import org.springframework.stereotype.Service;

import com.banqueMisr.champion.dto.ChampionLeagueDTO;
import com.banqueMisr.champion.dto.ChampionLeagueMatchDTO;
import com.banqueMisr.champion.dto.SubmitLeagueWinnerRequestDTO;
import com.banqueMisr.champion.dto.SubmitMatchWinnerRequestDTO;
import com.banqueMisr.champion.exception.BusinessException;
import com.banqueMisr.champion.exception.DataNotFoundException;

@Service
public interface ChampionMatchService {

	ChampionLeagueDTO startLeagueMatch() throws DataNotFoundException, BusinessException;

	ChampionLeagueMatchDTO submitMatchWinner(SubmitMatchWinnerRequestDTO dto) throws DataNotFoundException, BusinessException;

	void closeRound() throws BusinessException;

	ChampionLeagueDTO submitLeagueWinner(SubmitLeagueWinnerRequestDTO dto) throws DataNotFoundException, BusinessException;

}
