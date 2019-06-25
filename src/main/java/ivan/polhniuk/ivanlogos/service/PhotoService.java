package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.PhotoRequest;
import ivan.polhniuk.ivanlogos.dto.response.PhotoResponse;
import ivan.polhniuk.ivanlogos.entity.Photo;
import ivan.polhniuk.ivanlogos.repository.PhotoRepository;
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
        photo.setLink(request.getLink());
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
