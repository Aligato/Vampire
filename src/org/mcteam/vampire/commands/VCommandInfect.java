package org.mcteam.vampire.commands;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.mcteam.vampire.*;


public class VCommandInfect extends VCommand {
	
	public VCommandInfect() {
		requiredParameters = new ArrayList<String>();
		optionalParameters = new ArrayList<String>();
		senderMustBePlayer = false;
		requiredParameters.add("playername");
		optionalParameters.add("amount");
		permissions = "vampire.admin.command.infect";
		helpNameAndParams = "infect [playername] *[amount]";
		helpDescription = "Set infection. 0 to 100.";
	}
	
	@Override
	public void perform() {
		String playername = parameters.get(0);
		double amount = 1.0;
		if (parameters.size() == 2) {
			amount = Double.parseDouble(parameters.get(1));
		}
		Player player = Vampire.instance.getServer().getPlayer(playername);
		if (player == null) {
			this.sendMessage("Player not found");
			return;
		}
		VPlayer vplayer = VPlayer.get(player);
		vplayer.infectionSet(amount);
		this.sendMessage(player.getDisplayName() + " now has infection "+vplayer.infectionGet());
	}
}
