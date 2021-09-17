package uz.zako.infintz.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Triangle extends AbstracEntity {
    @NotNull
    private Integer a;

    @NotNull
    private Integer b;

    @NotNull
    private Integer c;
}
