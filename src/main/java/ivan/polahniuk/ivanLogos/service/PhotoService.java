package ivan.polahniuk.ivanLogos.service;

import ivan.polahniuk.ivanLogos.dto.request.PhotoRequest;
import ivan.polahniuk.ivanLogos.entity.Photo;
import ivan.polahniuk.ivanLogos.repository.PhotoRepository;
import ivan.polahniuk.ivanLogos.dto.response.PhotoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private ProductService productService;

    public void create(PhotoRequest request) {
        Photo photo = new Photo();
        if (request.getData()==null
        )
        photo.setData(request.getData());
        photo.setProduct(productService.findById(request.getProductId()));
        photoRepository.save(photo);
    }

    public Photo findById(Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Photo with id=" + id + " not exists"));
    }

    public List<PhotoResponse> findAll() {
        return photoRepository.findAll().stream().map(PhotoResponse::new).collect(Collectors.toList());
    }

}
