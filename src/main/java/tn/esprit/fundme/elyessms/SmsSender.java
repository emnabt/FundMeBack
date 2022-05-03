package tn.esprit.fundme.elyessms;

import tn.esprit.fundme.entities.User;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

	void sendSmsdeux(String msg, User a);
	void sendSmsmsg(String msg);


    // or maybe void sendSms(String phoneNumber, String message);
}