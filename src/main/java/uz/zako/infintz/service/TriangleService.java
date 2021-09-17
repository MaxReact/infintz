package uz.zako.infintz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zako.infintz.entity.Triangle;
import uz.zako.infintz.exceptions.BadRequestException;
import uz.zako.infintz.exceptions.ResourceNotFoundException;
import uz.zako.infintz.payload.TrianglePayload;
import uz.zako.infintz.repository.TriangleRepository;

@Service
@RequiredArgsConstructor
public class TriangleService {

    private final TriangleRepository triangleRepository;

    public Double triangleArea(Integer id) {
        Triangle triangle = triangleRepository.findById(id).orElseThrow(() -> new BadRequestException("not found"));
        int a = triangle.getA();
        int b = triangle.getB();
        int c = triangle.getC();
        int s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public Integer trianglePerimeter(Integer id) {
        Triangle triangle = triangleRepository.findById(id).orElseThrow(() -> new BadRequestException("not found"));
        return triangle.getA() + triangle.getB() + triangle.getC();
    }

    public Boolean save(TrianglePayload payload) {
        Triangle triangle = new Triangle();
        triangle.setA(payload.getA());
        triangle.setB(payload.getB());
        triangle.setC(payload.getC());
        return triangleRepository.save(triangle) != null;
    }

    public Boolean edit(Integer id, TrianglePayload payload) {
        Triangle triangle = triangleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("rectangle is not found"));
        triangle.setA(payload.getA());
        triangle.setB(payload.getB());
        triangle.setC(payload.getC());
        return triangleRepository.save(triangle) != null;
    }

    public Boolean deleteById(Integer id) {
        try {
            triangleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


}
