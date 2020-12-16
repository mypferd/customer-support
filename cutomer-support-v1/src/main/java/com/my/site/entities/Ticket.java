package com.my.site.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.my.site.validation.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@SqlResultSetMapping(
        name = "searchResultMapping.ticket",
        entities = { @EntityResult(entityClass = Ticket.class) },
        columns = { @ColumnResult(name = "_ft_scoreColumn", type = Double.class)}
)
@XmlRootElement(namespace = "http://example.com/xmlns/support", name = "ticket")
@XmlAccessorType(XmlAccessType.NONE)
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE,
         fieldVisibility = JsonAutoDetect.Visibility.NONE,
         getterVisibility = JsonAutoDetect.Visibility.NONE,
         isGetterVisibility = JsonAutoDetect.Visibility.NONE,
         setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "{validate.ticket.customer}")
    private UserPrincipal customer;

    @NotBlank(message = "{validate.ticket.subject}")
    private String subject;

    @NotBlank(message = "{validate.ticket.body}")
    private String body;

    private Instant dateCreated;

    @Valid
    private List<Attachment> attachments = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserPrincipal getCustomer() {
        return customer;
    }

    public void setCustomer(UserPrincipal customer) {
        this.customer = customer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
