package pl.jz.webapp.shell.command.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class ToDoShellPromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("command-app-shell>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
