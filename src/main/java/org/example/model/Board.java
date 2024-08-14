package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨.

    @ColumnDefault("0")
    private int count; //조회 수

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="userId")
    private User user;

   @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
   private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
