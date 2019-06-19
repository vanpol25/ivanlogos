package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.PhotoRequest;
import ivan.polhniuk.ivanlogos.entity.Photo;
import ivan.polhniuk.ivanlogos.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
