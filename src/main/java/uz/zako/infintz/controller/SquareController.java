package uz.zako.infintz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.infintz.entity.Square;
import uz.zako.infintz.exceptions.ResourceNotFoundException;
import uz.zako.infintz.model.Result;
import uz.zako.infintz.payload.SquarePayload;
import uz.zako.infintz.repository.SquareRepository;
import uz.zako.infintz.service.SquareService;

import java.util.List;

@RestController
@RequestMapping("/api/square")
@RequiredArgsConstructor
public class SquareController {
    private final SquareRepository squareRepository;
    private final SquareService squareService;

    @GetMapping("/all")
    public List<Square> getSquare() {
        return squareRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Square getById(@PathVariable Integer id) {
        return squareRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PostMapping("/add")
    public ResponseEntity addSquare(@RequestBody SquarePayload payload) {
        Boolean isSuccess = squareService.save(payload);
        if (!isSuccess) {
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true, "created"));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editSquare(@PathVariable Integer id, @RequestBody SquarePayload payload) {
        Boolean isSuccess = squareService.edit(id, payload);
        if (!isSuccess) {
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true, "edited"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Boolean isSuccess = squareService.deleteById(id);
        if (!isSuccess){
            return new ResponseEntity(new Result(false, "error :("), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new Result(true,"deleted"));
    }

    @GetMapping("/area/{id}")
    public Double getSquareAreaById(@PathVariable Integer id) {
        return squareService.squareArea(id);
    }

    @GetMapping("/perimeter/{id}")
    public Integer getSquarePerimeterById(@PathVariable Integer id) {
        return squareService.squarePerimeter(id);
    }
}
