package com.ssafy.heypapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.Carecenter;

@Repository
public interface CarecenterRepository extends JpaRepository<Carecenter, Long> {

}
