package Exceptions;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by IntelliJ IDEA.
 * User: Мария
 * Date: 01.06.12
 * Time: 2:07
 * To change this template use File | Settings | File Templates.
 */
public class WrongDataException extends Throwable {
    public WrongDataException(){};
    public WrongDataException(String gripe){
        super(gripe);
    }


}
