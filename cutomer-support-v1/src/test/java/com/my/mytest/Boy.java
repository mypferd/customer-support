package com.my.mytest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // 必须要标明这个元素
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Boy {
    String name = "CY";
    @XmlElement
    int age = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
