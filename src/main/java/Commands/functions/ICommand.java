package commands.functions;

import java.util.List;

public interface ICommand {
     String getInfo();
    default List<String> getAlliases(){
        return List.of();
    };

}
