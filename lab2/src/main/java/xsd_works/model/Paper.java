package xsd_works.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Paper {
    private int id;
    private String title;
    private String type;
    private boolean monthly;
    private Chars chars;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        return hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, monthly, chars);
    }
}
