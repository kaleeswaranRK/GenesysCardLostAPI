package com.scb.Model;

import org.springframework.stereotype.Component;

@Component
public class CardDetails {
private String cardNum;
private String relID;
public String getCardNum() {
	return cardNum;
}
public void setCardNum(String cardNum) {
	this.cardNum = cardNum;
}
public String getRelID() {
	return relID;
}
public void setRelID(String relID) {
	this.relID = relID;
}



}
