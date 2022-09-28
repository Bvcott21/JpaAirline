package com.edgarba.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

//@Entity
public class Document {
   // @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_gen")
    //@SequenceGenerator(name = "document_gen", sequenceName = "document_seq", allocationSize = 1)
    private long documentId;
    //@Enumerated(EnumType.ORDINAL)
    private DocumentType documentType;
    private String documentNumber;
    private LocalDate dateOfBirth;
    //@Enumerated(EnumType.ORDINAL)
    private Country nationality;

    public Document() {}

    public Document(DocumentType documentType, String documentNumber, LocalDate dateOfBirth, Country nationality) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    public long getDocumentId() {
        return documentId;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Country getNationality() {
        return nationality;
    }
    
}
