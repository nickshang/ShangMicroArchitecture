package com.shang.upms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Think on 2017/6/9.
 */
@Component
@ConfigurationProperties("amazon")
public class AmazonProperties {
    private String associateId;
    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }
    public String getAssociateId() {
        return associateId;
    }
}