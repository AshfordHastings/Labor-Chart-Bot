package labor.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LaborService {

	@Autowired
	JDAService jdaService;
	
	@Autowired 
	DBService dbService;
	
	@Autowired
	CommandService commandService;
	
	@Autowired
	NotifierService notifierService;

	public JDAService getJdaService() {
		return jdaService;
	}

	public CommandService getCommandService() {
		return commandService;
	}
	
	public DBService getDBService() {
		return dbService;
	}
	
	public NotifierService getNotifierService() {
		return notifierService;
	}
	
	
}
