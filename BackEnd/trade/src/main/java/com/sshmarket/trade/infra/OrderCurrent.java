package com.sshmarket.trade.infra;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "current")
public class OrderCurrent {

    private String name;
    private String job;

    public OrderCurrent(String name, String job) {
        this.name = name;
        this.job = job;
    }

    @Override
    public String toString() {
        return "name is " + name + " job is " + job;
    }
}
