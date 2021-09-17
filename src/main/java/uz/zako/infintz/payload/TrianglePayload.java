package uz.zako.infintz.payload;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrianglePayload {
    @NotNull
    private Integer a;

    @NotNull
    private Integer b;

    @NotNull
    private Integer c;
}
