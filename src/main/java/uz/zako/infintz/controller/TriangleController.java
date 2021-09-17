package uz.zako.infintz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.infintz.entity.Square;
import uz.zako.infintz.entity.Triangle;
import uz.zako.infintz.exceptions.ResourceNotFoundException;
import uz.zako.infintz.model.Result;
import uz.zako.infintz.payload.SquarePayload;
import uz.zako.infintz.payload.TrianglePayload;
import uz.zako.infintz.repository.TriangleRepository;
import uz.zako.infintz.service.TriangleService;

import java.util.List;

@RestController
@RequestMapping("/api/triangle")
@RequiredArgsConstructor
public class TriangleController {
    private final TriangleRepository triangleRepository;
    private final TriangleService triangleService;

    @GetMapping("/all")
    public List<Triangle> getTriangle() {
        return triangleRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Triangle getById(@PathVariable Integer id) {
        return triangleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PostMapping("/add")
    public ResponseEntity addTriangle(@RequestBody TrianglePayload payload) {
        Boolean isSuccess = triangleService.save(payload);
        if (!isSuccess) {
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true, "created"));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editTriangle(@PathVariable Integer id, @RequestBody TrianglePayload payload) {
        Boolean isSuccess = triangleService.edit(id, payload);
        if (!isSuccess) {
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true, "edited"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Boolean isSuccess = triangleService.deleteById(id);
        if (!isSuccess){
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true,"deleted"));
    }

    @GetMapping("/area/{id}")
    public Double getTriangleAreaById(@PathVariable Integer id) {
        return triangleService.triangleArea(id);
    }

    @GetMapping("/perimeter/{id}")
    public Integer getTrianglePerimeterById(@PathVariable Integer id) {
        return triangleService.trianglePerimeter(id);
    }
}
