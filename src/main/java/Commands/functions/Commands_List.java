package commands.functions;


import commands.*;
import commands.courses_classes.Courses;
import commands.courses_classes.ShowCourses;
import commands.interfaces.ICommand;
import commands.prefix_classes.Change_Prefix;
import commands.prefix_classes.Show_Prefix;
import commands.verify_classes.AddRoleVerify;
import commands.verify_classes.Verify;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Commands_List {
    //used for the bot to get all the commands
    public static List<ListenerAdapter> CommandListRun() {
        List<ListenerAdapter> list = new ArrayList<>();
        list.add(new Verify());
        list.add(new Help());
        list.add(new Change_Prefix());
        list.add(new Show_Prefix());
        list.add(new AddRoleVerify());
        list.add(new Courses());
        list.add(new ShowCourses());
        list.add(new Ping());
        list.add(new BotReady());
        return list;
    }
//used to get information for the help command
    public static List<ICommand> CommandListInfo() {

        List<ICommand> list = new ArrayList<>();
        list.add(new Help());
        list.add(new Verify());
        list.add(new Change_Prefix());
        list.add(new AddRoleVerify());
        list.add(new Courses());
        list.add(new Show_Prefix());
        list.add(new ShowCourses());
        list.add(new Ping());
        return list;
    }
}
