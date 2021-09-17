package uz.zako.infintz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.infintz.entity.Circle;

@Repository
public interface CircleRepository extends JpaRepository<Circle,Integer> {
}
