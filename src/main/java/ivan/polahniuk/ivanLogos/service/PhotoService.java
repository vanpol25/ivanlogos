package ivan.polahniuk.ivanLogos.service;

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

    public void create(String name, Long id) {
        Photo photo = new Photo();
        photo.setName(name);
        photo.setId(id);
        photoRepository.save(photo);
    }

    public void delete(Long id) {
        photoRepository.delete(findById(id));
    }

    public Photo findById(Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Photo with id=" + id + " not exists"));
    }

    public List<PhotoResponse> findAll() {
        return photoRepository.findAll().stream().map(PhotoResponse::new).collect(Collectors.toList());
    }

}
