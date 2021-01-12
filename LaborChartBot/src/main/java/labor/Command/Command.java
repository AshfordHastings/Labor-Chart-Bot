package labor.Command;

import labor.Service.LaborService;
import labor.Util.DiscordOutput;
import net.dv8tion.jda.api.entities.Message;

public interface Command {
	public void execute(Message message, DiscordOutput discordOutput, LaborService laborService);
}
