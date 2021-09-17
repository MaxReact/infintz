package uz.zako.infintz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zako.infintz.entity.Rectangle;
import uz.zako.infintz.exceptions.BadRequestException;
import uz.zako.infintz.exceptions.ResourceNotFoundException;
import uz.zako.infintz.payload.RectanglePayload;
import uz.zako.infintz.repository.RectangleRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RectangleService {

    private final RectangleRepository rectangleRepository;

    public Integer rectangleArea(Integer id) {
        Rectangle rectangle = rectangleRepository.findById(id).orElseThrow(() -> new BadRequestException("not found"));
        return rectangle.getHeight() * rectangle.getWidth();
    }

    public Integer rectanglePerimeter(Integer id) {
        Rectangle rectangle = rectangleRepository.findById(id).orElseThrow(() -> new BadRequestException("not found"));
        return 2 * (rectangle.getHeight() + rectangle.getWidth());
    }

    public Boolean save(RectanglePayload payload) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(payload.getHeight());
        rectangle.setWidth(payload.getWidth());
        return rectangleRepository.save(rectangle)!=null;
    }

    public Boolean edit(Integer id, RectanglePayload payload) {
        Rectangle rectangle = rectangleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("rectangle is not found"));
        rectangle.setHeight(payload.getHeight());
        rectangle.setWidth(payload.getWidth());
        return rectangleRepository.save(rectangle)!=null;
    }

    public Boolean deleteById(Integer id){
        try {
            rectangleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


}
