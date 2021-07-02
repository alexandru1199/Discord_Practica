package commands;

import java.util.List;

public interface ICommand {
    String getName();
    default List<String> getAlliases(){
        return List.of();
    };
}
