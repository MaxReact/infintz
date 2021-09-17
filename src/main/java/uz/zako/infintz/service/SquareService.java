package uz.zako.infintz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zako.infintz.entity.Square;
import uz.zako.infintz.exceptions.BadRequestException;
import uz.zako.infintz.exceptions.ResourceNotFoundException;
import uz.zako.infintz.payload.SquarePayload;
import uz.zako.infintz.repository.SquareRepository;

@Service
@RequiredArgsConstructor
public class SquareService {

    private final SquareRepository squareRepository;

    public Double squareArea(Integer id) {
        Square square = squareRepository.findById(id).orElseThrow(() -> new BadRequestException("not found"));
        return Math.pow(square.getSide(),2);
    }

    public Integer squarePerimeter(Integer id) {
        Square square = squareRepository.findById(id).orElseThrow(() -> new BadRequestException("not found"));
        return 4 * square.getSide();
    }

    public Boolean save(SquarePayload payload) {
        Square square = new Square();
        square.setSide(payload.getSide());
        return squareRepository.save(square)!=null;
    }

    public Boolean edit(Integer id, SquarePayload payload) {
        Square square = squareRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("rectangle is not found"));
        square.setSide(payload.getSide());
        return squareRepository.save(square)!=null;
    }

    public Boolean deleteById(Integer id){
        try {
            squareRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


}
