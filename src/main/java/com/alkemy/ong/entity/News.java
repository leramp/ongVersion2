package com.alkemy.ong.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "News")
@Table(name = "news")
@SQLDelete(sql = "UPDATE news SET deleted = true WHERE id=?" )
@Where(clause = "deleted = false")
public class News {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;



    @Column(
            name = "content",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String content;

    @Column(
            name = "image",
            nullable = false
    )
    private String image;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(
            name = "create_timestamp",
            updatable = false
    )
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(
            name = "update_timestamp"
    )
    private LocalDateTime updateDateTime;

    private boolean deleted = Boolean.FALSE;

    @OneToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "category_id_fk"
            )
    )
    private String categoryId;

}
