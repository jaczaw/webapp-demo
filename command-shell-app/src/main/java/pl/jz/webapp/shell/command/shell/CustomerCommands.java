package pl.jz.webapp.shell.command.shell;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;


@Command(group = "Customer Commands")
public class CustomerCommands {


@Command(command="find-customer",
 description="Finds a Customer By Id")
public String findCustomer(@Option(required = true,
description = "The customer id") Long id) {
return "Tekst";
}
}
