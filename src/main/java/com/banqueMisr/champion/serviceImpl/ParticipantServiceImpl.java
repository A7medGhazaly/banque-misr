package com.banqueMisr.champion.serviceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banqueMisr.champion.dto.ChampionGroupDTO;
import com.banqueMisr.champion.dto.ChampionGroupParticipantCountDTO;
import com.banqueMisr.champion.dto.ParticipantDTO;
import com.banqueMisr.champion.dto.ParticipantRequestDTO;
import com.banqueMisr.champion.entity.ChampionGroupEntity;
import com.banqueMisr.champion.entity.ParticipantEntity;
import com.banqueMisr.champion.exception.DataNotFoundException;
import com.banqueMisr.champion.repo.ParticipantRepo;
import com.banqueMisr.champion.service.ChampionGroupService;
import com.banqueMisr.champion.service.ParticipantService;
@Service
public class ParticipantServiceImpl implements ParticipantService {
	@Autowired
	ChampionGroupService championGroupService;
	@Autowired
	ParticipantRepo participantRepo;
	@Override
	public ParticipantDTO addNewParticipant(ParticipantRequestDTO participantRequestDTO) throws DataNotFoundException {
		List<ChampionGroupParticipantCountDTO> countDTOs = championGroupService.getGroupParticipantsCount();
		countDTOs = countDTOs.stream().sorted(Comparator.comparingLong(ChampionGroupParticipantCountDTO::getParticipantNumber))
				.collect(Collectors.toList());
		ParticipantEntity entity=new ParticipantEntity();
		entity.setEnglishName(participantRequestDTO.getParticipantName());
		entity.setEmail(participantRequestDTO.getEmail());
		entity.setChampionGroup(new ChampionGroupEntity(countDTOs.get(0).getGroupId(),countDTOs.get(0).getGroupName()));
		ParticipantEntity participantEntity=participantRepo.save(entity);
		ParticipantDTO participantDTO=new ParticipantDTO();
		BeanUtils.copyProperties(participantEntity, participantDTO);
		ChampionGroupDTO groupDTO=new ChampionGroupDTO();
		BeanUtils.copyProperties(participantEntity.getChampionGroup(), groupDTO);
		participantDTO.setChampionGroupDTO(groupDTO);
		return participantDTO;
	}
	
	@Override
	public List<ParticipantDTO> getAllParticipants() throws DataNotFoundException{
		List<ParticipantEntity>  entities=participantRepo.findAll();
		if(entities!=null && entities.size()>0) {
			List<ParticipantDTO> dtos= entities.stream().map(row->{
				ParticipantDTO participantDTO=new ParticipantDTO();
				ChampionGroupDTO groupDTO=new ChampionGroupDTO();
				BeanUtils.copyProperties(row.getChampionGroup(), groupDTO);
				BeanUtils.copyProperties(row, participantDTO);
				participantDTO.setChampionGroupDTO(groupDTO);
				return participantDTO;
			}).collect(Collectors.toList());
			return dtos;
		}else {
			throw new DataNotFoundException("There is no participants");
		}
	}
}
