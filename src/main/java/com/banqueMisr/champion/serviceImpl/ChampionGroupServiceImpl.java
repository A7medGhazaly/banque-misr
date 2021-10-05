package com.banqueMisr.champion.serviceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banqueMisr.champion.dto.ChampionGroupDTO;
import com.banqueMisr.champion.dto.ChampionGroupParticipantCountDTO;
import com.banqueMisr.champion.entity.ChampionGroupEntity;
import com.banqueMisr.champion.exception.DataNotFoundException;
import com.banqueMisr.champion.repo.ChampionGroupRepo;
import com.banqueMisr.champion.service.ChampionGroupService;
@Service
public class ChampionGroupServiceImpl implements ChampionGroupService {
	@Autowired
	ChampionGroupRepo groupRepo;

	@Override
	public List<ChampionGroupDTO> findChampionGroups() throws DataNotFoundException {
		List<ChampionGroupEntity> entities = groupRepo.findAll();
		if (entities != null && entities.size() > 0) {
			return entities.stream().map(row -> {
				ChampionGroupDTO dto = new ChampionGroupDTO();
				BeanUtils.copyProperties(row, dto);
				return dto;
			}).collect(Collectors.toList());
		} else {
			throw new DataNotFoundException("Cannot find Group Champion");
		}
	}
	@Override
	public List<ChampionGroupParticipantCountDTO> getGroupParticipantsCount() throws DataNotFoundException {
		List<Object[]> dataSet = groupRepo.getGroupPartitcipantCount();
		if (dataSet != null && dataSet.size() > 0) {
			return dataSet.stream().map(row -> {
				ChampionGroupParticipantCountDTO championGroupParticipantCountDTO = new ChampionGroupParticipantCountDTO();
				championGroupParticipantCountDTO.setGroupId(((Integer) row[0]).longValue());
				championGroupParticipantCountDTO.setGroupName(row[1].toString());
				championGroupParticipantCountDTO.setParticipantNumber(((BigInteger) row[2]).longValue());
				return championGroupParticipantCountDTO;
			}).collect(Collectors.toList());
		} else {
			throw new DataNotFoundException("Cannot find Group Champion");
		}
	}

}
