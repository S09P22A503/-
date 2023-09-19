package com.sshmarket.review.adapter.out.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
class JPAReviewImageEntity {

    @Id
    @GeneratedValue
    private Long id;
}
