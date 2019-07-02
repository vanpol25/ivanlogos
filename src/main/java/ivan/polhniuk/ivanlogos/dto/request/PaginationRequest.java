package ivan.polhniuk.ivanlogos.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;

@Getter
@Setter

public class PaginationRequest {
    @NotNull
    private Integer size;
    @NotNull
    private Integer page;
    @NotNull
    private Sort.Direction direction;
    @NotNull
    private String field;

    public Pageable toPageable() {
        return PageRequest.of(page, size, direction, field);
    }
}
