package com.ssafy.heypapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.heypapa.response.CarecenterResponse;
import com.ssafy.heypapa.service.CarecenterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "산후조리원 정보", tags = { "Carecenter" })
@RestController
@RequestMapping("/Carecenter")
public class CarecenterController {

	@Autowired
	private CarecenterService service;
	
	@GetMapping()
	@ApiOperation(value = "산후조리원 정보", notes = "<strong>산후조리원 정보</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<CarecenterResponse>> getAllCarecenter(Pageable pageable) {
		List<CarecenterResponse> carecenterList = service.getAllCarecenter(pageable);
		return ResponseEntity.status(200).body(carecenterList);
	}
	
}
