package com.scb.Model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class cardLost_Res extends CommonOutput{
	private CardLostResponseData response;

	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public CardLostResponseData getResponse() {
		return response;
	}


	public void setResponse(CardLostResponseData response) {
		this.response = response;
	}
}
