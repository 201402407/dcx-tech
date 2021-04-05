package dcx.lpoint.tran;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.primitives.Bytes;

import dcx.comn.exception.TranTypeException;
import dcx.comn.util.TranUtils;
import dcx.lpoint.prop.DataType;

/**
 * <p>총 77byte</p>
 */
public class TranHeader implements TranSerializer, TranDeserializer {
	private static AtomicInteger count = new AtomicInteger(0);

	// 헤더타입(1) - "Z” : 모든 온라인 전문
	private String headerType = "Z";

	// 전문번호(4) - 대분류(2)+중분류(1)+소분류(1), 전문번호체계 참조
	private String tranNo;

	// 기관코드(4) - 대분류(3)+소분류(1), 기관코드체계 참조
	private String agencyCode = "I500";

	// 전송일자(8) - 년월일(YYYYMMDD)
	private String sendDate;

	// 전송시간(6) - 시분초(hhmmss)
	private String sendTime;

	// 추적번호(22) - 전문번호(4)+기관코드(4)+거래일자(8)에 UNIQUE
	private String trackingNo;

	// 전문구분(2) - "ON" : Online, "OB" : Online Batch, "BT" : 배치
	private String tranType;

	// 응답코드(2) - "  " : 요청, "00" : 정상, 응답코드 참조
	private String responseCode;

	// 데이터 크기(4) - 데이터 부분의 크기(0000 ~ 9999)
	private int bodyLengh = 0;

	// 시스템사용(20) - 반드시 공백으로 20 byte를 채움
	@JsonIgnore
	private String filler;

	private String responseMessage;

	public TranHeader() {
		this.headerType = "Z";
	}

	public TranHeader(String tranNo) {
		this.tranNo = tranNo;
		this.agencyCode = "I500";
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
		this.sendDate = dateFormat.format(now);
		this.sendTime = timeFormat.format(now);
		this.trackingNo = tranNo + agencyCode + sendDate;
		this.tranType = "ON";
	}

	public void setTrackingNoSuffix(String suffix) {
		this.trackingNo = trackingNo + suffix;
	}

	public String getHeaderType() {
		return headerType;
	}

	public void setHeaderType(String headerType) {
		this.headerType = headerType;
	}

	public String getTranNo() {
		return tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public int getBodyLengh() {
		return bodyLengh;
	}

	public void setBodyLengh(int bodyLengh) {
		this.bodyLengh = bodyLengh;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage() {

		switch (this.responseCode) {
			case "00": this.responseMessage = "정상"; break;
			case "22": this.responseMessage = "시스템 장애";break;
			case "44": this.responseMessage = "승인 거절";break;
			case "77": this.responseMessage = "전문 오류";break;
			case "80": this.responseMessage = "DB 미등록";break;
			case "88": this.responseMessage = "운영사 DBMS 장애";break;
			case "92": this.responseMessage = "[92] Control 오류";break;
			case "99": this.responseMessage = "[99] Control 오류";break;
			default: this.responseMessage = "응답코드 없음";break;
		}
	}

	@Override
	public byte[] serialize() throws TranTypeException {
		return Bytes.concat(
			TranUtils.getBytes(this.headerType, DataType.AN, 1)
			, TranUtils.getBytes(this.tranNo, DataType.AN, 4)
			, TranUtils.getBytes(this.agencyCode, DataType.AN, 4)
			, TranUtils.getBytes(this.sendDate, DataType.AN, 8)
			, TranUtils.getBytes(this.sendTime, DataType.AN, 6)
			, TranUtils.getBytes(this.trackingNo, DataType.AN, 22)
			, TranUtils.getBytes(this.tranType, DataType.A, 2)
			, TranUtils.getBytes(this.responseCode, DataType.AN, 2)
			, TranUtils.getBytes(this.bodyLengh, DataType.N, 4)
			, TranUtils.getBytes(this.filler, DataType.A, 20)
		);
	}

	@Override
	public void deserialize(byte[] bytes) throws Exception {
		byte[] header = new byte[73];
		System.arraycopy(bytes, 2, header, 0, 73 );
		int pos = 0;
		byte[] headerTypeBytes = 		new byte[1];
		byte[] tranNoBytes = 			new byte[4];
		byte[] agencyCodeBytes = 		new byte[4];
		byte[] sendDateBytes = 			new byte[8];
		byte[] sendTimeBytes = 			new byte[6];
		byte[] trackingNoBytes = 		new byte[22];
		byte[] tranTypeBytes = 			new byte[2];
		byte[] responseCodeBytes = 		new byte[2];
		byte[] bodyLenghBytes = 		new byte[4];

		System.arraycopy(header, pos, headerTypeBytes, 0, 1);			pos = pos + 1;
		System.arraycopy(header, pos, tranNoBytes, 0, 4);				pos = pos + 4;
		System.arraycopy(header, pos, agencyCodeBytes, 0, 4);			pos = pos + 4;
		System.arraycopy(header, pos, sendDateBytes, 0, 8);			pos = pos + 8;
		System.arraycopy(header, pos, sendTimeBytes, 0, 6);			pos = pos + 6;
		System.arraycopy(header, pos, trackingNoBytes, 0, 22);			pos = pos + 22;
		System.arraycopy(header, pos, tranTypeBytes, 0, 2);			pos = pos + 2;
		System.arraycopy(header, pos, responseCodeBytes, 0, 2);		pos = pos + 2;
		System.arraycopy(header, pos, bodyLenghBytes, 0, 4);			pos = pos + 4;

		this.setHeaderType(new String(headerTypeBytes, "UTF-8"));
		this.setTranNo(new String(tranNoBytes, "UTF-8"));
		this.setAgencyCode(new String(agencyCodeBytes, "UTF-8"));
		this.setSendDate(new String(sendDateBytes, "UTF-8"));
		this.setSendTime(new String(sendTimeBytes, "UTF-8"));
		this.setTrackingNo(new String(trackingNoBytes, "UTF-8"));
		this.setTranType(new String(tranTypeBytes, "UTF-8"));
		this.setResponseCode(new String(responseCodeBytes, "UTF-8"));
		try {
			this.setBodyLengh(Integer.parseInt(new String(bodyLenghBytes, "UTF-8")));
		} catch (NumberFormatException nfe) {
			// ignore
			this.bodyLengh = 0;
		}
		setResponseMessage();
		//this.setFiller(new String(fillerBytes, "utf-8"));
	}
}
