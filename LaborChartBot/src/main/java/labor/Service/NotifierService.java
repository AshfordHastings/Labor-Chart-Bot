package labor.Service;

import java.net.URI;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.*;
import com.launchdarkly.eventsource.EventSource;

import labor.Util.NotifierEventHandler;
//
@Service
public class NotifierService{
	
	@Autowired
	LaborService laborService;
	
	public void subscribe() {
		EventHandler eventHandler = new NotifierEventHandler();
		String url = "http://localhost:8090/sse/laborUpdates";
		EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
		builder.connectTimeout(Duration.ofHours(2));
		EventSource eventSource = builder.build();
		eventSource.start();
	}
}
