package labor.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import labor.Command.Command;
import labor.Command.CommandInfo;
import labor.Util.DiscordOutput;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

@Service
public class CommandService {

	@Autowired
	LaborService laborService;
	
	public void addEventListeners(Command... commands) {
		addEventListeners(Arrays.asList(commands));
	}
	
	// Creates SubscribeEvents to be stored as EventHandlers 
	// EventListener constructed from command methods annotated with CommandInfo.class
	public void addEventListeners(List<Command> commands) {
		for(Command command : commands) {
			Method[] methods = command.getClass().getMethods();
			
			for(Method method : methods) {
				CommandInfo info;
				try {
					info = method.getAnnotation(CommandInfo.class);
				} catch(NullPointerException e) {
					continue;
				}
				if(info==null) {continue;}
				
				System.out.println("Preparing to add command : " + info.name());
				
				// EventHandler called upon MessageReceivedEvent
				this.laborService.getJdaService().getJDA().addEventListener(new AnnotatedEventManager() {
					@SubscribeEvent
					public void registerCommand(MessageReceivedEvent event) {
						Message message = event.getMessage();
						String messageString = message.getContentDisplay();
						
						if(!messageString.startsWith("!")) {
							return;
						}
						
						//TODO: Does map need to be here?
						List<String> messageParsed = Stream.of(messageString.split(" "))
								.map(str -> new String(str))
								.collect(Collectors.toList());
						
						if(!messageParsed.get(0).substring(1).equals(info.name())) {
							return;
						}
						
						
						DiscordOutput responseOutput = new DiscordOutput(event.getChannel());
						if(info.expectedArgs() != -1 && info.expectedArgs() != messageParsed.size() - 1) {
							responseOutput.sendMessage("Unexpected arguement count! Expected argument count is:" + info.expectedArgs() + ".");
							return;
						}
						
						// Command's execute() method called, specific to each command
						command.execute(message, responseOutput, laborService);
					}
				});
				System.out.println("New command added: " + info.name());
			}
		}
	}
}
