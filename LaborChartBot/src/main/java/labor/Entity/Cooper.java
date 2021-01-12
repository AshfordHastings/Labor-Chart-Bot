package labor.Entity;


import net.dv8tion.jda.api.entities.Member;

public class Cooper {

	private Long id;
	private String username;
	private String discordTag;
	
	public Cooper(Member member) {
		this.username = member.getUser().getAsTag();
		this.discordTag = member.getAsMention();
	}
	
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getDiscordTag() {
		return discordTag;
	}
	
	
}
