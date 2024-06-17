package pl.jz.webapp.shell.command.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;

//@Component
public class ToDoShellPromptProvider implements PromptProvider {

    /**
     * @return
     */
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("shell>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
