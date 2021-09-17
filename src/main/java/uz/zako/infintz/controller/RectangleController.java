package uz.zako.infintz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.infintz.entity.Rectangle;
import uz.zako.infintz.exceptions.ResourceNotFoundException;
import uz.zako.infintz.model.Result;
import uz.zako.infintz.payload.RectanglePayload;
import uz.zako.infintz.repository.RectangleRepository;
import uz.zako.infintz.service.RectangleService;

import java.util.List;

@RestController
@RequestMapping("/api/rectangle")
@RequiredArgsConstructor
public class RectangleController {
    private final RectangleRepository rectangleRepository;
    private final RectangleService rectangleService;

    @GetMapping("/all")
    public List<Rectangle> getRectangles() {
        return rectangleRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Rectangle getById(@PathVariable Integer id) {
        return rectangleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PostMapping("/add")
    public ResponseEntity addRectangles(@RequestBody RectanglePayload payload) {
        Boolean isSuccess = rectangleService.save(payload);
        if (!isSuccess) {
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true, "created"));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editRectangles(@PathVariable Integer id, @RequestBody RectanglePayload payload) {
        Boolean isSuccess = rectangleService.edit(id, payload);
        if (!isSuccess) {
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true, "edited"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Boolean isSuccess = rectangleService.deleteById(id);
        if (!isSuccess){
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true,"deleted"));
    }

    @GetMapping("/area/{id}")
    public Integer getRectangleAreaById(@PathVariable Integer id) {
        return rectangleService.rectangleArea(id);
    }

    @GetMapping("/perimeter/{id}")
    public Integer getRectanglePerimeterById(@PathVariable Integer id) {
        return rectangleService.rectanglePerimeter(id);
    }
}
