package uz.zako.infintz.payload;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RectanglePayload {
    @NotNull
    private Integer height;

    @NotNull
    private Integer width;
}
