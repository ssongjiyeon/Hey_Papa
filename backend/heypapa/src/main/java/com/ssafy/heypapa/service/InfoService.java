package com.ssafy.heypapa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.Info;
import com.ssafy.heypapa.repository.InfoRepository;
import com.ssafy.heypapa.response.InfoResponse;

@Service("InfoService")
public class InfoService implements IInfoService {

	@Autowired
	InfoRepository infoRepository;

	@Override
	public List<InfoResponse> getAllInfo() {
		List<Info> list = infoRepository.findAll();
		List<InfoResponse> copy = new ArrayList<>();
		InfoResponse ires;
		for(Info i : list) {
			ires = new InfoResponse();
			ires.setId(i.getId());
			ires.setBaby(i.getBaby());
			ires.setMom(i.getMom());
//			ires.setBaby_img(i.getBaby_img());
//			ires.setMom_img(i.getMom_img());
			copy.add(ires);
		}
		return copy;
	}
	
	
}
