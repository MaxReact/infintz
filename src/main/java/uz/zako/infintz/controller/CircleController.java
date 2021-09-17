package uz.zako.infintz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.infintz.entity.Circle;
import uz.zako.infintz.entity.Square;
import uz.zako.infintz.exceptions.ResourceNotFoundException;
import uz.zako.infintz.model.Result;
import uz.zako.infintz.payload.CirclePayload;
import uz.zako.infintz.payload.SquarePayload;
import uz.zako.infintz.repository.CircleRepository;
import uz.zako.infintz.service.CircleService;

import java.util.List;

@RestController
@RequestMapping("/api/circle")
@RequiredArgsConstructor
public class CircleController {
    private final CircleRepository circleRepository;
    private final CircleService circleService;

    @GetMapping("/all")
    public List<Circle> getSquare() {
        return circleRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Circle getById(@PathVariable Integer id) {
        return circleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PostMapping("/add")
    public ResponseEntity addCircle(@RequestBody CirclePayload payload) {
        Boolean isSuccess = circleService.save(payload);
        if (!isSuccess) {
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true, "created"));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editCircle(@PathVariable Integer id, @RequestBody CirclePayload payload) {
        Boolean isSuccess = circleService.edit(id, payload);
        if (!isSuccess) {
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true, "edited"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Boolean isSuccess = circleService.deleteById(id);
        if (!isSuccess){
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true,"deleted"));
    }

    @GetMapping("/area/{id}")
    public Double getCircleAreaById(@PathVariable Integer id) {
        return circleService.circleArea(id);
    }

    @GetMapping("/perimeter/{id}")
    public Double getCirclePerimeterById(@PathVariable Integer id) {
        return circleService.circlePerimeter(id);
    }
}
