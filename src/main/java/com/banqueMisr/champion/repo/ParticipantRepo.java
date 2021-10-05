package com.banqueMisr.champion.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banqueMisr.champion.entity.ParticipantEntity;

@Repository
public interface ParticipantRepo extends JpaRepository<ParticipantEntity, Long>{

}
