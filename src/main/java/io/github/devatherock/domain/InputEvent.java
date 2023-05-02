package io.github.devatherock.domain;

import java.time.ZonedDateTime;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputEvent {
	private String summary;
	private String location;
	private String description;
	private EventDateAndTimeZone start;
	private EventDateAndTimeZone end;
	
	@Getter
	@Setter
	public static class EventDateAndTimeZone {
		private ZonedDateTime dateTime;
		private String timeZone;
	}
	
	public Event toCalendarEvent() {
		Event calendarEvent = new Event();
    	calendarEvent.setSummary(summary);
    	calendarEvent.setLocation(location);
    	calendarEvent.setDescription(description);
    	
    	EventDateTime startDateTime = new EventDateTime();
    	startDateTime.setDateTime(new DateTime(start.dateTime.toEpochSecond() * 1000));
    	startDateTime.setTimeZone(start.timeZone);
    	calendarEvent.setStart(startDateTime);
    	
    	EventDateTime endDateTime = new EventDateTime();
    	endDateTime.setDateTime(new DateTime(end.dateTime.toEpochSecond() * 1000));
    	endDateTime.setTimeZone(end.timeZone);
    	calendarEvent.setEnd(endDateTime);
    	
    	return calendarEvent;
	}
}
