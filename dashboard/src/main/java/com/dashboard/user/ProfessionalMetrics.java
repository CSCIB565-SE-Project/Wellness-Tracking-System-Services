package com.dashboard.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document
public class ProfessionalMetrics {

    @Id
    private ObjectId id;

    private Long professionalId;
    private Long contentId;
    private int numberOfViews;

}

