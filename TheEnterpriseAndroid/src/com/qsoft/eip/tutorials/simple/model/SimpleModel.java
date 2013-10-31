package com.qsoft.eip.tutorials.simple.model;

import com.bindroid.trackable.TrackableField;

import java.io.Serializable;

/**
 * User: Le
 * Date: 10/22/13
 */
public class SimpleModel implements Serializable {

    private TrackableField<String> content = new TrackableField<String>("Empty");
    public String getContent() {
        return content.get();
    }
    public void setContent(String value) {
        content.set(value);
    }
}
