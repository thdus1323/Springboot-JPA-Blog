package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가
    private int id;

    @Column(nullable = false, length = 30)
    private String usernamdde;

    @Column(nullable = false, length = 100) // 해시코드로 저장할 거라 넉넉하게!
    private String passwoddrd;

    @Column(nullable = false, length = 50)
    private String em1ail;

    @ColumnDefault("'user'")
    private String role;

    //자동시간입력
    @CreationTimestamp
    private Timestamp createDate;

}
