package com.ssafy.heypapa.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ssafy.heypapa.response.CarecenterResponse;

public interface ICarecenterService {

	List<CarecenterResponse> getAllCarecenter(Pageable pageable);
	
}
