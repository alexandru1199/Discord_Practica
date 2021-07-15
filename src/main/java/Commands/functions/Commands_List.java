package commands.functions;


import commands.*;
import commands.verify.AddRoleVerify;
import commands.verify.Verify;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Commands_List {
    public static List<ListenerAdapter> ListaComenzi() {


        List<ListenerAdapter> list = new ArrayList<>();
        list.add(new Verify());

        list.add(new Generate_VoiceChannel());
        list.add(new Help());
        list.add(new Change_Prefix());
        list.add(new Show_Prefix());
        list.add(new Take_Project());
        list.add(new Show_Projects());
        list.add(new AddRoleVerify());
        list.add(new Curs());
        return list;
    }

    public static List<ICommand> ListaComenzi2() {


        List<ICommand> list = new ArrayList<>();
        list.add(new Help());
        list.add(new Verify());
        list.add(new Generate_VoiceChannel());
        list.add(new AddRoleVerify());
        list.add(new Curs());
        return list;
    }
}
