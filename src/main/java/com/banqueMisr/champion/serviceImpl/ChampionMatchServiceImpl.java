package com.banqueMisr.champion.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.banqueMisr.champion.dto.ChampionGroupDTO;
import com.banqueMisr.champion.dto.ChampionLeagueDTO;
import com.banqueMisr.champion.dto.ChampionLeagueMatchDTO;
import com.banqueMisr.champion.dto.ParticipantDTO;
import com.banqueMisr.champion.dto.SubmitLeagueWinnerRequestDTO;
import com.banqueMisr.champion.dto.SubmitMatchWinnerRequestDTO;
import com.banqueMisr.champion.entity.ChampionLeagueEntity;
import com.banqueMisr.champion.entity.ChampionLeagueMatchEntity;
import com.banqueMisr.champion.entity.ParticipantEntity;
import com.banqueMisr.champion.exception.BusinessException;
import com.banqueMisr.champion.exception.DataNotFoundException;
import com.banqueMisr.champion.repo.ChampionClosedRoundRepo;
import com.banqueMisr.champion.repo.ChampionLeagueMatchRepo;
import com.banqueMisr.champion.repo.ChampionLeagueRepo;
import com.banqueMisr.champion.repo.ParticipantRepo;
import com.banqueMisr.champion.service.ChampionMatchService;
import com.banqueMisr.champion.service.EmailService;
import com.banqueMisr.champion.service.ParticipantService;

@Service
public class ChampionMatchServiceImpl implements ChampionMatchService {
	@Autowired
	ParticipantService participantService;
	@Autowired
	ChampionLeagueMatchRepo championLeagueMatchRepo;
	@Autowired
	ChampionLeagueRepo championLeagueRepo;
	@Autowired
	ChampionClosedRoundRepo closedRoundRepo;
	@Autowired
	ChampionTransactionService transactionService;
	@Autowired
	EmailService emailService;
	@Autowired
	ParticipantRepo participantRepo;

	@Override
	public ChampionLeagueDTO startLeagueMatch() throws DataNotFoundException, BusinessException {
		List<ParticipantDTO> participantDTOs = participantService.getAllParticipants();
		if (championLeagueMatchRepo.findFirstByOrderById() != null) {
			throw new BusinessException("cannot start new round before closing last one");
		}
		Map<ChampionGroupDTO, List<ParticipantDTO>> map = new HashMap<>();
		List<ChampionGroupDTO> championGroupDTOs = new ArrayList<>();
		participantDTOs.forEach(row -> {
			row.getChampionGroupDTO();
			if (map.get(row.getChampionGroupDTO()) == null) {
				List<ParticipantDTO> list = new ArrayList<>();
				list.add(row);
				map.put(row.getChampionGroupDTO(), list);
				championGroupDTOs.add(row.getChampionGroupDTO());
			} else {
				map.get(row.getChampionGroupDTO()).add(row);
			}
		});
		if (map.size() % 2 != 0) {
			throw new BusinessException("number of groups must be an even number");
		}
		List<ParticipantDTO> firstGroup = null;
		List<ParticipantDTO> secondGroup = null;
		List<ChampionLeagueMatchEntity> allMatches = new ArrayList<>();
		for (int i = 0; i < championGroupDTOs.size(); i++) {
			if (i % 2 != 0) {
				firstGroup = map.get(championGroupDTOs.get(i));
			} else {
				secondGroup = map.get(championGroupDTOs.get(i));
			}
			if (firstGroup != null && secondGroup != null) {
				allMatches.addAll(buildGroupMatch(firstGroup, secondGroup));
				firstGroup = null;
				secondGroup = null;
			}
		}
		return startLeagueMatch(allMatches);
	}

	private ChampionLeagueDTO startLeagueMatch(List<ChampionLeagueMatchEntity> allMatches) {
		ChampionLeagueEntity championLeagueEntity = null;
		championLeagueEntity = championLeagueRepo.findFirstByOrderByIdDesc();
		if (championLeagueEntity == null || championLeagueEntity.getEndsAt() != null) {
			championLeagueEntity = new ChampionLeagueEntity();
			championLeagueEntity.setStartAt(new Date());
		}
		championLeagueEntity.setMatches(allMatches);
		for (ChampionLeagueMatchEntity row : allMatches) {
			row.setLeagueMatchEntity(championLeagueEntity);
		}
		ChampionLeagueEntity result = championLeagueRepo.save(championLeagueEntity);
		ChampionLeagueDTO championLeagueDTO = new ChampionLeagueDTO();
		BeanUtils.copyProperties(result, championLeagueDTO);
		List<ChampionLeagueMatchEntity> savedMatches = championLeagueMatchRepo.findByLeagueMatchEntity(result);
		championLeagueDTO.setMatches(savedMatches.stream().map(row -> {
			ChampionLeagueMatchDTO dto = new ChampionLeagueMatchDTO();
			BeanUtils.copyProperties(row, dto);
			ParticipantDTO firsParticipantDTO = new ParticipantDTO();
			BeanUtils.copyProperties(row.getFirstParticipant(), firsParticipantDTO);
			ParticipantDTO secondParticipantDTO = new ParticipantDTO();
			BeanUtils.copyProperties(row.getSecondParticipant(), secondParticipantDTO);
			dto.setFirstParticipant(firsParticipantDTO);
			dto.setSecondParticipant(secondParticipantDTO);
			return dto;
		}).collect(Collectors.toList()));
		return championLeagueDTO;
	}

	private List<ChampionLeagueMatchEntity> buildGroupMatch(List<ParticipantDTO> firstGroup,
			List<ParticipantDTO> secondGroup) throws BusinessException {
		List<ChampionLeagueMatchEntity> championLeagueMatchEntities = new ArrayList<>();
		if (firstGroup.size() != secondGroup.size()) {
			throw new BusinessException("number of participants in every group must be equivelants");
		}
		for (int i = 0; i < firstGroup.size(); i++) {
			ChampionLeagueMatchEntity entity = new ChampionLeagueMatchEntity();
			ParticipantEntity participantDTO = new ParticipantEntity();
			participantDTO.setId(firstGroup.get(i).getId());
			entity.setFirstParticipant(participantDTO);
			participantDTO = new ParticipantEntity();
			participantDTO.setId(secondGroup.get(i).getId());
			entity.setSecondParticipant(participantDTO);
			championLeagueMatchEntities.add(entity);
		}
		return championLeagueMatchEntities;
	}

	@Override
	public ChampionLeagueMatchDTO submitMatchWinner(SubmitMatchWinnerRequestDTO dto)
			throws DataNotFoundException, BusinessException {
		ParticipantEntity first = new ParticipantEntity();
		first.setId(dto.getWinnerId());
		ParticipantEntity second = new ParticipantEntity();
		second.setId(dto.getWinnerId());
		ChampionLeagueMatchEntity entity = championLeagueMatchRepo
				.findByIdAndFirstParticipantOrSecondParticipant(dto.getMatchId(), first, second).orElseThrow(
						() -> new DataNotFoundException("Cannot find match by given id or invalid participant id"));
		if (entity.getMatchWinner() != null && entity.getMatchWinner().getId() != null) {
			throw new BusinessException("match winner already exsit");
		}
		ParticipantEntity matchWinner = new ParticipantEntity();
		matchWinner.setId(dto.getWinnerId());
		entity.setMatchWinner(matchWinner);
		ChampionLeagueMatchEntity championLeagueMatchEntity = championLeagueMatchRepo.save(entity);
		ChampionLeagueMatchDTO leagueMatchDTO = new ChampionLeagueMatchDTO();
		ParticipantDTO firstDto = new ParticipantDTO();
		BeanUtils.copyProperties(championLeagueMatchEntity.getFirstParticipant(), firstDto);
		ParticipantDTO secondDTO = new ParticipantDTO();
		BeanUtils.copyProperties(championLeagueMatchEntity.getSecondParticipant(), secondDTO);
		ParticipantDTO winner = new ParticipantDTO();
		BeanUtils.copyProperties(championLeagueMatchEntity.getMatchWinner(), winner);
		leagueMatchDTO.setFirstParticipant(firstDto);
		leagueMatchDTO.setMatchWinner(winner);
		leagueMatchDTO.setSecondParticipant(secondDTO);
		BeanUtils.copyProperties(championLeagueMatchEntity, leagueMatchDTO);
		return leagueMatchDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, DataAccessException.class,
			BusinessException.class })
	public void closeRound() throws BusinessException {
		transactionService.closeRound();
	}
	
	@Override
	public ChampionLeagueDTO submitLeagueWinner(SubmitLeagueWinnerRequestDTO dto) throws DataNotFoundException, BusinessException {
		List<ChampionLeagueMatchEntity> championLeagueMatchEntities = championLeagueMatchRepo.findByMatchWinner(null);
		if (championLeagueMatchEntities != null && championLeagueMatchEntities.size() != 0) {
			throw new BusinessException("Cannot submit league winner before close round");
		}
		ChampionLeagueEntity championLeagueEntity = championLeagueRepo.findById(dto.getLeagueId())
				.orElseThrow(() -> new DataNotFoundException("there is no league"));
		if(championLeagueEntity.getEndsAt()==null) {
			ParticipantEntity matchWinner = new ParticipantEntity();
			matchWinner.setId(dto.getWinnerId());
			championLeagueEntity.setWinner(matchWinner);
			championLeagueEntity.setEndsAt(new Date());
			ChampionLeagueEntity championLeague = championLeagueRepo.save(championLeagueEntity);
			ChampionLeagueDTO leagueDTO = new ChampionLeagueDTO();
			BeanUtils.copyProperties(championLeague, leagueDTO);
			ParticipantDTO winner = new ParticipantDTO();
			BeanUtils.copyProperties(championLeague.getWinner(), winner);
			leagueDTO.setWinner(winner);
			emailService.sendMessage(participantRepo.findById(dto.getWinnerId()).orElse(null).getEmail());
			return leagueDTO;
		}else {
			throw new BusinessException("league winner already exsit");
		}

	}

}
