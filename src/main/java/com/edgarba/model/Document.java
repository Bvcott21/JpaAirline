package com.edgarba.model;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Document {
    @Enumerated(EnumType.ORDINAL)
    private DocumentType documentType;
    private String documentNumber;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.ORDINAL)
    private Country nationality;

    public Document() {}

    public Document(DocumentType documentType, String documentNumber, LocalDate dateOfBirth, Country nationality) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
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
