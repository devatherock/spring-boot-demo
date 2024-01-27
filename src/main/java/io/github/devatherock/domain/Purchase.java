package io.github.devatherock.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "purchases")
@Getter
@Setter
public class Purchase {
  @Id
  private String id;

  @Column(name = "purchase_time")
  @JsonProperty("purchase_time")
  private ZonedDateTime purchaseTime;
}