package com.ForgeEssentials.commands;

import com.ForgeEssentials.core.PlayerInfo;
import com.ForgeEssentials.core.commands.ForgeEssentialsCommandBase;
import com.ForgeEssentials.util.Localization;
import com.ForgeEssentials.util.OutputHandler;
import com.ForgeEssentials.util.AreaSelector.Point;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;

public class CommandHome extends ForgeEssentialsCommandBase
{

	@Override
	public String getCommandName()
	{
		return "home";
	}

	@Override
	public void processCommandPlayer(EntityPlayer sender, String[] args)
	{
		if (args.length >= 1 && args[0].equals("here"))
			PlayerInfo.getPlayerInfo(sender).home = new Point((int) sender.posX, (int) sender.posY, (int) sender.posZ);
		else if (args.length >= 3)
		{
			try
			{
				PlayerInfo.getPlayerInfo(sender).home = new Point(new Integer(args[0]), new Integer(args[1]), new Integer(args[2]));
			} catch (NumberFormatException e)
			{
				OutputHandler.chatError(sender, Localization.get("message.error.nan"));
			}
		} else
		{
			Point home = PlayerInfo.getPlayerInfo(sender).home;
			if (home == null)
				OutputHandler.chatError(sender, Localization.get("message.error.nohome") + getSyntaxPlayer(sender));
			else
				((EntityPlayerMP) sender).playerNetServerHandler.setPlayerLocation(home.x, home.y, home.z, sender.rotationYaw, sender.rotationPitch);
		}
	}

	@Override
	public void processCommandConsole(ICommandSender sender, String[] args)
	{
	}

	@Override
	public boolean canConsoleUseCommand()
	{
		return false;
	}

	@Override
	public boolean canPlayerUseCommand(EntityPlayer player)
	{
		return true;
	}

	@Override
	public String getCommandPerm()
	{
		return "ForgeEssentials.BasicCommands." + getCommandName();
	}

}
