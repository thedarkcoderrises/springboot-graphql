package com.tdcr.graphql.dao.pojo;

import java.util.List;

public class Friend {

    List<Long> friends;

    List<Person> idiots;

    public List<Long> getFriends() {
        return friends;
    }

    public void setFriends(List<Long> friends) {
        this.friends = friends;
    }

    public List<Person> getIdiots() {
        return idiots;
    }

    public void setIdiots(List<Person> idiots) {
        this.idiots = idiots;
    }
}
