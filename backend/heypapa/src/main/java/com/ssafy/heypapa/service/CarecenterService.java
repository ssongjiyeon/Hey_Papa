package com.ssafy.heypapa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.Carecenter;
import com.ssafy.heypapa.repository.CarecenterRepository;
import com.ssafy.heypapa.response.CarecenterResponse;

@Service("CarecenterService")
public class CarecenterService implements ICarecenterService {

	@Autowired
	CarecenterRepository carecenterRepository;

	@Override
	public List<CarecenterResponse> getAllCarecenter() {
		List<Carecenter> list = carecenterRepository.findAll();
		List<CarecenterResponse> copy = new ArrayList<>();
		CarecenterResponse cres;
		for(Carecenter c: list) {
			cres = new CarecenterResponse();
			cres.setId(c.getId());
			cres.setSido(c.getSido());
			cres.setSigungu(c.getSigungu());
			cres.setName(c.getName());
			cres.setAddress(c.getAddress());
			cres.setPhoneNumber(c.getPhoneNumber());
			cres.setGeneralRoom(c.getGeneralRoom());
			cres.setSpecialRoom(c.getSpecialRoom());
			cres.setLongitude(c.getLongitude());
			cres.setLatitude(c.getLatitude());
			copy.add(cres);
		}
		return copy;
	}
	
	
}
