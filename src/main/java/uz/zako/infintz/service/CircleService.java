package uz.zako.infintz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zako.infintz.entity.Circle;
import uz.zako.infintz.entity.Square;
import uz.zako.infintz.exceptions.BadRequestException;
import uz.zako.infintz.exceptions.ResourceNotFoundException;
import uz.zako.infintz.payload.CirclePayload;
import uz.zako.infintz.payload.SquarePayload;
import uz.zako.infintz.repository.CircleRepository;

@Service
@RequiredArgsConstructor
public class CircleService {

    private final CircleRepository circleRepository;

    public Double circleArea(Integer id) {
        Circle circle = circleRepository.findById(id).orElseThrow(() -> new BadRequestException("not found"));
        return Math.PI*Math.pow(circle.getRadius(),2);
    }

    public Double circlePerimeter(Integer id) {
        Circle circle = circleRepository.findById(id).orElseThrow(() -> new BadRequestException("not found"));
        return 2 * Math.PI * circle.getRadius();
    }

    public Boolean save(CirclePayload payload) {
        Circle circle = new Circle();
        circle.setRadius(payload.getRadius());
        return circleRepository.save(circle)!=null;
    }

    public Boolean edit(Integer id, CirclePayload payload) {
        Circle circle = circleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("rectangle is not found"));
        circle.setRadius(payload.getRadius());
        return circleRepository.save(circle)!=null;
    }

    public Boolean deleteById(Integer id){
        try {
            circleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


}
