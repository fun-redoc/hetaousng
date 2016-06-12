package rsh.spring;

import org.springframework.data.rest.core.annotation.*;


@RepositoryEventHandler(Person.class)
public class AppEventHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(Person customer) {
        System.out.println("Inside handleBeforeCreate ....");
    }

    @HandleBeforeSave
    public void handleBeforeSave(Person customer) {
        System.out.println("Inside handleBeforeSave ....");
    }

    @HandleAfterSave
    public void handleAfterSave(Person customer) {
        System.out.println("Inside handleAfterSave ....");
    }

}
