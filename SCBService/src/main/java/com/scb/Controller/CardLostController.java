package com.scb.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.Model.CardDetails;
import com.scb.Model.CardLostResponseData;
import com.scb.Model.cardLost_Res;

@RestController
@RequestMapping("/cardLostUpdate")
public class CardLostController {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<?> setCardLost(@RequestBody CardDetails cardDetails) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cardLost_Res cardLostres = new cardLost_Res();
		String startDate = dateTimeFormat.format(new Date());
		String response = "";
		try {
			String cardNum = cardDetails.getCardNum();
			String relID = cardDetails.getRelID();
			if (cardNum != null && cardNum.length() == 16) {
				if (relID != null && !(relID.isEmpty())) {
					response = new String(Files.readAllBytes(
							Paths.get("D:/Subbu/host/BH/DummyResponse/RKALEES/setCardLost/" + cardNum + ".json")));
				}
			}
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			CardLostResponseData dataObjects = objectMapper.readValue(response, CardLostResponseData.class);
			cardLostres.setErrorcode(String.valueOf(HttpStatus.OK.value()));
			cardLostres.setErrormessage("success");
			cardLostres.setMessageId(String.valueOf(HttpStatus.OK.value()));
			cardLostres.setResponse(dataObjects);
			System.out.println(cardLostres);
		} catch (IOException e) {
			System.out.println(e);
			cardLostres.setStartTime(startDate);
			cardLostres.setEndTime(dateTimeFormat.format(new Date()));
			cardLostres.setErrorcode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			cardLostres.setMessageId(String.valueOf(HttpStatus.NOT_FOUND.value()));
			cardLostres.setErrormessage(e.getMessage());
			return new ResponseEntity<>(cardLostres,HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println(e);
			cardLostres.setStartTime(startDate);
			cardLostres.setEndTime(dateTimeFormat.format(new Date()));
			cardLostres.setErrorcode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()));
			cardLostres.setErrormessage(e.getMessage());
			cardLostres.setMessageId(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()));
			return new ResponseEntity<>(cardLostres,HttpStatus.EXPECTATION_FAILED);
		}
		cardLostres.setStartTime(startDate);
		cardLostres.setEndTime(dateTimeFormat.format(new Date()));
		return new ResponseEntity<>(cardLostres,HttpStatus.OK);
	}
}
