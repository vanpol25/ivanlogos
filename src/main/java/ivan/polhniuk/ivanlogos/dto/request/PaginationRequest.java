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
    private Sort.Direction direction;
    private String field;

    public Pageable toPageable() {
        PageRequest pageRequest;
        if (direction != null && field != null) {
            pageRequest = PageRequest.of(page, size, direction, field);
        } else if (direction != null) {
            pageRequest = PageRequest.of(page, size, direction, "id");
        } else if (field != null) {
            pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, field);
        } else {
            pageRequest = PageRequest.of(page, size);
        }
        return pageRequest;
    }

}

