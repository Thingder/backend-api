package com.redcutlery.thingder.module.naver.dto.SMSRespose;

import lombok.Data;

@Data
public class SMSResponse{
	private String requestId;
	private String requestTime;
	private String statusCode;
	private String statusName;
}