package ivan.polahniuk.ivanLogos.service;

import ivan.polahniuk.ivanLogos.dto.request.PhotoRequest;
import ivan.polahniuk.ivanLogos.dto.response.PhotoResponse;
import ivan.polahniuk.ivanLogos.entity.Photo;
import ivan.polahniuk.ivanLogos.entity.Product;
import ivan.polahniuk.ivanLogos.repository.PhotoRepository;
import ivan.polahniuk.ivanLogos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    private static final String EMPTY_IMG = "empty.png";

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    public void create(PhotoRequest request) throws IOException {
        Photo photo = new Photo();
        photo.setName(fileService.saveFile(request.getData()));
        photo.setProduct(productService.findById(request.getProductId()));
        photoRepository.save(photo);
        addMainImageToProduct(photo.getProduct().getId());
    }

    public void updateMainImg(Long id) {
        Photo photo = findById(id);
        Product product = productService.findById(photo.getProduct().getId());
        product.setMainImg(photo.getName());
        productRepository.save(product);
    }

    public void delete(Long id) throws IOException {
        Photo photo = findById(id);
        photoRepository.delete(photo);
        fileService.deleteFile(photo.getName());
        addMainImageToProduct(photo.getProduct().getId());
    }

    private void addMainImageToProduct(Long id) {
        Product product = productService.findById(id);
        if (product.getPhotos().size() == 0) {
            product.setMainImg(EMPTY_IMG);
        } else {
            product.setMainImg(product.getPhotos().get(0).getName());
        }
        productRepository.save(product);
    }

    public Photo findById(Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Photo with id=" + id + " not exists"));
    }

    public List<PhotoResponse> findAll() {
        return photoRepository.findAll().stream().map(PhotoResponse::new).collect(Collectors.toList());
    }

}
