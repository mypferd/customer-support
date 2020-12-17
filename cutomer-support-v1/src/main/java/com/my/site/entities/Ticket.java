package com.my.site.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.my.site.converters.InstantConverter;
import com.my.site.validation.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
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

    @Id
    @Column(name = "TicketId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement
    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "UserId")
    @XmlElement
    @JsonProperty
    public UserPrincipal getCustomer() {
        return customer;
    }

    public void setCustomer(UserPrincipal customer) {
        this.customer = customer;
    }

    @Basic
    @XmlElement
    @JsonProperty
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Lob
    @XmlElement
    @JsonProperty
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Convert(converter = InstantConverter.class)
    @XmlElement
    @XmlSchemaType(name = "dateTime")
    @JsonProperty
    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinTable(name = "Ticket_Attachment",
            joinColumns = { @JoinColumn(name = "TicketId") },
            inverseJoinColumns = { @JoinColumn(name = "AttachmentId") })
    @OrderColumn(name = "SortKey")
    @XmlElement(name = "attachment")
    @JsonProperty
    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    @Transient
    public int getNumberOfAttachments() {
        return this.attachments.size();
    }
}
