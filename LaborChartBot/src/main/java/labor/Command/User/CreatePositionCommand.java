package labor.Command.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import labor.Command.Command;
import labor.Command.CommandInfo;
import labor.Entity.Position;
import labor.Service.LaborService;
import labor.Util.DiscordOutput;
import net.dv8tion.jda.api.entities.Message;

public class CreatePositionCommand implements Command {
	
	@CommandInfo(name="create_position", expectedArgs=6)
	@Override
	public void execute(Message message, DiscordOutput discordOutput, LaborService laborService) {
		// TODO Auto-generated method stub
		List<String> messageParsed = Stream.of(message.getContentDisplay().split(" "))
				.map(str -> new String(str))
				.collect(Collectors.toList());
		
		String id = messageParsed.get(1);
		String name = messageParsed.get(2);
		String time = messageParsed.get(3);
		String labordays = messageParsed.get(4);
		String length = messageParsed.get(5);
		String numSlots = messageParsed.get(6);
		
		Position newPosition = new Position(id, name, time, labordays, Integer.valueOf(length), Integer.valueOf(numSlots));
		laborService.getDBService().postPosition(newPosition);
		
		Position position = laborService.getDBService().findPositionById(id);
		
		String returnMessage = new String();
		returnMessage = returnMessage.concat("New Position has been saved to the database:" + '\n' + position);
		discordOutput.sendMessage(returnMessage);
	}
	
}
