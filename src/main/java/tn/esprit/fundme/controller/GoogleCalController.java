package tn.esprit.fundme.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


import com.fasterxml.jackson.databind.ObjectMapper;
 
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;






@RestController
public class GoogleCalController {
    
  //   private final static Log logger = LogFactory.getLog(GoogleCalController.class);
 	private static final String APPLICATION_NAME = "serviceCal";
 //	private static HttpTransport httpTransport;
 	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
 	private static com.google.api.services.calendar.Calendar client;
 	
 URL url = getClass().getResource("/auth.p12");
 File keyFile=new File(url.getPath());
 	GoogleClientSecrets clientSecrets;
 	GoogleAuthorizationCodeFlow flow;
 	Credential credential;
 	
 	HttpTransport TRANSPORT ;
 	private String SERVICE_ACCOUNT="ak47-125@alpine-surge-344813.iam.gserviceaccount.com";
 	 	
 	 	

 	

  	private Set<Event> events = new HashSet<>();

 	

 	public void setEvents(Set<Event> events) {
 		this.events = events;
 	}

 	public Set<Event> getEvents() throws IOException {
 		return this.events;
 	}



 	@RequestMapping(value = "/add", method = RequestMethod.GET)
 	public ResponseEntity<String> oauth2Callback(HttpServletRequest request) throws IOException, GeneralSecurityException {
 		com.google.api.services.calendar.model.Events eventList;
 		String message;
 		
// 			TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
// 			credential = flow.createAndStoreCredential(response, "userID"); //for Oauth2
 		Preconditions.checkArgument( !Strings.isNullOrEmpty(APPLICATION_NAME ),
 			      "applicationName cannot be null or empty!" );
 			try {
 			 	TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
 			 	
 			
 			credential = new GoogleCredential.Builder().setTransport(TRANSPORT)
 		            .setJsonFactory(JSON_FACTORY)
 		            .setServiceAccountId(SERVICE_ACCOUNT)
 		            .setServiceAccountScopes(Collections.singleton(CalendarScopes.CALENDAR))
 		            .setServiceAccountPrivateKeyFromP12File(keyFile)
 		            
 		            .build();
 			credential.refreshToken();
 			client = new com.google.api.services.calendar.Calendar.Builder(TRANSPORT, JSON_FACTORY, credential)
 					.setApplicationName(APPLICATION_NAME).build();
 			 
 			System.out.println(client);
  			Events events = client.events();
 			eventList = events.list("primary").execute();
 			message = eventList.getItems().toString();
 			Event event = new Event()
 				    .setSummary("Google I/O 2015")
 				    .setLocation("800 Howard St., San Francisco, CA 94103")
 				    .setDescription("A chance to hear more about Google's developer products.");

 				DateTime startDateTime = new DateTime("2022-05-28T09:00:00-07:00");
 				EventDateTime start = new EventDateTime()
 				    .setDateTime(startDateTime)
 				    .setTimeZone("Africa/Tunis");
 				event.setStart(start);

 				DateTime endDateTime = new DateTime("2022-08-01T17:00:00-07:00");
 				EventDateTime end = new EventDateTime()
 				    .setDateTime(endDateTime)
 				    .setTimeZone("Africa/Tunis");
 				event.setEnd(end);

 				String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
 				event.setRecurrence(Arrays.asList(recurrence));
//			EventAttendee[] attendees = new EventAttendee[] {
// 				    new EventAttendee().setEmail("greatgatsbylala18@gmail.com"),
//				    new EventAttendee().setEmail("himanshuranjan3030@gmail.com"),
//				};
//		event.setAttendees(Arrays.asList(attendees));

 				EventReminder[] reminderOverrides = new EventReminder[] {
 				    new EventReminder().setMethod("email").setMinutes(24 * 60),
 				    new EventReminder().setMethod("popup").setMinutes(10),
 				};
 
 				Event.Reminders reminders = new Event.Reminders()
 				    .setUseDefault(false)
 				    .setOverrides(Arrays.asList(reminderOverrides));
 				event.setReminders(reminders);

 				String calendarId = "primary";
 
 
 				event = client.events().insert("emna.bentaher@esprit.tn", event).execute();
 	 			 
 				System.out.printf("Event created: %s\n", event.getHtmlLink());
 		

 		System.out.println("cal message:" + message);
 		return new ResponseEntity<>(message, HttpStatus.OK);
 			}catch(IOException e)
			 	{
			 		System.out.println(e);
			 	}
 			return new ResponseEntity<>("Nothing", HttpStatus.OK);
 	}

 	
}