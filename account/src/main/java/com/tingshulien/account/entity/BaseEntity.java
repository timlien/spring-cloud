package com.tingshulien.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter @Setter @ToString
public class BaseEntity {

  @CreatedDate
  @Column(name = "create_at",updatable = false)
  private LocalDateTime createAt;

  @CreatedBy
  @Column(name = "create_by",updatable = false)
  private String createBy;

  @LastModifiedDate
  @Column(name = "update_at", insertable = false)
  private LocalDateTime updateAt;

  @LastModifiedBy
  @Column(name = "update_by", insertable = false)
  private String updateBy;

}
