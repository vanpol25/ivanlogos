package ivan.polhniuk.ivanlogos.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter

public class PaginationRequest {
    @NotNull
    @Positive
    private Integer size;
    @NotNull
    @Positive
    private Integer page;
    private Sort.Direction direction;
    private String field;

    public Pageable toPageable() {
        if (direction != null && field != null) {
            return PageRequest.of(page, size, direction, field);
        } else if (direction != null) {
            return PageRequest.of(page, size, direction, "id");
        } else if (field != null) {
            return PageRequest.of(page, size, Sort.Direction.ASC, field);
        } else {
            return PageRequest.of(page, size);
        }
    }
    //!!!Ask about!!!
    public Pageable toPageable2() {
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

