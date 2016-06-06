package rsh.spring;

import org.springframework.data.rest.core.annotation.*;


@RepositoryEventHandler(Customer.class)
public class AppEventHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(Customer customer) {
        System.out.println("Inside handleBeforeCreate ....");
    }

    @HandleBeforeSave
    public void handleBeforeSave(Customer customer) {
        System.out.println("Inside handleBeforeSave ....");
    }

    @HandleAfterSave
    public void handleAfterSave(Customer customer) {
        System.out.println("Inside handleAfterSave ....");
    }

}
