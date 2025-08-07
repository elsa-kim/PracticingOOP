package bridge;

import java.util.ArrayList;
import java.util.List;

public class ChoicesResponse {
    private final List<String[]> choices;

    private ChoicesResponse(List<String[]> choices) {
        this.choices = choices;
    }

    public static ChoicesResponse generate(List<String[]> response) {
        return new ChoicesResponse(response);
    }

    public List<String> getTop(){
        List<String> topside = new ArrayList<>();
        for(String[] choice : choices){
            if(choice[0].equals(Direction.DOWN.toString())){
                topside.add(" ");
            }else{
                topside.add(choice[1]);
            }
        }
        return topside;
    }

    public List<String> getBottom(){
        List<String> bottom = new ArrayList<>();
        for(String[] choice : choices){
            if(choice[0].equals(Direction.UP.toString())){
                bottom.add(" ");
            }else{
                bottom.add(choice[1]);
            }
        }
        return bottom;
    }

    public int getSize(){
        return choices.size();
    }
}
