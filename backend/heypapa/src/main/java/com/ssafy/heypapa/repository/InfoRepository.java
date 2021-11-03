package com.ssafy.heypapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.Info;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {

}
