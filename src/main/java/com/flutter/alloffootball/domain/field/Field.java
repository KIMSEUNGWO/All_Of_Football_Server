package com.flutter.alloffootball.domain.field;

import com.flutter.alloffootball.domain.match.Match;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FIELD")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FIELD_ID")
    private Long id;

    private String title;
    private String description;

    @Embedded
    private Address address;

    @Embedded
    private FieldData fieldData;

    @Builder.Default
    @OneToMany(mappedBy = "field", orphanRemoval = true)
    private List<FieldImage> fieldImages = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "field", orphanRemoval = true)
    private List<Match> matchList = new ArrayList<>();
}
