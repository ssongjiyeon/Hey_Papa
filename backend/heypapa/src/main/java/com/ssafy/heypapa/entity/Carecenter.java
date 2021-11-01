package com.ssafy.heypapa.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 산후조리원 모델 정의.
 */

@Entity
@Getter
@Setter
public class Carecenter extends BaseEntity {

	private String sido; // 시도
	private String sigungu; // 시군구
	private String name; // 산후조리원 이름
	private String address; // 주소
	private String phoneNumber; // 전화번호
	private String generalRoom; // 일반룸
	private String specialRoom; // 특실룸
	private Double longitude; // 경도
	private Double latitude; // 위도
	
}