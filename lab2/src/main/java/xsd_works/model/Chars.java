package xsd_works.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Chars {
    private boolean colored;
    private int pagesCount;
    private boolean glossy;
    private String subscriptionIndex;

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
        return Objects.hash(colored, pagesCount, glossy, subscriptionIndex);
    }
}
